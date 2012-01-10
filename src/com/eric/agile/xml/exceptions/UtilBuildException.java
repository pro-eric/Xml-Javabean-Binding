package com.eric.agile.xml.exceptions;

public class UtilBuildException extends Exception {
	
	private static final long serialVersionUID = 4361014918858674221L;

	public UtilBuildException( String msg ) {
		super( msg );
	}
	public UtilBuildException( String msg, Throwable t ) {
		super( msg, t );
	}
	public UtilBuildException( Throwable t ) {
		super( t );
	}
	
}
