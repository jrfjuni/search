package br.com.magazinelabs.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.bson.Document;
import org.bson.conversions.Bson;

import br.com.magazinelabs.controller.FilterSearch;
import br.com.magazinelabs.service.collection.ICollection;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

/**
 * 
 * @author JUNNIOR
 *
 */
public class MovieDAO extends BaseDAO{
	
	private MongoCollection<Document> collection;
	
	/**
	 * <p> M�todo respons�vel em realizar uma consulta na tabela de Movies, a partir dos par�metros informados.
	 * @param filterSearch
	 * @return List<Document>
	 * @throws Exception 
	 */
	public List<Document> findMoviesByFilter(FilterSearch filterSearch) throws Exception{
		
		List<Document> documents = new ArrayList<Document>();
		
		BasicDBList basicDBList = new BasicDBList();
		createFilter(filterSearch, basicDBList);
		
		MongoCursor<Document> mongoCursor = getCollectionMoveis().find(new BasicDBObject("$and", basicDBList)).iterator();
		
		while (mongoCursor.hasNext()) {
			documents.add(mongoCursor.next());
		}
		
		mongoCursor.close();
			
		return documents;
	}
	
	public Document findByNameFile(String file) throws Exception{
		BasicDBList basicDBList = new BasicDBList();
		basicDBList.add(new Document("file", Pattern.compile(file, Pattern.CASE_INSENSITIVE)));
		
		MongoCursor<Document> mongoCursor = getCollectionMoveis().find(new BasicDBObject("$and", basicDBList)).iterator();
		
		return (mongoCursor.hasNext()) ? mongoCursor.next() : null;
	}
	
	private void createFilter(FilterSearch filterSearch, BasicDBList basicDBList){
		List<String> words = filterSearch.getWords();
		
		for (String word : words) {
			basicDBList.add(new Document("description", Pattern.compile(word, Pattern.CASE_INSENSITIVE)));
		}
	}
	
	public void save(List<Document> documents) throws Exception {
		save(documents, ICollection.COLLECTION_MOVIE);
	}
	
	public void save(Document document) throws Exception {
		save(document, ICollection.COLLECTION_MOVIE);
	}
	
	public void remove(String file) throws Exception {
		Bson condition = new Document("file", file);
		remove(condition, ICollection.COLLECTION_MOVIE);
	}
	
	private MongoCollection<Document> getCollectionMoveis() throws Exception  {
			
		if (collection == null) 
			collection = getCollection(ICollection.COLLECTION_MOVIE);

		return collection;
	}
}