package com.ikhokha.techcheck;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		
		Map<String, Integer> totalResults = new HashMap<>();
				
		File docPath = new File("docs");
		File[] commentFiles = docPath.listFiles((d, n) -> n.endsWith(".txt"));
		
		int revers_loop_index = commentFiles.length-1; // starting from the back to the front
        for(int  fwd_loop_index = 0; fwd_loop_index < revers_loop_index ; fwd_loop_index++) {
        	 Map<String, Integer> fileResults, fileResults2;
			RunnableThread R1 = new RunnableThread(commentFiles[fwd_loop_index]);
			RunnableThread R2 = new RunnableThread(commentFiles[revers_loop_index]);
			
		    R1.start();
		    R2.start();
		    
		    fileResults = R1.returnResults();
		    fileResults2 = R2.returnResults();
		    
			addReportResults(fileResults, totalResults);	
			addReportResults(fileResults2, totalResults);	
			revers_loop_index--;
		}
        
        if(commentFiles.length%2!=0) 
        { //compute one more time since the middle file wasn't computed if file is odd.
			RunnableThread R3 = new RunnableThread(commentFiles[revers_loop_index]);
		    R3.start();
		    Map<String, Integer> fileResults3 = R3.returnResults();
			addReportResults(fileResults3, totalResults);	
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


