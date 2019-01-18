package optional.create;

import java.io.Serializable;
import java.util.Optional;

public class OfNullable {
    public static void main(String[] args) {
        CustomerDomain domainObj = new CustomerDomain();
       
        domainObj.getPostcode().ifPresent( System.out::println );
      
    }

}

class CustomerDomain implements Serializable {
    private static final long serialVersionUID = 1L;

    private String postcode; // optional field, thus may be null
    
    public Optional<String> getPostcode() {
        return Optional.ofNullable(postcode);
    }
    
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
