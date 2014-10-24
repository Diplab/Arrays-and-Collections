package util.map;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
/**
 * Demo {@link Map#entrySet()} is reflecting to the original map.
 * @author timmy00274672
 *
 */
public class MapCollectionDemo {

    public static void main(String[] args) {
	String[] strings =
		{ "A0", "B0", "C0", "D0", "E0", "F0", "G0", "H0", "I0" };
	LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
	for (int i = 0; i < strings.length; i++) {
	    linkedHashMap.put(i, strings[i]);
	}

	Set<Entry<Integer, String>> entrySet = linkedHashMap.entrySet();
	for (Entry<Integer, String> entry : entrySet) {
	    System.out.format("After setValue of %s = \"FF\", ", entry);
	    entry.setValue("FF");
	    System.out.format("linkedHashMap = %s \n", linkedHashMap);
	}
    }

}
