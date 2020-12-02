import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;

import org.junit.Test;


/*
 *   Please code the tests in the format of reverseArray_returnsExpectedResult. This is an example of how we write our tests at Cardano.
 *
 *   Test 1-4 tests are easy as the function returns a result that can be asserted. Tests 5-7 are more difficult to assert as they are
 *   void, use your knowledge to write a meaningful test.
 *
 *   Feel free to use the internet to help you with you answers but make sure you understand the answer as we will ask you questions.
 */

public class CodeTestSpec {
	
	
    @Test    
    public void reverseArray_returnsExpectedResult() {
        // arrange
        final String[] EXPECTED = {"x", "y", "z"};

        // act
        final String[] actual = CodeTest.reverseArray(new String[] {"z", "y", "x"});

        // assert
        assertArrayEquals(EXPECTED, actual);
    }

    @Test   
    public void uppercaseArray_returnsExpectedResult() {
        // arrange
        final String[] EXPECTED = {"CAT", "DOG", "BIRD"};

        // act
        final String[] actual = CodeTest.uppercaseArray(new String[] {"cat", "dog", "bird"});

        // assert
        assertArrayEquals(EXPECTED, actual);
    }

    @Test    
    public void findWordCount_returnsExpectedResult() {  	
        // arrange
        final String[] EXPECTED =  new String[] {"the=2", "cat=1", "jumped=1", "over=1", "mat=1"};
    	
    	// act
        final Object[] actual = CodeTest.aux("the cat jumped over the mat");
    	
        // assert
        for (int i=0; i< actual.length; i++) {
        	assertTrue(EXPECTED[i].equals(actual[i]));  
        } 
    }

    @Test    
    public void composeU_returnsExpectedResult() {          
        
        Function<Integer, Integer> f1 = n -> n*3;        
        Function<Integer, Integer> f2 = n -> n+2;         
       
        Function<Integer,Integer> fresult = CodeTest.composeU(f1, f2);            
        
        // arrange
        final Integer EXPECTED =  20;
    	
    	// act
        final Integer actual = fresult.apply(6);
        
        // assert
        assertEquals(EXPECTED, actual);
    }

    
    @Test    
    public void writeContentsToConsole_returnsExpectedResult() {    	
  	
		CodeTest.writeContentsToConsole();       

		// arrange
		 String EXPECTED =  ">> WITH JAVA11 -> 123 - 456 -789 - 910 <<";
		
		// act
		String actual ="";
    	try {
    		 actual = Files.readString(Paths.get("src/main/resources/test1.txt"));    		
    	} catch (Exception e) {
			e.printStackTrace();
		}    
    	
        // assert
    	assertEquals(EXPECTED, actual);    	
    }


    
    //JUnit5
    @Test   
    public void handleInvalidArgument_returnsExpectedResult() {
    	
    	Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
    		CodeTest.handleInvalidArgument();           
        });    
    	
        // arrange
        final String EXPECTED =  "Test Message: pctg has an invalid value";
    	
    	// act
        final String actual = exception.getMessage();
    	
        // assert
        assertEquals(EXPECTED, actual);
    }
    
    //JUnit4
    @Test(expected=IllegalArgumentException.class)    
    public void handleInvalidArgument_returnsExpectedResult2() {
    	CodeTest.setPercentage(150);    	
    }
    


	@Test   
    public void puzzle_returnsExpectedResult() {
        
		CodeTest.puzzle();
		
		// arrange
        Path path = Paths.get("src/main/resources/test2.txt");
        String EXPECTED ="";        
		try {
			EXPECTED = Files.readString(path);			
		} catch (IOException e) {			
			e.printStackTrace();
		} 		
		
		// act
  	    // read console
        final String actual = "5,8,7,3,4,4,'Snap'";
        
        // assert
        assertEquals(EXPECTED, actual); 
  	        
    }

		
    
}
