package br.com.magazinelabs.util;

import java.util.Properties;

import br.com.magazinelabs.exception.ResourceException;

/**
 * 
 * @author JUNNIOR
 *
 */
public class UtilMessageResource extends UtilResource{
	
	private static Properties prop;

	public static String getMessage(String key) throws ResourceException {
		return getPropConfig().getProperty(key);
	}
	
	private static Properties getPropConfig() throws ResourceException{
		if (prop == null) 
			prop = getPropertie("message.properties");
		
		return prop;
	}
}