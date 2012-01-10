package com.eric.agile.xml.util;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.dom4j.Branch;
import org.dom4j.Document;
import org.dom4j.DocumentException;
//import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

//import com.eric.agile.entity.entitys.BussPmContainEntity;
//import com.eric.agile.entity.entitys.PerformanceEntity;
import com.eric.agile.xml.entity.annotations.XMLElement;
import com.eric.agile.xml.entity.annotations.XMLElementList;
import com.eric.agile.xml.entity.annotations.XMLElementsContainer;
import com.eric.agile.xml.entitys.BussPmRespContainEntity;
import com.eric.agile.xml.entitys.PerformanceRespEntity;
import com.eric.agile.xml.entitys.XMLConvertible;
import com.eric.agile.xml.exceptions.XMLParserException;

public class XMLGenerator {

	public static void main( String[] args ) {
		try {
			SAXReader reader = new SAXReader();
			Document rltDoc = reader.read( new File( "files/rlt.xml" ) );
			parseXml( rltDoc );
		} catch( DocumentException e ) {
			e.printStackTrace();
		}
	}
	
	public static void build( Branch branch, XMLConvertible entity, Class<?> cl, Method[] methods ) {
		XMLElementsContainer xc = cl.getAnnotation( XMLElementsContainer.class );
		Branch currentBranch = null;
		if( xc != null ) {
			currentBranch = branch.addElement( xc.name() );
			for( Method m : methods ) {
				XMLElement xe = m.getAnnotation( XMLElement.class );
				if( xe != null ) {
					try {
						currentBranch.addElement( xe.name() ).addText( m.invoke( entity ).toString() );
					} catch( IllegalArgumentException e ) {
						e.printStackTrace();
					} catch( IllegalAccessException e ) {
						e.printStackTrace();
					} catch( InvocationTargetException e ) {
						e.printStackTrace();
					}
				}
				XMLElementList ltag = m.getAnnotation( XMLElementList.class );
				if( ltag != null ) {
					try {
						Element e = currentBranch.addElement( ltag.name() );
						Object rlt = m.invoke( entity );
						List<?> list = null;
						Method[] ms = null;
						Class<?> clazz = null;
						Class<?> tmp = null;
						if( rlt instanceof List<?> ) {
							list = (List<?>)rlt;
							for( Object obj : list ) {
								if( obj instanceof XMLConvertible ) {
									tmp = obj.getClass();
									if( ms == null || !clazz.equals( tmp ) ) {
										clazz = tmp;
										ms = clazz.getDeclaredMethods();
									}
									build( e, (XMLConvertible)obj, obj.getClass(), ms );
								}
							}
						}
					} catch( IllegalArgumentException e ) {
						e.printStackTrace();
					} catch( IllegalAccessException e ) {
						e.printStackTrace();
					} catch( InvocationTargetException e ) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public static void build( Branch branch, XMLConvertible entity, Class<?> cl ) {
		XMLElementsContainer xc = cl.getAnnotation( XMLElementsContainer.class );
		Branch currentBranch = null;
		if( xc != null ) {
			currentBranch = branch.addElement( xc.name() );
			for( Method m : cl.getDeclaredMethods() ) {
				XMLElement xe = m.getAnnotation( XMLElement.class );
				if( xe != null ) {
					try {
						currentBranch.addElement( xe.name() ).addText( m.invoke( entity ).toString() );
					} catch( IllegalArgumentException e ) {
						e.printStackTrace();
					} catch( IllegalAccessException e ) {
						e.printStackTrace();
					} catch( InvocationTargetException e ) {
						e.printStackTrace();
					}
				}
				XMLElementList ltag = m.getAnnotation( XMLElementList.class );
				if( ltag != null ) {
					try {
						Element e = currentBranch.addElement( ltag.name() );
						Object rlt = m.invoke( entity );
						List<?> list = null;
						if( rlt instanceof List<?> ) {
							list = (List<?>)rlt;
							for( Object obj : list ) {
								if( obj instanceof XMLConvertible ) {
									build( e, (XMLConvertible)obj, obj.getClass() );
								}
							}
						}
					} catch( IllegalArgumentException e ) {
						e.printStackTrace();
					} catch( IllegalAccessException e ) {
						e.printStackTrace();
					} catch( InvocationTargetException e ) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static void parseXml( Document doc ) {
		HashMap<Class<?>, Method[]> cm = loadClassMethodsMapping();
		Set<Class<?>> keys = cm.keySet();
		Element rootElement = doc.getRootElement();
		String rootName = rootElement.getName();
		XMLConvertible rlt = null;
		try {
			for( Class<?> clazz : keys ) {
				XMLElementsContainer ec = clazz.getAnnotation( XMLElementsContainer.class );
				if( rootName.equals( ec.name() ) ) {
					rlt = parse( rootElement, clazz, cm );
				} else {
					continue;
				}
			}
		} catch( XMLParserException e ) {
			e.printStackTrace();
		}
		
		if( rlt != null ) {
			BussPmRespContainEntity entity = (BussPmRespContainEntity)rlt;
			System.out.println( "Count : " + entity.getBusiPmCount() + ", Result : " + entity.getError() );
			for( PerformanceRespEntity en : entity.getPerformances() )
				System.out.println( en.getBusiPmSeq() + " : " + en.getResult() );
		}
	}
	
	public static XMLConvertible parse( Element branch, Class<?> cl, HashMap<Class<?>, Method[]> cm ) throws XMLParserException {
		try {
			Method[] methods = cm.get( cl );
			XMLConvertible instance = (XMLConvertible) cl.newInstance();
			Object obj = null;
			Object subObj = null;
			Element b = null;
			for( Method m : methods ) {
				Iterator<?> iter = branch.nodeIterator();
				while( iter.hasNext() ) {
					obj = iter.next();
					XMLElement xe = m.getAnnotation( XMLElement.class );
					if( xe != null ) {
						if( obj instanceof Element && xe.name().equals( ( b = (Element)obj ).getName() ) ) {
							Class<?>[] params = m.getParameterTypes();
							if( params.length == 1 ) {
								m.invoke( instance, b.getData() );
							}
						}
					}
					
					XMLElementList xel = m.getAnnotation( XMLElementList.class );
					if( xel != null ) {
						if( obj instanceof Element && xel.name().equals( ( b = (Element)obj ).getName() ) ) {
							Iterator<?> subIter = b.nodeIterator();
							List<XMLConvertible> list = new LinkedList<XMLConvertible>();
							m.invoke( instance, list );
							while( subIter.hasNext() ) {
								subObj = subIter.next();
								if( subObj instanceof Element ) {
									Class<?> clazz = null;
									if( ( clazz = getEqualsClass( ( Element )subObj, cm.keySet() ) ) != null )
										list.add( parse( ( Element )subObj, clazz, cm ) );
								}
							}
						}
					}
				}
			}
			return instance;
		} catch( IllegalArgumentException e ) {
			throw new XMLParserException( e );
		} catch( InstantiationException e ) {
			throw new XMLParserException( e );
		} catch( IllegalAccessException e ) {
			throw new XMLParserException( e );
		} catch( InvocationTargetException e ) {
			throw new XMLParserException( e );
		}
	}
	
	public static Class<?> getEqualsClass( Element e, Set<Class<?>> set ) {
		for( Class<?> clazz : set ) {
			XMLElementsContainer ec = clazz.getAnnotation( XMLElementsContainer.class );
			if( ec.name().equals( e.getName() ) ) {
				return clazz;
			}
		}
		return null;
	}
	
	public static HashMap<Class<?>, Method[]> loadClassMethodsMapping() {
		HashMap<Class<?>, Method[]> mapping = new HashMap<Class<?>, Method[]>();
		mapping.put( PerformanceRespEntity.class, PerformanceRespEntity.class.getDeclaredMethods() );
		mapping.put( BussPmRespContainEntity.class, BussPmRespContainEntity.class.getDeclaredMethods() );
		return mapping;
	}
	
}
