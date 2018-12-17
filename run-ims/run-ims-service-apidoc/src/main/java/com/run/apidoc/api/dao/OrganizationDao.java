package com.run.apidoc.api.dao;


import java.util.List;

import com.run.apidoc.entity.Corporation;
import com.run.apidoc.entity.Group;
import com.run.apidoc.entity.ProductionLine;

public interface OrganizationDao {
    /**
     * get corporation list
     *
     * @return
     */
    List<Corporation> getCorporationList();

    /**
     * get group list
     *
     * @return
     */
    List<Group> getGroupList(int productionLineId);

    /**
     * get production line list
     *
     * @return
     */
    List<ProductionLine> getProductionLineList(int corpId);

    /**
     * add group
     *
     * @param group
     */
    int addGroup(Group group);

    /**
     * add production line
     *
     * @param productionLine
     */
    int addProductionLine(ProductionLine productionLine);

    /**
     * remove group
     *
     * @param groupId
     */
    void removeGroup(int groupId);

    /**
     * remove production line
     *
     * @param productionLineId
     */
    void removeProductionLine(int productionLineId);

    /**
     * update group
     *
     * @param group
     */
    void updateGroup(Group group);

    /**
     * update production line
     *
     * @param productionLine
     */
    void updateProductionLine(ProductionLine productionLine);

    /**
     * get group
     *
     * @param id
     * @return
     */
    Group getGroup(int id);

    /**
     * get production line
     *
     * @param id
     * @return
     */
    ProductionLine getProductionLine(int id);

    /**
     * get corporation
     *
     * @param id
     * @return
     */
    Corporation getCorporation(int id);

    /**
     * update ProductionLine.projectNum
     *
     * @param productionLineId
     */
    void updateCountersInProductionLine(int productionLineId);



    /**
     * add a user to corporation
     *
     * @param corpId
     * @param userId
     * @param roleId
     */
    void addUserToCorp(int corpId, int userId, int roleId);

    /**
     * check if user in corporation
     *
     * @param userId
     * @param corpId
     * @return
     */
    boolean isUserInCorp(int userId, int corpId);

    /**
     * get user role in corporation
     *
     * @param userId
     * @param corpId
     * @return roleId
     */
    int getUserRoleInCorp(int userId, int corpId);

    /**
     * set user role in corporation
     *
     * @param userId
     * @param corpId
     * @param roleId
     */
    void setUserRoleInCorp(int userId, int corpId, int roleId);

    /**
     *
     *
     * @param pageNum
     * @param pageSize
     * @param keyword
     * @return
     */
    List<Corporation> getCorporationListWithPager(int pageNum, int pageSize, String keyword);

    /**
     *
     *
     * @param keyword
     * @return
     */
    int getCorporationListWithPagerNum(String keyword);

    /**
     *
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @param keyword
     * @return
     */
    List<Corporation> getCorporationListWithPager(int userId, int pageNum, int pageSize, String keyword);

    /**
     *
     *
     * @param userId
     * @param keyword
     * @return
     */
    int getCorporationListWithPagerNum(int userId, String keyword);

    /**
     * add new corporation
     *
     * @param corporation
     */
    int addCorporation(Corporation corporation);

    /**
     * get number of member in specific corporation
     *
     * @param corpId
     * @return
     */
    int getMemberNumOfCorporation(int corpId);

    /**
     * get project num of corporation
     *
     * @param corpId
     * @return
     */
    int getProjectNumOfCorporation(int corpId);

    /**
     * get action num of corporation
     *
     * @param corpId
     * @return
     */
    int getActionNumOfCorporation(int corpId);

    /**
     * delete all membership from team
     *
     * @param curUserId the user now operating, used for transfer project
     *                  when the project operated are owned by the user removed.
     * @param userId
     * @param corpId
     */
    void deleteMembershipFromCorp(int curUserId, int userId, int corpId);

    /**
     * update corporation
     *
     * @param c
     */
    void updateCorporation(Corporation c);

    /**
     * get team id by project id
     *
     * @param id
     * @return
     */
    int getTeamIdByProjectId(int id);
}
