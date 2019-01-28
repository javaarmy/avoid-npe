package optional.nextinshow;

import java.util.Optional;

/**
 * if the Optional is empty we want to log it or track that fact by incrementing
 * some metric
 */
public class IfPresentOrElse {
    public static void main(String[] args) {
        Optional<String> value = Optional.of("get");
        
        //Now
        if ( value.isPresent() )
            System.out.println("isPresent");
        else
            System.out.println("Empty");

        //Next
        value.ifPresentOrElse( s -> System.out.println("isPresent"), 
                               () -> System.out.println("Empty") );
    }
}