package com.eric.agile.xml.entitys;

import com.eric.agile.xml.entity.annotations.XMLElement;
import com.eric.agile.xml.entity.annotations.XMLElementsContainer;
import com.eric.agile.xml.entitys.XMLConvertible;

@XMLElementsContainer( name = "performance" )
public class BusiPmRespEntity implements XMLConvertible {
	
	public Integer getBusiPmSeq() {
		return busiPmSeq;
	}
	@XMLElement( name = "busipmseq", type = "Integer" )
	public void setBusiPmSeq( Integer busiPmSeq ) {
		this.busiPmSeq = busiPmSeq;
	}
	
	public Integer getResult() {
		return result;
	}
	
	@XMLElement( name = "result", type = "Integer" )
	public void setResult( Integer result ) {
		this.result = result;
	}
	
	private Integer busiPmSeq;
	private Integer result;
	
}
