package br.com.magazinelabs.controller;

import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import br.com.magazinelabs.exception.ResourceException;
import br.com.magazinelabs.exception.SearchException;
import br.com.magazinelabs.service.IMovieService;
import br.com.magazinelabs.service.MovieService;
import br.com.magazinelabs.util.Util;
import br.com.magazinelabs.util.UtilFile;
import br.com.magazinelabs.util.UtilMessageResource;

/**
 * 
 * @author JUNNIOR
 *
 */
public class SearchController {
	
	private static IMovieService movieService;
	
	private static Logger mongoLogger = Logger.getLogger("org.mongodb.driver");

	public static void main(String[] args) throws SearchException  {
		
		mongoLogger.setLevel(Level.SEVERE);
		
		try {
			
			FilterSearch filterSearch = new FilterSearch();
			StringBuilder term = new StringBuilder();
			
			if(fillFilter(args, filterSearch, term)){
				
				List<Document> documents = getMovieService().findMoviesByFilter(filterSearch);
				
				if(Util.isNull(documents) || documents.isEmpty()) 
					System.out.println(MessageFormat.format(UtilMessageResource.getMessage("msg.not.found.files"), term.toString().trim()));
				else
					showMessage(documents, term.toString().trim());
				
			}else
				System.out.println(UtilMessageResource.getMessage("msg.required.term"));
			
		} catch (Exception e) {
			throw new SearchException(e.getMessage());
		} 
	}
	
	/**
	 * <p> M�todo respons�vel em exibir a mensagem referente a quantidade de ocorr�ncias encontradas na pesquisa e seus respectivos arquivos.
	 * @param documents
	 * @param term
	 * @throws ResourceException
	 */
	private static void showMessage(List<Document> documents, String term) throws ResourceException {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(MessageFormat.format(
					UtilMessageResource.getMessage("msg.find.qty"), 
					documents.size(), 
					term
				)).append("\n");
		
		sb.append(MessageFormat.format(
					UtilMessageResource.getMessage("msg.find.files"), 
					term
		)).append("\n");
		
		for (Document document : documents) {
			sb.append(UtilFile.PATH_FILE_MOVIES).append("/").append(document.get("file")).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	/**
	 * <p> M�todo responsável em preencher o {@link FilterSearch} com os argumentos passados por parâmetro
	 * @param words
	 * @param filter
	 * @param term
	 * @throws Exception
	 */
	public static Boolean fillFilter(String[] words, FilterSearch filter, StringBuilder term) throws Exception {
		Boolean filterValid = Boolean.TRUE;
		
		
		if(Util.isNotNull(words) && words.length > 0) {
			for (String word : words) {
				
				String[] wordSplit = word.split("\\W");
				
				if(Util.isNotNull(wordSplit)){
					
					for (String wordSlipt : wordSplit) {
						
						if(Util.isNotNullAndEmpty(wordSlipt)){
							filter.getWords().add(wordSlipt);
							term.append(wordSlipt).append(" ");
						}
					}
				}
			}
			
			if(filter.getWords().isEmpty())
				filterValid = Boolean.FALSE;
			
		}else
			filterValid = Boolean.FALSE;
		
		return filterValid;
	}
	
	private static IMovieService getMovieService() {
		if (movieService == null) {
			movieService = new MovieService();
		}

		return movieService;
	}
}