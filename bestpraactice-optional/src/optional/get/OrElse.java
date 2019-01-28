package optional.get;

import java.util.Optional;

public class OrElse {

    public static String DEFAULT_STR = "Default/Unknown String";
    public static void main(String[] args) {
        Optional<String> str = Optional.empty();
        
        //Avoid
        if( str.isPresent() ) {
            System.out.println( str.get() );
        } else {
            System.out.println( DEFAULT_STR );
        }

        //Adopt
        System.out.println( str.orElse(  DEFAULT_STR ) );

    }
}