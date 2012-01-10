package com.eric.agile.xml.demos;

import java.util.LinkedList;

import org.dom4j.Document;

import com.eric.agile.xml.entitys.BussPmContainEntity;
import com.eric.agile.xml.entitys.PerformanceEntity;
import com.eric.agile.xml.exceptions.XMLManufacturerException;
import com.eric.agile.xml.util.XMLManufacturer;

public class Tester {
	public static void main(String[] args) {
		LinkedList<PerformanceEntity> entitys = new LinkedList<PerformanceEntity>();
		PerformanceEntity p1 = new PerformanceEntity( 1, "BP-3017-0010-0001", "√Ë ˆ1", "WXT.FEE1", "20110331000000", "20110401000000", "1" );
		PerformanceEntity p2 = new PerformanceEntity( 2, "BP-3017-0010-0002", "√Ë ˆ2", "WXT.FEE2", "20110331000000", "20110401000000", "2" );
//		for( int i = 0; i < 100000; i++ ) {
//			entitys.add( p1 );
//		}
		entitys.add( p1 );
		entitys.add( p2 );
		BussPmContainEntity bpc = new BussPmContainEntity();
		bpc.setCount( entitys.size() );
		bpc.setPerformances( entitys );
		
		//XMLManufacturer xm = new XMLManufacturer();
		try {
			Document doc = XMLManufacturer.genXmlDocument( bpc, "GBK" );
			System.out.println( doc.asXML() );
		} catch (XMLManufacturerException e) {
			e.printStackTrace();
		}
		
	}

}
