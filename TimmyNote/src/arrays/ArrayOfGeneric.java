/**
 * 
 */
package arrays;

/**
 * We cannot create a generic array, but we can create an Array without generic
 * type and then cast to a generic one.
 * 
 * @author timmy00274672
 * 
 */
public class ArrayOfGeneric {

    static final int SIZE = 100;
    static Generic<Integer>[] gia;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
	gia = new Generic[SIZE]; // OK

	// This will fail: Cannot create a generic array of Generic<Integer>
	// gia = new Generic<Integer>[SIZE];
	System.out.println(gia.getClass().getSimpleName());

	gia[0] = new Generic<Integer>();

	// Type mismatch: cannot convert from Generic<Double> to
	// Generic<Integer>
	// gia[1] = new Generic<Double>();
    }

}

class Generic<T> {
    void test(T t) {
    }
}