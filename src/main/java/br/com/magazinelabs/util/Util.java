package br.com.magazinelabs.util;

import java.util.List;

/**
 * 
 * @author JUNNIOR
 *
 */
public class Util {
	
	/**
	 * <p> M�todo respons�vel em validar se um determinado Object n�o � Null
	 * @param obj
	 * @return
	 */
	public static Boolean isNotNull(Object obj){
		return obj != null;
	}
	
	/**
	 * <p> M�todo respons�vel em validar se um determinado Object n�o � null e vazio
	 * @param obj
	 * @return
	 */
	public static Boolean isNotNullAndEmpty(Object obj){
		Boolean notNullEmpty = Boolean.FALSE;
		
		if(isNotNull(obj)){
			
			String classObj = obj.getClass().getName();
			
			if(classObj.equals(String.class.getName())){
				String value = obj.toString();
				
				if(!value.trim().isEmpty())
					notNullEmpty = Boolean.TRUE;
				
			}
		}
		
		return notNullEmpty;
	}
	
	/**
	 * <p> M�todo respons�vel em validar se um Object � null
	 * @param obj
	 * @return
	 */
	public static Boolean isNull(Object obj){
		return obj == null;
	}
	
	/**
	 * <p> M�todo respons�vel em validar de uma determinada lista n�o � Null e vazia.
	 * @param objets
	 * @return
	 */
	public static Boolean listIsNotNullAndEmpty(List<? extends Object> objets){
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