package cluster.myMethods;

public class DataManipulationMethods {
	
	public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String NUMBERS = "1234567890";
	
	/**Returns an array where holes are removed keeping the same order of values*/
	public static boolean shakeArray(Object[] array) {
		int lenght = array.length;
		
		if (lenght == 0) return false;
		
		boolean output = false;
		while(existsHoles(array)) {
			for (int i = 1; i < lenght; i++) {
				if (array[i-1] == null) {
					array[i-1] = array[i];
					array[i] = null;
					output = true;
				}
			}
		}
		
		return output;
	}
	
	/**Returns true if there is a null before a value different from null in an array*/
	public static boolean existsHoles(Object[] array) {
		int lenght = array.length;
		boolean output = false;
		
		if (lenght == 0) return output;
		
		for (int i = 1; i < lenght; i++) {
			if (array[i-1] == null && array[i] != null) {
				output = true;
			}
		}
		
		return output; 
	}
	
	/**Return a new array that is the concatenation of array1 and array2*/
	public static Object[] concatArrays(Object[] array1, Object[] array2) {
		Object[] output = new Object[array1.length + array2.length];
		int i, j;
		
		for (i = 0; i<array1.length; i++) {
			output[i] = array1[i];
		}
		
		for (j = i; j<array2.length; j++) {
			output[j] = array2[j];
		}
		
		return output;
		
		
	}
	
	/**Given an array of objects returns true if it contains the other object passed by parameter. Note: works only for if the method Object.equals() is defines*/
	public static boolean objArrayContains (Object[] objects, Object object) {
		boolean output = false;
		for (Object objectsItem : objects) {
			if (objectsItem.equals(object)) {
				output = true;
			}
		}
		return output;
	}
	
	/**Convert a number into its "2-digit format" as a String, e.g (int)3 --> (String)03*/
	public static String convertTo2Format(int number) {
		return convertToNFormat(number, 2);
	}
	
	/**Convert a number into its "4-digit format" as a String, e.g (int)91 --> (String)0091*/
	public static String convertTo4Format(int number) {
		return convertToNFormat(number, 4);
	}

	/**
	 * Returns the conversion of a number in a String made of N digits
	 * If the number it's negative it is the equivalent of doing number.toString()
	 * */
	public static String convertToNFormat(Integer number, int n) {
		String output = number.toString();
		if (number >=0 && integerLenght(number) < n) {
				int zerosToBeAdded = n - integerLenght(number);
				output = addNZerosBefore(number, zerosToBeAdded);
		}
		return output;
	}
	
	/**Returns a string with N zeros before the the number*/
	public static String addNZerosBefore(Integer number, int n) {
		String output = number.toString();
		String toBeAdded = "";
		
		for (int i=0; i<n; i++) {
			toBeAdded = toBeAdded + "0";
		}
		
		return toBeAdded + output;		
	}
	
	/**Returns the number of digits in an int number*/
	public static int integerLenght(Integer number) {
		return number.toString().toCharArray().length;
	}
	
	/**Returns true if the string passed by parameter contains a Latin Character in Upper Case*/
	public static boolean containsUpperCase(String s) {;
		return containsCharset(s, ALPHABET);
	}
	
	/**Returns true if the string passed by parameter contains a Latin Character in Lower Case*/
	public static boolean containsLowerCase(String s) {
		return containsCharset(s, ALPHABET.toLowerCase());
	}
	
	/**Returns true if the string passed by parameter contains a number*/
	public static boolean containsNumber(String s) {
		return containsCharset(s, NUMBERS);
	}
	
	/**Returns true if the string passed by parameter contains a char into the other String passed by parameter*/
	public static boolean containsCharset(String s, String charset) {
		boolean output = false;
		char[] uppercaseLetters = charset.toCharArray();
		for (char c : uppercaseLetters) {
			if (s.indexOf(c) != -1) {
				output = true;
			}
		}
		return output;
	}
	
}
