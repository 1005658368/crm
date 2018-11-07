package org.crmframework.core.util;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

public class BeanUtil extends BeanUtils{

    /**
     * 对象之间复制属性值
     * @param srcBean 源对象
     * @param dstBean 目标对象
     * @param revertMap 属性转换map,key为源对象的属性,value为目标对象的属性
     * @param props 源对象指定复制的属性
     * @param ignoredProps 源对象忽略的属性
     * @param copyNull 是否复制null值
     * @param copyEmptyStr 是否复制""字符串不支持" "等
     * @throws Exception
     */
    public static void copy(Object srcBean,Object dstBean,Map<String, String> revertMap,Collection<String> props,Collection<String> ignoredProps,boolean copyNull,boolean copyEmptyStr) throws Exception{
        BeanWrapper src = new BeanWrapperImpl(srcBean);
        PropertyDescriptor[] srcpds = src.getPropertyDescriptors();
        String srcProName=null;
        String descPropName=null;
        for (PropertyDescriptor spd : srcpds) {
            srcProName=spd.getName();
            Object srcValue = src.getPropertyValue(srcProName);
            // 过滤掉不在指定属性列表中的属性
            if (props != null && !props.contains(srcProName)) {
                continue;
            }
            // 过滤掉在忽略属性列表中的属性
            if (ignoredProps != null && ignoredProps.contains(srcProName)) {
                continue;
            }
            if (revertMap != null) {
                descPropName = revertMap.get(srcProName);
                if (descPropName == null) {
                    descPropName = srcProName;
                }
            } else {
                descPropName = srcProName;
            }
//            Method getMethod = spd.getReadMethod();// 获得get方法
//            if (getMethod == null) {
//                continue;
//            }
            Object value = src.getPropertyValue(srcProName);
            if (value == null && !copyNull) {
                continue;
            }
            // 只处理"" 不处理 " "
            if (value != null && value.toString().length() == 0 && !copyEmptyStr) {
                continue;
            }
            //目标对象没有该属性的set方法则跳过
            PropertyDescriptor dpd=null;
            try {
                dpd = new PropertyDescriptor(descPropName,dstBean.getClass());
            }catch(Exception e){
                continue;
            }
            if(null==dpd){
                continue;
            }

            Method setMethod=dpd.getWriteMethod();
            if (setMethod == null) {
                continue;
            }
            copyProperty(dstBean,srcProName,value);
        }
    }

    public static void copy(Object srcBean,Object dstBean,boolean copyNull) throws Exception{
        BeanUtil.copy(srcBean,dstBean,null,null,null,copyNull,false);
    }

    public static void copy(Object srcBean,Object dstBean) throws Exception{
        BeanUtil.copy(srcBean,dstBean,null,null,null,true,true);
    }

    public static void apply(Object srcBean,Object dstBean) throws Exception{
        BeanUtil.copy(srcBean,dstBean,null,null,null,false,false);
    }

}
