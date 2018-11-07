package com.crm.crm.base.mdm.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.crm.crm.base.mdm.entity.*;
import com.crm.crm.base.mdm.service.FunctionService;
import com.crm.crm.base.mdm.service.RoleService;
import com.crm.crm.base.mdm.vo.TSFunctionVo;
import com.crm.crm.common.vo.ComboTree;
import com.crm.crm.common.vo.MiniDaoPage;
import com.crm.crm.common.vo.ValidForm;
import org.I0Itec.zkclient.ExceptionUtil;
import org.crmframework.core.minidao.service.MiniDaoFastQueryService;
import org.crmframework.core.service.SystemService;
import org.crmframework.core.tag.easyui.TagUtil;
import org.crmframework.core.util.*;
import org.crmframework.core.vo.AjaxJson;
import org.crmframework.core.vo.DataGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

@Scope("prototype")
@Controller
@RequestMapping("/roleController")
public class RoleController{
    @Reference
    @Autowired(required = false)
    RoleService roleService;
    @Resource
    MiniDaoFastQueryService miniDaoFastQueryService;
    @Reference
    @Autowired(required = false)
    FunctionService functionService;
    @Resource
    SystemService systemService;

    /**
     * 角色列表
     * @return
     */
    @RequestMapping(params = "role")
    public ModelAndView role() {
        return new ModelAndView("role/roleList");
    }

    /**
     * 角色列表
     * @param role
     * @param request
     * @param response
     * @param dataGrid
     */
    @RequestMapping(params = "roleGrid")
    public void roleGrid(TSRole role, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        MiniDaoPage<TSRole> miniDaoPage=roleService.findRoleList(role,dataGrid.getPage(),dataGrid.getRows());
        dataGrid.setResults(miniDaoPage.getResults());
        dataGrid.setTotal(miniDaoPage.getTotal());
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "addorupdate")
    public ModelAndView addorupdate(TSRole role, HttpServletRequest req) {
        req.setAttribute("opid", req.getParameter("opid"));
        if (role.getId() != null) {
            role = systemService.getEntity(TSRole.class, role.getId());
            req.setAttribute("role", role);
        }
        return new ModelAndView("role/role");
    }

    /**
     * 检查角色名称
     *
     * @param role
     * @return
     */
    @RequestMapping(params = "checkRoleName")
    @ResponseBody
    public ValidForm checkRoleName(TSRole role, HttpServletRequest request, HttpServletResponse response) {
        ValidForm v = new ValidForm();
        String roleName = oConvertUtils.getString(request.getParameter("param"));
        String code = oConvertUtils.getString(request.getParameter("code"));
        List<TSRole> roles = systemService.findByProperty(TSRole.class, "roleName", roleName);
        if (roles.size() > 0 && !code.equals(roleName)) {
            v.setInfo("角色名称已存在");
            v.setStatus("n");
        }
        return v;
    }

    /**
     * 检查角色
     *
     * @param role
     * @return
     */
    @RequestMapping(params = "checkRole")
    @ResponseBody
    public ValidForm checkRole(TSRole role, HttpServletRequest request, HttpServletResponse response) {
        ValidForm v = new ValidForm();
        String roleCode = oConvertUtils.getString(request.getParameter("param"));
        String code = oConvertUtils.getString(request.getParameter("code"));
        List<TSRole> roles = systemService.findByProperty(TSRole.class, "roleCode", roleCode);
        if (roles.size() > 0 && !code.equals(roleCode)) {
            v.setInfo("角色编码已存在");
            v.setStatus("n");
        }
        return v;
    }

    /**
     * 保存角色
     * @param role
     * @return
     */
    @RequestMapping(params = "saveRole")
    @ResponseBody
    public AjaxJson saveRole(TSRole role, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            if(StringUtil.isNotEmpty(role.getId())){
                TSRole oldRole=systemService.getEntity(TSRole.class,role.getId());
                BeanUtil.apply(role,oldRole);
                role=oldRole;
            }else{
                role.setId(null);
            }
            systemService.saveOrUpdate(role);
        }catch(Exception e){
            ExceptionHandlerUtil.handlerException(ajaxJson, e);
        }
        return ajaxJson;
    }

    /**
     * 角色对应的用户列表
     * @param request
     * @return
     */
    @RequestMapping(params = "roleUser")
    public ModelAndView roleUser(HttpServletRequest request) {
        request.setAttribute("roleId", request.getParameter("roleId"));
        return new ModelAndView("role/roleUserList");
    }

    /**
     * 角色对应的用户列表
     * @param user
     * @param dataGrid
     * @param request
     * @param response
     */
    @RequestMapping(params = "roleUserGrid")
    public void roleUserDatagrid(TSUser user,DataGrid dataGrid,HttpServletRequest request,HttpServletResponse response) {
        String roleId = request.getParameter("roleId");
        String sql="select u.*,bu.* from t_s_role_user ru join t_s_user u on u.id=ru.userid join t_s_base_user bu on bu.id=u.id where ru.roleid=:roleId ";
        Map<String, Object> paramMap=new HashMap<String, Object>();
        paramMap.put("roleId",roleId);
        MiniDaoPage<TSUser> miniDaoPage=miniDaoFastQueryService.queryReturnMinidaoMiniDaoPage(sql,paramMap,dataGrid.getPage(),dataGrid.getRows(),TSUser.class);
        dataGrid.setResults(miniDaoPage.getResults());
        dataGrid.setTotal(miniDaoPage.getTotal());
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "roleFunction")
    public ModelAndView fun(HttpServletRequest request) {
        request.setAttribute("roleId", request.getParameter("roleId"));
        return new ModelAndView("role/roleFunctionList");
    }

    @RequestMapping(params = "setAuthority")
    @ResponseBody
    public List<ComboTree> setAuthority(ComboTree comboTree,String roleId,HttpServletRequest request){
        List<ComboTree> comboTrees = new ArrayList<ComboTree>();
        List<TSFunctionVo> topFunctionList=functionService.findFunctionTreeByParentId(null==comboTree.getId()?"0":comboTree.getId());
        List<TSFunctionVo> roleFunctionList=functionService.findFunctionByRoleId(roleId);
        List<String> roleFunctionIdList=new ArrayList<String>();
        for(TSFunctionVo vo:roleFunctionList){
            roleFunctionIdList.add(vo.getId());
        }
        comboTrees=functionService.makeComboTree(topFunctionList,roleFunctionIdList);
        MutiLangUtil.setMutiTree(comboTrees);
        return comboTrees;
    }

    @RequestMapping(params = "updateAuthority")
    @ResponseBody
    public AjaxJson updateAuthority(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            String roleId = request.getParameter("roleId");
            String rolefunction = request.getParameter("rolefunctions");
            String[] roleFunctions = rolefunction.split(",");
            List<String> newFunctionList=new ArrayList<String>();
            for (String s : roleFunctions) {
                newFunctionList.add(s);
            }
            roleService.updateRoleFunction(roleId,newFunctionList);
        } catch (Exception e) {
            ExceptionHandlerUtil.handlerException(ajaxJson, e);
        }
        return ajaxJson;
    }

    @RequestMapping(params = "operationListForFunction")
    public ModelAndView operationListForFunction(String functionId,String roleId,HttpServletRequest request) {
        List<TSOperation> functionOperationList = systemService.findHql("from TSOperation where TSFunction.id=?",functionId);
        String sql=" " +
                " select o.id from t_s_role_function rf " +
                " join t_s_operation o on rf.operation like '%'||o.id||'%' " +
                " where 1=1 " +
                " and rf.functionid=:functionId " +
                " and rf.roleid=:roleId " +
                " "
                ;
        Map<String, Object> paramMap=new HashMap<String, Object>();
        paramMap.put("functionId",functionId);
        paramMap.put("roleId",roleId);
        List<String> roleOperationList=miniDaoFastQueryService.queryReturnMinidaoList(sql,paramMap,String.class);
        request.setAttribute("operationList", functionOperationList);
        request.setAttribute("operationcodes", roleOperationList);
        request.setAttribute("functionId", functionId);
        return new ModelAndView("role/operationListForFunction");
    }

    @RequestMapping(params = "updateOperation")
    @ResponseBody
    public AjaxJson updateOperation(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            String roleId = request.getParameter("roleId");
            String functionId = request.getParameter("functionId");
            String operationcodes = request.getParameter("operationcodes");
//        try {
//            operationcodes = URLDecoder.decode(operationcodes, "utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
            systemService.jdbcUpdate("update t_s_role_function set operation=? where roleId=? and functionId=?",operationcodes,roleId,functionId);
        } catch (Exception e) {
            ExceptionHandlerUtil.handlerException(ajaxJson, e);
        }
        return ajaxJson;
    }
}
