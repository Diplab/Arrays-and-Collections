package util.map;

import static org.junit.Assert.*;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import util.CountingMapData;

public class AbstractMapTest {

    protected AbstractMap<Integer, String> map, emptyMap;

    @Before
    public void setUp() throws Exception {
	map = new LinkedHashMap<>();
	emptyMap = new LinkedHashMap<>();
	map.putAll(new CountingMapData(25));
    }

    /*
     * public void testHashCode() { fail("Not yet implemented"); }
     */
    @Test
    public void testEquals() {
	HashMap<Object, Object> hashMap = new HashMap<>();
	hashMap.putAll(map);
	assertEquals("Equals is based on entrySet", map.equals(hashMap), true);
	assertEquals("Equals is based on entrySet", hashMap.equals(map), true);
    }

    @Test
    public void testSize() {
	assertEquals(map.size(), 25);
    }

    @Test
    public void testIsEmpty() {
	assertEquals(map.isEmpty(), false);
	assertEquals(emptyMap.isEmpty(), true);
    }

    @Test
    public void testContainsValue() {
	emptyMap.put(1, "A");
	assertEquals(emptyMap.containsValue("A"), true);
	assertEquals(emptyMap.containsValue(map), false);
	assertEquals(emptyMap.containsValue(new String("A")), true);
	assertEquals(emptyMap.containsKey("B"), false);
    }

    @Test
    public void testContainsKey() {
	emptyMap.put(1, "A");
	assertEquals(emptyMap.containsKey(1), true);
	assertEquals(emptyMap.containsKey(2), false);
	assertEquals(emptyMap.containsKey("A"), false);
    }

    @Test
    public void testGet() {
	emptyMap.put(1, "A");
	assertEquals(emptyMap.get(1), "A");
	assertEquals(emptyMap.get(2), null);
    }

    @Test
    public void testPut() {
	emptyMap.put(1, "A");
	Set<Entry<Integer, String>> keySet = emptyMap.entrySet();
	Entry<Integer, String> next = keySet.iterator().next();
	assertEquals(next.getValue(), "A");
	assertEquals(next.getKey(), new Integer(1));
    }

    @Test
    public void testRemove() {
	emptyMap.put(1, "A");
	assertEquals(emptyMap.remove(1), "A");
	assertEquals(emptyMap.remove(1), null);
    }

    @Test
    public void testPutAll() {
	assertEquals("PutAllError", map.size(), 25);
    }

    @Test
    public void testClear() {
	map.clear();
	assertEquals(map.size(), 0);
    }

    @Test
    public void testKeySet() {
	emptyMap.put(1, "A");
	Set<Integer> keySet = emptyMap.keySet();
	assertEquals(keySet.contains(1), true);

	emptyMap.clear();
	assertEquals(keySet.contains(1), false);

	emptyMap.put(1, "A");
	assertEquals(keySet.contains(1), true);

	emptyMap.putAll(map);
	assertEquals(keySet.size(), 25);

	Exception exception = null;
	try {
	    keySet.add(1);
	} catch (Exception e) {
	    exception = e;
	}
	assertNotNull(exception);
    }

    @Test
    public void testValues() {
	emptyMap.put(1, "A");
	Collection<String> values = emptyMap.values();
	assertEquals(values.contains("A"), true);
	assertEquals(values.contains("B"), false);
	assertEquals(values.contains(1), false);

	emptyMap.put(2, "B");
	assertEquals(values.contains("B"), true);
	values.remove(values.iterator().next());
	assertEquals(emptyMap.size(), 1);

	values.remove(values.iterator().next());
	assertEquals(emptyMap.size(), 0);

    }

    @Test
    public void testEntrySet() {
	emptyMap.put(1, "A");
	Set<Entry<Integer, String>> entrySet = emptyMap.entrySet();
	assertEquals(entrySet.size(), 1);
	Entry<Integer, String> first = entrySet.iterator().next();
	assertEquals(first.getKey(), new Integer(1));
	assertEquals(first.getValue(), "A");
	first.setValue("B");
	assertEquals(emptyMap.containsValue("B"), true);
	assertEquals(emptyMap.containsValue("A"), false);

	emptyMap.put(2, "B");
	emptyMap.put(3, "C");
	assertEquals(entrySet.size(), 3);

	emptyMap.remove(1);
	assertEquals(entrySet.size(), 2);
	assertFalse(entrySet.remove(first));

	first = entrySet.iterator().next();
	assertTrue(entrySet.remove(first));
	assertFalse(entrySet.remove(first));
	assertEquals(entrySet.size(), 1);

	emptyMap.putAll(map);
	entrySet.removeAll(entrySet);
	assertEquals(entrySet.size(), 0);
    }

}
