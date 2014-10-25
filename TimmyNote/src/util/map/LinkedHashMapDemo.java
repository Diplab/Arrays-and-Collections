package util.map;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * When using {@link LinkedHashMap}, you can
 * {@link LinkedHashMap#LinkedHashMap(int, float, boolean)} to specify LRU.
 * 
 * @author timmy00274672
 * 
 */
public class LinkedHashMapDemo {

    public static void main(String[] args) {
	String[] strings =
		{ "A0", "B0", "C0", "D0", "E0", "F0", "G0", "H0", "I0" };
	LinkedHashMap<Integer, String> linkedHashMap =
		new LinkedHashMap<>(16, 0.75f, true);
	for (int i = 0; i < strings.length; i++) {
	    linkedHashMap.put(i, strings[i]);
	}
	System.out.format("linkedHashMap = %s\n", linkedHashMap);
	for (int i = 0; i < strings.length; i++) {
	    System.out.format("get %s and than linkedHashMap = %s\n",
		    linkedHashMap.get(new Integer(i)), linkedHashMap);
	}
	Set<Integer> keySet = linkedHashMap.keySet();
	keySet.remove(0);
	// keySet.removeAll(keySet);
	System.out.format("linkedHashMap = %s\n", linkedHashMap);
	Collection<String> values = linkedHashMap.values();
	// values.add("!!");
	values.remove(strings[1]);
	System.out.format("linkedHashMap = %s\n", linkedHashMap);
    }
}
