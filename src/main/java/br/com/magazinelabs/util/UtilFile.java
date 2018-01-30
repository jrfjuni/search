package br.com.magazinelabs.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

/**
 * <p> CLASSE RESPONSÁVEL EM REALIZAR OPERAÇÕES EM ARQUIVOS
 * @author Junnior
 *
 */
public class UtilFile {
	
	public static final String PATH_FILE_MOVIES = "filesmovies";
	
	/**
	 * <p> Método responsável em recuperar arquivos de Movie (resource/filesmovies) e converter os mesmos para o tipo Document
	 * 
	 * @return List<Document>
	 */
	public static List<Document> getDocumentsMovie() {
		List<Document> documents = new ArrayList<Document>();
		
		try {
			
			URL uri = Thread.currentThread().getContextClassLoader().getResource(PATH_FILE_MOVIES);
			File[] files = new File(uri.toURI()).listFiles();
						
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
			// TODO: handle exception
		}
		
		return documents;
	}
}