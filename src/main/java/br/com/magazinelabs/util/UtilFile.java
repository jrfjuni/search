package br.com.magazinelabs.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import br.com.magazinelabs.comum.OperatingResult;
import br.com.magazinelabs.exception.SearchException;

/**
 * <p> CLASSE RESPONSÁVEL EM REALIZAR OPERAÇÕES EM ARQUIVOS
 * @author Junnior
 *
 */
public class UtilFile {
	
	public static final String PATH_FILE_MOVIES = "resources/filesmovies";
	
	public static OperatingResult findWordsResource(List<String> words, Class<?> clazz, String pathResource, Boolean convertToDocument) throws URISyntaxException, IOException, SearchException{
		OperatingResult operatingResult = new OperatingResult();
		
		 URL dirURL = clazz.getClassLoader().getResource(pathResource);
	      
	      if (dirURL != null && dirURL.getProtocol().equals("file")) 
	    	  operatingResult = findWordsFiles(new File(dirURL.toURI()).listFiles(), words);
	      
	      // NO CASO DE UM ARQUIVO JAR, NÃO PODEMOS ENCONTRAR O DIRETÓRIO, ASSUMIMOS O MESMO DIR QUE O CLASS
	      if (dirURL == null) {
	        String me = clazz.getName().replace(".", "/")+".class";
	        dirURL = clazz.getClassLoader().getResource(me);
	      }
	      
	      if (dirURL.getProtocol().equals("jar")) 
	    	  operatingResult = getFilesJar(dirURL, pathResource, words);
	     
	      return operatingResult;
	      
	}
	
	protected static OperatingResult getFilesJar(URL dirURL, String pathResource, List<String> words) throws SearchException{
		OperatingResult operatingResult = new OperatingResult(Boolean.TRUE);
	
		try {
			
	  	  	StringBuffer filesWithWord = new StringBuffer();
	  	  	Integer qtyFiles = 0;
	     
		  	/* A JAR path */
		    String jarPath = dirURL.getPath().substring(5, dirURL.getPath().indexOf("!"));
	      
		    JarFile jar = new JarFile(URLDecoder.decode(jarPath, "UTF-8"));
	      
		    Enumeration<JarEntry> entries = jar.entries();
	      
		      while(entries.hasMoreElements()) {
		        String name = entries.nextElement().getName();
		        
		        if (name.startsWith(pathResource)) {
		          String entry = name.substring(pathResource.length());
		          
		          StringBuffer sb = new StringBuffer();
		          String path = pathResource.startsWith("/") ? pathResource : "/" + pathResource;
		          sb.append(path).append(entry);
		          
					 InputStream inputStream = ClassLoader.class.getClass().getResourceAsStream(sb.toString());
					 InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
					 BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
					 
					 String currentFile;
					 
					 while( (currentFile = bufferedReader.readLine()) != null){
						 if(addFileWithWords(currentFile, words)){
							 filesWithWord.append(name).append("\n");
							 qtyFiles++;
						 }
					 }
		        }
		      }
	      
		  jar.close();
	      
	      	Map<String, Object> extraData = new HashMap<String, Object>();
			extraData.put("files", filesWithWord.toString());
			extraData.put("qtyFiles", qtyFiles);
			operatingResult.setExtraData(extraData);
	      
		
		} catch (UnsupportedEncodingException e) {
			throw new SearchException(e.getMessage());
		} catch (IOException e) {
			throw new SearchException(e.getMessage());
		}
		
		 return operatingResult;
	}
	
	/**
	 * <p> Método responsável em percorrer os arquivos e verificar se os mesmos possuem os termos de busca
	 *  
	 * @param files
	 * @param words
	 * @param filesWithWord
	 * @param qtyFiles
	 * @throws SearchException
	 */
	protected static OperatingResult findWordsFiles(File[] files, List<String> words) throws SearchException {
		OperatingResult operatingResult = new OperatingResult(Boolean.TRUE);
		
		try {
			StringBuffer filesWithWord = new StringBuffer();
			Integer qtyFiles = 0;
			
			for (File file : files) {
				
				FileInputStream fileInputStream = new FileInputStream(file);
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
				
				String nameFile = PATH_FILE_MOVIES + "/" + file.getName();
				String currentFile;
				
				while( (currentFile = bufferedReader.readLine()) != null ){
					if(addFileWithWords(currentFile, words)){
						filesWithWord.append(nameFile).append("\n");
						qtyFiles++;
					}
				} 
				
				fileInputStream.close();
				bufferedReader.close();
			}
			
			 Map<String, Object> extraData = new HashMap<String, Object>();
			 extraData.put("files", filesWithWord.toString());
			 extraData.put("qtyFiles", qtyFiles);
			 operatingResult.setExtraData(extraData);
			
		} catch (FileNotFoundException e) {
			throw new SearchException(e.getMessage());
		} catch (IOException e) {
			throw new SearchException(e.getMessage());
		}
		
		return operatingResult;
	}
	
	protected static Boolean addFileWithWords(String line, List<String> words){
		Boolean add = Boolean.FALSE;
		
		// VERIFICA SE A LINHA EM QUESTÃO POSSUI OS TERMOS BUSCADOS
		 if( Arrays.asList(line.split("\\s")).containsAll(words))
			 add = Boolean.TRUE;
		 
		 return add;
	}
}