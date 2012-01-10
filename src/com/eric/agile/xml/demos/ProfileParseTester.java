package com.eric.agile.xml.demos;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import com.eric.agile.xml.entitys.ProfileEntity;
import com.eric.agile.xml.exceptions.XMLParserException;
import com.eric.agile.xml.util.XMLParser;

public class ProfileParseTester {
	public static void main( String[] args ) {
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read( new File( "files/config.xml" ) );
			ProfileEntity entity = (ProfileEntity)XMLParser.parseXml( doc );
			System.out.println( entity );
		} catch( DocumentException e ) {
			e.printStackTrace();
		} catch( XMLParserException e ) {
			e.printStackTrace();
		}
	}

}
