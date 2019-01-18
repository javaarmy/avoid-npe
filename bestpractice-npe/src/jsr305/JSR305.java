package jsr305;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class JSR305 {
    public static void main(String[] args) {
        String arg= null;
        mustbeNotNull(arg); // here tool warns you arg is null.
    }

    public static String maybeNullable(@Nullable String arg ) {

        return arg.trim(); //here tool warns becuase you need to do null check.
    }

    public static String mustbeNotNull(@Nonnull String arg) {
        return arg.trim();
    }

    @Nonnull
    public static String mustreturnNonNull(String arg ) {
        return null; //here tool warns because you are returning null;
    }
}