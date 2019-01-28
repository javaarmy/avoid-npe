package optional.donts;

import java.util.Optional;

public class Identity {
    public static void main(String[] args) {
        
        Optional<String> actualItem = Optional.of("test");
        Optional<String> expectedItem = Optional.of("test");     
    
        // Avoid Identity sensitive operations 
        // include equality (==), identity hash-based, or synchronization

        //Avoid
        if ( actualItem == expectedItem ) System.out.println("Equal");
         
        //Adopt
        if ( actualItem.equals(expectedItem) ) System.out.println("Equal");
        
        Optional<String> str = Optional.of("TEST"); 
        //Optional class is a value based class like LocalDateTime.
        synchronized(str) {
            
        }   
    }


}