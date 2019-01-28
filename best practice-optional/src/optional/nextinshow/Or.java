package optional.nextinshow;

import java.util.Optional;

/**
 * orElse() and orElseGet() methods both returns unwrapped values.
 * the or() method that returns another Optional lazily if our Optional is
 * empty. If our first Optional has a defined value, the lambda passed to the
 * or() method will not be invoked
 */
public class Or {
    public static void main(String[] args) {
        
        Optional<String> value = Optional.of("test");
        Optional<String> defaultValue = Optional.of("default");

        //Now
        value.orElse( defaultValue.get() );

        value.orElseGet(()-> defaultValue.get() );
        
        //Next
        value.or( ()-> defaultValue);
    }
}