package avoid.npe;

import java.util.Map;

public class Maps {
    public static void main(String[] args) {
        
        Map<String, String> map = Map.of("key1", "value1", "key2", "value2" );
        
        // Avoid
        String key = "key1";
        String value = map.get(key);

        System.out.println(value.toString()); // An exception will be thrown, if the value is null.

        // Adopt
        key = "key3";

        if (!map.isEmpty() || map.containsKey(key) || map.containsValue(value) ) {
    
            value = map.get(key);
            System.out.println(value.toString()); // No exception will be thrown.
        }

    }
   
}