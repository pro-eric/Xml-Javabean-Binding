package com.eric.agile.xml.entitys;

import java.util.List;

import com.eric.agile.xml.entity.annotations.XMLElementList;
import com.eric.agile.xml.entity.annotations.XMLElementsContainer;

@XMLElementsContainer( name = "list" )
public class LoadableClassesContainerEntity implements XMLConvertible {
	private List<LoadableClass> classes;

	@XMLElementList( name = "classes" )
	public void setClasses( List<LoadableClass> classes ) {
		this.classes = classes;
	}

	public List<LoadableClass> getClasses() {
		return classes;
	}
}
