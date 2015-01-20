package org.bbaw.bts.core.services.impl.services;

import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.inject.Inject;

import org.bbaw.bts.btsmodel.BTSDBCollectionRoleDesc;
import org.bbaw.bts.btsmodel.BTSObject;
import org.bbaw.bts.btsmodel.BTSProject;
import org.bbaw.bts.btsmodel.BTSProjectDBCollection;
import org.bbaw.bts.btsmodel.BtsmodelFactory;
import org.bbaw.bts.commons.BTSConstants;
import org.bbaw.bts.core.commons.BTSCoreConstants;
import org.bbaw.bts.core.dao.BTSProjectDao;
import org.bbaw.bts.core.remote.dao.RemoteBTSProjectDao;
import org.bbaw.bts.core.services.BTSProjectService;
import org.bbaw.bts.core.services.impl.generic.GenericObjectServiceImpl;
import org.bbaw.bts.db.DBManager;
import org.bbaw.bts.searchModel.BTSQueryRequest;
import org.eclipse.core.runtime.IProgressMonitor;

public class BTSProjectServiceImpl extends GenericObjectServiceImpl<BTSProject, String> implements BTSProjectService
{
	@Inject
	private BTSProjectDao projectDao;

	@Inject
	private RemoteBTSProjectDao remoteprojectDao;

	@Inject
	private DBManager dbManager;
	
	@Override
	public BTSProject createNew()
	{
		BTSProject entity = BtsmodelFactory.eINSTANCE.createBTSProject();
		super.setId(entity);
		super.setRevision(entity);
		entity.setDBCollectionKey(BTSCoreConstants.ADMIN);
		return entity;
	}

	@Override
	public boolean save(BTSProject entity)
	{
		super.addRevisionStatement(entity);
		if (entity.getDbConnection() != null) {
			try {
				dbManager.prepareDBSynchronization(entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//FIXME teste ob db collections indiziert sind, sonst gibt Fehlermeldung
//		try {
//			dbManager.prepareDBIndexing(entity, null);
//		} catch (URISyntaxException e) {
//			e.printStackTrace();
//		}
		for (BTSProjectDBCollection coll : entity.getDbCollections())
		{
			try {
				saveAuthorisation(entity, coll);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		projectDao.add(entity, BTSCoreConstants.ADMIN);
		return true;
	}


	private void saveAuthorisation(BTSProject entity, BTSProjectDBCollection coll)
	{

		if (!coll.getRoleDescriptions().isEmpty())
		{
			Set<String> memberUsers = new HashSet<String>();
			Set<String> memberRoles = new HashSet<String>();
			BTSDBCollectionRoleDesc memberDesc = null;

			for (BTSDBCollectionRoleDesc desc : coll.getRoleDescriptions())
			{
				if (!"admins".equals(desc.getRoleName()))
				{
					for (String s : desc.getUserNames())
					{
						memberUsers.add(s);
					}
					for (String s : desc.getUserRoles())
					{
						memberRoles.add(s);
					}
					if ("members".equals(desc.getRoleName()))
					{
						memberDesc = desc;
					}
				}


			}
			if (memberDesc == null)
			{
				memberDesc = BtsmodelFactory.eINSTANCE.createBTSDBCollectionRoleDesc();
				memberDesc.setRoleName("members");
				coll.getRoleDescriptions().add(memberDesc);
			}
			memberDesc.getUserNames().addAll(memberUsers);
			memberDesc.getUserRoles().addAll(memberRoles);
			try {
				remoteprojectDao.addAuthorisation(coll, entity.getPrefix());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			projectDao.addAuthorisation(coll, entity.getPrefix());
		} else
		{
			try {
				remoteprojectDao.addAuthorisation(coll, entity.getPrefix());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			projectDao.addAuthorisation(coll, entity.getPrefix());
		}

	}

	@Override
	public boolean saveMultiple(Set<BTSProject> entities)
	{
		for (BTSProject p : entities)
		{
			save(p);
		}
		return true;
	}

	@Override
	public void update(BTSProject entity)
	{
		projectDao.update(entity, BTSCoreConstants.ADMIN);

	}

	@Override
	public void remove(BTSProject entity)
	{
		projectDao.remove(entity, BTSCoreConstants.ADMIN);

	}

	@Override
	public BTSProject find(String key, IProgressMonitor monitor)
	{
		return projectDao.find(key, BTSCoreConstants.ADMIN);
	}

	@Override
	public List<BTSProject> list(String objectState, IProgressMonitor monitor)
	{
		return projectDao.list(BTSCoreConstants.ADMIN, objectState);
	}

	@Override
	public List<BTSProject> query(BTSQueryRequest query, String objectState,
			boolean registerQuery, IProgressMonitor monitor)
	{
		List<BTSProject> objects = new Vector<BTSProject>();
		for (String p : getActiveProjects())
		{
			objects.addAll(projectDao.query(query, BTSCoreConstants.ADMIN,
					BTSCoreConstants.ADMIN, objectState, registerQuery));
		}
		return filter(objects);
	}

	@Override
	public List<BTSProject> query(BTSQueryRequest query, String objectState, IProgressMonitor monitor) {
		return query(query, objectState, true, monitor);
	}

	@Override
	public List<BTSProject> listRemoteProjects(String username, String password)
	{
		return remoteprojectDao.list(BTSCoreConstants.ADMIN, username, password);
	}

	@Override
	public List<BTSProject> list(String dbPath, String queryId,
			String objectState, IProgressMonitor monitor)
	{
		return filter(projectDao.findByQueryId(queryId, dbPath, objectState));
	}

	@Override
	public BTSProject findByProjectPrefix(String project) {
		if (main_project.equals(project))
		{
			return (BTSProject) context.get(BTSCoreConstants.MAIN_PROJECT);
		}
		List<BTSProject> projects = list(BTSConstants.OBJECT_STATE_ACTIVE, null);
		for (BTSProject pro : projects)
		{
			if (pro.getPrefix() != null && pro.getPrefix().equals(project))
			{
				return pro;
			}
		}
		return null;
	}

	@Override
	public BTSProjectDBCollection checkAndAddDBCollection(BTSProject project, String collName,
			boolean index, boolean synchronize) {
		BTSProjectDBCollection collection = null;
		for (BTSProjectDBCollection coll : project.getDbCollections())
		{
			if (project.getPrefix() != null)
			{
				if (collName.equals(coll.getCollectionName()))
				{
					collection = coll;
					break;
				}
			}
		}
		if (collection == null)
		{
			collection = BtsmodelFactory.eINSTANCE.createBTSProjectDBCollection();
			collection.setCollectionName(collName);
			project.getDbCollections().add(collection);
		}
		collection.setIndexed(index);
		collection.setSynchronized(synchronize);
		return collection;
	}

	@Override
	public boolean removeUserUserGroupFromAuthorization(BTSObject object,
			List<BTSProject> projects) {
		if (object == null || object.get_id() == null) return false;
		for (BTSProject project : projects)
		{
			removeUserUserGroupFromAuthorizationProject(object, project);
		}
		return true;
	}

	private void removeUserUserGroupFromAuthorizationProject(BTSObject object,
			BTSProject project) {
		for (BTSProjectDBCollection coll : project.getDbCollections())
		{
			removeUserUserGroupFromAuthorizationDBCollection(object, coll);
		}
		
	}

	private void removeUserUserGroupFromAuthorizationDBCollection(
			BTSObject object, BTSProjectDBCollection coll) {
		for (BTSDBCollectionRoleDesc role : coll.getRoleDescriptions())
		{
			role.getUserNames().remove(object.get_id());
		}
		
	}


}
