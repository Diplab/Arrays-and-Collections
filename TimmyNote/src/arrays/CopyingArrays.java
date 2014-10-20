package arrays;

import java.util.Arrays;

import util.Generated;
import util.RandomGenerators;

/**
 * Show that {@link System#arraycopy(Object, int, Object, int, int)} copies
 * array (only with reference). It may throw {@link IndexOutOfBoundsException}.
 * 
 * @author timmy00274672
 * 
 */
public class CopyingArrays {

    /**
     * @param args
     */
    public static void main(String[] args) {
	String[] stringArray1 = Generated.array(String.class,
		new RandomGenerators.StringGenerator(), 5);
	/*
	 * get java.lang.ArrayIndexOutOfBoundsException
	 */
	// String[] stringArray2 = new String[3];
	String[] stringArray2 = new String[stringArray1.length];
	System.arraycopy(stringArray1, 0, stringArray2, 0, stringArray1.length);
	System.out.println(String.format("Array1: %s",
		Arrays.toString(stringArray1)));
	System.out.println(String.format("Array2: %s",
		Arrays.toString(stringArray2)));

	// Just copy the reference
	System.out.println(String.format(
		"stringArray1[0] == stringArray2[1] = %b",
		stringArray1[0] == stringArray2[1]));
    }

}
