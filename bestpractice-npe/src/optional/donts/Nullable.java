package optional.donts;

import java.util.Optional;

public class Nullable {
    public static void main(String[] args) {
        
        //Avoid
        Optional<String> str = null;

        //Adopt
        Optional<String> emptyStr = Optional.empty();

    }
}