package com.flightNet.demo.demo.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class FileDaoImpl implements Dao {
	
	@Value("${filePath}")
	String filePath;

	@Override
	public int getDiscount(String userType, int amount) {
		
		return processInputFile(this.filePath,userType,amount);
	}
	
	private int processInputFile(String inputFilePath, String userType, int amount) {
		int discountAmount=0;
		Path pathToFile = Paths.get(inputFilePath);
		try(BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)){
	     Map<String,Integer> index=new HashMap<>();
	     String line = br.readLine();
	     String[] attributes=line.split(","); 
	     for (int in=0;in<attributes.length;in++) {
	    	 index.put(attributes[in], in);
		}
	      while (line != null) { 
	    	 attributes = line.split(","); 
	    	  if(userType.equalsIgnoreCase(attributes[index.get("userType")])  && amount > Integer.parseInt(attributes[index.get("start")])){
	    		  if(Integer.parseInt(attributes[index.get("end")]) < amount){
	    			  int amountToBeDiscounted=Integer.parseInt(attributes[index.get("end")])-Integer.parseInt(attributes[index.get("start")]);
	    				 discountAmount +=(amountToBeDiscounted * Integer.parseInt(attributes[index.get("discountPerc")])/100);
	    			  
	    		  }else{
	    			  int amountToBeDiscounted=amount-Integer.parseInt(attributes[1]);
	    				 discountAmount +=(amountToBeDiscounted * Integer.parseInt(attributes[index.get("discountPerc")])/100);
	    		  }
	    	  }
	    	  
	    	  line = br.readLine(); 
	    	  
	      }
	      }catch (IOException e) {
			e.printStackTrace();
		}finally {
			
		}
	    return discountAmount ;
	}
	
	
}
