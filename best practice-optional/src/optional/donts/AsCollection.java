package optional.donts;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AsCollection {

    public static void main(String[] args) {
        // Avoid
        Map<String, Optional<String>> items1 = new HashMap<>();
        items1.put("I1", Optional.ofNullable("one"));
        items1.put("I2", Optional.ofNullable("two"));    

        //Adopt
        Map<String, String> items2 = new HashMap<>();
        items2.put("I1", "one");
        items2.put("I2", null);

        //Approach1
        System.out.println( items2.containsKey("I3") ? items2.get("I3"):"Unknown");

        //Approach2
        System.out.println( items2.getOrDefault("I2", "Unknown") );

        //Approach3
        System.out.println( items2.computeIfAbsent("I3", (str)-> "Unknown" ) );



    }
    
}