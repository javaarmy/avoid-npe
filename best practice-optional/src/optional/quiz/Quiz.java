package optional.quiz;

import java.math.BigInteger;
import java.util.Optional;

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
    
}