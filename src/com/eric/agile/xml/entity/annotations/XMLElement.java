package com.eric.agile.xml.entity.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，标记某一可转换为XML格式的实体类中的某一个
 * 需要被转换成XML元素的某一无参数的GET方法，以获取对应的
 * XML元素需要的值
 * 
 * @author Jiasi Sun
 */

@Target( ElementType.METHOD )
@Retention( RetentionPolicy.RUNTIME )
public @interface XMLElement {
	/**
	 * 注解标记的GET方法获得的值在XML中的元素名称(ELEMENT)
	 * @return 备注解的GET返回值对应的XML元素名称
	 */
	public String name();
	
	/**
	 * 注解标记的GET方法获得的值在XML中的元素类型(ELEMENT)
	 * @return 备注解的GET返回值对应的XML元素类型
	 */
	public String type();
}
