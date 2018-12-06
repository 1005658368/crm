package test;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.crm.crm.base.mdm.entity.TSUser;
import org.apache.cxf.binding.soap.saaj.SAAJOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.handler.WSHandlerConstants;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class CTest2 {
	private static final Logger log = LoggerFactory.getLogger(CTest.class);

	@Test
	public void Test1() throws Exception{
//		Map<String,String> map=new HashMap<>();
//		map.put("TYPENAME","三全");
//		String maps=JSONObject.toJSONString(map);
//		System.out.println(maps);
//		System.out.println(JSONObject.parseObject(maps));
//		String mapss=JSONObject.toJSONString(maps);
//		System.out.println(mapss);
//		String s = null;
//		s="{\"TYPENAME\":\"三全\"}";
//		System.out.println(s);
//		String ss=JSONObject.toJSONString(s);
//		System.out.println(ss);

		TSUser user=new TSUser();
		user.setUserName("cx1");
		System.out.println(JSONObject.toJSONString(user,SerializerFeature.WriteMapNullValue));
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(user));
//		JSONObject jsonObject=JSONObject.parseObject(s);
//		Object o=jsonObject.get("TYPENAME");
//		Map map1=JSONObject.parseObject(s,HashMap.class);
//		System.out.println("cx1");
//		System.out.println(Globals.OPERATIONCODES);
//		ArrayList<String> list=new ArrayList<String>();
//		list.add("1");
//		list.add("2");
//		list.add("3");
//		list.add("4");
//		list.add("5");
//		for(String s:list){
//			if(s.equals("2")||s.equals("3")){
//				list.remove(s);
//			}
//			System.out.println(s);
//		}
//		System.out.println("abc1");
//		System.out.println("abc2");
//		List list=new ArrayList<>();
//		List list=null;
//		System.out.println(CollectionUtils.isEmpty(list));
	}


	@Test
	public void Test2(){
//		String loginName = "loginName";
//		String password = "password";
//		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
//		Map<String,Object> outProps = new HashMap<String,Object>();
//		outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
//		outProps.put(WSHandlerConstants.USER, loginName);
//		outProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
////		outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, ClientPasswordCallback.class.getName());
//		factory.getOutInterceptors().add(new WSS4JOutInterceptor(outProps));
//		factory.getOutInterceptors().add(new SAAJOutInterceptor());
//
////		factory.setServiceClass(IHelloWorld.class);
//		factory.setAddress("http://127.0.0.1:8081/struts2-spring-hibernate/webservice/HelloWorldService");
////		IHelloWorld helloWorldImpl = (IHelloWorld) factory.create();
////		helloWorldImpl.sayName("cx");

	}

	@Test
	public void Test3(){
	}


}
