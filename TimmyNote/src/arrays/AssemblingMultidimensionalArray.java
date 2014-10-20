package arrays;

import java.util.Arrays;

/**
 * @author timmy00274672
 * 
 */
public class AssemblingMultidimensionalArray {

    public static void main(String[] args) {
	int[][] aIntegers;
	aIntegers = new int[3][]; // ok
	System.out.println(Arrays.deepToString(aIntegers));
	aIntegers = new int[3][2];
	System.out.println(Arrays.deepToString(aIntegers));
	aIntegers = new int[][]{{1,2},{2,3},{1,3}};
	System.out.println(Arrays.deepToString(aIntegers));
    }
}
