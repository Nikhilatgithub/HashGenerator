package com.bajaj.hashgen.utils;

import java.security.SecureRandom;

import com.fasterxml.jackson.databind.JsonNode;

public class HashUtility {
	
	
	public static String getDestinationValue(JsonNode node) {
        if (node.isObject()) {
            if (node.has("destination")) {
                return node.get("destination").asText();
            }
            for (JsonNode child : node) {
                String result =  getDestinationValue(child);
                if (result != null) {
                    return result;
                }
            }
        } else if (node.isArray()) {
            for (JsonNode child : node) {
                String result = getDestinationValue(child);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }
	
	 public static String getRandomString(int length) {
	        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	        StringBuilder result = new StringBuilder();
	        SecureRandom random = new SecureRandom();
	        for (int i = 0; i < length; i++) {
	            result.append(characters.charAt(random.nextInt(characters.length())));
	        }
	        return result.toString();
	    }
}
