package br.com.magazinelabs.controller;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.MessageFormat;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.com.magazinelabs.exception.SearchException;
import br.com.magazinelabs.util.UtilMessageResource;

public class SearchControllerTest {
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	

	@Test
	public void testFillFilterNull() throws Exception{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		
		String expectMessage = UtilMessageResource.getMessage("msg.required.term");
		
		SearchController.main(null);
		
		Assert.assertTrue(expectMessage.equalsIgnoreCase(out.toString().trim()));
	}
	
	
	@Test
	public void testFindMoviesByFilterNotFound() throws Exception{
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		
		String term = "abcdefghij";
		String expectMessage = MessageFormat.format(UtilMessageResource.getMessage("msg.not.found.files"), term);
		
		FilterSearch filterSearch = new FilterSearch();
		filterSearch.getWords().add(term);
		
		SearchController.main(new String[]{term});
		
		Assert.assertTrue(expectMessage.equalsIgnoreCase(out.toString().trim()));
	}
	
	@Test
	public void testMain(){
		Exception ex = null;
		
		try {
			SearchController.main(new String[]{"2008"});
		} catch (SearchException e) {
			ex = e;
		}
		
		
		Assert.assertNull(ex);
	}
}