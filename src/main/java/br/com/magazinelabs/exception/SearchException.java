package br.com.magazinelabs.exception;

/**
 * 
 * @author JUNNIOR
 *
 */
public class SearchException extends Exception{
	
	private static final long serialVersionUID = -6668616444203817593L;
	
	public SearchException(String message) {
		super(message);
	}
	
	public SearchException(String message, Throwable ex) {
		super(message, ex);
	}
}