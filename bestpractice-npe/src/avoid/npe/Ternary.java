package avoid.npe;

public class Ternary {
    /**
     *
     */

    private static final String EMPTY_STR = "";

    public static void main(String[] args) {
        
        String str = EMPTY_STR;
        
        String message = null;

        //Avoid
        if ( str == null ) {
            message = EMPTY_STR;
        } else {
            message = str.substring(0, 10);
        }

        //Adopt
        message = (str == null) ? EMPTY_STR : str.substring(0, 10);

        System.out.println( message );
    }

}