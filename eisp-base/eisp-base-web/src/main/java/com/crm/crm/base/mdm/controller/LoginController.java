package com.crm.crm.base.mdm.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.crm.crm.base.mdm.service.AuthorizeServiceI;
import com.crm.crm.base.mdm.service.FunctionService;
import com.crm.crm.base.mdm.service.UserService;
import com.crm.crm.busi.test.service.TestService;
import com.crm.crm.pojo.entity.TSFunction;
import com.crm.crm.pojo.entity.TSUser;
import com.crm.crm.pojo.vo.TSFunctionVo;
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
import javax.servlet.http.HttpSession;
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

    @RequestMapping(params = "left")
    public ModelAndView left(HttpServletRequest request) {
        TSUser user=ClientManager.getClient().getUser();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main/left");
        List<TSFunctionVo> functionList=functionService.findFunctionByUserId("8a8249c75451b26b015451b5db770022");
        //去重
//        Map<String,TSFunctionVo> functionMap=new HashMap<String,TSFunctionVo>();
        Map<String,Map<String,TSFunctionVo>> levelFunctionMap=new HashMap<String,Map<String,TSFunctionVo>>();
        for(TSFunctionVo vo:functionList){
//            if(!functionMap.containsKey(vo.getId())){
//                functionMap.put(vo.getId(),vo);
//            }
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
//        functionList=(List<TSFunctionVo>)functionMap.values();
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
        request.setAttribute("functionList", levelFunctionMap.get("0").values());
//        request.setAttribute("menuMap", getFunctionMap(user));
        return modelAndView;
    }

    private Map<Integer, List<TSFunction>> getFunctionMap(TSUser user) {
        Map<Integer, List<TSFunction>> functionMap = new HashMap<Integer, List<TSFunction>>();
        Map<String, TSFunction> loginActionlist = getUserFunction(user);
        if (loginActionlist.size() > 0) {
            Collection<TSFunction> allFunctions = loginActionlist.values();
            for (TSFunction function : allFunctions) {
                if (!functionMap.containsKey(function.getFunctionLevel() + 0)) {
                    functionMap.put(function.getFunctionLevel() + 0, new ArrayList<TSFunction>());
                }
                functionMap.get(function.getFunctionLevel() + 0).add(function);
            }
            // 菜单栏排序
            Collection<List<TSFunction>> c = functionMap.values();
            for (List<TSFunction> list : c) {
                Collections.sort(list, new NumberComparator());
            }
        }
        return functionMap;
    }

    private Map<String, TSFunction> getUserFunction(TSUser user) {
        List<TSFunction> functionList = authorizeService.loadFunctionByUserId(user.getId());
        Map<String , TSFunction> map=assembleFunctionByIdMap(null, functionList);
        return map;
    }

    private Map<String , TSFunction> assembleFunctionByIdMap(Map<String, TSFunction> map,List<TSFunction> functions) {
        if (map == null) {
            map = new HashMap<String, TSFunction>();
        }
        for (TSFunction function: functions) {
            map.put(function.getId(), function);
        }
        return map;
    }
}
