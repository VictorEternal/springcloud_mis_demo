package com.run.apidoc.impl.dao;

import java.util.List;

import com.run.apidoc.api.dao.OrganizationDao;
import com.run.apidoc.api.dao.ProjectDao;
import com.run.apidoc.entity.Corporation;
import com.run.apidoc.entity.Group;
import com.run.apidoc.entity.ProductionLine;

public class OrganizationDaoImpl implements OrganizationDao {

	private ProjectDao projectDao;



	public ProjectDao getProjectDao() {
		return projectDao;
	}



	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}



	public List<Corporation> getCorporationList() {
		return null;
	}



	public List<Group> getGroupList(int productionLineId) {
		return null;
	}



	public List<ProductionLine> getProductionLineList(int corpId) {
		return null;
	}



	public int addGroup(Group group) {
		return 0;
	}



	public int addProductionLine(ProductionLine productionLine) {
		return 0;
	}



	public void removeGroup(int groupId) {
	}



	public void removeProductionLine(int productionLineId) {
	}



	public void updateGroup(Group group) {
	}



	public void updateProductionLine(ProductionLine line) {
	}



	public Group getGroup(int id) {
		return null;
	}



	public ProductionLine getProductionLine(int id) {
		return null;
	}



	public void updateCountersInProductionLine(int productionLineId) {

	}



	public void addUserToCorp(int corpId, int userId, int roleId) {
	}



	public boolean isUserInCorp(int userId, int corpId) {
		return false;
	}



	public int getUserRoleInCorp(int userId, int corpId) {
		return 0;
	}



	public void setUserRoleInCorp(int userId, int corpId, int roleId) {
	}



	public List<Corporation> getCorporationListWithPager(int pageNum, int pageSize, String keyword) {
		return null;
	}



	public int getCorporationListWithPagerNum(String keyword) {
		return 0;
	}



	public List<Corporation> getCorporationListWithPager(int userId, int pageNum, int pageSize, String keyword) {
		return null;
	}



	public int getCorporationListWithPagerNum(int userId, String keyword) {
		return 0;
	}



	public int addCorporation(Corporation c) {
		return 0;
	}



	public int getMemberNumOfCorporation(int corpId) {
		return 0;
	}



	public int getProjectNumOfCorporation(int corpId) {
		return 0;
	}



	public int getActionNumOfCorporation(int corpId) {
		return 0;
	}



	public void deleteMembershipFromCorp(int curUserId, int userId, int corpId) {

	}



	public void updateCorporation(Corporation c) {
	}



	public int getTeamIdByProjectId(int id) {
		return 0;
	}



	public Corporation getCorporation(int id) {
		return null;
	}
}
