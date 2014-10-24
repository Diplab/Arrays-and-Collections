package util.map;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SlowMap<K, V> extends AbstractMap<K, V> {

    private List<K> keyList = new ArrayList<>();
    private List<V> valueList = new ArrayList<>();

    @Override
    public int size() {
	return keyList.size();
    }

    @Override
    public boolean isEmpty() {
	return keyList.isEmpty();
    }

    @Override
    public boolean containsValue(Object value) {
	return valueList.contains(value);
    }

    @Override
    public boolean containsKey(Object key) {
	return keyList.contains(key);
    }

    @Override
    public V remove(Object key) {
	int index = keyList.indexOf(key);
	if (index == -1) {
	    return null;
	} else {
	    V temp = valueList.get(index);
	    keyList.remove(index);
	    valueList.remove(index);
	    return temp;
	}
    }

    @Override
    public void clear() {
	keyList.clear();
	valueList.clear();
    }

    private class MapEntry implements Map.Entry<K, V> {
	private int index;

	public MapEntry(int index) {
	    super();
	    this.index = index;
	}

	@Override
	public K getKey() {
	    return keyList.get(index);
	}

	@Override
	public V getValue() {
	    return valueList.get(index);
	}

	@Override
	public V setValue(V value) {
	    V temp = getValue();
	    valueList.set(index, value);
	    return temp;

	}

	@Override
	public String toString() {
	    return String.format("%s=%s", getKey(), getValue());
	}

	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + index;
	    return result;
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
		return true;
	    if (obj == null)
		return false;
	    if (getClass() != obj.getClass())
		return false;
	    @SuppressWarnings("unchecked")
	    MapEntry other = (MapEntry) obj;
	    if (!getOuterType().equals(other.getOuterType()))
		return false;
	    if (index != other.index)
		return false;
	    return true;
	}

	private SlowMap<K, V> getOuterType() {
	    return SlowMap.this;
	}

    }

    @Override
    public Set<java.util.Map.Entry<K, V>> entrySet() {
	Set<java.util.Map.Entry<K, V>> tempSet = new HashSet<>();
	for (int i = 0; i < keyList.size(); i++) {
	    tempSet.add(new MapEntry(i));
	}
	return tempSet;
    }

    @Override
    public V put(K key, V value) {
	// find key,
	// ====if not find > add
	// ====if find, modify the value
	int index = keyList.indexOf(key);
	if (index == -1) {
	    keyList.add(key);
	    valueList.add(keyList.indexOf(key), value);
	    return null;
	} else {
	    V v = valueList.get(index);
	    valueList.set(index, value);
	    return v;
	}
    }

    @Override
    public V get(Object key) {
	int index = keyList.indexOf(key);
	if (index == -1) {
	    return null;
	} else {
	    return valueList.get(index);
	}
    }

}
