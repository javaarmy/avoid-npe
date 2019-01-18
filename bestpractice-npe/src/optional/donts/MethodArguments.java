package optional.donts;

import java.util.Optional;

public class MethodArguments {
    public static void main(String[] args) {
        getPreferedName( Optional.empty() ) ;        
    }

    public static String getPreferedName( Optional<String> name ){

        if (name == null)
            throw new IllegalArgumentException();
    
        return name.orElse("anonymous");
    
    }

}
