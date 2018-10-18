crm基础框架，一直完善下去 啊 哈哈
编码须知：
1 名词解释
1.1 普通service指spring的service(只允许写在eisp-core项目中)
1.2 通用service指既可以是普通service又可以是dubbo的service(不允许写在eisp-core项目中，并且必须可以单独以web项目部署以提供dubbo服务)

2 注解使用规范，必须严格执行
2.1 普通service必须用@Component注解
2.2 通用service必须用@Component+@Service(dubbo)
2.3 普通service注入必须用@Autowired
2.4 通用service注入必须用@Reference+@Autowired(required = false)

3 部署须知
3.1 服务化(dubbo)部署
3.1.1 web项目pom文件中删除对service项目的依赖
3.1.2 spring-mvc.xml中引入<import resource="soa-consumer.xml"/>
3.1.3 service项目单独以web项目部署
3.2 单项目部署
3.2.1 web项目pom文件中添加对service项目的依赖
3.2.1 spring-mvc.xml中删除<import resource="soa-consumer.xml"/>
3.1.3 service项目不需要单独部署




