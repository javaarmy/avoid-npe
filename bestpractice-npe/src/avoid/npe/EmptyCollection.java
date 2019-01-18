package avoid.npe;

import java.util.Collections;
import java.util.List;

public class EmptyCollection {

    
    public static void main(String[] args) {
        System.out.println( getList() );
    }
    
    public static List<Integer> getList() {
        List<Integer> numbers = null;
        //Avoid
        //return numbers;

        //Adopt
      return (numbers == null) ? Collections.emptyList() : numbers;
      
        
}