package optional.nextinshow;

import java.util.Optional;

/**
 * easily return atrueif anOptionalis empty via theisEmpty()method
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