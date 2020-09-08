package com.ikhokha.techcheck;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MetricsComputation {
	
	Map<String, Integer> resultsMap = new HashMap<>();

	 
	public Map<String, Integer>  getShorterThan15(String line) {
		
		resultsMap =  (line.length() < 15) ?  incOccurrence(resultsMap, "SHORTER_THAN_15") : resultsMap;
		return resultsMap;
	}
	
	public Map<String, Integer>  getMoverMention(String line) {
		
		resultsMap =  line.contains("Mover") ?  incOccurrence(resultsMap, "MOVER_MENTIONS") : resultsMap;
		return resultsMap;	
			
	}
	
	public Map<String, Integer>  getShakerMention(String line) {
		
		resultsMap =  line.contains("Shaker") ?  incOccurrence(resultsMap, "SHAKER_MENTIONS") : resultsMap;
		return resultsMap;	
			
	}

	public Map<String, Integer>  getQuestionsMention(String line) {
		resultsMap =  line.contains("?") ?  incOccurrence(resultsMap, "QUESTION_MENTIONS") : resultsMap;
		return resultsMap;	
		
	}
	
	public Map<String, Integer>  getSpam(String line) {
		//if(new RegExp("([a-zA-Z0-9]+://)?([a-zA-Z0-9_]+:[a-zA-Z0-9_]+@)?([a-zA-Z0-9.-]+\\.[A-Za-z]{2,4})(:[0-9]+)?(/.*)?").test(line)
		resultsMap =  ( line.contains("http://") || line.contains("https://")) ?  incOccurrence(resultsMap, "SPAM_MENTIONS") : resultsMap;
		return resultsMap;
		
	}

	
	private Map<String, Integer>  incOccurrence(Map<String, Integer> countMap, String key) {
			
		countMap.putIfAbsent(key, 0);
		countMap.put(key, countMap.get(key) + 1);
		return countMap;
	}
}
