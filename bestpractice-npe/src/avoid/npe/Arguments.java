package avoid.npe;
public class Arguments {
    
    public static void main( String[] args ) {
      System.out.println( getLength( "Test" ) ); 
    }

    public static int getLength( String s ) {
        if( s == null)
         throw new IllegalArgumentException();

        return s.length();
    }
}