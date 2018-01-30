package br.com.magazinelabs.exception;

/**
 * <p> Classe responsável em tratar as exceções de Resource
 * @author Junnior
 *
 */
public class ResourceException extends Exception{
	
	private static final long serialVersionUID = 2204865349861989261L;
	
	public ResourceException(Throwable ex) {
		super("Erro ao recuperar o arquivo resource!", ex);
	}
	
	public ResourceException(String message) {
		super(message);
	}
	
	public ResourceException(String message, Throwable ex) {
		super(message, ex);
	}
}