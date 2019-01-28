package optional.nextinshow;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Stream {
    public static void main(String[] args) {
        List<Domain> domains = new ArrayList<>();
        
        //Now
        // filter out the values with Optional::isPresent and then perform mapping with the Optional::get function to extract values:
        List<String> filteredList = domains.stream()
                                    .map(Domain::getName)
                                    .filter(Optional::isPresent)
                                    .map(Optional::get)
                                    .collect(Collectors.toList());
        
        //Next
        List<String> filteredList1 =    domains.stream()
                                        .map(Domain::getName)
                                        .flatMap(Optional::stream)
                                        .collect(Collectors.toList());
        
    }

    
}

class Domain {
    private String name;

    public Optional<String> getName(){

        return Optional.ofNullable(name);
    }
}