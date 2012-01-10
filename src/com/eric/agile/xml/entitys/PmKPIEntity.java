package com.eric.agile.xml.entitys;

public class PmKPIEntity extends KPIEntity {
	
	public String getStartTimeName() {
		return startTimeName;
	}
	public void setStartTimeName(String startTimeName) {
		this.startTimeName = startTimeName;
	}
	public String getEndTimeName() {
		return endTimeName;
	}
	public void setEndTimeName(String endTimeName) {
		this.endTimeName = endTimeName;
	}
	public void setColType(String colType) {
		this.colType = colType;
	}
	public String getColType() {
		return colType;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getDesc() {
		return desc;
	}
	
	@Override
	public String toString() {
		return super.toString() + ", desc : " + desc + ", colType : " + colType 
			+ "startTimeName : " + startTimeName + " endTimeName : " + endTimeName;
	}
	
	private String desc;
	private String colType;
	private String startTimeName;
	private String endTimeName;
}