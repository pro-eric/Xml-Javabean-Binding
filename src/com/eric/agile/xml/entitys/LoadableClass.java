package com.eric.agile.xml.entitys;

import com.eric.agile.xml.entity.annotations.XMLElement;
import com.eric.agile.xml.entity.annotations.XMLElementsContainer;

@XMLElementsContainer( name = "class" )
public class LoadableClass implements XMLConvertible {
	private String name;

	@XMLElement( name = "name", type = "String" )
	public void setClazz( String name ) {
		this.name = name;
	}

	public String getClazz() {
		return name;
	}
	
	public Class<? extends XMLConvertible> getLoadableClass() throws ClassNotFoundException {
		Class<?> clazz = Class.forName( name );
		return clazz.asSubclass( XMLConvertible.class );
	}
}
