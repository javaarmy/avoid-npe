package jsr305.nullcheck;

import javax.annotation.Nullable;


public class NullableCheck {

	public static void main( String[] args )
	   {
	      testNullable( "" );
	   }
	   
	   public static String testNullable(@Nullable String str) {
	      return str.length() > 2 ? "": "false";
	   }
}
