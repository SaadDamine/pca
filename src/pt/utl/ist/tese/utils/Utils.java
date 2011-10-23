//
//  Utils.java
//  Artists
//
//  Created by andré on 11/1/08.
//  Copyright (c) 2008 __MyCompanyName__. All rights reserved.
//

package pt.utl.ist.tese.utils;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.grlea.log.SimpleLogger;

public class Utils {
	
	@SuppressWarnings("unused")
	private static final SimpleLogger log = new SimpleLogger(Utils.class);
	
	private final static String[] BASIC_ROMAN_NUMBERS = { "m", "cm", "d", 
														  "cd", "c", "xc", 
														  "l", "xl", "x", 
														  "ix", "v", "iv", "i" };
	
	private final static int[] BASIC_VALUES = { 1000, 900, 500, 400, 100, 
												90, 50, 40, 10, 9, 5, 4, 1 };
	
	
	public static String intToRoman (int value) {
		if ( value <= 0 && value >= 4000) {
			return null;
		}
		
		String romanString = "";
		
		int remainder = value;
		for (int i = 0; i < BASIC_VALUES.length; i++) {
			while (remainder >= BASIC_VALUES[i]) {
				romanString += BASIC_ROMAN_NUMBERS[i];
				remainder -= BASIC_VALUES[i];
			}
		}
		return romanString;
	}
	
	public static boolean checkEmailPattern( String email ) {
		
		boolean status;
		
		String emailPattern = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*";
		emailPattern += "@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+";
		emailPattern += "(?:[A-Z]{2}|com|org|net|gov|mil|biz|info|mobi|name|aero|jobs|museum)";
		
		Pattern p = Pattern.compile(emailPattern);
		
		Matcher m = p.matcher(email);
		
		status = m.matches();

		return status;
	}
	
	// 	Serializable methods
	
	public static byte[] toBlob(Object obj) throws java.io.IOException{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(obj);
		oos.flush();
		oos.close();
		bos.close();
		byte[] data = bos.toByteArray();
		return data;
	}
	
	public static Object toObject(byte[] array) throws java.io.IOException{
		ByteArrayInputStream bos = new ByteArrayInputStream(array);
		ObjectInputStream oos = new ObjectInputStream(bos);
		Object obj = null;
		try{
			obj = oos.readObject();
		}catch  (java.lang.ClassNotFoundException e){
			System.out.println("Fui com os porcos a converter em objecto: " + e);
		}
		oos.close();
		bos.close();
		return obj;
	}

}
