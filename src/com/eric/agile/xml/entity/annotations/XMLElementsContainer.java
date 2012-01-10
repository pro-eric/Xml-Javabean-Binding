package com.eric.agile.xml.entity.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，标记某一可转换为XML格式的实体类中的元素都包含于的上级元素
 * 
 * @author Jiasi Sun
 */

@Target( ElementType.TYPE )
@Retention( RetentionPolicy.RUNTIME )
public @interface XMLElementsContainer {
	public String name();
}
