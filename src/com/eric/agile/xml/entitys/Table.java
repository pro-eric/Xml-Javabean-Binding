package com.eric.agile.xml.entitys;

import java.util.List;

import com.eric.agile.xml.entity.annotations.XMLElement;
import com.eric.agile.xml.entity.annotations.XMLElementList;
import com.eric.agile.xml.entity.annotations.XMLElementsContainer;

@XMLElementsContainer( name = "table" )
public class Table implements XMLConvertible {
	
	public String getName() {
		return name;
	}
	
	@XMLElement( name = "name", type = "String" )
	public void setName(String name) {
		this.name = name;
	}
	public String getStartTime() {
		return startTime;
	}
	
	@XMLElement( name = "starttime", type = "String" )
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	
	@XMLElement( name = "endtime", type = "String" )
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getInterfaceId() {
		return interfaceId;
	}
	
	@XMLElement( name = "interfaceid", type = "String" )
	public void setInterfaceId(String interfaceId) {
		this.interfaceId = interfaceId;
	}
	public String getGranularity() {
		return granularity;
	}
	
	@XMLElement( name = "granularity", type = "String" )
	public void setGranularity(String granularity) {
		this.granularity = granularity;
	}
	public String getServiceType() {
		return serviceType;
	}
	
	@XMLElement( name = "servicetype", type = "String" )
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	
	public List<Column> getColumns() {
		return columns;
	}
	@XMLElementList( name = "columns" )
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	
	@Override
	public String toString() {
		return "Name : " + name + ", startTime : " + startTime + ", endTime : " +
			endTime + ", interfaceId : " + interfaceId + ", granularity : " + granularity + 
			", serviceType : " + serviceType + ", columns : " + columns.size();
	}
	
	private String name;
	private String startTime;
	private String endTime;
	private String interfaceId;
	private String granularity;
	private String serviceType;
	private List<Column> columns;
}
