package optional.advanced;

import java.util.Optional;

public class Filter {

    public static void main(String[] args) {
        
        Optional<String> str = Optional.of("test");
        //Avoid
        if( str.isPresent() )
            System.out.println( str.get().length() > 5 ? true : false );
        
        //Adopt
         System.out.println ( str.filter( s -> s.length() > 5).isPresent() );

    }
}