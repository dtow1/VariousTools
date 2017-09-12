package com.jasonrboyer.systemtools.watchprocess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Configuration {
	private File configFile;
	private BufferedReader fileReader;
	private String fileName;
	private ArrayList<String> processes = new ArrayList<String>();
	
	public Configuration() {
		this("config.cfg");
	}
	public Configuration(String fileName){
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
			configFile.createNewFile();
			configFile.setReadable(true);
			configFile.setWritable(true);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			fileReader = new BufferedReader(new FileReader(configFile));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			String currentLine;
			while((currentLine = fileReader.readLine()) != null) {
				getParameters(currentLine);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void getParameters(String toParse) {
		int  colLoc = toParse.indexOf(':');
		String[] parsed = parseLine(toParse);
		switch(parsed[0]) {
			case ("-process"):
				for(int i=1;i<parsed.length;i++) {
					processes.add(parsed[i]);
				}
				break;	
		}
		
	}
	
	private String[] parseLine(String toParse) {
		String[] results = null;
		
		if(toParse!=null) {
			results=toParse.split(";|:|,");
		}
		
		return results;
	}
	
	public String getName() {
		return configFile.getName();
	}
	
	public ArrayList<String> getProcesses() {
		return (ArrayList<String>) processes.clone();
	}
	
	
}
