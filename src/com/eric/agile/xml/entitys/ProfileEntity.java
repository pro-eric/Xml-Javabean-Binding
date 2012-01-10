package com.eric.agile.xml.entitys;

import com.eric.agile.xml.entity.annotations.XMLElement;
import com.eric.agile.xml.entity.annotations.XMLElementsContainer;

@XMLElementsContainer( name = "configuration" )
public class ProfileEntity implements XMLConvertible {
	
	public String getTimeout() {
		return timeout;
	}
	
	@XMLElement( name = "soap-timeout", type = "Integer" )
	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}
	public String getTryTimes() {
		return tryTimes;
	}
	
	@XMLElement( name = "try-times", type = "Integer" )
	public void setTryTimes(String tryTimes) {
		this.tryTimes = tryTimes;
	}
	public String getDelay() {
		return delay;
	}
	
	@XMLElement( name = "delay", type = "String" )
	public void setDelay(String delay) {
		this.delay = delay;
	}
	
	public String toString() {
		return "configuration : timeout : " + this.timeout + ", tryTimes : " 
			+ this.tryTimes + ", delay : " + delay;
	}
	
	private String timeout;
	private String tryTimes;
	private String delay;
}
