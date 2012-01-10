package com.eric.agile.xml.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Branch;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.eric.agile.xml.entity.annotations.XMLElement;
import com.eric.agile.xml.entity.annotations.XMLElementList;
import com.eric.agile.xml.entity.annotations.XMLElementsContainer;
import com.eric.agile.xml.entitys.XMLConvertible;
import com.eric.agile.xml.exceptions.XMLManufacturerException;

public class XMLManufacturer {
	private static Map<Class<?>, Method[]> mapping = new ConcurrentHashMap<Class<?>, Method[]>();
	
	public static Document genXmlDocument( XMLConvertible entity, String encoding ) throws XMLManufacturerException {
		Document doc = DocumentHelper.createDocument();
		doc.setXMLEncoding( encoding );
		build( doc, entity );
		return doc;
	}
	
	public static Branch build( Branch branch, XMLConvertible entity ) throws XMLManufacturerException {
		Class<?> cl = entity.getClass();
		XMLElementsContainer xc = cl.getAnnotation( XMLElementsContainer.class );
		Branch currentBranch = null;
		if( xc != null ) {
			currentBranch = branch.addElement( xc.name() ); // create current root element & add to parent element.
			Method[] methods = mapping.get( cl ); // try to obtain methods array of class 'cl'.
			if( methods == null ) { // failed.
				methods = cl.getDeclaredMethods();
				mapping.put( cl, methods ); // put class-methods mapping into concurrent hashmap.
			}
			XMLElement xe = null;
			XMLElementList ltag = null;
			try {
				for( Method m : methods ) {
					// obtain all signal field which marked by 'XMLElement' of this class.
					xe = m.getAnnotation( XMLElement.class );
					if( xe != null ) {
						String text = m.invoke( entity ) == null ? "" : m.invoke( entity ).toString();
						currentBranch.addElement( xe.name() )
							.addText( text ); 
							// create corresponding element & fill it by the result of invoking get method by using entity object 'entity'. 
					}
					// obtain list field whick marked by 'XMLElementList'.
					ltag = m.getAnnotation( XMLElementList.class );
					if( ltag != null ) {
						Element e = currentBranch.addElement( ltag.name() ); // create parent element of list' elements.
						Object rlt = m.invoke( entity );
						List<?> list = null;
						if( rlt instanceof List<?> ) {
							list = (List<?>)rlt;
							for( Object obj : list ) {
								if( obj instanceof XMLConvertible ) {
									build( e, (XMLConvertible)obj ); // recursive call to build method.
								}
							}
						}
					}
				}
			} catch ( IllegalArgumentException e ) {
				throw new XMLManufacturerException( e );
			} catch (IllegalAccessException e) {
				throw new XMLManufacturerException( e );
			} catch (InvocationTargetException e) {
				throw new XMLManufacturerException( e );
			} // catch all exception & then convert to 'XMLManufacturerException'.
		}
		return branch;
	}

	
	
}
