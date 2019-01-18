package  optional.create;

import java.util.Optional;
/**
 * Optional.empty() is the more advanced way to create an empty object 
 * by getting rid of NullPointerException.
 */
public class Empty {
    public static void main(String[] args) {
        //Avoid
        String str = null;
        
        if( str != null ) {
            System.out.println( str.length() ) ;
        } else {
            System.out.println( "null string");
        }

        //Adopt
        Optional<String> optStr = Optional.empty();
        
        System.out.println( optStr.orElse("null string") );
        
    }
}