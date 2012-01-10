package com.eric.agile.xml.entitys;

import com.eric.agile.xml.entity.annotations.XMLElement;
import com.eric.agile.xml.entity.annotations.XMLElementsContainer;

@XMLElementsContainer( name = "performance" )
public class PerformanceRespEntity implements XMLConvertible {
	
	public String getBusiPmSeq() {
		return busiPmSeq;
	}
	@XMLElement( name = "busipmseq", type = "String" )
	public void setBusiPmSeq( String busiPmSeq ) {
		this.busiPmSeq = busiPmSeq;
	}
	
	public String getResult() {
		return result;
	}
	
	@XMLElement( name = "result", type = "String" )
	public void setResult( String result ) {
		this.result = result;
	}
	
	private String busiPmSeq;
	private String result;
	
}
