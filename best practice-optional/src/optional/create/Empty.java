package  optional.create;

import java.util.Optional;
/**
 * Optional.empty() is the more advanced way to create an empty object 
 * by getting rid of NullPointerException.
 */
public class Empty {
    public static void main(String[] args) {
       
         //Avoid
         Optional<String> optStr = null;
       
         //Adopt
         Optional<String> optStr1 = Optional.empty();

            
    }

}