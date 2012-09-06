package com.github.loop.md5hash;

import java.security.MessageDigest;

public class StringToMD5 {
    
    String input;
    
    public StringToMD5(String inputText) {
	input = inputText;
    }
    
    public StringToMD5() {
    }
    
    
    public final String getHashFromString(String value) { 
	  if (value == null || value.length() == 0) { 
	    return null; 
	  } 

	  try { 
	    MessageDigest hashEngine = MessageDigest.getInstance("MD5"); 
	    hashEngine.update(value.getBytes("iso-8859-1"), 0, value.length()); 
	    return convertToHex(hashEngine.digest()); 
	  } catch (Exception e) { 
	    return null; 
	  } 
	} 
    
    private static final String convertToHex(byte[] data) { 
	  if (data == null || data.length == 0) { 
	    return null; 
	  } 

	  final StringBuffer buffer = new StringBuffer(); 
	  for (int byteIndex = 0; byteIndex < data.length; byteIndex++) { 
	    int halfbyte = (data[byteIndex] >>> 4) & 0x0F; 
	    int two_halfs = 0; 
	    do { 
	      if ((0 <= halfbyte) && (halfbyte <= 9)) 
	        buffer.append((char) ('0' + halfbyte)); 
	      else 
	        buffer.append((char) ('a' + (halfbyte - 10))); 
	      halfbyte = data[byteIndex] & 0x0F; 
	    } while (two_halfs++ < 1); 
	  } 

	  return buffer.toString(); 
	} 

}
