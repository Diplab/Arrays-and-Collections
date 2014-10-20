package arrays;

import java.util.Arrays;

/**
 * It show that {@link Arrays#fill(Object[], Object)} uses reference to fill,
 * not copies real object.</br> And then use
 * {@link Arrays#equals(Object[], Object[])} to do shallow copy.
 * 
 * @author timmy00274672
 * @see Arrays#equals(Object[], Object[])
 * d
 * @see Arrays#fill(Object[], Object)
 */
public class FillingArray {

    public static void main(String[] args) {
	ClassForFilling cFilling = new ClassForFilling();
	Object[] obs1 = new Object[2], obs2 = new Object[2];
	Arrays.fill(obs1, cFilling);
	Arrays.fill(obs2, cFilling);
	System.out.println(Arrays.toString(obs1));
	System.out.println(obs1[0] == obs1[1]);

	System.out.println(obs1.equals(obs2));
	System.out.println(Arrays.equals(obs1, obs2));

    }

    private static class ClassForFilling {
	private static int index = 0;
	private int id;

	public ClassForFilling() {
	    index++;
	    id = index;
	}

	@Override
	public String toString() {
	    return id + "";
	}
    }
}
