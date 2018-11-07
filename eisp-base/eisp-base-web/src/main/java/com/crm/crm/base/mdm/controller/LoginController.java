package com.crm.crm.base.mdm.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.crm.crm.base.mdm.service.AuthorizeServiceI;
import com.crm.crm.base.mdm.service.FunctionService;
import com.crm.crm.base.mdm.service.UserService;
import com.crm.crm.busi.test.service.TestService;
import com.crm.crm.base.mdm.entity.TSUser;
import com.crm.crm.base.mdm.vo.TSFunctionVo;
import org.apache.commons.collections.CollectionUtils;
import org.crmframework.core.common.service.CommonService;
import org.crmframework.core.service.SystemService;
import org.crmframework.core.util.*;
import org.crmframework.core.vo.AjaxJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Scope("prototype")
@Controller
@RequestMapping("/loginController")
public class LoginController {
    @Autowired
    CommonService commonServiceImpl;
    @Reference
    @Autowired(required = false)
    UserService userService;
    @Reference
    @Autowired(required = false)
    AuthorizeServiceI authorizeService;
    @Reference
    @Autowired(required = false)
    TestService testService;
    @Reference
    @Autowired(required = false)
    FunctionService functionService;

    @RequestMapping(params = "test")
    public String test(){
        System.out.println("cx");
        System.out.println(commonServiceImpl.test().size());
        String test1=userService.test("cx1");
//        String test2=userService2.test("cx2");
        String test3=testService.test("cx3");
        return "login/login2";
    }

    @RequestMapping(params = "test2")
    @ResponseBody
    public Map test2() {
        String s = "111";
        s="{\"TYPENAME\":\"三全\"}";
        JSONObject jsonObject=JSONObject.parseObject(s);
        Map map=JSONObject.parseObject(s,HashMap.class);
        return map;
    }

    /**
     * 检查用户
     * @param user
     * @param request
     * @return
     */
    @RequestMapping(params = "checkuser")
    @ResponseBody
    public AjaxJson checkuser(TSUser user, HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        Map<String, Object> map = new HashMap<String,Object>();
        user.setPassWord(Md5EncryptionAndDecryption.encryPwd(user.getPassWord()));
        user = userService.checkUserExits(user);
        if (user == null) {
            ajaxJson.setMsg("用户名或密码错误！");
            ajaxJson.setSuccess(false);
            return ajaxJson;
        }
        Client client = ClientManager.getClient();
        if (null==client) {
            client=new Client();
            client.setUser(user);
            ClientManager.setClient(client);
        }else{
            TSUser clientUser=client.getUser();
            if(!clientUser.getId().equals(user.getId())){//当前登录人和已登录人不一样时根据业务逻辑处理
                client.setUser(user);
            }
        }
        return ajaxJson;
    }

    @RequestMapping(params = "test1")
    @ResponseBody
    public Map<String,Object> test1(HttpServletRequest request) {
        StringBuffer sb = new StringBuffer();
        sb.append(" select ts.typename from xps_material xm ");
        sb.append(" join T_B_PRODUCT_L_M tb  on tb.SPECI_NUM=xm.prdha ");
        sb.append(" left join T_B_PRODUCT_L_M t on tb.PARENTLEVELID=t.id ");
        sb.append(" left join t_s_type ts on t.brand=ts.typecode ");
        sb.append(" left join t_s_typegroup tp on tp.id=ts.typegroupid ");
        sb.append(" where decode(xm.LVORM,'X','0','1')=1 and tp.typegroupcode='product_brand' ");
        sb.append(" and xm.matnr=?  ");
        List<Map<String, Object>> list=commonServiceImpl.findForJdbc(sb.toString(),"1100528001");
        Map<String, Object> map=list.get(0);
        return map;
    }

    /**
     * 登录进入首页
     * @param userName
     * @param password
     * @param request
     * @return
     */
    @RequestMapping(params = "login")
    public ModelAndView login(String userName, String password, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Client client = ClientManager.getClient();
        if (null==client) {
            modelAndView.setViewName("login/login");
        }
        modelAndView.setViewName("main/main");
        return modelAndView;
    }

    /**
     * 左侧菜单业
     * @param request
     * @return
     */
    @RequestMapping(params = "left")
    public ModelAndView left(HttpServletRequest request) {
        TSUser user=ClientManager.getClient().getUser();
        //获取当前用户所有菜单
        List<TSFunctionVo> functionList=functionService.findFunctionByUserId(user.getId());
        //菜单分层并去重
        Map<String,Map<String,TSFunctionVo>> levelFunctionMap=functionService.setChildren(functionList);
        //获取一级层菜单并排序
        List<TSFunctionVo> topFunctionList=new ArrayList<TSFunctionVo>(levelFunctionMap.get("0").values());
        Collections.sort(topFunctionList,new Comparator<TSFunctionVo>(){
            @Override
            public int compare(TSFunctionVo o1, TSFunctionVo o2) {
                if (Integer.parseInt(o1.getFunctionOrder()) <= Integer.parseInt(o2.getFunctionOrder()))
                    return -1;
                else
                    return 1;
            }}
         );
        request.setAttribute("functionList",topFunctionList);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main/left");
        return modelAndView;
    }

    /**
     * 右侧工作流首页
     * @param request
     * @return
     */
    @RequestMapping(params = "home")
    public ModelAndView home(HttpServletRequest request) {
        return new ModelAndView("redirect:workflowController.do?home");
    }

}
