package br.com.magazinelabs.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import br.com.magazinelabs.comum.OperatingResult;
import br.com.magazinelabs.controller.FilterSearch;
import br.com.magazinelabs.exception.SearchException;

/**
 * <p> CLASSE RESPONS�VEL EM REALIZAR OPERA��ES EM ARQUIVOS
 * @author Junnior
 *
 */
public class UtilFile {
	
	public static final String PATH_FILE_MOVIES = "filesmovies";
	
	/**
	 * <p> M�todo responsável em recuperar arquivos de Movie (resource/filesmovies) e converter os mesmos para o tipo Document
	 * 
	 * @return List<Document>
	 * @throws SearchException 
	 */
	public static List<Document> getDocumentsMovie() throws SearchException {
		List<Document> documents = new ArrayList<Document>();
		
		try {
			
			File[] files = getFilesMovies();
						
			for (File file : files) {
				
				FileInputStream fileInputStream = new FileInputStream(file);
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
				
				String readLine = "";
				
				StringBuilder sb = new StringBuilder();
				while( (readLine = bufferedReader.readLine()) != null ){
					sb.append(readLine);
				} 

				documents.add(new Document()
						        .append("file", file.getName())
						        .append("description", sb.toString()));
				
				fileInputStream.close();
				bufferedReader.close();
			}
			
		} catch (Exception e) {
			throw new SearchException(e.getMessage());
		}
		
		return documents;
	}
	
	/**
	 * <p> Método responsável em validar de uma determinada lista de palavras estão presentes em algum dos arquivos de filme.
	 * 
	 * @param filterSearch
	 * @return
	 * @throws SearchException 
	 */
	public static OperatingResult getDocumentsByFilter(FilterSearch filterSearch) throws SearchException{
		
		OperatingResult operatingResult = new OperatingResult(Boolean.TRUE);
		
		try {
			
			StringBuilder sb = new StringBuilder();
			File[] files = getFilesMovies();
			Integer qty = 0;
			
			for (File file : files) {
				
				FileInputStream fileInputStream = new FileInputStream(file);
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
				
				String readLine = "";
				
				while( (readLine = bufferedReader.readLine()) != null ){
					
					  readLine = readLine.toLowerCase();
						
					 // Verifica se a linha em questão possui os termos buscados 
					 if( Arrays.asList(readLine.split("\\s")).containsAll(filterSearch.getWords())){
						 sb.append(PATH_FILE_MOVIES).append(file.getName()).append("\n");
						 qty++;
					 }
				} 
				
				fileInputStream.close();
				bufferedReader.close();
			}
			
			Map<String, Object> extraData = new HashMap<String, Object>();
			extraData.put("files", sb.toString());
			extraData.put("qty", qty);
			
			operatingResult.setExtraData(extraData);
			
		} catch (Exception e) {
			throw new SearchException(e.getMessage());
		}
		
		return operatingResult;
	}
	
	private static File[] getFilesMovies() throws URISyntaxException{
		URL uri = Util.class.getResource("/resources/" +PATH_FILE_MOVIES);
		return new File(uri.toURI()).listFiles();
	}
}