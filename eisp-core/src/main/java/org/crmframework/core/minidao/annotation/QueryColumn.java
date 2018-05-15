package org.crmframework.core.minidao.annotation;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface QueryColumn {
	/**
	 * 查询字段名
	 */
	String column() default "";
	/**
	 * where 
	 */
	String sign() default "";
	/**
	 * 是否是布尔类型
	 * @return
	 */
	boolean type()   default false;
}
