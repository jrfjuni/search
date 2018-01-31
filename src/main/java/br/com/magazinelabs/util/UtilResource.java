package br.com.magazinelabs.util;

import java.util.Properties;

import br.com.magazinelabs.exception.ResourceException;

/**
 * 
 * @author JUNNIOR
 *
 */
public class UtilResource {
	
	private static final String PATH = "/resources/";
	
	private static Properties prop = new Properties();
	
	public static Properties getPropertie(String name) throws ResourceException {
		try {
			prop.load(UtilResource.class.getResourceAsStream(PATH + name));
		} catch (Exception e) {
			throw new ResourceException(e);
		}
		return prop;
	}
}