package br.com.magazinelabs.util;

import java.util.Properties;

import br.com.magazinelabs.exception.ResourceException;

/**
 * 
 * @author JUNNIOR
 *
 */
public class UtilResource {
	
	private static Properties prop = new Properties();
	
	public static Properties getPropertie(String name) throws ResourceException {
		try {
			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(name));
		} catch (Exception e) {
			throw new ResourceException(e);
		}
		return prop;
	}
}