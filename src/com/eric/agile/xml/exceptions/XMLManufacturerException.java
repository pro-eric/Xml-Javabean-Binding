package com.eric.agile.xml.exceptions;

public class XMLManufacturerException extends Exception {
	private static final long serialVersionUID = 3904246254037421916L;
	public XMLManufacturerException( String msg ) {
		super( msg );
	}
	public XMLManufacturerException( String msg, Throwable t ) {
		super( msg, t );
	}
	public XMLManufacturerException( Throwable t ) {
		super( t );
	}
}
