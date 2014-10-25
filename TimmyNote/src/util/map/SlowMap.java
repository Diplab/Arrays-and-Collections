package util.map;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import util.CountingMapData;

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

	K key;

	public MapEntry(K key) {
	    super();
	    this.key = key;
	}

	@Override
	public K getKey() {
	    return key;
	}

	@Override
	public V getValue() {
	    return SlowMap.this.get(key);
	}

	@Override
	public V setValue(V value) {
	    V temp = getValue();
	    SlowMap.this.put(key, value);
	    return temp;

	}

	@Override
	public String toString() {
	    return String.format("%s=%s", getKey(), getValue());
	}

	@SuppressWarnings("rawtypes")
	private SlowMap getOuterType() {
	    return SlowMap.this;
	}

	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + getOuterType().hashCode();
	    result = prime * result + ((key == null) ? 0 : key.hashCode());
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
	    if (key == null) {
		if (other.key != null)
		    return false;
	    } else if (!key.equals(other.key))
		return false;
	    return true;
	}

    }

    @Override
    public Set<java.util.Map.Entry<K, V>> entrySet() {
	return new AbstractSet<Map.Entry<K, V>>() {
	    

	    @Override
	    public boolean remove(Object o) {
		if (!(o instanceof Map.Entry)) {
		    System.out.println("remove not instanceof");
		    return false;
		} else {
		    @SuppressWarnings("unchecked")
		    Map.Entry<K, V> entry = (Entry<K, V>) o;

		    boolean b = SlowMap.this.remove(entry.getKey()) != null;
		    return b;
		}

	    }
	    
	    @Override
	    public boolean removeAll(Collection<?> c) {
		boolean result = false;
		for (Iterator<?> iterator = c.iterator(); iterator.hasNext();) {
		    Object object = (Object) iterator.next();
		    result = result | remove(object);
		}
		return result;
	    }

	    @Override
	    public Iterator<java.util.Map.Entry<K, V>> iterator() {
		return new Iterator<java.util.Map.Entry<K, V>>() {
		    ArrayList<K> keyTempList = new ArrayList<>(keyList);
		    int index = -1;

		    @Override
		    public boolean hasNext() {
			return index < keyTempList.size() - 1;
		    }

		    @Override
		    public java.util.Map.Entry<K, V> next() {
			index++;
			return new MapEntry(keyTempList.get(index));
		    }

		    @Override
		    public void remove() {
			index++;
			SlowMap.this.remove(keyTempList.get(index));
		    }
		};
	    }

	    @Override
	    public int size() {
		return SlowMap.this.size();
	    }

	};

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

    @Override
    public Collection<V> values() {
	return new AbstractSet<V>() {

	    @Override
	    public Iterator<V> iterator() {
		return new Iterator<V>() {
		    ArrayList<V> valueTempList = new ArrayList<>(valueList);
		    int index = -1;

		    @Override
		    public boolean hasNext() {
			return index < valueTempList.size() - 1;
		    }

		    @Override
		    public V next() {
			index++;
			return valueTempList.get(index);
		    }

		    @Override
		    public void remove() {
			SlowMap.this.remove(keyList.get(index));
		    }

		};
	    }

	    @Override
	    public int size() {
		return keyList.size();
	    }

	    @Override
	    public boolean remove(Object o) {
		for (Iterator<V> iterator = iterator(); iterator.hasNext();) {
		    V item = iterator.next();
		    if (item.equals(o)) {
			iterator.remove();
			return true;
		    }
		}
		return false;
	    }
	};
    }

    public static void main(String[] args) {
	SlowMap<Integer, String> map = new SlowMap<>();
	map.putAll(new CountingMapData(5));

	System.out.format("%s, %s", map.keyList, map.keyList.indexOf(1));
	map.remove(0);
	System.out.format("%s, %s", map.keyList, map.keyList.indexOf(1));
	map.entrySet().iterator().next();
    }

}
