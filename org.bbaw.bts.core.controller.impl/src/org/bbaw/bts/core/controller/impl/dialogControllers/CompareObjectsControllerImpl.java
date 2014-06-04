package org.bbaw.bts.core.controller.impl.dialogControllers;

import java.util.List;
import java.util.Vector;

import javax.inject.Inject;

import org.bbaw.bts.btsmodel.BTSCorpusObject;
import org.bbaw.bts.btsmodel.BTSDBBaseObject;
import org.bbaw.bts.core.controller.dialogControllers.CompareObjectsController;
import org.bbaw.bts.core.services.CorpusObjectService;
import org.bbaw.bts.tempmodel.DBRevision;

public class CompareObjectsControllerImpl implements CompareObjectsController {

	
	@Inject
	private CorpusObjectService corpusObjectService;

	@Override
	public List<BTSDBBaseObject> listConflictingVersions(BTSDBBaseObject object) {
		List<String> conflicts = object.getConflictingRevs();
		List<BTSDBBaseObject> conflictObjects = new Vector<BTSDBBaseObject>();
		for (String rev : conflicts)
		{
			BTSDBBaseObject o = corpusObjectService.find(object.get_id(), object.getDBCollectionKey(), rev);
			conflictObjects.add(o);
		}
		return conflictObjects;
	}

	@Override
	public boolean removeRevision(BTSDBBaseObject object, String revision) {
		return corpusObjectService.removeRevision((BTSCorpusObject) object, revision);
	}

	@Override
	public void reloadConflicts(BTSDBBaseObject object) {
		corpusObjectService.reloadConflicts((BTSCorpusObject) object);
		
	}

	@Override
	public List<BTSDBBaseObject> listAvailableVersions(BTSDBBaseObject object,
			boolean fetchFromRemote) {
		List<DBRevision> revisions = corpusObjectService.listAvailableRevisions(object, fetchFromRemote);
		List<BTSDBBaseObject> availableRevisions = new Vector<BTSDBBaseObject>();
		for (DBRevision rev : revisions)
		{
			BTSDBBaseObject o = null;
			switch (rev.getLocation())
			{
				case DBRevision.LOCAL:
				{
					try {
						o = corpusObjectService.find(object.get_id(), object.getDBCollectionKey(), rev.getRevision());
						if (o != null)
						{
							availableRevisions.add(o);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				}
				case DBRevision.REMOTE:
				{
					try {
						o = corpusObjectService.find(object.get_id(), object.getDBCollectionKey(), rev.getRevision(), true);
						if (o != null)
						{
							availableRevisions.add(o);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				}
			}
			
		}
		return availableRevisions;
	}

}
