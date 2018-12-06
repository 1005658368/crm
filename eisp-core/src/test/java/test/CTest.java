package test;

import com.crm.crm.base.mdm.entity.TSFunction;
import com.crm.crm.base.mdm.entity.TSRole;
import com.crm.crm.base.mdm.vo.TSFunctionVo;
import org.apache.commons.beanutils.BeanUtils;
import org.crmframework.core.common.service.CommonService;
import org.crmframework.core.minidao.service.MiniDaoFastQueryService;
import org.crmframework.core.service.SystemService;
import org.crmframework.core.util.BeanUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    public void Test1() throws Exception{
        log.debug("cx1");
        TSRole role=new TSRole();
        role.setRoleName("cx1");
        role.setRoleCode("cx1");
        systemService.saveOrUpdate(role);
        List<TSRole> rolelist=systemService.findHql("from TSRole where roleName='cx1'");
        System.out.println(rolelist.get(0).getRoleName());
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

//        String hql = "from TSUser u where u.userName = :userName and u.passWord=:passWord";
//        Map<String,Object> paramMap=new HashMap<String,Object>();
//        paramMap.put("userName", "admin");
//        paramMap.put("passWord", "900150983cd24fb0d6963f7d28e17f72");
//        List<TSUser> userList=commonServiceImpl.findHql(hql,paramMap);

        //List<TSFunction> functionList = systemService.findHql("from TSFunction f where nvl(f.TSFunction.id,'0')='0'");
//        TSFunction oobj = systemService.getEntity(TSFunction.class, "402880e548016f8a0148017532fc0005");
//        TSFunction nobj = new TSFunction();
//        nobj.setFunctionName(oobj.getFunctionName()+"cx1");
//        BeanUtil.copy(nobj,oobj,null,null,null,false,false);
//        System.out.println(oobj.getFunctionName());
//        List<Map<String, Object>> maplist=systemService.findForJdbc("select * from t_s_role_function where functionid=?","40286e8166cdd57d0166cddbb94a0000");
//        systemService.jdbcUpdate("delete from t_s_role_function where functionid=?","40286e8166cdd57d0166cddbb94a0000");
//        systemService.jdbcUpdate("delete from t_s_function where id=?","40286e8166cdd57d0166cddbb94a0000");
        log.debug("cx2");
    }

}
