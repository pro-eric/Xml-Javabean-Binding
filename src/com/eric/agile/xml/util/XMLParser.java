package com.eric.agile.xml.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.eric.agile.xml.entity.annotations.XMLElement;
import com.eric.agile.xml.entity.annotations.XMLElementList;
import com.eric.agile.xml.entity.annotations.XMLElementsContainer;
import com.eric.agile.xml.entitys.BusiPmRespContainEntity;
import com.eric.agile.xml.entitys.BusiPmRespEntity;
import com.eric.agile.xml.entitys.LoadableClass;
import com.eric.agile.xml.entitys.LoadableClassesContainerEntity;
import com.eric.agile.xml.entitys.XMLConvertible;
import com.eric.agile.xml.exceptions.UtilBuildException;
import com.eric.agile.xml.exceptions.XMLParserException;

/**
 * @see com.eric.agile.xml.entity.annotations.XMLElement
 * @see com.eric.agile.xml.entity.annotations.XMLElementList
 * @see com.eric.agile.xml.entity.annotations.XMLElementsContainer
 * 
 * @version 1.0
 * @author Jiasi Sun
 * @since 1.0
 */

public class XMLParser {
	
	private static Map<Class<? extends XMLConvertible>, Method[]> cms = loadClassMethodsMapping();
//	private static Logger logger = Logger.getLogger( "XMLParser" );
	
	private Map<Class<? extends XMLConvertible>, Method[]> clazMethodsMapping;
		
	public XMLParser( File file ) throws UtilBuildException {
		if( !file.exists() )
			throw new UtilBuildException( new FileNotFoundException() );
		try {
			clazMethodsMapping = loadClassMethodsMapping( file );
		} catch( DocumentException e ) {
			throw new UtilBuildException( e.getMessage(), e );
		} catch( XMLParserException e ) {
			throw new UtilBuildException( e.getMessage(), e );
		} catch( ClassNotFoundException e ) {
			throw new UtilBuildException( e.getMessage(), e );
		}
	}
	
	public static void main( String[] args ) {
		try {
			System.out.println( "start." );
			SAXReader reader = new SAXReader();
			String rlt = "<?xml version=\"1.0\" encoding=\"gb2312\" ?>" + 
			"<busipmcontent>" +
				"<requestid>BP_KPI_1027_00012011-11-01 14:00:00005</requestid>" +
				"<busipmcount>6</busipmcount>" + 
				"<error>requestId格式不正确</error>" +
				"<performances>" +
					"<performance>" + 
						"<busipmseq>1</busipmseq>" +
						"<result>0</result>" +
					"</performance>" +
				"</performances>" +
			"</busipmcontent>";
			BusiPmRespContainEntity entity = ( BusiPmRespContainEntity )parseXml( reader.read( new StringReader( rlt ) ) );
			System.out.println( entity.getRequestId() );
			System.out.println( entity.getError() );
			System.out.println( entity.getBusiPmCount() );
			for( BusiPmRespEntity e : entity.getPerformances() ) {
				System.out.println( e.getBusiPmSeq() );
				System.out.println( e.getResult() );
			}
		} catch (XMLParserException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Parse a document of xml
	 * Build & fill an Entity which implements interface <code>XMLConvertible</code> base on result of parsing a xml <code>Document</code>
	 * 
	 * @param doc <code>Docuemnt</code> load from xml source by using dom4j
	 * @return An entity implements XMLConvertible
	 * @throws XMLParserException
	 * 
	 * @see org.dom4j.Document
	 * @see com.eric.agile.xml.exceptions.XMLParserException
	 * @see com.eric.agile.xml.entitys.XMLConvertible
	 */
	public static XMLConvertible parseXml( Document doc ) throws XMLParserException {
		Set<Class<? extends XMLConvertible>> keys = cms.keySet(); // obtain class set which contains entitys extends XMLConvertible.
		Element rootElement = doc.getRootElement();
		XMLConvertible rlt = null;
		Class<?> clazz = null;
		if( ( clazz = getEqualsClass( rootElement, keys ) ) != null ) { // root element's corresponding class has found in cms mapping key set.
			rlt = parse( rootElement, clazz );
		} else {
			throw new XMLParserException( "no corresponding class had defined" ); // no corresponding class had defined.
		}
		return rlt;
	}
	
	private static XMLConvertible parse( Element element, Class<?> cl ) throws XMLParserException {
		try {
			Method[] methods = cms.get( cl );
			XMLConvertible instance = (XMLConvertible) cl.newInstance(); // create a new entity by reflection.
			Object tmpElement = null; // temporary sub element of parameter 'element'.
			Object tmpSubElement = null; // temporary sub element of temporary sub element.
			Element b = null; // temporary reference of element.
			for( Method m : methods ) {
				Iterator<?> iter = element.nodeIterator();
				while( iter.hasNext() ) { // iterate all children.
					tmpElement = iter.next();
					XMLElement xe = m.getAnnotation( XMLElement.class ); // obtain one element an reflect invoke method to set.
					if( xe != null ) {
						if( tmpElement instanceof Element && xe.name().equals( ( b = (Element)tmpElement ).getName() ) ) {
							Class<?>[] params = m.getParameterTypes(); 
							if( params.length == 1 ) { // bean's 'set' method just have one parameter.
								//System.out.println( "method : " + m + ", param type : " + params[ 0 ] );
								//System.out.println( "Method : " + m );
								//System.out.println( xe.type() );
								if( "Double".equals( xe.type() ) ) {
									m.invoke( instance, Double.parseDouble( b.getData().toString() ) );
								} else if( "Integer".equals( xe.type() ) ) {
									m.invoke( instance, Integer.parseInt( b.getData().toString() ) );
								} else if( "String".equals( xe.type() ) ) {
									m.invoke( instance, b.getData().toString() );
								} else {
									m.invoke( instance, b.getData().toString() );
								}
								
							}
						}
					}
					
					XMLElementList xel = m.getAnnotation( XMLElementList.class ); // obtain list of elements and reflect invoke method to set list.
					if( xel != null ) {
						if( tmpElement instanceof Element && xel.name().equals( ( b = (Element)tmpElement ).getName() ) ) {
							Iterator<?> subIter = b.nodeIterator(); // iterate all children
							List<XMLConvertible> list = new LinkedList<XMLConvertible>();
							//System.out.println( "list set method : " + m.getParameterTypes().length );
							m.invoke( instance, list ); // set list
							while( subIter.hasNext() ) {
								tmpSubElement = subIter.next();
								if( tmpSubElement instanceof Element ) {
									Class<? extends XMLConvertible> clazz = null;
									if( ( clazz = getEqualsClass( ( Element )tmpSubElement, cms.keySet() ) ) != null )
										list.add( parse( ( Element )tmpSubElement, clazz ) ); //recusion invoke parse it's children elements.
								}
							}
						}
					}
				}
			}
			return instance;
		} catch( IllegalArgumentException e ) {
			throw new XMLParserException( e.getMessage(), e );
		} catch( InstantiationException e ) {
			throw new XMLParserException( e.getMessage(), e );
		} catch( IllegalAccessException e ) {
			throw new XMLParserException( e.getMessage(), e );
		} catch( InvocationTargetException e ) {
			throw new XMLParserException( e.getMessage(), e );
		}
	}
	
	/**
	 * Compare Element's name with each class's XMLElementsContainer annotation
	 * 
	 * @param e <code>Element</code> 
	 * @param set a set of entity which extends <code>XMLConverible</code>
	 * @return <code>Class<? extends XMLConvertible></code>
	 * 
	 */
	private static Class<? extends XMLConvertible> getEqualsClass( Element e, Set<Class<? extends XMLConvertible>> set ) {
		for( Class<? extends XMLConvertible> clazz : set ) {
			XMLElementsContainer ec = clazz.getAnnotation( XMLElementsContainer.class );
			//System.out.println( "ec : " + ec + ", e : " + e );
			if( ec.name().equals( e.getName() ) ) {
				return clazz;
			}
		}
		return null;
	}
	
	public Map<Class<? extends XMLConvertible>, Method[]> loadClassMethodsMapping( File file ) throws DocumentException, XMLParserException, ClassNotFoundException {
		Map<Class<? extends XMLConvertible>, Method[]> mapping = new ConcurrentHashMap<Class<? extends XMLConvertible>, Method[]>();
		cms = mapping;
		try {
			mapping.put( LoadableClassesContainerEntity.class, LoadableClassesContainerEntity.class.getDeclaredMethods() );
			mapping.put( LoadableClass.class, LoadableClass.class.getDeclaredMethods() );
			
			SAXReader reader = new SAXReader();
			Document doc = reader.read( file );
			LoadableClassesContainerEntity entity = (LoadableClassesContainerEntity) XMLParser.parseXml( doc );
			
			for( LoadableClass lc : entity.getClasses() ) {
				Class<? extends XMLConvertible> clazz = lc.getLoadableClass();
				mapping.put( clazz, clazz.getDeclaredMethods() );
			}
		} catch( SecurityException e ) {
			throw e;
		} catch( DocumentException e ) {
			throw e;
		} catch( XMLParserException e ) {
			throw e;
		} catch( ClassNotFoundException e ) {
			throw e;
		}
		return mapping;
	}
	
	public static Map<Class<? extends XMLConvertible>, Method[]> loadClassMethodsMapping() {
		Map<Class<? extends XMLConvertible>, Method[]> mapping = new ConcurrentHashMap<Class<? extends XMLConvertible>, Method[]>();
		cms = mapping;
		try {
			mapping.put( LoadableClassesContainerEntity.class, LoadableClassesContainerEntity.class.getDeclaredMethods() );
			mapping.put( LoadableClass.class, LoadableClass.class.getDeclaredMethods() );
			
			SAXReader reader = new SAXReader();
			Document doc = reader.read( new File( "files/class-mapping-config.xml" ) );
			LoadableClassesContainerEntity entity = (LoadableClassesContainerEntity) XMLParser.parseXml( doc );
			
			for( LoadableClass lc : entity.getClasses() ) {
				Class<? extends XMLConvertible> clazz = lc.getLoadableClass();
				mapping.put( clazz, clazz.getDeclaredMethods() );
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (XMLParserException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return mapping;
	}
}
