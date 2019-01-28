package optional.advanced;

import java.util.Optional;

public class IfPresent {
    public static void main(String[] args) {
        Optional<String> optStr = args.length > 0 ? Optional.empty() : Optional.of("ARGUMENTS");

        //Avoid
        System.out.println( optStr.get() ); // if "String" is empty then this code will throw a java.util.NoSuchElementException

        //Avoid
        if( optStr.isPresent() )
            System.out.println( optStr.get() ); //isPresent()-get() pair has a bad reputation, we do have alternatives.
        
        //Adopt
        optStr.ifPresent( System.out::println );

    }
}