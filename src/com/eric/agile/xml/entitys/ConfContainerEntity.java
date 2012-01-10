package com.eric.agile.xml.entitys;

import java.util.List;

import com.eric.agile.xml.entity.annotations.XMLElementList;
import com.eric.agile.xml.entity.annotations.XMLElementsContainer;

@XMLElementsContainer( name = "list" )
public class ConfContainerEntity implements XMLConvertible {
	private List<Table> tables;

	@XMLElementList( name = "tables" )
	public void setTables(List<Table> tables) {
		this.tables = tables;
	}

	public List<Table> getTables() {
		return tables;
	}
}
