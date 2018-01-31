package br.com.magazinelabs.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import br.com.magazinelabs.controller.FilterSearch;
import br.com.magazinelabs.dao.MovieDAO;
import br.com.magazinelabs.exception.SearchException;
import br.com.magazinelabs.service.collection.ICollection;
import br.com.magazinelabs.util.Util;
import br.com.magazinelabs.util.UtilFile;
import br.com.magazinelabs.util.UtilMessageResource;

/**
 * 
 * @author JUNNIOR
 *
 */
public class MovieService implements IMovieService {
	
	private MovieDAO movieDAO;
			
	public List<Document> findMoviesByFilter(FilterSearch filterSearch) throws Exception{
		
		List<Document> documents = new ArrayList<Document>();
				
		createCollectionMovies();
		
		if(Util.isNotNull(filterSearch) && Util.isNotNull(filterSearch.getWords()) && !filterSearch.getWords().isEmpty())
			documents = getMovieDAO().findMoviesByFilter(filterSearch);
		else
			throw new SearchException(UtilMessageResource.getMessage("msg.required.term"));

		return documents;
	}
	
	/**
	 * <p> Método responsável em verificar e criar a Collection de MOVIE's
	 */
	@SuppressWarnings("static-access")
	private void createCollectionMovies() throws Exception{
		
		if(!getMovieDAO().collectionExists(ICollection.COLLECTION_MOVIE)){
			System.out.println(MessageFormat.format(UtilMessageResource.getMessage("msg.create.collection"), ICollection.COLLECTION_MOVIE));
			getMovieDAO().createCollection(ICollection.COLLECTION_MOVIE);
			save(UtilFile.getDocumentsMovie());
		}
	}
	
	public void save(List<Document> documents) throws Exception {
		getMovieDAO().save(documents);
	}
	
	public void save(Document document) throws Exception {
		getMovieDAO().save(document);
	}
	
	public Document findByNameFile(String file) throws Exception{
		return getMovieDAO().findByNameFile(file);
	}
	
	public void remove(String file) throws Exception{
		getMovieDAO().remove(file);
	}
	
	public MovieDAO getMovieDAO() {
		if (movieDAO == null) {
			movieDAO = new MovieDAO();
		}
		return movieDAO;
	}
}