package optional.donts;

import java.util.Optional;
import java.util.Collections;
import java.util.List;

public class EmptyCollection {


    //Avoid
    public static Optional<List<String>> getChildItems(int id){
        List<String> list = null;

        return Optional.ofNullable(list);
    }

    //Adopt
    public static List<String> getEmptyChildItems(int id) {
        List<String> list = null;

        return list == null ? Collections.emptyList(): list;
    }

}