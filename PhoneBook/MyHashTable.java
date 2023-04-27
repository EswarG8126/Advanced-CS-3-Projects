public class MyHashTable<K, V> {
	private class Entry {
		K key;
		V val;
		Entry next;

		public Entry(K key, V val) {
			this.key = key;
			this.val = val;
		}
	}

	private Object[] ht;
	private int size;

	public MyHashTable() {
		ht = new Object[11];
		size = 0;
	}

	public V put(K key, V value) {
		if (size == ht.length || key == null || value == null) {
			return null;
		}
		if (ht[key.hashCode() % ht.length] == null) {
			ht[key.hashCode() % ht.length] = new Entry(key, value);
			size++;
		} else {
			Entry current = (Entry) ht[key.hashCode() % ht.length];
			while (current != null) {
				if (key.equals(current.key)) {
					V prev = current.val;
					current.val = value;
					return prev;
				}
				current = current.next;
			}
			current = (Entry) ht[key.hashCode() % ht.length];
			while (current.next != null) {
				current = current.next;
			}
			current.next = new Entry(key, value);
			size++;
		}
		return null;
	}

	public V get(K key) {
		if (key == null) {
			return null;
		}
		if (((Entry) ht[key.hashCode() % ht.length]) == null) {
			return null;
		}
		if (((Entry) ht[key.hashCode() % ht.length]).key.equals(key)) {
			return ((Entry) ht[key.hashCode() % ht.length]).val;
		} else {
			Entry current = (Entry) ht[key.hashCode() % ht.length];
			while (current != null) {
				if (key.equals(current.key)) {
					return current.val;
				}
				current = current.next;
			}
		}
		return null;
	}

	public int size() {
		return size;
	}

	public V remove(K key) {
		if (key == null) {
			return null;
		}
		if (((Entry) ht[key.hashCode() % ht.length]) == null) {
			return null;
		}
		if (((Entry) ht[key.hashCode() % ht.length]).key.equals(key)) {
			V existingVal = ((Entry) ht[key.hashCode() % ht.length]).val;
			ht[key.hashCode() % ht.length] = ((Entry) ht[key.hashCode() % ht.length]).next;
			size--;
			return existingVal;
		} else {
			Entry current = (Entry) ht[key.hashCode() % ht.length];
			while (current.next != null) {
				if (key.equals(current.next.key)) {
					V existingVal = current.next.val;
					current.next = current.next.next;
					size--;
					return (V) existingVal;
				}
				current = current.next;
			}
		}
		return null;
	}
}