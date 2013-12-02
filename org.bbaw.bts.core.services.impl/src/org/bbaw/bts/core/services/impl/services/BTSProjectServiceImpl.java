package org.bbaw.bts.core.services.impl.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.inject.Inject;

import org.bbaw.bts.btsmodel.BTSDBCollectionRoleDesc;
import org.bbaw.bts.btsmodel.BTSProject;
import org.bbaw.bts.btsmodel.BTSProjectDBCollection;
import org.bbaw.bts.btsmodel.BtsmodelFactory;
import org.bbaw.bts.core.dao.BTSProjectDao;
import org.bbaw.bts.core.remote.dao.RemoteBTSProjectDao;
import org.bbaw.bts.core.services.BTSProjectService;
import org.bbaw.bts.core.services.impl.internal.ServiceConstants;
import org.bbaw.bts.searchModel.BTSQueryRequest;

public class BTSProjectServiceImpl extends GenericObjectServiceImpl<BTSProject, String> implements BTSProjectService
{
	@Inject
	private BTSProjectDao projectDao;

	@Inject
	private RemoteBTSProjectDao remoteprojectDao;

	@Override
	public BTSProject createNew()
	{
		BTSProject entity = BtsmodelFactory.eINSTANCE.createBTSProject();
		super.setId(entity);
		super.setRevision(entity);
		return entity;
	}

	@Override
	public boolean save(BTSProject entity)
	{
		for (BTSProjectDBCollection coll : entity.getDbCollections())
		{
			saveAuthorisation(entity, coll);
		}
		projectDao.add(entity, ServiceConstants.ADMIN);
		return false;
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
			remoteprojectDao.addAuthorisation(coll, entity.getPrefix());
			projectDao.addAuthorisation(coll, entity.getPrefix());
		} else
		{
			remoteprojectDao.addAuthorisation(coll, entity.getPrefix());
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
		projectDao.update(entity, ServiceConstants.ADMIN);

	}

	@Override
	public void remove(BTSProject entity)
	{
		projectDao.remove(entity, ServiceConstants.ADMIN);

	}

	@Override
	public BTSProject find(String key)
	{
		return projectDao.find(key, ServiceConstants.ADMIN);
	}

	@Override
	public List<BTSProject> list()
	{
		return filter(projectDao.list(ServiceConstants.ADMIN));
	}

	@Override
	public List<BTSProject> query(BTSQueryRequest query)
	{
		List<BTSProject> objects = new Vector<BTSProject>();
		for (String p : active_projects.split(ServiceConstants.SPLIT_PATTERN))
		{
			objects.addAll(projectDao.query(query, ServiceConstants.ADMIN, ServiceConstants.ADMIN));
		}
		return filter(objects);
	}

	@Override
	public List<BTSProject> listRemoteProjects()
	{
		return remoteprojectDao.list(ServiceConstants.ADMIN);
	}

	@Override
	public List<BTSProject> list(String dbPath, String queryId)
	{
		return filter(projectDao.findByQueryId(queryId, dbPath));
	}

}
