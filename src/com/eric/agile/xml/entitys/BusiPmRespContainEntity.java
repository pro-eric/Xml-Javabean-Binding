package com.eric.agile.xml.entitys;

import java.util.List;

import com.eric.agile.xml.entity.annotations.XMLElement;
import com.eric.agile.xml.entity.annotations.XMLElementList;
import com.eric.agile.xml.entity.annotations.XMLElementsContainer;
import com.eric.agile.xml.entitys.XMLConvertible;

@XMLElementsContainer( name = "busipmcontent" )
public class BusiPmRespContainEntity implements XMLConvertible {
	public String getRequestId() {
		return requestId;
	}
	
	@XMLElement( name = "requestid", type = "String" )
	public void setRequestId( String requestId ) {
		this.requestId = requestId;
	}
	public Integer getBusiPmCount() {
		return busiPmCount;
	}
	
	@XMLElement( name = "busipmcount", type = "Integer" )
	public void setBusiPmCount( Integer busiPmCount ) {
		this.busiPmCount = busiPmCount;
	}
	public String getError() {
		return error;
	}
	
	@XMLElement( name = "error", type = "String" )
	public void setError( String error ) {
		this.error = error;
	}
	public List<BusiPmRespEntity> getPerformances() {
		return performances;
	}
	
	@XMLElementList( name = "performances" )
	public void setPerformances( List<BusiPmRespEntity> performances ) {
		this.performances = performances;
	}
	
	private String requestId;
	private Integer busiPmCount;
	private String error;
	private List<BusiPmRespEntity> performances;
}
