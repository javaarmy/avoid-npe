package optional.donts;

import java.util.Objects;
import java.util.Optional;

//as per Optional Api Note it is mainly intended to use for return value.
    // Not for Fields
    // Not for Method arguments
    // Not for constructors
    // Not for setter methods.
public class OnlyReturnValue {
    //Avoid
    Optional<String> zip;
    Optional<String> emptyZip = Optional.empty();

    //Adopt
    String zipStr;
    String emptyZipStr = ""; 
    


//Method Arguments
public static String getPreferedName( Optional<String> name ){

    if (name == null)
        throw new IllegalArgumentException();

    return name.orElse("anonymous");

}

}

//SetterArguments
//Avoid
class DomainAvoidS {
    
    private Optional<String> postcode; // optional field, thus may be null
    
    public Optional<String> getPostcode() {
        return postcode;
    }
    
    public void setPostcode(Optional<String> postcode) {
        this.postcode = postcode;
    }
    
}

//Adopt
class DomainAdoptS {
    private String postcode; // optional field, thus may be null

    public Optional<String> getPostcode() {
      return Optional.ofNullable(postcode);
    }
    public void setPostcode(String postcode) {
       this.postcode = postcode;
    }
}

//Constructors
//Avoid
class DomainAvoid {
    private final String name;               // cannot be null
    private final Optional<String> postcode; // optional field, thus may be null
    
    public DomainAvoid(String name, Optional<String> postcode) {
        this.name = Objects.requireNonNull(name, () -> "Name cannot be null");
        this.postcode = postcode;
    }
    public Optional<String> getPostcode() {
        return postcode;
    }
    
}

//Adopt
class DomainAdopt {
    private final String name;     // cannot be null
    private final String postcode; // optional field, thus may be null
    
    public DomainAdopt(String name, String postcode) {
        this.name = Objects.requireNonNull(name, () -> "Name cannot be null");
        this.postcode = postcode;
    }
    public Optional<String> getPostcode() {
        return Optional.ofNullable(postcode);
    }
}