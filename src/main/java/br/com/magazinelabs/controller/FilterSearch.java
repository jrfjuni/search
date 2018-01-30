package br.com.magazinelabs.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author JUNNIOR
 *
 */
public class FilterSearch {
	
	private List<String> words;

	public List<String> getWords() {
		if (words == null) {
			words = new ArrayList<String>();
		}
		return words;
	}

	public void setWords(List<String> words) {
		this.words = words;
	}
}