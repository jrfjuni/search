package br.com.magazinelabs.util;

import java.text.MessageFormat;
import java.util.List;

import br.com.magazinelabs.exception.ResourceException;

public class UtilShowMessage {
	
	public static void messageSuccessSearch(List<Object> objects, String term, Boolean isTypeDocument) throws ResourceException{
		
		/*StringBuilder sb = new StringBuilder();
		
		createMessageHeaderSuccessSearch(sb, objects.size(), term);
		
		if(isTypeDocument){
			
			for (Object obj : objects) {
				Document document = (Document) obj;
				sb.append(UtilFile.PATH_FILE_MOVIES).append("/").append(document.get("file")).append("\n");
			}
		}*/
		
		
	}
	
	/**
	 * <p> Método responsável em criar o Header de exibição da messagem de sucesso para busca de termos.
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
	
	/**
	 * <p> Método responsável em exibir a mensagem de erro para termos não encontrados
	 * @param term
	 * @throws ResourceException
	 */
	public static void showMessageTermNotFound(String term) throws ResourceException{
		System.err.println(MessageFormat.format(UtilMessageResource.getMessage("msg.not.found.files"), term.toString().trim()));
	}
}