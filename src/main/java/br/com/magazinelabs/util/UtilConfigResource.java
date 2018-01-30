package br.com.magazinelabs.util;

import java.util.Properties;

import br.com.magazinelabs.exception.ResourceException;

/**
 * 
 * @author JUNNIOR
 *
 */
public class UtilConfigResource extends UtilResource{
	
	private static Properties prop;

	public static String getConfig(String key) throws ResourceException {
		return getPropConfig().getProperty(key);
	}
	
	private static Properties getPropConfig() throws ResourceException{
		if (prop == null) 
			prop = getPropertie("config.properties");
		
		return prop;
	}
}