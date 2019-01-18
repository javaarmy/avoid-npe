package optional.donts;

import java.util.Optional;

public class Synchronized {
    public static void main(String[] args) {
        Optional<String> str = Optional.of("TEST");
        
        //Optional class is a value based class like LocalDateTime.
        synchronized(str) {
            
        }    
    }
    
}