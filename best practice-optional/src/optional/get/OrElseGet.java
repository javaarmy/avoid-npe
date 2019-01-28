package optional.get;

import java.util.Optional;

public class OrElseGet {
    public static void main(String[] args) {
        
        Optional<String> str = Optional.empty();

        //Avoid
        if( str.isPresent() ) {
            System.out.println( str.get() );
        } else {
            System.out.println( getDefaultString() );
        }

        //Adopt
        System.out.println( str.orElseGet(OrElseGet::getDefaultString));
    }

    public static String getDefaultString(){

        return Math.random() % 2 ==0 ? "Even String" :"Odd String";
    }
}