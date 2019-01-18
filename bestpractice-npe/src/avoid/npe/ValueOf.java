package avoid.npe;

import java.util.UUID;

public class ValueOf {
    public static void main(String[] args) {

        UUID uuid = UUID.randomUUID();
        uuid = null;
        //Avoid
        System.out.println( uuid.toString() );


        //Adopt
        System.out.println( String.valueOf( uuid ) );
    }
}