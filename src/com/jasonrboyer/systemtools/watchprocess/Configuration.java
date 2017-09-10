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
				parseString(currentLine);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void parseString(String toParse) {
		int  colLoc = toParse.indexOf(':');
		switch(toParse.substring(1,colLoc)) {
			case ("process"):
				String[] process = toParse.substring(colLoc+1,toParse.length()-1).split(",");
				for(String prcs: process) {
					processes.add(prcs);
				}
				break;	
		}
		
	}
	
	public String getName() {
		return configFile.getName();
	}
	
	public ArrayList<String> getProcesses() {
		return (ArrayList<String>) processes.clone();
	}
	
	
}
