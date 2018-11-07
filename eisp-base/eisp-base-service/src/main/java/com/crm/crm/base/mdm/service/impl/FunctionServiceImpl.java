package com.crm.crm.base.mdm.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.crm.crm.base.mdm.dao.FunctionDao;
import com.crm.crm.base.mdm.service.FunctionService;
import com.crm.crm.base.mdm.vo.TSFunctionVo;
import com.crm.crm.common.vo.ComboTree;
import org.apache.commons.collections.CollectionUtils;
import org.crmframework.core.common.service.impl.CommonServiceImpl;
import org.crmframework.core.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
@Service
@Transactional
public class FunctionServiceImpl implements FunctionService{
    @Autowired
    FunctionDao functionDao;

    @Override
    public List<TSFunctionVo> findFunctionByUserId(String userId) {
        List<TSFunctionVo> result=functionDao.findFunctionByUserIdSQL(userId);
        return result;
    }

    @Override
    public List<TSFunctionVo> findFunctionByRoleId(String roleId) {
        List<TSFunctionVo> result=functionDao.findFunctionByRoleIdSQL(roleId);
        return result;
    }

    @Override
    public List<TSFunctionVo> findFunctionByParentId(String parentId) {
        List<TSFunctionVo> result=functionDao.findFunctionByParentIdSQL(parentId);
        return result;
    }

    @Override
    public Map<String,Map<String,TSFunctionVo>> setChildren(List<TSFunctionVo> functionList){
        //菜单分层并去重
        Map<String,Map<String,TSFunctionVo>> levelFunctionMap=new HashMap<String,Map<String,TSFunctionVo>>();
        for(TSFunctionVo vo:functionList){
            String level=String.valueOf(vo.getFunctionLevel());
            String id=vo.getId();
            if(levelFunctionMap.containsKey(level)){
                if(!levelFunctionMap.get(level).containsKey(id)){
                    levelFunctionMap.get(level).put(id,vo);
                }
            }else{
                Map<String,TSFunctionVo> levelMap=new HashMap<String,TSFunctionVo>();
                levelMap.put(id,vo);
                levelFunctionMap.put(level,levelMap);
            }
        }
        //设置菜单的下级菜单
        List keylist=new ArrayList<String>(levelFunctionMap.keySet());
        Collections.sort(keylist);
        for(int i=keylist.size()-2;i>=0;i--){
            Collection<TSFunctionVo> tlist0=levelFunctionMap.get(keylist.get(i)).values();
            Collection<TSFunctionVo> tlist1=levelFunctionMap.get(keylist.get(i+1)).values();
            for(TSFunctionVo vo:tlist0){
                List<TSFunctionVo> tlist2=new ArrayList<TSFunctionVo>();
                for(TSFunctionVo vo2:tlist1){
                    if(vo2.getParentFunctionId().equals(vo.getId())){
                        if(null==vo.getFunctionVoList()){
                            List<TSFunctionVo> tlist3=new ArrayList<TSFunctionVo>();
                            vo.setFunctionVoList(tlist3);
                        }
                        vo.getFunctionVoList().add(vo2);
                        tlist2.add(vo2);
                    }
                }
                //排序
                if(CollectionUtils.isNotEmpty(vo.getFunctionVoList())){
                    List childFunctionList=vo.getFunctionVoList();
                    Collections.sort(childFunctionList,new Comparator<TSFunctionVo>(){
                        @Override
                        public int compare(TSFunctionVo o1, TSFunctionVo o2) {
                            if (Integer.parseInt(o1.getFunctionOrder()) < Integer.parseInt(o2.getFunctionOrder()))
                                return -1;
                            else
                                return 1;
                        }}
                    );
                    vo.setFunctionVoList(childFunctionList);
                }
                tlist1.removeAll(tlist2);
            }
        }
        return levelFunctionMap;
    }

    @Override
    public List<TSFunctionVo> sortByOrder(List<TSFunctionVo> functionList){
        Collections.sort(functionList,new Comparator<TSFunctionVo>(){
            @Override
            public int compare(TSFunctionVo o1, TSFunctionVo o2) {
                if (Integer.parseInt(o1.getFunctionOrder()) <= Integer.parseInt(o2.getFunctionOrder()))
                    return -1;
                else
                    return 1;
            }}
        );
        return functionList;
    }

    @Override
    public List<TSFunctionVo> findFunctionTreeByParentId(String parentId){
        List<TSFunctionVo> functionvoList=findFunctionByParentId(null==parentId?"0":parentId);
        //菜单分层并去重
        Map<String,Map<String,TSFunctionVo>> levelFunctionMap=setChildren(functionvoList);
        //获取一级菜单并按照菜单的order排序
        List keylist=new ArrayList<String>(levelFunctionMap.keySet());
        Collections.sort(keylist);
        List<TSFunctionVo> topFunctionList=new ArrayList<TSFunctionVo>(levelFunctionMap.get(keylist.get(0)).values());
        topFunctionList=sortByOrder(topFunctionList);
        return topFunctionList;
    }

    @Override
    public List<ComboTree> makeComboTree(List<TSFunctionVo> topFunctionTreeList, List<String> openIdList){
        List<ComboTree> comboTrees = new ArrayList<ComboTree>();
        for(TSFunctionVo f:topFunctionTreeList){
            ComboTree ct=new ComboTree();
            ct.setId(f.getId());
            ct.setText(f.getFunctionName());
            if(null!=f.getParentFunctionId()){
                ct.setPid(f.getParentFunctionId());
            }
            if((!CollectionUtil.listNotEmptyNotSizeZero(f.getFunctionVoList()))&&CollectionUtil.listNotEmptyNotSizeZero(openIdList)&&openIdList.contains(f.getId())){
                ct.setChecked(true);
            }
            if(CollectionUtil.listNotEmptyNotSizeZero(f.getFunctionVoList())){
                ct.setState("closed");
                ct.setChildren(makeComboTree(f.getFunctionVoList(),openIdList));
            }
            comboTrees.add(ct);
        }
        return comboTrees;
    }

}
