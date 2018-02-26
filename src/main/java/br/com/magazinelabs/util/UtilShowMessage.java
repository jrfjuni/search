package br.com.magazinelabs.util;

import java.text.MessageFormat;

import br.com.magazinelabs.exception.ResourceException;

public class UtilShowMessage {
	
	/**
	 * <p> M�todo respons�vel em criar o Header de exibi��o da messagem de sucesso para busca de termos.
	 * @param qty
	 * @param term
	 * @return
	 * @throws ResourceException
	 */
	public static StringBuilder createMessageHeaderSuccessSearch(Integer qty, String term) throws ResourceException{
		StringBuilder sb = new StringBuilder();
		
		sb.append(MessageFormat.format(
				UtilMessageResource.getMessage("msg.find.qty"), 
				qty, 
				term.trim()
			)).append("\n");
	
		sb.append(MessageFormat.format(
					UtilMessageResource.getMessage("msg.find.files"), 
					term.trim()
		)).append("\n");
		
		return sb;
	}
}