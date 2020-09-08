package com.ikhokha.techcheck;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		
		Map<String, Integer> totalResults = new HashMap<>();
				
		File docPath = new File("docs");
		File[] commentFiles = docPath.listFiles((d, n) -> n.endsWith(".txt"));
		
        for(int i = 0; i < commentFiles.length ; i++) {
			
			RunnableThread R1 = new RunnableThread(commentFiles[i]);
		    R1.start();
		    Map<String, Integer> fileResults = R1.returnResults();
			addReportResults(fileResults, totalResults);	

		}
		System.out.println("RESULTS\n=======");
		totalResults.forEach((k,v) -> System.out.println(k + " : " + v));
		
	}
	
	/**
	 * This method adds the result counts from a source map to the target map 
	 * @param source the source map
	 * @param target the target map
	 */
	private static void addReportResults(Map<String, Integer> source, Map<String, Integer> target) {

		for (Map.Entry<String, Integer> entry : source.entrySet()) {
			int newValue = target.get(entry.getKey()) !=null ? target.get(entry.getKey()) : 0 ; 
			target.put(entry.getKey(), newValue  + entry.getValue());
		}
		
	}

}


