public class PhoneBook implements IMap {
	private class Entry {
		Person name;
		PhoneNumber pN;
		Entry next;

		public Entry(Person p, PhoneNumber n) {
			name = p;
			pN = n;
		}
	}

	private Entry[] ht;
	private int size;

	public PhoneBook() {
		ht = new Entry[11];
		size = 0;
	}

	@Override
	public PhoneNumber put(Person person, PhoneNumber phone) {
		if (size == ht.length || person == null || phone == null) {
			return null;
		}
		if (ht[person.hashCode() % ht.length] == null) {
			ht[person.hashCode() % ht.length] = new Entry(person, phone);
			size++;
		} else {
			Entry current = ht[person.hashCode() % ht.length];
			while (current != null) {
				if (person.equals(current.name)) {
					PhoneNumber prev = current.pN;
					current.pN = phone;
					return prev;
				}
				current = current.next;
			}
			current = ht[person.hashCode() % ht.length];
			while (current.next != null) {
				current = current.next;
			}
			current.next = new Entry(person, phone);
			size++;
		}
		return null;
	}

	@Override
	public PhoneNumber get(Person person) {
		if (person == null) {
			return null;
		}
		if (ht[person.hashCode() % ht.length] == null) {
			return null;
		}
		if (ht[person.hashCode() % ht.length].name.equals(person)) {
			return ht[person.hashCode() % ht.length].pN;
		} else {
			Entry current = ht[person.hashCode() % ht.length];
			while (current != null) {
				if (person.equals(current.name)) {
					return current.pN;
				}
				current = current.next;
			}
		}
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public PhoneNumber remove(Person person) {
		if (person == null) {
			return null;
		}
		if (ht[person.hashCode() % ht.length] == null) {
			return null;
		}
		if (ht[person.hashCode() % ht.length].name.equals(person)) {
			PhoneNumber existingVal = ht[person.hashCode() % ht.length].pN;
			ht[person.hashCode() % ht.length] = ht[person.hashCode() % ht.length].next;
			size--;
			return existingVal;
		} else {
			Entry current = ht[person.hashCode() % ht.length];
			while (current.next != null) {
				if (person.equals(current.next.name)) {
					PhoneNumber existingVal = current.next.pN;
					current.next = current.next.next;
					size--;
					return existingVal;
				}
				current = current.next;
			}
		}
		return null;
	}
}