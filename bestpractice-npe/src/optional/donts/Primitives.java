package optional.donts;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

public class Primitives {
    public static void main(String[] args) {
        //Avoid
        Optional<Integer> priceInt = Optional.of(50);
        Optional<Long> priceLong = Optional.of(50L);
        Optional<Double> priceDouble = Optional.of(50.43d);

        //Boxing and unboxing are expensive operations that are prone to induce performance penalties. 
        //Adopt
        OptionalInt priceInt2 = OptionalInt.of(50);           // unwrap via getAsInt()
        OptionalLong priceLong2 = OptionalLong.of(50L);        // unwrap via getAsLong()
        OptionalDouble priceDouble2 = OptionalDouble.of(50.43d); // unwrap via getAsDouble()
    }
}