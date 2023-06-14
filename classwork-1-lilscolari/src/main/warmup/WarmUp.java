package main.warmup;
import java.util.Arrays;
import java.util.*;

/**
 * A selection of warmup static methods to get you familiar
 * with the taste of Java.
 * 
 * @author Cameron Scolari
 */
public class WarmUp {
	
    /**
     * Generates a SHEESH String (Capitalized "SH" on both sides
     * of some number of 'E's) with as many E's in the middle as
     * specified by the numE parameter. Useful for a variety
     * of social media applications.
     * 
     * @param numE The number of E's in the returned SHEESH
     * @return The SHEESH String
     */
    public static String sheeshGen (int numE) throws IllegalArgumentException {
    	String sh = "SH";
    	String amount = ("E".repeat(numE));
    	return (sh + amount + sh);
    }
    
    /**
     * Returns the number of times that the given digit (not really
     * forbidden, I'm just dramatic) appears in the given num. If
     * digit is not a number between 0-9, throws an
     * IllegalArgumentException.
     * 
     * @param num The number in which to count the number of times the
     * forbidden digit appears
     * @param digit The forbidden digit (0-9, otherwise throws an
     * IllegalArgumentException)
     * @return The number of times digit appears in num
     */
    
    public static int forbiddenDigit (int num, int digit) throws IllegalArgumentException {
    	int max = 0;
        for (int i = 0; i < Integer.toString(num).length(); i++) {
        	String number = Integer.toString(num);
        	String sub_str = number.substring(i, i + 1);
        	if (sub_str.equals(Integer.toString(digit))) {
        		max++;
        }
        }
        return max;
    }
    
    /**
     * Returns the number of unique, unrepeated words that are found
     * in the given sentence sent. Words are defined as space-separated,
     * non-empty, contiguous sequences of characters that are case-sensitive
     * (i.e., "cat" and "Cat" are two different words).
     * 
     * @param sent The sentence in which to count unique words
     * @return The number of unique, unrepeated words in sent
     */
    /*
     * Credit to GeeksForGeeks and W3Schools for inspiration and ideas.
     */
    public static int uniqueWords (String sent) 
    {
    	if (sent.length() == 0) 
    	{
    		return 0;
    	}
    	System.out.println("new problem");
    	int count = 0;
    	String[] str_array = sent.split(" ");
    	Map<String, Integer> words = new HashMap<String, Integer>();
    	for (var word : str_array) 
    	{
    		if (words.containsKey(word))
    		{
    			words.computeIfPresent(word, (k, v) -> v + 1);
    		}
    		else 
    		{
    			words.put(word, 1);
    		}
    	}
    	for (Map.Entry<String,Integer> entry : words.entrySet()) 
    	{
    		int value = entry.getValue();
    		if (value == 1)
    		{
    			count ++;
    		}
    	}
    	return count;
    }
}

