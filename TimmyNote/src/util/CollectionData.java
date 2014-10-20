package util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

@SuppressWarnings("serial")
public class CollectionData<T> extends ArrayList<T> {
    public CollectionData(Generator<T> gen, int quantity) {
	for (int i = 0; i < quantity; i++)
	    add(gen.next());
    }

    // A generic convenience method:
    public static <T> CollectionData<T> list(Generator<T> gen, int quantity) {
	return new CollectionData<T>(gen, quantity);
    }

    public static <T> T[] array(T[] a, Generator<T> gen) {
	return new CollectionData<>(gen, a.length).toArray(a);
    }

    public static <T> T[] arrays(Class<T> type, Generator<T> gen, int size) {
	@SuppressWarnings("unchecked")
	T[] arrayTs = (T[]) Array.newInstance(type, size);
	return array(arrayTs, gen);
    }

    public static void main(String[] args) {
	RandomGenerators.setRandom(new Random(1)); // This will make the result
						   // the same everytime

	String[] strings = array(new String[4],
		new RandomGenerators.StringGenerator());
	System.out.println(Arrays.toString(strings));
	
	strings = arrays(String.class, new RandomGenerators.StringGenerator(), 4);
	System.out.println(Arrays.toString(strings));
    }
}