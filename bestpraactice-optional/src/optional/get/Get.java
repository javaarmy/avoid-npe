package optional.get;

import java.util.Optional;

public class Get {
    public static void main(String[] args) {

        Optional<String> optStr = args.length == 0 ? Optional.empty() : Optional.of("ARGUMENTS");

        //Avoid
        //if "String" is empty then this code will throw a java.util.NoSuchElementException
        System.out.println( optStr.get() ); 

        //Prefer
        //isPresent()-get() pair has a bad reputation, we do have alternatives.
        if( optStr.isPresent() )
            System.out.println( optStr.get() ); 

    }
}