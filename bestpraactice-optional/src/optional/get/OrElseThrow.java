package optional.get;

import java.util.Optional;

public class OrElseThrow {
    public static void main(String[] args) {
        Optional<String> optStr = args.length > 0 ? Optional.empty() : Optional.of("ARGUMENTS");

        //Avoid
        if( optStr.isPresent() )
            System.out.println( optStr.get() ); //isPresent()-get() pair has a bad reputation, we do have alternatives.

        //Adopt
        System.out.println( optStr.orElseThrow() );

        //Adopt
        System.out.println( optStr.orElseThrow(RuntimeException::new) ); //throw custom exception.
    }

}