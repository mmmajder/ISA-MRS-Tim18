package mrsa.tim018.utils;

public class StringUtils {


	public static String removeSpaces(String input){
	    return input.trim().replaceAll("\\s+", " ");
	}
	
	public static String capitalizeString(String string) {
	    return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
	}
	
	public static String uniformString(String string) {
	    return string.charAt(0) + string.substring(1).toString().toLowerCase();
	}
	
	public static String capitalizeAllWords(String string) {
		String[] words = string.split("\\s+");
	    for (int index = 0; index < words.length; index++) {
	        words[index] = capitalizeString(words[index]);
	    }
	    return String.join(" ", words);
	}
	
	public static String capitalizeFirstWord(String string) {
		String[] words = string.split("\\s+");
	    for (int index = 0; index < words.length; index++) {
	    	if(index==0) {
	    		words[index] = uniformString(words[index]);
	    	}
	    	else {
	    		words[index] = capitalizeString(words[index]);
	    	}
	    }
	    return String.join(" ", words);
	}
}
