package optional.nextinshow;

import java.util.Optional;

/**
 * easily return a true if an Optional is empty via the isEmpty() method
 */
public class IsEmpty {
    public static void main(String[] args) {
        Optional<String> str = Optional.of("test");

        //Now
        System.out.println( !str.isPresent() );
        
        //Next
        System.out.println( str.isEmpty() );
    }
}