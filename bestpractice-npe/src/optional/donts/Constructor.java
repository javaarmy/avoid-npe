package optional.donts;

import java.util.Objects;
import java.util.Optional;

public class Constructor {


}

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
    
