 select f.id as id,f.functionlevel as functionlevel,f.functionname as functionname,
 f.functionorder as functionorder,f.functionurl as functionurl,f.parentfunctionid as parentfunctionid,
 f.iconid as iconid,f.functiontype as functiontype,i.path as iconPath,i.iconclas as iconClas
 from t_s_role_user ru
 join t_s_role_function rf on ru.roleid=rf.roleid
 join t_s_function f on f.id=rf.functionid
 join t_s_icon i on i.id=f.iconid
 where 1=1
 <#if userId ?exists && userId ?length gt 0>
 and ru.userid=:userId
 </#if>

