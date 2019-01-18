package avoid.npe;

public class Literals
{
    public static void main( String[] args) {
      String str= null;

      //Avoid
      if( str.equals("Test") ) {
            /* Change str to correct NPE*/
      }

      //Adopt
      if( "Test".equals(str) ) {
           /* best to use */
      }
    }
}