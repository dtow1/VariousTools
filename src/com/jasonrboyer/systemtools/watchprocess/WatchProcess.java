package com.jasonrboyer.systemtools.watchprocess;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class WatchProcess {

	public static void main(String[] args) {
		ArrayList<String> processes;
		Process p;
		StringBuilder errorMessage = new StringBuilder();
		Configuration myConfig = new Configuration();
		System.out.println("In main: " + myConfig.getName());
		File testing = myConfig.getConfigFile();
		
		processes = myConfig.getProcesses();
		
		for(String prcs: processes) {
			System.out.println("Main process");
			System.out.println(prcs);
		}
	    String line;
	    try {
	    		for(String prcs: processes) {
	    			String proc;
	    			//This is for temporary use on my Mac. OSX doesn't include pid and eclipse isn't picking up the path
	    			//correctly using path_helper(path helper does pick it up though) so just using absolute path, this utility
	    			//will run on linux systems(the target system) just fine
	    			p = Runtime.getRuntime().exec("/usr/local/bin/pidof " + prcs);
	    			
	    			//This is the final version to use
	    			//p = Runtime.getRuntime().exec("pidof bash");
	    			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
	    			proc=input.readLine();
	    			System.out.println("Proc: " + proc + " prcs: " + prcs);
	    			if(proc==null) {
	    				errorMessage.append("Process: " + prcs + " not running.\n");
	    			}
	    			
	    			
		    	}
	    		

	    } catch (Exception err) {
	      System.out.println(err);
	      System.out.println(err.getStackTrace());

	      System.out.println(err.getMessage());
	      
	    }	
	    System.out.println("Polltime" + myConfig.getPollTime());
		System.out.println("Polltime" + myConfig.getAlertTime());		
		System.out.println("Error: " + errorMessage);

	}

}
