package arrays;

import java.util.Arrays;

import util.Generated;
import util.RandomGenerators;

/**
 * Demo {@link Arrays#equals(Object[], Object[]) Arrays.equals},
 * {@link Arrays#deepEquals} and {@link Object#equals}.
 */
public class CompareArrays {

    /**
     * @param args
     */
    public static void main(String[] args) {
	String[] stringArray1 =
		Generated.array(String.class,
			new RandomGenerators.StringGenerator(), 5);
	String[] stringArray2 = new String[stringArray1.length];
	System.arraycopy(stringArray1, 0, stringArray2, 0, stringArray1.length);

	System.out.println(String.format(
		"Arrays.equals(stringArray1, stringArray2 = %B",
		Arrays.equals(stringArray1, stringArray2)));
	System.out.println(String.format("stringArray1 == stringArray2: %B",
		stringArray1 == stringArray2));

	TestEqual[] testEquals =
		new TestEqual[] { new TestEqual(1), new TestEqual(2) };
	TestEqual[] testEquals2 =
		new TestEqual[] { new TestEqual(1), new TestEqual(2) };
	System.out.println(String.format(
		"Arrays.equals(testEquals, testEquals2) = %B",
		Arrays.equals(testEquals, testEquals2)));

	testEquals = new TestEqual[] { new TestEqual2(1), new TestEqual2(2) };
	testEquals2 = new TestEqual[] { new TestEqual2(1), new TestEqual2(2) };
	System.out.println(String.format(
		"Arrays.equals(testEquals, testEquals2) = %B",
		Arrays.equals(testEquals, testEquals2)));

	int[][] doubleIntArray = { { 1 }, { 2 } };
	int[][] doubleIntArray2 = { { 1 }, { 2 } };
	System.out.println(String.format(
		"Arrays.deepEquals(doubleIntArray, doubleIntArray2): %B",
		Arrays.deepEquals(doubleIntArray, doubleIntArray2)));
    }

    private static class TestEqual {
	int id;

	public TestEqual(int id) {
	    super();
	    this.id = id;
	}
    }

    private static class TestEqual2 extends TestEqual {

	public TestEqual2(int id) {
	    super(id);
	}

	@Override
	public boolean equals(Object obj) {
	    boolean result = false;
	    if (obj instanceof TestEqual2) {
		TestEqual2 otherEqual2 = (TestEqual2) obj;
		result = otherEqual2.id == this.id;
	    }
	    return result;
	}
    }
}
