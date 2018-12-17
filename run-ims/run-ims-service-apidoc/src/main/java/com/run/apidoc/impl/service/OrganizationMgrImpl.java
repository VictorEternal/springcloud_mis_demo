package com.run.apidoc.impl.service;

import java.util.List;

import com.run.apidoc.api.dao.OrganizationDao;
import com.run.apidoc.api.service.OrganizationMgr;
import com.run.apidoc.api.service.ProjectMgr;
import com.run.apidoc.entity.Corporation;
import com.run.apidoc.entity.Group;
import com.run.apidoc.entity.Module;
import com.run.apidoc.entity.Page;
import com.run.apidoc.entity.ProductionLine;
import com.run.apidoc.entity.Project;
import com.run.apidoc.entity.RapError;

public class OrganizationMgrImpl implements OrganizationMgr {
	private OrganizationDao	organizationDao;
	private ProjectMgr		projectMgr;



	public ProjectMgr getProjectMgr() {
		return projectMgr;
	}



	public void setProjectMgr(ProjectMgr projectMgr) {
		this.projectMgr = projectMgr;
	}



	public OrganizationDao getOrganizationDao() {
		return organizationDao;
	}



	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}



	public List<Corporation> getCorporationList() {
		return organizationDao.getCorporationList();
	}



	public List<Corporation> getCorporationListWithPager(int pageNum, int pageSize, String keyword) {

		List<Corporation> list = organizationDao.getCorporationListWithPager(pageNum, pageSize, keyword);

		for (Corporation c : list) {
			int memberNum = organizationDao.getMemberNumOfCorporation(c.getId());
			c.setMemberNum(memberNum + 1); // +1 project creator
			c.setHasAccess(true);
			c.setCreatorName("");
		}
		return list;
	}



	public int getCorporationListWithPagerNum(String keyword) {
		return organizationDao.getCorporationListWithPagerNum(keyword);
	}



	public List<Corporation> getCorporationListWithPager(int userId, int pageNum, int pageSize, String keyword) {
		List<Corporation> list = organizationDao.getCorporationListWithPager(userId, pageNum, pageSize, keyword);
		for (Corporation c : list) {
			int memberNum = organizationDao.getMemberNumOfCorporation(c.getId());
			c.setMemberNum(memberNum + 1); // +1 project creator
			c.setHasAccess(true);
			c.setCreatorName("");
		}
		return list;
	}



	public int getCorporationListWithPagerNum(int userId, String keyword) {
		return organizationDao.getCorporationListWithPagerNum(userId, keyword);
	}



	public List<Group> getGroupList(int productionLineId) {
		return organizationDao.getGroupList(productionLineId);
	}



	public List<ProductionLine> getProductionLineList(int corpId) {
		return organizationDao.getProductionLineList(corpId);
	}



	public int addGroup(Group group) {
		return organizationDao.addGroup(group);
	}



	public int addProductionLine(ProductionLine productionLine) {
		return organizationDao.addProductionLine(productionLine);
	}



	public RapError removeGroup(int groupId) {
		if (!groupCanBeDeleted(groupId)) {
			return new RapError(RapError.ERR_HAS_CHILDREN, "为确保您的数据安全，请先删除分组下的项目，再删除该分组。");
		} else {
			organizationDao.removeGroup(groupId);
			return new RapError();
		}
	}



	private boolean groupCanBeDeleted(int groupId) {
		List<Project> list = projectMgr.getProjectListByGroup(groupId);

		return list == null || list.size() == 0;
	}



	public RapError removeProductionLine(int productionLineId) {
		if (!productionLineCanBeDeleted(productionLineId)) {
			return new RapError(RapError.ERR_HAS_CHILDREN, "为确保您的数据安全，请先删除业务线下的分组及项目，再删除该业务线。");
		} else {
			organizationDao.removeProductionLine(productionLineId);
			return new RapError();
		}
	}



	private boolean productionLineCanBeDeleted(int productionLineId) {
		List<Group> list = this.getGroupList(productionLineId);
		return list == null || list.size() == 0;
	}



	public void updateGroup(Group group) {
		organizationDao.updateGroup(group);
	}



	public void updateProductionLine(ProductionLine productionLine) {
		organizationDao.updateProductionLine(productionLine);
	}



	public ProductionLine getProductionLine(int plid) {
		return organizationDao.getProductionLine(plid);
	}



	public Corporation getCorporation(int id) {
		return organizationDao.getCorporation(id);
	}



	public boolean canUserAccessCorp(int userId, int corpId) {
		Corporation c = getCorporation(corpId);
		if (c == null)
			return false;
		if (c.getUserId() == userId)
			return true; // team owner
		if (c.getAccessType() == Corporation.PUBLIC_ACCESS)
			return true; // public team
		return organizationDao.isUserInCorp(userId, corpId);
	}



	public int getUserRoleInCorp(int userId, int corpId) {
		return organizationDao.getUserRoleInCorp(userId, corpId);
	}



	public boolean canUserAccessPage(int userId, int pageId) {
		Page page = projectMgr.getPage(pageId);
		if (page != null) {
			Module module = page.getModule();
			if (module != null) {
				Project project = module.getProject();
				if (project != null) {
					return true;
				}
			}
		}
		return false;
	}



	public boolean canUserAccessProductionLine(int userId, int plId) {
		ProductionLine pl = this.getProductionLine(plId);
		int corpId = pl.getCorporationId();
		Corporation team = getCorporation(corpId);
		if (team.getAccessType() == Corporation.PUBLIC_ACCESS) {
			return true;
		}
		return canUserAccessCorp(userId, corpId);
	}



	public boolean canUserManageProductionLine(int userId, int plId) {
		ProductionLine pl = this.getProductionLine(plId);
		int corpId = pl.getCorporationId();
		return canUserManageProductionLineList(userId, corpId);
	}



	public boolean canUserAccessGroup(int userId, int groupId) {
		Group g = organizationDao.getGroup(groupId);
		ProductionLine pl = getProductionLine(g.getProductionLineId());
		int corpId = pl.getCorporationId();
		Corporation team = getCorporation(corpId);
		if (team.getAccessType() == Corporation.PUBLIC_ACCESS) {
			return true;
		}
		return canUserAccessCorp(userId, corpId);
	}



	public boolean canUserManageGroup(int userId, int groupId) {
		Group g = organizationDao.getGroup(groupId);
		ProductionLine pl = getProductionLine(g.getProductionLineId());
		int corpId = pl.getCorporationId();
		return corpId != 0;
	}



	public boolean removeMemberFromCorp(int curUserId, int userId, int corpId) {
		organizationDao.deleteMembershipFromCorp(curUserId, userId, corpId);
		return true;
	}



	public int getTeamIdByProjectId(int id) {
		return organizationDao.getTeamIdByProjectId(id);
	}



	public Group getGroup(int id) {
		return organizationDao.getGroup(id);
	}



	public int getMemberNumOfCorporation(int corpId) {
		return organizationDao.getMemberNumOfCorporation(corpId);
	}



	public int getProjectNumOfCorporation(int corpId) {
		return organizationDao.getProjectNumOfCorporation(corpId);
	}



	public int getActionNumOfCorporation(int corpId) {
		return organizationDao.getActionNumOfCorporation(corpId);
	}



	public boolean canUserManageProductionLineList(int userId, int corpId) {
		Corporation c = getCorporation(corpId);
		return c.getAccessType() == Corporation.PUBLIC_ACCESS;
	}

}
