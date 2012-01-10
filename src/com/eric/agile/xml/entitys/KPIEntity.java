package com.eric.agile.xml.entitys;

public class KPIEntity {
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInterfaceId() {
		return interfaceId;
	}
	public void setInterfaceId(String interfaceId) {
		this.interfaceId = interfaceId;
	}
	public String getKpiId() {
		return kpiId;
	}
	public void setKpiId(String kpiId) {
		this.kpiId = kpiId;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getColName() {
		return colName;
	}
	public void setColName(String colName) {
		this.colName = colName;
	}
	public int getGranularity() {
		return granularity;
	}
	public void setGranularity(int granularity) {
		this.granularity = granularity;
	}
	public String getDn() {
		return dn;
	}
	public void setDn(String dn) {
		this.dn = dn;
	}
	
	public String toString() {
		return "id : " + id + ", interfaceId : " + interfaceId + ", kpiId : " 
			+ kpiId + ", tableName : " + tableName + ", colName : " + colName
			+ ", granularity : " + granularity + ", dn : " + dn;
	}
	
	private int id;
	private String interfaceId;
	private String kpiId;
	private String tableName;
	private String colName;
	private int granularity;
	private String dn;
}
