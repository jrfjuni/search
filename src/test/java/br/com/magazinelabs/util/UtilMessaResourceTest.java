package br.com.magazinelabs.util;

import org.junit.Assert;
import org.junit.Test;

import br.com.magazinelabs.exception.ResourceException;

public class UtilMessaResourceTest {
	
	@Test
	public void testGetMessage() throws ResourceException{
		String msg = UtilMessageResource.getMessage("msg.required.term");
		Assert.assertNotNull(msg);
	}
	
	@Test
	public void testGetMessageNull() throws ResourceException{
		String msg = UtilMessageResource.getMessage("msg.test");
		Assert.assertNull(msg);
	}
}