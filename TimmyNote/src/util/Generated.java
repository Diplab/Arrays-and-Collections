package util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class Generated {
    public static <T> T[] array(T[] a, Generator<T> gen) {
	return new CollectionData<>(gen, a.length).toArray(a);
    }

    public static <T> T[] array(Class<T> type, Generator<T> gen, int size) {
	@SuppressWarnings("unchecked")
	T[] arrayTs = (T[]) Array.newInstance(type, size);
	return array(arrayTs, gen);
    }

    public static void main(String[] args) {
	RandomGenerators.setRandom(new Random(1)); // This will make the result
						   // the same everytime

	String[] strings =
		Generated.array(new String[4],
			new RandomGenerators.StringGenerator());
	System.out.println(Arrays.toString(strings));

	strings =
		Generated.array(String.class,
			new RandomGenerators.StringGenerator(), 4);
	System.out.println(Arrays.toString(strings));
    }
}
