import org.crmframework.core.common.service.CommonService;
import org.crmframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Start {

    public static void main(String[] args) throws Exception {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { Start.class.getResource("/META-INF/spring/application-context.xml").toString()});
//        context.start();
//        while (true) {
//            System.in.read();
//        }

/*
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:application-context.xml"}, true);
        System.out.println("cxl启动----------------");
        SystemServiceImpl systemService=context.getBean("systemService", SystemServiceImpl.class);
        System.out.println(systemService);
        System.out.println(systemService.getSessionFactory());
        Session session=systemService.getSessionFactory().openSession();
        System.out.println(session);
        List list=session.createSQLQuery("select id as id from t_s_base_user").list();
        System.out.println(list.size());
        System.out.println("cxl结束----------------");
*/

        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:application-context.xml"}, true);
        System.out.println("cxl启动----------------");
        CommonService commonService=context.getBean("commonService", CommonServiceImpl.class);
        List list=commonService.test();
        System.out.println(list.size());
        System.out.println("cxl结束----------------");

    }
}