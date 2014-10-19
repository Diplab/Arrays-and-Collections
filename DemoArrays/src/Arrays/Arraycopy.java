package Arrays;
import java.util.Arrays;



public class Arraycopy {

	public static void main(String[] args) {
		int[] i = new int[7]; 
		int[] j = new int[10];
		Arrays.fill(i, 10);
		Arrays.fill(j, 19);
		System.out.println("i = " + Arrays.toString(i));
		System.out.println("j = " + Arrays.toString(j));
		System.arraycopy(i, 0, j, 0, i.length);
		System.out.println("j = " + Arrays.toString(j));
		
	}

}
