package arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import util.CollectionData;
import util.Generator;

/**
 * Show Comparable / Comparator and Collections.reverseOrder 
 * @author timmy00274672
 * 
 */
public class CompType implements Comparable<CompType> {

    int i, j;

    public CompType(int i, int j) {
	super();
	this.i = i;
	this.j = j;
    }

    @Override
    public String toString() {
	return String.format("[i=%d, j=%d]", i, j);
    }

    @Override
    public int compareTo(CompType o) {
	return (this.i > o.i) ? 1 : ((this.i == o.i) ? 0 : -1);
    }

    public static void main(String[] args) {
	Generator<CompType> generator = new Generator<CompType>() {
	    private Random random = new Random();

	    @Override
	    public CompType next() {

		return new CompType(random.nextInt(1000), random.nextInt(1000));
	    }
	};
	CompType[] compTypes =
		CollectionData.arrays(CompType.class, generator, 5);
	System.out.println(Arrays.toString(compTypes));

	Arrays.sort(compTypes);
	System.out.println(Arrays.toString(compTypes));

	Arrays.sort(compTypes, Collections.reverseOrder());
	System.out.println(Arrays.toString(compTypes));

	Arrays.sort(compTypes, new Comparator<CompType>() {

	    @Override
	    public int compare(CompType o1, CompType o2) {
		return o1.i ^ 2 - o2.i ^ 2;
	    }
	});
	System.out.println(Arrays.toString(compTypes));

    }

}
