package optional.donts;

import java.util.Optional;

public class Fields {
    //Avoid
    Optional<String> zip;
    Optional<String> emptyZip = Optional.empty();

    //Adopt
    String zipStr;
    String emptyZipStr = ""; 
    
}