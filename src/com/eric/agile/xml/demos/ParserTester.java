package com.eric.agile.xml.demos;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ParserTester {

	public static void main( String[] args ) {
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read( new File( "files/BP_KPI_3017_0003-20110401000001-364-001.xml" ) );
			Element e = doc.getRootElement();
			System.out.println( "Root node name : " + e.getName() );
			Iterator<?> iter = e.elementIterator();
			Object tmp = null;
			Element ie = null; //'ie' stand for iterator element.
			while( iter.hasNext() ) {
				tmp = iter.next();
				if( tmp instanceof Element ) {
					ie = (Element)tmp;
					System.out.println( "Iter node name : " + ie.getName() );
				}
				else
					System.out.println( "Not instance of Element." );
			}
			
			List<?> list = doc.selectNodes( "/busipmcontent/performances/performance/busipmdesc" );
			for( Object obj : list ) {
				if( obj instanceof Element ) {
					ie = (Element)obj;
					System.out.println( "Value : " + ie.getTextTrim() );
					System.out.println( "Child node count : " + ie.nodeCount() );
				}
			}
		} catch( DocumentException e ) {
			e.printStackTrace();
		}
	}

}
