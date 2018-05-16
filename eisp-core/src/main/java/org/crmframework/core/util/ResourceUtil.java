package org.crmframework.core.util;

import org.apache.commons.lang3.StringUtils;
import org.crmframework.core.entity.DynamicDataSourceEntity;
import org.crmframework.core.entity.TSIcon;
import org.crmframework.core.entity.TSType;
import org.crmframework.core.entity.TSTypegroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;


/**
 * 项目参数工具类
 * 
 */
public class ResourceUtil {
	private static final Logger logger = LoggerFactory.getLogger(ResourceUtil.class);
	public static final String LOCAL_CLINET_USER = "LOCAL_CLINET_USER";
	/**
	 * 缓存字段分组【缓存】
	 */
	public static Map<String, TSTypegroup> allTypeGroups = new HashMap<String,TSTypegroup>();
	/**
	 * 缓存字典【缓存】
	 */
	public static Map<String, List<TSType>> allTypes = new HashMap<String,List<TSType>>();
	
	/**
	 * 国际化【缓存】
	 */
	public static Map<String, String> mutiLangMap = new HashMap<String, String>(); 
	/**
	 * 缓存系统图标【缓存】
	 */
	public static Map<String, TSIcon> allTSIcons = new HashMap<String,TSIcon>();
	/**
	 * 动态数据源参数配置【缓存】
	 */
	public static Map<String, DynamicDataSourceEntity> dynamicDataSourceMap = new HashMap<String, DynamicDataSourceEntity>();
	
	private static final ResourceBundle bundle = ResourceBundle.getBundle("sysConfig");
	
	/**
	 * 属性文件[resources/sysConfig.properties]
	 * #默认开启模糊查询方式 1为开启 条件无需带*就能模糊查询[暂时取消]
	 * fuzzySearch=0
	 */

//	public final static boolean fuzzySearch= ResourceUtil.isFuzzySearch();


	/**
	 * 获取session定义名称
	 * 
	 * @return
	 */
	public static final String getSessionattachmenttitle(String sessionName) {
		return bundle.getString(sessionName);
	}
	/**
	 * 获得请求路径【注意： 不通用】
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestPath(HttpServletRequest request) {

//		String requestPath = request.getRequestURI() + "?" + request.getQueryString();
		String queryString = request.getQueryString();
		String requestPath = request.getRequestURI();
		if(StringUtils.isNotEmpty(queryString)){
			requestPath += "?" + queryString;
		}

		if (requestPath.indexOf("&") > -1) {// 去掉其他参数(保留一个参数) 例如：loginController.do?login
			requestPath = requestPath.substring(0, requestPath.indexOf("&"));
		}

		if(requestPath.indexOf("=")!=-1){

			if(requestPath.indexOf(".do")!=-1){
				requestPath = requestPath.substring(0,requestPath.indexOf(".do")+3);
			}else{
				requestPath = requestPath.substring(0,requestPath.indexOf("?"));
			}

		}

		requestPath = requestPath.substring(request.getContextPath().length() + 1);// 去掉项目路径
		return requestPath;
	}

	/**
	 * 获取配置文件参数
	 * 
	 * @param name
	 * @return
	 */
	public static final String getConfigByName(String name) {
		return bundle.getString(name);
	}

	/**
	 * 获取配置文件参数
	 * 
	 * @return
	 */
	public static final Map<Object, Object> getConfigMap(String path) {
		ResourceBundle bundle = ResourceBundle.getBundle(path);
		Set set = bundle.keySet();
		return oConvertUtils.SetToMap(set);
	}

	
	
	public static String getSysPath() {
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		String temp = path.replaceFirst("file:/", "").replaceFirst("WEB-INF/classes/", "");
		String separator = System.getProperty("file.separator");
		String resultPath = temp.replaceAll("/", separator + separator).replaceAll("%20", " ");
		return resultPath;
	}

	/**
	 * 获取项目根目录
	 * 
	 * @return
	 */
	public static String getPorjectPath() {
		String nowpath; // 当前tomcat的bin目录的路径 如
						// D:\java\software\apache-tomcat-6.0.14\bin
		String tempdir;
		nowpath = System.getProperty("user.dir");
		tempdir = nowpath.replace("bin", "webapps"); // 把bin 文件夹变到 webapps文件里面
		tempdir += "\\"; // 拼成D:\java\software\apache-tomcat-6.0.14\webapps\sz_pro
		return tempdir;
	}

	public static String getClassPath() {
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		String temp = path.replaceFirst("file:/", "");
		String separator = System.getProperty("file.separator");
		String resultPath = temp.replaceAll("/", separator + separator);
		return resultPath;
	}

	public static String getSystempPath() {
		return System.getProperty("java.io.tmpdir");
	}

	public static String getSeparator() {
		return System.getProperty("file.separator");
	}

	public static String getParameter(String field) {
		HttpServletRequest request = ContextHolderUtils.getRequest();
		return request.getParameter(field);
	}

	/**
	 * 获取数据库类型
	 * 
	 * @return
	 * @throws Exception 
	 */
	public static final String getJdbcUrl() {
		return DBTypeUtil.getDBType().toLowerCase();
	}

    /**
     * 获取随机码的长度
     *
     * @return 随机码的长度
     */
    public static String getRandCodeLength() {
        return bundle.getString("randCodeLength");
    }

    /**
     * 获取随机码的类型
     *
     * @return 随机码的类型
     */
    public static String getRandCodeType() {
        return bundle.getString("randCodeType");
    }


    /**
     * 获取组织机构编码长度的类型
     *
     * @return 组织机构编码长度的类型
     */
    public static String getOrgCodeLengthType() {
        return bundle.getString("orgCodeLengthType");
    }

    /**
     * 获取用户session 中的变量
     * @param key
     * 			Session 中的值
     * @return
     */
	private static String getSessionData(String key) {
		//${myVar}%
		//得到${} 后面的值
		String moshi = "";
		if(key.indexOf("}")!=-1){
			 moshi = key.substring(key.indexOf("}")+1);
		}
		String returnValue = null;

		if (key.contains("#{")) {
			key = key.substring(2,key.indexOf("}"));
		}
		//从session中取得值
		if (!StringUtils.isEmpty(key)) {
			HttpSession session = ContextHolderUtils.getSession();
			returnValue = (String) session.getAttribute(key);
		}

		//结果加上${} 后面的值
		if(returnValue!=null){returnValue = returnValue + moshi;}
		return returnValue;
	}

	public static void main(String[] args) {
		logger.info(getPorjectPath());
		logger.info(getSysPath());
	}


}
