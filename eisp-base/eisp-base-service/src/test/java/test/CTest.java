package test;

import com.crm.crm.base.mdm.service.FunctionService;
import com.crm.crm.pojo.vo.TSFunctionVo;
import org.crmframework.core.common.service.CommonService;
import org.crmframework.core.minidao.service.MiniDaoFastQueryService;
import org.crmframework.core.service.SystemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={ "classpath:dataSource.xml" })
public class CTest {
    private static final Logger log = LoggerFactory.getLogger(CTest.class);

    @Resource
    CommonService commonServiceImpl;
    @Resource
    public MiniDaoFastQueryService miniDaoFastQueryService;
    @Resource
    SystemService systemService;
    @Resource
    FunctionService functionService;
    @Test
    public void Test1(){
        log.debug("cx1");
//        List<MutiLangEntity> mutiLang = commonServiceImpl.test();
//        List<TSFunction> functionList=commonServiceImpl.findHql("select f from TSRoleUser ru,TSRoleFunction rf,TSFunction f where ru.TSRole.id=rf.TSRole.id and rf.TSFunction.id=f.id and f.functionLevel=0 and ru.TSUser.id=? ","8a8249c75451b26b015451b5db770022");
//        List<TSFunctionVo> functionList=commonServiceImpl.findSql("select f.id as id,f.functionlevel as functionlevel,f.functionname as functionname,f.functionorder as functionorder,f.functionurl as functionurl,f.parentfunctionid as parentfunctionid,f.iconid as iconid,f.functiontype as functiontype,i.path as iconPath,i.iconclas as iconClas from t_s_role_user ru join t_s_role_function rf on ru.roleid=rf.roleid join t_s_function f on f.id=rf.functionid join t_s_icon i on i.id=f.iconid where ru.userid=? ",TSFunctionVo.class,"8a8249c75451b26b015451b5db770022");
//        List<TSFunctionVo> functionList=commonServiceImpl.findSql("select f.id as \"id\" from t_s_role_user ru join t_s_role_function rf on ru.roleid=rf.roleid join t_s_function f on f.id=rf.functionid join t_s_icon i on i.id=f.iconid where ru.userid=? ",TSFunctionVo.class,"8a8249c75451b26b015451b5db770022");
//        List functionList=commonServiceImpl.findSql("select f.id as id,f.functionlevel as functionlevel,f.functionname as functionname,f.functionorder as functionorder,f.functionurl as functionurl,f.parentfunctionid as parentfunctionid,f.iconid as iconid,f.functiontype as functiontype,i.path as iconPath,i.iconclas as iconClas from t_s_role_user ru join t_s_role_function rf on ru.roleid=rf.roleid join t_s_function f on f.id=rf.functionid join t_s_icon i on i.id=f.iconid where ru.userid=? ","8a8249c75451b26b015451b5db770022");

//        Map<String, Object> paramMap=new HashMap<String, Object>();
//        paramMap.put("userId","8a8249c75451b26b015451b5db770022");
//        String sql="" +
//                " select f.id as id,f.functionlevel as functionlevel,f.functionname as functionname,f.functionorder as functionorder,f.functionurl as functionurl,f.parentfunctionid as parentfunctionid,f.iconid as iconid,f.functiontype as functiontype,i.path as iconPath,i.iconclas as iconClas from t_s_role_user ru join t_s_role_function rf on ru.roleid=rf.roleid join t_s_function f on f.id=rf.functionid join t_s_icon i on i.id=f.iconid where 1=1 " +
//                " <#if userId ?exists && userId ?length gt 0>" +
//                " and ru.userid=:userId " +
//                " </#if>"+
//                " ";
//        List functionList=miniDaoFastQueryService.queryReturnMinidaoList(sql,paramMap,TSFunctionVo.class);
//        List functionList=systemService.test6("8a8249c75451b26b015451b5db770022");
//        List functionList2=systemService.test7("8a8249c75451b26b015451b5db770022");
//        List functionList=functionService.findFunctionByUserId("8a8249c75451b26b015451b5db770022");
//        System.out.println(functionList.size());
//        System.out.println(functionList2.size());
//        List list=commonServiceImpl.test();
//        List list1=commonServiceImpl.findSql("select * from t_s_base_user");
//        System.out.println(list1.size());
//        String hql = "from TSUser u where u.userName = :userName and u.passWord=:passWord";
//        Map<String,Object> paramMap=new HashMap<String,Object>();
//        paramMap.put("userName", "admin");
//        paramMap.put("passWord", "900150983cd24fb0d6963f7d28e17f72");
//        List<TSUser> list2=commonServiceImpl.findHql(hql,paramMap);
//        List list2=commonServiceImpl.findHql("from TSUser");
//        System.out.println(list2.size());


        List<TSFunctionVo> functionList=functionService.findFunctionByUserId("8a8249c75451b26b015451b5db770022");
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
        //按照层级分组
        for(int i=levelFunctionMap.keySet().size()-2;i>=0;i--){
            Collection<TSFunctionVo> tlist0=levelFunctionMap.get(String.valueOf(i)).values();
            Collection<TSFunctionVo> tlist1=levelFunctionMap.get(String.valueOf(i+1)).values();
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
                tlist1.removeAll(tlist2);
            }
        }

        log.debug("cx2");
    }

}