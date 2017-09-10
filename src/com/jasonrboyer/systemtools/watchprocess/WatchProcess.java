package com.jasonrboyer.systemtools.watchprocess;

import java.io.File;

public class WatchProcess {

	public static void main(String[] args) {
		
		Configuration myConfig = new Configuration();
		System.out.println("In main: " + myConfig.getName());
		File testing = myConfig.getConfigFile();
		
		

	}

}
