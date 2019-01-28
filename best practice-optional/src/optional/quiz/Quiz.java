package optional.quiz;

import java.math.BigInteger;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Quiz {
    public static void main(String[] args) {
        Optional<BigInteger> first = getFirstValue();
        Optional<BigInteger> second = getSecondValue();
        // add first and second , treating empty as zero , returning an optional of the sum 
        // if both or empty return an empty.

    }

        public static Optional<BigInteger> getFirstValue() {
           return Optional.of(BigInteger.TEN);
        }

        public static Optional<BigInteger> getSecondValue() {
            return Optional.of(BigInteger.ONE);
            
        }
        
    BiFunction<Optional<BigInteger>,Optional<BigInteger>,Optional<BigInteger>> addition = (first,second) -> {
        

       return Optional.of(first.orElse(BigInteger.ZERO)
             .add(first.orElse(BigInteger.ZERO)));
        
    }
}