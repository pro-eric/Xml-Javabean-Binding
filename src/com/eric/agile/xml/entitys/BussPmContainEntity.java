package com.eric.agile.xml.entitys;

import java.util.List;

import com.eric.agile.xml.entity.annotations.*;

@XMLElementsContainer( name = "busipmcontent" )
public class BussPmContainEntity implements XMLConvertible {
	
	@XMLElement( name = "busipmcount", type = "INTEGER" )
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	@XMLElementList( name = "performances" )
	public List<PerformanceEntity> getPerformances() {
		return performances;
	}
	public void setPerformances(List<PerformanceEntity> performances) {
		this.performances = performances;
	}
	
	
	private int count;
	private List<PerformanceEntity> performances;
}
