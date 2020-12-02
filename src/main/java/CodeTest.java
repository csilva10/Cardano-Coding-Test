import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.runner.Version;

/**
 * 
 * @author Carlos Silva
 * Cardano Software Engineer Coding Test
 *
 */
public class CodeTest {
    public static void main(String[] args) {
        System.out.println(">> Calls to all completed tests.");
        System.out.println(">> JUnit version is: " + Version.id());
        
        CodeTestSpec cts = new CodeTestSpec();        
        System.out.println(">> Call ReverseArray -> Done");
        cts.reverseArray_returnsExpectedResult();
        System.out.println(">> Call UpperCase -> Done");
        cts.uppercaseArray_returnsExpectedResult();
        System.out.println(">> Call FindWordCount -> Done");       
        cts.findWordCount_returnsExpectedResult(); 
        System.out.println(">> Call ComposeU -> Done");
        cts.composeU_returnsExpectedResult();
        System.out.println(">> Call writeContentsToConsole -> Done");
        cts.writeContentsToConsole_returnsExpectedResult();
        System.out.println(">> Call handleInvalidArgument -> Done");           
        cts.handleInvalidArgument_returnsExpectedResult();
        System.out.println(">> Call puzzle -> Done");
        cts.puzzle_returnsExpectedResult();            
    }
        
 
    /**
     * Create a function that accepts an array of numbers and returns a reversed array (e.g. [1,2,3] would be [3,2,1]
     * @param input
     * @return String[] reversed array
     */
    public static String[] reverseArray(String[] input) {
        Collections.reverse(Arrays.asList(input));        
        return input;
    }

    /**
     * Write a function that transforms an array of strings to an upper-case array of strings
     * @param input
     * @return String[] upper-case array of strings
     */
	public static String[] uppercaseArray(String[] input) {
		for (int i = 0; i < input.length; i++) {
			input[i] = input[i].toUpperCase();
		}	
		return input;
	}


    /**
     * Given a sentence create a function that returns the number of unique words
     * @param text
     * @param wordToFind
     * @return int number of unique words
     */
    public static int findWordCount(String text, String wordToFind) {
		Pattern pattern = Pattern.compile(wordToFind);
		Matcher matcher = pattern.matcher(text);
		int count = 0;
		while (matcher.find()) {
			count++;
		}
		return count;
    }

  /**
   * Write a function 'composeu' that takes two unary functions and returns a unary function that calls them both. 
   * A unary function has a single argument and a single return value    
   * @param f1
   * @param f2
   * @return Function<Integer,Integer>
   */
    public static Function<Integer,Integer> composeU(Function<Integer,Integer> f1, Function<Integer,Integer> f2){
		Function<Integer, Integer> result = f2.compose(f1);
		return result;
    }

    /**
     * Write a function that reads from a file and prints the contents to the console
     */
    public static void writeContentsToConsole()  {      
    	
    	// JAVA_11 Implementation     	 	
    	readFile("src/main/resources/test1.txt");
    	
    	/*
    	// JAVA_8 Implementation is working too
    	Stream<String> file = null;
    	try {
			file = Files.lines(Paths.get("src/main/resources/test1.txt"));
			file.forEach(System.out::println);			
		} catch (IOException e) {			
			e.printStackTrace();
		} finally {
			if (file != null) {
				file.close();
			}
		}
    	 */    	
    }

    /**
     * Example of how a function would handle an invalid argument
     */
    public static void handleInvalidArgument() {
		int pctg = 150;
		try {
			setPercentage(pctg);
		} catch (IllegalArgumentException exc) {
			System.out.println(exc.getMessage());
			throw new IllegalArgumentException("Test Message: pctg has an invalid value");
		}
    }
    

    /**
     * Set percentage
     * @param pctg
     * @throws IllegalArgumentException
     */
    public static void setPercentage(int pctg) throws IllegalArgumentException {
        if( pctg < 0 || pctg > 100) {
        	throw new IllegalArgumentException(">> pctg has an invalid value <<");
         }
    }
    

    /**
     * Write a console application that accepts a random sequence of numbers and loops through looking for 2 equal, consecutive numbers.
 	 * When found write 'Snap' to the console else print out the number (e.g. 1,3,5,5,'Snap').
     */
    public static void puzzle() { 

		// Variables
		int num = 1;
		int prev = 12;
		int max = 10;

		int i = 0;
		List<String> list = new ArrayList<String>();
		//Random rand = new Random();
		Random rand = new Random(getSeed());
		boolean found = false;
		while (i < max && !found) {
			num = rand.nextInt(10);
			System.out.print(num);
			list.add(String.valueOf(num));
			if (i < max - 1) {
				System.out.print(",");
			}
			if (i > 0 && num == prev) {
				System.out.print("'Snap'");
				list.add("'Snap'");
				found = true;
			} else {
				prev = num;
			}
			i++;
		}
		System.out.println();
		String text = list.toString().join(",", list);
		String localPath = "src/main/resources/test2.txt";
		try {
			writeFile(text, localPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}  
	
    /**
     * Fixed seed so that random number generation sequence is same to realize the test.
     * @return
     */
    static long getSeed() {
        return 1;
    }
    

	/**
	 * Method to associate the number of occurrences of a word in a text.
	 * @param str
	 * @return Object []
	 */
	 public static Object [] aux (String str) {
		ArrayList<String> list = new ArrayList<String>();   
		Object [] strResult;
		
		String[] strArray = str.split("\\s+");
        Map<String, Integer> hashMap = new LinkedHashMap<String, Integer>(); 
        for (int i=0; i<strArray.length; i++) {
        	if (!hashMap.containsKey(strArray[i])) {
        		hashMap.put(strArray[i], findWordCount(str, strArray[i]));
        	}
        } 
             
        for (Entry<String, Integer> element : hashMap.entrySet()) {
        	list.add(element.toString());
        }    
        
        strResult = list.toArray();
        return strResult;
        
	}

    /**
     * Read a file whose argument is the path where the file is located 
     * and shows the content by console.
     * @param path
     */
	 public static void readFile(String path) {
    	try {
    		String content = Files.readString(Path.of(path));
    		System.out.println(content);
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
	/**
	 * Write a text given as an argument, in a file whose argument is the path where the file is located.
	 * @param text
	 * @param localPath
	 * @throws IOException
	 */
    public static void writeFile(String text, String localPath) throws IOException{
    	Path path = Paths.get(localPath); 
    	Files.write(path, text.getBytes());
    }    

    /**
     * Write data given as an argument, in a file whose argument is the path where the file is located.
     * @param pathFile
     * @param data
     * @param create
     */
    public static void writeFileJ11(String pathFile, String data, boolean create) {
		try {
			Path path = null;
			if (create) {
				path = Files.writeString(Path.of(pathFile), data, StandardOpenOption.CREATE_NEW);
			} else {
				path = Files.writeString(Path.of(pathFile), data, StandardOpenOption.APPEND);
			}
			System.out.println(path);
			String s = Files.readString(path);
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
    } 
}

