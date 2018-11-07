package com.crm.crm.base.mdm.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.crm.crm.base.mdm.entity.TSFunction;
import com.crm.crm.base.mdm.entity.TSIcon;
import com.crm.crm.base.mdm.entity.TSOperation;
import com.crm.crm.base.mdm.service.FunctionService;
import com.crm.crm.base.mdm.vo.TSFunctionVo;
import com.crm.crm.common.vo.ComboTree;
import com.crm.crm.common.vo.MiniDaoPage;
import org.crmframework.core.minidao.service.MiniDaoFastQueryService;
import org.crmframework.core.service.SystemService;
import org.crmframework.core.tag.easyui.TagUtil;
import org.crmframework.core.util.*;
import org.crmframework.core.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Scope("prototype")
@Controller
@RequestMapping("/functionController")
public class FunctionController {
    @Resource
    SystemService systemService;
    @Reference
    @Autowired(required = false)
    FunctionService functionService;
    @Resource
    MiniDaoFastQueryService miniDaoFastQueryService;
    /**
     * 菜单列表页面跳转
     *
     * @return
     */
    @RequestMapping(params = "function")
    public ModelAndView function(HttpServletRequest request) {
        systemService.setNoOptCode();
        return new ModelAndView("function/functionList");
    }

    /**
     * 菜单列表
     */
    @RequestMapping(params = "functionGrid")
    @ResponseBody
    public List<TreeGrid> functionGrid(HttpServletRequest request, TreeGrid treegrid) {
        List<TreeGrid> treeGrids = new ArrayList<TreeGrid>();
/*
        List<TSFunction> functionList = null;
        String hql="from TSFunction f where nvl(f.TSFunction.id,'0')=?";
        if(null==treegrid.getId()){
            functionList=systemService.findHql(hql,"0");
        }else{
            functionList=systemService.findHql(hql,treegrid.getId());
        }
        for(TSFunction f:functionList){
            TreeGrid tf=new TreeGrid();
            tf.setId(f.getId());
            tf.setText(f.getFunctionName());
            if(null!=f.getTSFunction()){
                tf.setParentId(f.getTSFunction().getId());
                tf.setParentText(f.getTSFunction().getFunctionName());
            }else{
                tf.setParentId("");
                tf.setParentText(f.getFunctionName());
            }
            tf.setCode(f.getTSIcon().getIconPath());
            tf.setSrc("");
            if(CollectionUtil.listNotEmptyNotSizeZero(f.getTSFunctions())){
                tf.setState("closed");
            }
            tf.setOrder(f.getFunctionOrder());
            tf.setFunctionType(f.getFunctionType()+"");
            treeGrids.add(tf);
        }
*/
        List<TSFunctionVo> functionvoList=functionService.findFunctionByParentId(null==treegrid.getId()?"0":treegrid.getId());
        //菜单分层并去重
        Map<String,Map<String,TSFunctionVo>> levelFunctionMap=functionService.setChildren(functionvoList);
        //获取一级层菜单并排序
        List keylist=new ArrayList<String>(levelFunctionMap.keySet());
        Collections.sort(keylist);
        List<TSFunctionVo> topFunctionList=new ArrayList<TSFunctionVo>(levelFunctionMap.get(keylist.get(0)).values());
        topFunctionList=functionService.sortByOrder(topFunctionList);
        for(TSFunctionVo f:topFunctionList){
            TreeGrid tf=new TreeGrid();
            tf.setId(f.getId());
            tf.setText(f.getFunctionName());
            if(null!=f.getParentFunctionId()){
                tf.setParentId(f.getParentFunctionId());
                tf.setParentText(f.getParentFunctionName());
            }else{
                tf.setParentId("");
                tf.setParentText(f.getFunctionName());
            }
            tf.setCode(f.getIconPath());
            tf.setSrc("");
            if(CollectionUtil.listNotEmptyNotSizeZero(f.getFunctionVoList())){
                tf.setState("closed");
            }
            tf.setOrder(f.getFunctionOrder());
            tf.setFunctionType(f.getFunctionType()+"");
            treeGrids.add(tf);
        }
        MutiLangUtil.setMutiTree(treeGrids);
        return treeGrids;
    }


    @RequestMapping(params = "setPFunction")
    @ResponseBody
    public List<ComboTree> setPFunction(HttpServletRequest request, ComboTree comboTree) {
        List<ComboTree> comboTrees = new ArrayList<ComboTree>();
        List<TSFunctionVo> functionvoList=functionService.findFunctionByParentId(null==comboTree.getId()?"0":comboTree.getId());
        //菜单分层并去重
        Map<String,Map<String,TSFunctionVo>> levelFunctionMap=functionService.setChildren(functionvoList);
        //获取一级层菜单并排序
        List keylist=new ArrayList<String>(levelFunctionMap.keySet());
        Collections.sort(keylist);
        List<TSFunctionVo> topFunctionList=new ArrayList<TSFunctionVo>(levelFunctionMap.get(keylist.get(0)).values());
        topFunctionList=functionService.sortByOrder(topFunctionList);
        for(TSFunctionVo f:topFunctionList){
            ComboTree ct=new ComboTree();
            ct.setId(f.getId());
            ct.setText(f.getFunctionName());
            if(null!=f.getParentFunctionId()){
                ct.setPid(f.getParentFunctionId());
            }
            if(CollectionUtil.listNotEmptyNotSizeZero(f.getFunctionVoList())){
                ct.setState("closed");
            }
            comboTrees.add(ct);
        }
        MutiLangUtil.setMutiTree(comboTrees);
        return comboTrees;
    }

    /**
     * 新增菜单页面跳转
     *
     * @return
     */
    @RequestMapping(params = "addorupdate")
    public ModelAndView addorupdate(TSFunction function, HttpServletRequest req) {
        List<TSIcon> iconlist = systemService.findHql("from TSIcon where iconType != 3");
        req.setAttribute("iconlist", iconlist);
        List<TSIcon> iconDeskList = systemService.findHql("from TSIcon where iconType = 3");
        req.setAttribute("iconDeskList", iconDeskList);
        if (StringUtil.isNotBlank(function.getId())) {
            function = systemService.getEntity(TSFunction.class, function.getId());
        }
        if (function.getTSFunction() != null && function.getTSFunction().getId() != null) {
            function.setFunctionLevel((short) 1);
            function.setTSFunction((TSFunction) systemService.getEntity(TSFunction.class, function.getTSFunction().getId()));
        }
        req.setAttribute("function", function);
        return new ModelAndView("function/function");
    }

    /**
     * 保存菜单
     * @param function
     * @param request
     * @return
     */
    @RequestMapping(params = "saveFunction")
    @ResponseBody
    public AjaxJson saveFunction(TSFunction function, HttpServletRequest request) throws Exception{
        AjaxJson ajaxJson = new AjaxJson();
        try {
            if(StringUtil.isNotBlank(function.getId())){
                TSFunction old = systemService.getEntity(TSFunction.class, function.getId());
                BeanUtil.apply(function,old);
                function=old;
            }else{
                function.setId(null);
            }
            systemService.saveOrUpdate(function);
        }catch(Exception e){
            ExceptionHandlerUtil.handlerException(ajaxJson, e);
        }
        return ajaxJson;
    }


    /**
     * 删除菜单
     * @param function
     * @param request
     * @return
     */
    @RequestMapping(params = "delFunction")
    @ResponseBody
    public AjaxJson delFunction(TSFunction function, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        try{
            function = systemService.getEntity(TSFunction.class, function.getId());
            String childrenSql="select f.id from t_s_function f start with f.id=? connect by prior f.id= f.parentfunctionid";
            systemService.jdbcUpdate("delete from t_s_operation where functionid in ("+childrenSql+")",function.getId());
            systemService.jdbcUpdate("delete from t_s_role_function where functionid in ("+childrenSql+")",function.getId());
            systemService.jdbcUpdate("delete from t_s_function where id in ("+childrenSql+")",function.getId());
        }catch(Exception e){
            ExceptionHandlerUtil.handlerException(ajaxJson, e);
        }
        return ajaxJson;
    }

    /**
     * 菜单按钮列表
     * @param request
     * @param functionId
     * @return
     */
    @RequestMapping(params = "operation")
    public ModelAndView operation(HttpServletRequest request, String functionId) {
        request.setAttribute("functionId", functionId);
        return new ModelAndView("operation/operationList");
    }

    /**
     * 菜单按钮列表
     * @param request
     * @param response
     * @param dataGrid
     */
    @RequestMapping(params = "opdategrid")
    public void opdategrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        String functionId = request.getParameter("functionId");
        String sql="select * from t_s_operation o where o.functionid=:functionId";
        Map<String, Object> paramMap=new HashMap<String, Object>();
        paramMap.put("functionId",functionId);
        MiniDaoPage miniDaoPage=miniDaoFastQueryService.queryReturnMinidaoMiniDaoPage(sql, paramMap, dataGrid.getPage(), dataGrid.getRows(), TSOperation.class);
        dataGrid.setTotal(miniDaoPage.getTotal());
        dataGrid.setResults(miniDaoPage.getResults());
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * 菜单按钮新增
     * @param operation
     * @param req
     * @return
     */
    @RequestMapping(params = "addorupdateop")
    public ModelAndView addorupdateop(TSOperation operation, HttpServletRequest req) {
        List<TSIcon> iconlist = systemService.findHql("from TSIcon");
        req.setAttribute("iconlist", iconlist);
        if (operation.getId() != null) {
            operation = systemService.getEntity(TSOperation.class, operation.getId());
            req.setAttribute("operation", operation);
        }
        String functionId = req.getParameter("functionId");
        req.setAttribute("functionId", functionId);
        return new ModelAndView("operation/operation");
    }


    /**
     * 菜单按钮保存
     * @param operation
     * @return
     */
    @RequestMapping(params = "saveOperation")
    @ResponseBody
    public AjaxJson saveOperation(TSOperation operation, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        try{
            if(StringUtil.isNotBlank(operation.getId())){
                TSOperation old = systemService.getEntity(TSOperation.class, operation.getId());
                BeanUtil.apply(operation,old);
                operation=old;
            }else{
                operation.setId(null);
            }
            systemService.saveOrUpdate(operation);
        }catch(Exception e){
            ExceptionHandlerUtil.handlerException(ajaxJson, e);
        }
        return ajaxJson;
    }

    @RequestMapping(params = "delOperation")
    @ResponseBody
    public AjaxJson delOperation(TSOperation operation, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        try{
            systemService.jdbcUpdate("delete from t_s_operation o where o.id=?",operation.getId());
        }catch(Exception e){
            ExceptionHandlerUtil.handlerException(ajaxJson, e);
        }
        return ajaxJson;
    }
}
