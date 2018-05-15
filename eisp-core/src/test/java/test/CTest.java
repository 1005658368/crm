package test;

import org.crmframework.core.common.service.CommonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={ "classpath:application-context.xml" })
public class CTest {
    private static final Logger log = LoggerFactory.getLogger(CTest.class);

    @Resource
    CommonService commonService;

    @Test
    public void Test1(){
        log.debug("cx1");
        List list=commonService.test();
        System.out.println(list.size());
        log.debug("cx2");
    }

}
