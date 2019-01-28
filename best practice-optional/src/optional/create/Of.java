package optional.create;

import java.io.Serializable;
import java.util.Optional;

public class Of {
    public static void main(String[] args) {
        CustomerDomain domainObj = new CustomerDomain("test name");
            //inside business logic 
            domainObj.setDomainName(
                //its a bug that we passed null instead of "odd test name"
                Math.random() % 2 == 0 ? "even test name" : null ); 

            domainObj.getDomainName().orElse( "Unknown Domain"); 
    }

   
}

class CustomerDomain implements Serializable {
    private static final long serialVersionUID = 1L;

    public CustomerDomain(String domainName ) {
         this.domainName = domainName;
    }
    private String domainName; //Mandatory field, no chance of null;
    
    public Optional<String> getDomainName() {
        return Optional.ofNullable(domainName); //it should be of()
    }
    
    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }
}