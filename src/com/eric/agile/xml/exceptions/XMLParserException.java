package com.eric.agile.xml.exceptions;

public class XMLParserException extends Exception {
	
	private static final long serialVersionUID = -2223671767180752101L;
	
	public XMLParserException( String msg ) {
		super( msg );
	}
	public XMLParserException( String msg, Throwable t ) {
		super( msg, t );
	}
	public XMLParserException( Throwable t ) {
		super( t );
	}
	
}
