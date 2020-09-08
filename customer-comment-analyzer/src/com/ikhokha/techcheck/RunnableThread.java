package com.ikhokha.techcheck;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class RunnableThread implements Runnable{

	 private Thread t;
	 private File  threadFileName  ;
	 static Map<String, Integer> fileResults = new HashMap<>();

	   public RunnableThread( File fileName ) {
		   threadFileName = fileName;
	   }
	   
	   public void run() {
	      try {
	         
	        	CommentAnalyzer commentAnalyzer = new CommentAnalyzer(threadFileName);
	 			fileResults = commentAnalyzer.analyze();
	            Thread.sleep(50);
	         
	      }catch (InterruptedException e) {
	         System.out.println("Thread " +  threadFileName + " interrupted.");
	      }
	   }
	   
	   public void start () {
	      if (t == null) {
	         t = new Thread (this);
	         t.run();
	      }
	   }
	

	public static  Map<String, Integer>  returnResults () {
	    return fileResults;
	}

}