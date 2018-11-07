package com.crm.crm.base.mdm.service;

import com.crm.crm.base.mdm.vo.TSFunctionVo;
import com.crm.crm.common.vo.ComboTree;
import java.util.List;
import java.util.Map;

public interface FunctionService {

    /**
     * 根据用户ID查找菜单
     * @param userId
     * @return
     */
    List<TSFunctionVo> findFunctionByUserId(String userId);

    List<TSFunctionVo> findFunctionByRoleId(String roleId);

    /**
     * 查询当前菜单以及当前菜单的下级菜单
     * @param parentId
     * @return
     */
    List<TSFunctionVo> findFunctionByParentId(String parentId);

    /**
     * 菜单分层，并设置菜单的子菜单
     * @param functionList
     * @return
     */
    Map<String,Map<String,TSFunctionVo>> setChildren(List<TSFunctionVo> functionList);

    /**
     * 按照order字段排序
     * @param functionList
     * @return
     */
    List<TSFunctionVo> sortByOrder(List<TSFunctionVo> functionList);

    /**
     * 根据父类id得到树形结构的菜单
     * @param parentId
     * @return
     */
    List<TSFunctionVo> findFunctionTreeByParentId(String parentId);

    /**
     * 根据菜单树得到ComboTree
     * @param topFunctionTreeList
     * @param openIdList
     * @return
     */
    List<ComboTree> makeComboTree(List<TSFunctionVo> topFunctionTreeList, List<String> openIdList);
}
