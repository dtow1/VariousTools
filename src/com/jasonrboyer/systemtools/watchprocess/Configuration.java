package com.jasonrboyer.systemtools.watchprocess;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Configuration {
	private File configFile;
	private String fileName;
	private ArrayList<String> processes = new ArrayList<String>();
	
	public Configuration() {
		this("config.cfg");
	}
	public Configuration(String fileName){
		System.out.println("checking for " + fileName);
		this.fileName=fileName;
		loadConfig();
	} 
	
	public File getConfigFile() {
		if(!configFile.exists()) {
			System.out.println("No config file");
			loadConfig();
		}
		return configFile;
	}
	
	public void loadConfig() {
		System.out.println("Loading config file : " + fileName);
		configFile = new File(fileName);

		try {
			
			System.out.println("create: " + configFile.createNewFile());
			System.out.println("Set Readable: " + configFile.setReadable(true));
			System.out.println("Set Writable: " + configFile.setWritable(true));
			System.out.println(configFile.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(configFile.exists());
		System.out.println(getName());
	}
	
	public String getName() {
		return configFile.getName();
	}
	
	
}
