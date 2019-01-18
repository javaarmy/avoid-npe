package avoid.npe;

import org.apache.commons.lang3.StringUtils;

public class Utilities {
    public static void main(String[] args) {
        String str = "";
        //Avoid
        if( str != null && !str.isEmpty() ) {
            // avoid using these statements
        }
       
        //Adopt
        StringUtils.isNoneEmpty( str );
        StringUtils.isEmpty( str );
        StringUtils.trimToEmpty(str);
        
        
    }
}