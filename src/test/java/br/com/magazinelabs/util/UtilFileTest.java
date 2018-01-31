package br.com.magazinelabs.util;

import java.util.List;

import org.bson.Document;
import org.junit.Assert;
import org.junit.Test;

public class UtilFileTest {
	
	@Test
	public void testGetDocumentsMovie() throws Exception{
		List<Document> documents = UtilFile.getDocumentsMovie();
		Assert.assertTrue(Util.listIsNotNullAndEmpty(documents));
	}
}