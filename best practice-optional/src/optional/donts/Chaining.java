package optional.donts;

import java.util.Optional;

public class Chaining {
    public static void main(String[] args) {
        
       System.out.println( getStatus() );
    }

    public static String getStatus(){
        String status = null ;
        //Avoid
            status = Optional.ofNullable(status).orElse("EMPTY");

        //Adopt
            status = status == null ? "EMPTY" : status;
            
        return status;

    }
}