package com.eric.agile.xml.entity.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * �Զ���ע�⣬���ĳһ��ת��ΪXML��ʽ��ʵ�����е�Ԫ�ض������ڵ��ϼ�Ԫ��
 * 
 * @author Jiasi Sun
 */

@Target( ElementType.TYPE )
@Retention( RetentionPolicy.RUNTIME )
public @interface XMLElementsContainer {
	public String name();
}
