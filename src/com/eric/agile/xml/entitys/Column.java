package com.eric.agile.xml.entitys;

import com.eric.agile.xml.entity.annotations.XMLElement;
import com.eric.agile.xml.entity.annotations.XMLElementsContainer;

@XMLElementsContainer( name = "column" )
public class Column implements XMLConvertible {
	private String name;
	private String kpiid;
	private String kpiname;
	private String dn;
	private String type;
	private String desc;
	private String dnchinese;
	
	public String getName() {
		return name;
	}
	
	@XMLElement( name = "name", type = "String" )
	public void setName(String name) {
		this.name = name;
	}
	public String getKpiid() {
		return kpiid;
	}
	
	@XMLElement( name = "kpiid", type = "String" )
	public void setKpiid(String kpiid) {
		this.kpiid = kpiid;
	}
	public String getDn() {
		return dn;
	}
	
	@XMLElement( name = "dn", type = "String" )
	public void setDn(String dn) {
		this.dn = dn;
	}
	
	@Override
	public String toString() {
		return "Name : " + name + ", kpiid : " + kpiid + ", kpiname : " + kpiname + ", dn : " + dn + ", dnchinese : " + dnchinese + ", type : " + type;
	}

	@XMLElement( name = "type", type = "String" )
	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	@XMLElement( name = "desc", type = "String" )
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	@XMLElement( name = "kpiname", type = "String" )
	public void setKpiname(String kpiname) {
		this.kpiname = kpiname;
	}
	
	public String getKpiname() {
		return kpiname;
	}

	@XMLElement( name = "dnchinese", type = "String" )
	public void setDnchinese(String dnchinese) {
		this.dnchinese = dnchinese;
	}

	public String getDnchinese() {
		return dnchinese;
	}
	
}
