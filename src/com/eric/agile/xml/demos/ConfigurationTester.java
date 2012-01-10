package com.eric.agile.xml.demos;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import com.eric.agile.xml.entitys.Column;
import com.eric.agile.xml.entitys.ConfContainerEntity;
import com.eric.agile.xml.entitys.PmKPIEntity;
import com.eric.agile.xml.entitys.Table;
import com.eric.agile.xml.exceptions.XMLParserException;
import com.eric.agile.xml.util.XMLParser;

public class ConfigurationTester {

	public static void main( String[] args ) {
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read( new File( "files/fabrications.xml" ) );
			ConfContainerEntity entity = (ConfContainerEntity) XMLParser.parseXml( doc );
			Map<String, PmKPIEntity> mapping = new HashMap<String, PmKPIEntity>();
			System.out.println( "Entity : " + entity );
			for( Table table : entity.getTables() ) {
				System.out.println( table );
				for( Column col : table.getColumns() ) {
					PmKPIEntity e = new PmKPIEntity();
					e.setInterfaceId( table.getInterfaceId() );
					e.setKpiId( col.getKpiid() );
					e.setTableName( table.getName() );
					e.setColName( col.getName() );
					e.setColType( col.getType() );
					e.setGranularity( Integer.parseInt( table.getGranularity() ) );
					e.setDn( col.getDn() );
					e.setStartTimeName( table.getStartTime() );
					e.setEndTimeName( table.getEndTime() );
					mapping.put( e.getKpiId(), e );
					System.out.println( col );
				}
			}
			for( String key : mapping.keySet() ) {
				System.out.println( key + "[" + mapping.get( key ) + "]" );
			}
		} catch( DocumentException e ) {
			e.printStackTrace();
		} catch( XMLParserException e ) {
			e.printStackTrace();
		}
	}
}
