package optional.donts;

import java.util.Optional;

public class SetterArguments {
    public static void main(String[] args) {
        
    }
}

//Avoid
class DomainAvoid {
    
    private Optional<String> postcode; // optional field, thus may be null
    
    public Optional<String> getPostcode() {
        return postcode;
    }
    
    public void setPostcode(Optional<String> postcode) {
        this.postcode = postcode;
    }
    
}

//Adopt
class DomainAdopt {
    private String postcode; // optional field, thus may be null

    public Optional<String> getPostcode() {
      return Optional.ofNullable(postcode);
    }
    public void setPostcode(String postcode) {
       this.postcode = postcode;
    }
}
