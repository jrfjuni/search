package br.com.magazinelabs.controller;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.magazinelabs.comum.OperatingResult;
import br.com.magazinelabs.exception.SearchException;
import br.com.magazinelabs.service.IMovieService;
import br.com.magazinelabs.service.MovieService;
import br.com.magazinelabs.util.Util;
import br.com.magazinelabs.util.UtilFile;
import br.com.magazinelabs.util.UtilMessageResource;
import br.com.magazinelabs.util.UtilShowMessage;

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
			
			args = new String[]{"2006"};
			FilterSearch filterSearch = new FilterSearch();
			StringBuilder term = new StringBuilder();
			
			if(fillFilter(args, filterSearch, term)){
				
				OperatingResult result = UtilFile.findWordsResource(filterSearch.getWords(), SearchController.class, UtilFile.PATH_FILE_MOVIES, Boolean.FALSE);;
				
				if(result.getSuccess()){
					
					Integer qty = (Integer) result.getExtraData().get("qtyFiles");
					String files = (String) result.getExtraData().get("files");
					
					if(qty > 0){
						StringBuilder sb = UtilShowMessage.createMessageHeaderSuccessSearch(qty, term.toString());
						sb.append(files);
						System.out.println(sb.toString());
					}else
						System.out.println(MessageFormat.format(UtilMessageResource.getMessage("msg.not.found.files"), term.toString().trim()));
					
				}
			}else
				System.out.println(UtilMessageResource.getMessage("msg.required.term"));
			
		} catch (Exception e) {
			throw new SearchException(e.getMessage());
		} 
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
							filter.getWords().add(wordSlipt.toLowerCase());
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
	
	@SuppressWarnings("unused")
	private static IMovieService getMovieService() {
		if (movieService == null) {
			movieService = new MovieService();
		}

		return movieService;
	}
}