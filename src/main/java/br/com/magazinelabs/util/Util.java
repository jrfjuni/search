package br.com.magazinelabs.util;

import java.util.List;

/**
 * 
 * @author JUNNIOR
 *
 */
public class Util {
	
	public static Boolean isNotNull(Object obj){
		return obj != null;
	}
	
	public static Boolean isNull(Object obj){
		return obj == null;
	}
	
	public static Boolean listIsNotNullAndEmpty(List<?> objets){
		Boolean isNot = Boolean.TRUE;
		
		if(isNotNull(objets) && !objets.isEmpty()){
			for (Object object : objets) {
				
				if(isNull(object))
					isNot = Boolean.FALSE;
			}
			
		}else
			isNot = Boolean.FALSE;
		
		return isNot;
	}
}