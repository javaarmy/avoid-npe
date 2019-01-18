package avoid.npe;

/**
 * assert statements should be reserved for testing and disabled in production because errors should have been resolved by then,
 * and enabling assertions affects performance.
 */
public class Assertions {
    public static void main(String[] args) {
        getLength("test");
    }


    public static int getLength(String s) {
    /* Ensure that the String is not null. */
        assert (s != null);
        return s.length();
   
    }
}
