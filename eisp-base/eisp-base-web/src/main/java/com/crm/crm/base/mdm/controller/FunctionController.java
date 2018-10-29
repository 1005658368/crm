package com.crm.crm.base.mdm.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.crm.crm.base.mdm.entity.TSFunction;
import com.crm.crm.base.mdm.service.FunctionService;
import com.crm.crm.base.mdm.vo.TSFunctionVo;
import org.crmframework.core.minidao.service.MiniDaoFastQueryService;
import org.crmframework.core.service.SystemService;
import org.crmframework.core.util.CollectionUtil;
import org.crmframework.core.util.MutiLangUtil;
import org.crmframework.core.vo.TreeGrid;
import org.crmframework.core.vo.TreeGridModel;
import org.crmframework.core.vo.datatable.SortDirection;
import org.hibernate.criterion.CriteriaQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
     * 权限列表页面跳转
     *
     * @return
     */
    @RequestMapping(params = "function")
    public ModelAndView function() {
        return new ModelAndView("function/functionList");
    }

    /**
     * 权限列表
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
        String findFunctionByParentId =
                " select f.id as id,f.functionlevel as functionlevel,f.functionname as functionname,f.functionorder as functionorder," +
                        " f.functionurl as functionurl,f.parentfunctionid as parentfunctionid,f.iconid as iconid," +
                        " f.functiontype as functiontype,i.path as iconPath,i.iconclas as iconClas " +
                        " from t_s_function f " +
                        " left join t_s_function pf on pf.id=f.parentfunctionid " +
                        " join t_s_icon i on i.id=f.iconid " +
                        " where 1=1 " +
                        " and f.id in " +
                        " ( select f.id from t_s_function f start with nvl(f.parentfunctionid,'0')=:parentId connect by prior f.id= f.parentfunctionid ) " +
                        " ";
        Map<String, Object> paramMap=new HashMap<String, Object>();
        paramMap.put("parentId",null==treegrid.getId()?"0":treegrid.getId());
        List<TSFunctionVo> functionvoList = miniDaoFastQueryService.queryReturnMinidaoList(findFunctionByParentId,paramMap,TSFunctionVo.class);
        //菜单分层并去重
        Map<String,Map<String,TSFunctionVo>> levelFunctionMap=functionService.setChildren(functionvoList);
        //获取一级层菜单并排序
        List keylist=new ArrayList<String>(levelFunctionMap.keySet());
        Collections.sort(keylist);
        List<TSFunctionVo> topFunctionList=new ArrayList<TSFunctionVo>(levelFunctionMap.get(keylist.get(0)).values());
        Collections.sort(topFunctionList,new Comparator<TSFunctionVo>(){
            @Override
            public int compare(TSFunctionVo o1, TSFunctionVo o2) {
                if (Integer.parseInt(o1.getFunctionOrder()) <= Integer.parseInt(o2.getFunctionOrder()))
                    return -1;
                else
                    return 1;
            }}
        );
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


}
