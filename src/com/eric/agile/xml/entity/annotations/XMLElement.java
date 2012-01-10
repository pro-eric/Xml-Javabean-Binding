package com.eric.agile.xml.entity.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * �Զ���ע�⣬���ĳһ��ת��ΪXML��ʽ��ʵ�����е�ĳһ��
 * ��Ҫ��ת����XMLԪ�ص�ĳһ�޲�����GET�������Ի�ȡ��Ӧ��
 * XMLԪ����Ҫ��ֵ
 * 
 * @author Jiasi Sun
 */

@Target( ElementType.METHOD )
@Retention( RetentionPolicy.RUNTIME )
public @interface XMLElement {
	/**
	 * ע���ǵ�GET������õ�ֵ��XML�е�Ԫ������(ELEMENT)
	 * @return ��ע���GET����ֵ��Ӧ��XMLԪ������
	 */
	public String name();
	
	/**
	 * ע���ǵ�GET������õ�ֵ��XML�е�Ԫ������(ELEMENT)
	 * @return ��ע���GET����ֵ��Ӧ��XMLԪ������
	 */
	public String type();
}
