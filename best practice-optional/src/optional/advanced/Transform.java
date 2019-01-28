package optional.advanced;

import java.util.Optional;

public class Transform {
    public static void main(String[] args) {
        //The map()method applies the function argument to the value, then returns the result wrapped in an Optional
        
        // transform name to upper case
        //Avoid
        Optional<String> lowername = Optional.of("test");

        Optional<String> uppername;
        if (lowername.isPresent()) {
            uppername = Optional.of(lowername.get().toUpperCase());
        } else {

        uppername = Optional.empty();
        
        //Map unwraps value -> process it -> wraps back to Optional
        //Adopt
        String uppernameStr = lowername.map(Transform::getPreferredName).get();    
        
        }
        
        //The flatMap()method takes a function argument that is applied to anOptionalvalue, 
        // and then returns the result directly.
    
        //Avoid
        lowername.map(Transform::getPreferredNameOpt).get().map(String::toUpperCase);

        //flatMap() unwraps value -> process it -> no wraping
        //Adopt
        lowername.flatMap(Transform::getPreferredNameOpt).map(String::toUpperCase);

    }
    public static String getPreferredName(String str) {
        return "preferred";
    }

    public static Optional<String> getPreferredNameOpt(String str) {
        return Optional.of("preferred" + str);
    }
}