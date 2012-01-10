package com.eric.agile.xml.entitys;

import com.eric.agile.xml.entity.annotations.XMLElement;
import com.eric.agile.xml.entity.annotations.XMLElementsContainer;

@XMLElementsContainer( name = "performance" )
public class PerformanceEntity implements XMLConvertible {
	/**
	 * Just For Test
	 */
	public PerformanceEntity( int seq, String kpiid, String desc, String dn, String begintime, String endtime, String value ) {
		this.seq = seq;
		this.kpiid = kpiid;
		this.desc = desc;
		this.dn = dn;
		this.begintime = begintime;
		this.endtime = endtime;
		this.value = value;
	}
	
	@XMLElement( name = "busipmseq", type = "INTEGER" )
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	@XMLElement( name = "busipmkpiid", type = "STRING" )
	public String getKpiid() {
		return kpiid;
	}
	public void setKpiid(String kpiid) {
		this.kpiid = kpiid;
	}
	
	@XMLElement( name = "busipmdesc", type = "STRING" )
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@XMLElement( name = "dn", type = "STRING" )
	public String getDn() {
		return dn;
	}
	public void setDn(String dn) {
		this.dn = dn;
	}
	
	@XMLElement( name = "begintime", type = "STRING" )
	public String getBegintime() {
		return begintime;
	}
	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}
	
	@XMLElement( name = "endtime", type = "STRING" )
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
	@XMLElement( name = "value", type = "STRING" )
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	private int seq;
	private String kpiid;
	private String desc;
	private String dn;
	private String begintime;
	private String endtime;
	private String value;
}
