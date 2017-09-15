package com.jasonrboyer.systemtools.watchprocess;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class WatchProcess {

	public static void main(String[] args) {
		ArrayList<String> processes;
		Process p;
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
	    			p = Runtime.getRuntime().exec("pidof " + prcs);
	    			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
	    			proc=input.readLine();
	    			if(proc==null) {
	    				System.out.println("Process: " + prcs + " not located");
	    			}else {
	    				System.out.println("Process: " + prcs + "found!");
	    			}
	    			
	    			
		    	}
	    		

	    } catch (Exception err) {
	      System.out.println(err);
	    }
		System.out.println("Polltime" + myConfig.getPollTime());
		System.out.println("Polltime" + myConfig.getAlertTime());		
		

	}

}
