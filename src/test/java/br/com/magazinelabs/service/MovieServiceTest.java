package br.com.magazinelabs.service;

import java.util.List;

import org.bson.Document;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.com.magazinelabs.controller.FilterSearch;
import br.com.magazinelabs.exception.SearchException;
import br.com.magazinelabs.util.UtilMessageResource;

public class MovieServiceTest {
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testFindMoviesByFilter() throws Exception{

		 FilterSearch filterSearch = new FilterSearch();
		 filterSearch.getWords().add("2008");
		 
		 List<Document> documents = new MovieService().findMoviesByFilter(filterSearch);
		 Assert.assertNotEquals(0, documents.size());
	}
	
	@Test
	public void testSearchExceptionFilterNull() throws Exception{
		exception.expect(SearchException.class);
		exception.expectMessage(UtilMessageResource.getMessage("msg.required.term"));
		new MovieService().findMoviesByFilter(null);
	}
	
	
	public void testSaveAndFindMovie() throws Exception{
		IMovieService service = new MovieService();
		
		Document document = new Document();
		document.append("file", "magazinelabs-test-insert.txt");
		
		service.save(document);
		
		Document docFind = service.findByNameFile("magazinelabs-test-insert.txt");
		
		Assert.assertNotNull(docFind);
	}
	
	@Test
	public void testRemoveFile() throws Exception{
		IMovieService service = new MovieService();
		
		service.remove("magazinelabs-test-insert.txt");
		
		Document docRemove = service.findByNameFile("magazinelabs-test-insert.txt");
		Assert.assertNull(docRemove);
	}
}