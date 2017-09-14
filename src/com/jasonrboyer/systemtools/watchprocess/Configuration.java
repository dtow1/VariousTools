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
	private int checktime=0;
	private int alerttime=0;
	
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
				if(currentLine.length() > 0 && currentLine.charAt(0)=='-') {
					getParameters(currentLine);
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void getParameters(String toParse) {
		int colLoc = toParse.indexOf(':');
		String[] parsed = parseLine(toParse);
		switch(parsed[0]) {
			case ("-process"):
				for(int i=1;i<parsed.length;i++) {
					processes.add(parsed[i]);
				}
				break;	
			case ("-checkhours"):
				checktime+=(getNumber(parsed[1])*3600);
				break;
			case ("-checkminutes"):
				checktime+=(getNumber(parsed[1])*60);
				break;
			case ("-checkseconds"):
				checktime+=(getNumber(parsed[1]));
				break;
			case ("-alerthours"):
				alerttime+=(getNumber(parsed[1])*3600);
				break;
			case ("-alertminutes"):
				alerttime+=(getNumber(parsed[1])*60);
				break;
			case ("-alertseconds"):
				alerttime+=(getNumber(parsed[1]));
				break;
		}
		
	}
	
	private int getNumber(String number) {
		int value;
		if(number==null) {
			value=0;
		}
		try {
			value = Integer.parseInt(number);
			if(value<0) {
				value=0;
			}
		} catch (NumberFormatException e) {
		    value=0;
		}
		return value;
	}
	
	private String[] parseLine(String toParse) {
		String[] results = null;
		
		if(toParse!=null) {
			results=(toParse.split(";|:|,"));
		}
		
		return results;
	}
	
	public String getName() {
		return configFile.getName();
	}
	
	public int getPollTime() {
		return checktime;
	}
	
	public int getAlertTime() {
		return alerttime;
	}
	
	public ArrayList<String> getProcesses() {
		return (ArrayList<String>) processes.clone();
	}
	
	
}
