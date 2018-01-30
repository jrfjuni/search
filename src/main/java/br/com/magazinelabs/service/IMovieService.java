package br.com.magazinelabs.service;

import java.util.List;

import org.bson.Document;

import br.com.magazinelabs.controller.FilterSearch;

/**
 * 
 * @author JUNNIOR
 *
 */
public interface IMovieService {
	
	/**
	 * <p> Método responsável em realizar a busca dos Movies por filtro.
	 * @param filterSearch
	 * @return List<Document>
	 */
	List<Document> findMoviesByFilter(FilterSearch filterSearch)  throws Exception;
	
	/**
	 * <p> Método responsável em inserir uma lista de Documents para Collection de Movies
	 * @param documents
	 */
	void save(List<Document> documents) throws Exception;
	
	/**
	 * <p> Método responsável em inserir um Document para Collection de Movies
	 * @param documents
	 */
	void save(Document document) throws Exception;
	
	/**
	 * <p> Método responsável em recuperar o Document pelo nome
	 * @param file
	 * @return
	 */
	Document findByNameFile(String file) throws Exception;
	
	/**
	 * <p> Metódo responsável em remover um Document pelo nome
	 * @param file
	 */
	void remove(String file) throws Exception;
}