 select f.id as id,f.functionlevel as functionlevel,f.functionname as functionname,
 f.functionorder as functionorder,f.functionurl as functionurl,f.parentfunctionid as parentfunctionid,
 f.iconid as iconid,f.functiontype as functiontype,i.path as iconPath,i.iconclas as iconClas
 from t_s_function f
 left join t_s_function pf on pf.id=f.parentfunctionid
 join t_s_icon i on i.id=f.iconid
 where 1=1
 and f.id in
 (
 select f.id from t_s_function f start with nvl(f.parentfunctionid,'0')=:parentId
 connect by prior f.id= f.parentfunctionid
 )





