package CompType;

import generatora.Generated;
import generatora.Generator;
import generatora.RandomGenerator;
import java.util.Arrays;

class ConvertTo{
	
	
	  public static int[] primitive(Integer[] in) {
		  int[] result = new int[in.length];
		  for(int i = 0; i < in.length; i++)
			  result[i] = in[i];
		  return result;
	  }
	
}

public class ArraySearching {

	public static void main(String[] args) {
		Generator<Integer> gen = new RandomGenerator.Integer(1000);
		int[] a = ConvertTo.primitive(Generated.array(new Integer[25], gen));
		System.out.println("Unsorted array: " + Arrays.toString(a));
		Arrays.sort(a);
		System.out.println("Sorted array: " + Arrays.toString(a));
		while(true) {
			int r = gen.next();
			int location = Arrays.binarySearch(a, r);
			if(location >= 0) {
				System.out.println("Location of " + r + " is " + location +
						", a[" + location + "] = " + a[location]);
				break; 
			}
		}
	}
}
