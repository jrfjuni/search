package br.com.magazinelabs.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.com.magazinelabs.exception.ResourceException;

public class UtilResourceTest {
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testPropertieNotFound() throws ResourceException{
		exception.expect(ResourceException.class);
		UtilResource.getPropertie("propertie.not.found");
	}
}