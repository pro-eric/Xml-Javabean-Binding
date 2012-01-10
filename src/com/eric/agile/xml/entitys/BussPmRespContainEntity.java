package com.eric.agile.xml.entitys;

import java.util.List;

import com.eric.agile.xml.entity.annotations.XMLElement;
import com.eric.agile.xml.entity.annotations.XMLElementList;
import com.eric.agile.xml.entity.annotations.XMLElementsContainer;

@XMLElementsContainer( name = "busipmcontent" )
public class BussPmRespContainEntity implements XMLConvertible {
	public String getRequestId() {
		return requestId;
	}
	
	@XMLElement( name = "requestid", type = "String" )
	public void setRequestId( String requestId ) {
		this.requestId = requestId;
	}
	public String getBusiPmCount() {
		return busiPmCount;
	}
	
	@XMLElement( name = "busipmcount", type = "Integer" )
	public void setBusiPmCount( String busiPmCount ) {
		this.busiPmCount = busiPmCount;
	}
	public String getError() {
		return error;
	}
	
	@XMLElement( name = "error", type = "String" )
	public void setError( String error ) {
		this.error = error;
	}
	public List<PerformanceRespEntity> getPerformances() {
		return performances;
	}
	
	@XMLElementList( name = "performances" )
	public void setPerformances( List<PerformanceRespEntity> performances ) {
		this.performances = performances;
	}
	
	private String requestId;
	private String busiPmCount;
	private String error;
	private List<PerformanceRespEntity> performances;
}
