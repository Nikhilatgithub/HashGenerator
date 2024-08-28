package com.bajaj.hashgen;

import java.io.File;
import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;

import com.bajaj.hashgen.utils.HashUtility;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DestinationHashGenerator {

	public static void main(String[] args) {
		
		  String prnNumber = args[0];
	        String jsonFilePath = args[1];

	        try {
	            // Read the JSON file
	            ObjectMapper objectMapper = new ObjectMapper();
	            JsonNode rootNode = objectMapper.readTree(new File(jsonFilePath));

	            String destinationValue =HashUtility.getDestinationValue(rootNode);

	            if (destinationValue == null) {
	                System.err.println("Key 'destination' is not found in the given JSON file.");
	                System.exit(1);
	            }

	            // it will Generate a random 8-character string
	            String randomStr = HashUtility.getRandomString(8);
	            String concatenatedString = prnNumber + destinationValue + randomStr;

	            // Generate the MD5 hash
	            String md5Hash = DigestUtils.md5Hex(concatenatedString);
	            System.out.println(md5Hash + ";" + randomStr);

	        } catch (Exception e) {
	            System.err.println("Error reading JSON file: " + e.getMessage());
	            System.exit(1);
	        }
	    }

}
