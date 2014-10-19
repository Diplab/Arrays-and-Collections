import java.util.Arrays;


public class ThreeDimensionArray {

	public static void main(String[] args) {
		int [][][] a =new int[5][6][7];
		
		System.out.println(a.length);
		System.out.println(a[0].length);
		System.out.println(a[0][1].length);
		System.out.println(Arrays.deepToString(a));
		
	}

}
