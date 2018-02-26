package br.com.magazinelabs.dao;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import br.com.magazinelabs.exception.SearchException;
import br.com.magazinelabs.service.collection.ICollection;
import br.com.magazinelabs.util.UtilMessageResource;

public class BaseDAOTest {
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	//@Test
	public void testDropCollection() throws Exception{
		
		if(!BaseDAO.collectionExists(ICollection.COLLECTION_TEST))
			BaseDAO.createCollection(ICollection.COLLECTION_TEST);
			
		BaseDAO.dropCollection(ICollection.COLLECTION_TEST);
		
		Boolean collectionExist = BaseDAO.collectionExists(ICollection.COLLECTION_TEST);
		Assert.assertFalse(collectionExist);
	}
	
	//@Test
	public void testCreateNewCollection() throws Exception {
		BaseDAO.createCollection(ICollection.COLLECTION_TEST);
		Boolean collectionExist = BaseDAO.collectionExists(ICollection.COLLECTION_TEST);
		Assert.assertTrue(collectionExist);
	}
	
	//@Test
	public void testCreateNewCollectionExist() throws Exception{
		String expectMessage = MessageFormat.format(UtilMessageResource.getMessage("msg.collection.exist"), ICollection.COLLECTION_TEST);
		exception.expect(SearchException.class);
		exception.expectMessage(expectMessage);
		BaseDAO.createCollection(ICollection.COLLECTION_TEST);
	}
	
	//@Test
	public void testSaveListDocumentNull() throws Exception{
		exception.expect(SearchException.class);
		exception.expectMessage(UtilMessageResource.getMessage("msg.document.required"));
		
		List<Document> documents = null;
		BaseDAO.save(documents, ICollection.COLLECTION_TEST);
	}
	
	//@Test
	public void testSaveElementListDocumentNull() throws Exception{
		exception.expect(SearchException.class);
		exception.expectMessage(UtilMessageResource.getMessage("msg.document.required"));
		
		List<Document> documents = new ArrayList<Document>();
		documents.add(new Document().append("file", "magazinelabs-test-1.txt"));
		documents.add(null);
		documents.add(new Document().append("file", "magazinelabs-test-2.txt"));
		documents.add(new Document().append("file", "magazinelabs-test-3.txt"));
		documents.add(new Document().append("file", "magazinelabs-test-4.txt"));
		documents.add(null);
		
		BaseDAO.save(documents, ICollection.COLLECTION_TEST);
	}
	
	//@Test
	public void testSaveDocumentNull() throws Exception{
		exception.expect(SearchException.class);
		exception.expectMessage(UtilMessageResource.getMessage("msg.document.required"));
		
		Document document = null;
		BaseDAO.save(document, ICollection.COLLECTION_TEST);
	}
}