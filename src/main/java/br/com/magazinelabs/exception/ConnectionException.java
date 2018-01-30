package br.com.magazinelabs.exception;

/**
 * 
 * @author JUNNIOR
 *
 */
public class ConnectionException extends Exception{

private static final long serialVersionUID = 2204865349861989261L;
	
	public ConnectionException(Throwable ex) {
		super("Erro ao realizar conexão com o banco!", ex);
	}
	
	public ConnectionException(String message) {
		super(message);
	}
	
	public ConnectionException(String message, Throwable ex) {
		super(message, ex);
	}
}