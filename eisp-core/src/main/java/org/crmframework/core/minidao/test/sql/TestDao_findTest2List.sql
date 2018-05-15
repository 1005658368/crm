SELECT
 U.ID AS ID
 FROM
 T_S_BASE_USER U
WHERE
 1=1
<#if vo.id ? exists && vo.id ? length gt 0>
 AND U.ID='${vo.id}'
</#if>
<#if vo.id ? exists && vo.id ? length gt 0>
 AND U.ID=:vo.id
</#if>
<#if vo.id ? exists && vo.id ? length gt 0>
 AND U.ID LIKE '%${vo.id}%'
</#if>
<#if vo.id ? exists && vo.id ? length gt 0>
 AND U.ID LIKE '%'||:vo.id||'%'
</#if>
