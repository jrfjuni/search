package br.com.magazinelabs.dao;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import br.com.magazinelabs.exception.ConnectionException;
import br.com.magazinelabs.exception.ResourceException;
import br.com.magazinelabs.exception.SearchException;
import br.com.magazinelabs.util.Util;
import br.com.magazinelabs.util.UtilConfigResource;
import br.com.magazinelabs.util.UtilMessageResource;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoSocketException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * 
 * @author JUNNIOR
 *
 */
public abstract class BaseDAO {
	
	private static MongoClient client;
	
	/**
	 * <p> M�todo respons�vel em criar e retornar uma conex�o com o banco
	 * @return
	 * @throws Exception
	 */
	public static MongoDatabase getConnection() throws Exception {
		MongoDatabase mongoDB = null;
		
		try {
			createConnection();
			mongoDB = client.getDatabase(UtilConfigResource.getConfig("mongoDB.base"));
		}catch(ResourceException e) {
			throw new ConnectionException(e);
		}catch (ConnectionException ex) {
			throw new ConnectionException(ex);
		}
		
		return mongoDB;
	}
	
	/**
	 * <p> Método responsável em criar uma conexão com o banco.
	 * @throws Exception
	 */
	private static void createConnection() throws Exception {
		try {
		
			Builder opts = MongoClientOptions.builder()
					.maxConnectionIdleTime(0)
					.cursorFinalizerEnabled(Boolean.FALSE);
			
			MongoClientURI uri = new MongoClientURI(UtilConfigResource.getConfig("mongoDB.uri"), opts);
			
			client = new MongoClient(uri);
			
		} catch (ResourceException e) {
			throw new ResourceException(e);
		} catch(MongoSocketException ex){
			throw new ConnectionException(ex);
		}
	}
	
	/**
	 * <p> Metódo respons�vel em criar uma nova Collection caso ainda não exista
	 * @param nameCollection
	 * @throws Exception
	 */
	public static void createCollection(String nameCollection) throws Exception{
		
		if(!collectionExists(nameCollection))
			getConnection().createCollection(nameCollection);
		else
			throw new SearchException(MessageFormat.format(UtilMessageResource.getMessage("msg.collection.exist"), nameCollection));
			
	}
	
	/**
	 * <p> M�todo respons�vel em verificar se uma determinada Collection est� criada no banco.
	 * @param collection
	 * @return
	 * @throws Exception
	 */
	public static Boolean collectionExists(String collection) throws Exception {
		return getConnection().listCollectionNames().into(new ArrayList<String>()).contains(collection);
	}

	/**
	 * <p> M�todo respons�vel em excluir uma Collection
	 * @param nameCollection
	 * @throws Exception
	 */
	protected static void dropCollection(String nameCollection) throws Exception {
		
		if(collectionExists(nameCollection)){
			MongoCollection<Document> collection = getConnection().getCollection(nameCollection);
			collection.drop();
		}else
			throw new SearchException(MessageFormat.format(UtilMessageResource.getMessage("msg.collection.not.found"), nameCollection));
	}
	
	/**
	 * <p> M�todo respons�vel em realizar o insert de uma lista de {@link Document} em uma determinada Collection
	 * @param documents
	 * @param nameCollection
	 */
	protected static void save(List<Document> documents, String nameCollection) throws Exception {
		
		if(Util.listIsNotNullAndEmpty(documents)){
			MongoCollection<Document> collection = getConnection().getCollection(nameCollection);
			collection.insertMany(documents);
		}else
			throw new SearchException(UtilMessageResource.getMessage("msg.document.required"));

	}
	
	protected static MongoCollection<Document> getCollection(String nameCollection) throws Exception{
		
		if(collectionExists(nameCollection)){
			return getConnection().getCollection(nameCollection);
		}else
			throw new SearchException(MessageFormat.format(UtilMessageResource.getMessage("msg.collection.not.found"), nameCollection));
		
	}
	
	/**
	 * <p> M�todo respons�vel em realizar o insert de um  {@link Document} em uma determinada Collection
	 * @param document
	 * @param nameCollection
	 */
	protected static void save(Document document, String nameCollection) throws Exception {
		
		if(Util.isNotNull(document)){
			MongoCollection<Document> collection = getConnection().getCollection(nameCollection);
			collection.insertOne(document);
		}else
			throw new SearchException(UtilMessageResource.getMessage("msg.document.required"));
		
	}
	
	/**
	 * <p> M�todo respons�vel em remover documentos de uma determinada collection a partir de uma condi��o
	 * @param condition
	 * @param nameColletion
	 * @throws Exception
	 */
	protected static void remove(Bson condition, String nameColletion) throws Exception {
		
		if(collectionExists(nameColletion)){
			MongoCollection<Document> collection = getConnection().getCollection(nameColletion);
			collection.deleteMany(condition);
		}else
			throw new SearchException(MessageFormat.format(UtilMessageResource.getMessage("msg.collection.not.found"), nameColletion));
			
	}
}