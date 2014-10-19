package Arrays;
import java.util.Arrays;


public class ComparingArrays {

	public static void main(String[] args) {
		int[] a1 = new int[10];
		int[] a2 = new int[10];
		Arrays.fill(a1, 50);
		Arrays.fill(a2, 50);
		System.out.println(Arrays.equals(a1, a2));
		String[] s1 = new String[4];
		String[] s2 = new String[4];
		Arrays.fill(s1, "Yo!");
		Arrays.fill(s2, "Yo!");
		System.out.println(Arrays.equals(s1, s2));
	}

}
