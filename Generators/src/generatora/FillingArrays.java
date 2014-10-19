package generatora;
import java.util.Arrays;


public class FillingArrays {

	public static void main(String[] args) {
		int size = 6;
		boolean[] a1 = new boolean[size];
		int[] a2 = new int[size];
		String[] a3 = new String[size];
		Arrays.fill(a1, true);
		System.out.println("a1 = " + Arrays.toString(a1));
		Arrays.fill(a2, 10);
		System.out.println("a2 = " + Arrays.toString(a2));
		Arrays.fill(a3, "Last");
		System.out.println("a3 = " + Arrays.toString(a3));
		
	}

}
