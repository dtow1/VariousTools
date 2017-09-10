package com.jasonrboyer.systemtools.watchprocess;

import java.io.File;
import java.util.ArrayList;

public class WatchProcess {

	public static void main(String[] args) {
		ArrayList<String> processes;
		Configuration myConfig = new Configuration();
		System.out.println("In main: " + myConfig.getName());
		File testing = myConfig.getConfigFile();
		
		processes = myConfig.getProcesses();
		
		for(String prcs: processes) {
			System.out.println("Main prcss");
			System.out.println(prcs);
		}
		
		

	}

}
