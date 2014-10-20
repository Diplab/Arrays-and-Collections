package util;

import java.util.ArrayList;

/**
 * It's a little like Adapter pattern
 * 
 * @author timmy00274672
 * 
 * @param <T>
 */
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

}