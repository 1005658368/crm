package com.crm.crm.base.mdm.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.crm.crm.base.mdm.dao.FunctionDao;
import com.crm.crm.base.mdm.service.FunctionService;
import com.crm.crm.base.mdm.vo.TSFunctionVo;
import org.apache.commons.collections.CollectionUtils;
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

}
