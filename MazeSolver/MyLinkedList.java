public class MyLinkedList<T> {
	private ListNode head;
	private ListNode tail;
	private int size;

	private class ListNode {
		T val;
		ListNode next;

		public ListNode(T val) {
			this.val = val;
		}

		@Override
		public String toString() {
			return "" + this.val;
		}
	}

	public MyLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

	public MyLinkedList(T val) {
		head = new ListNode(val);
		tail = head;
		size = 1;
	}

	public MyLinkedList(T... vals) {
		for (T i : vals) {
			add(i);
		}
	}

	public void add(T newVal) {
		if (head == null) {
			head = new ListNode(newVal);
			tail = head;
			size++;
		} else {
			tail.next = new ListNode(newVal);
			tail = tail.next;
			size++;
		}
	}

	public boolean contains(T target) {
		ListNode current = head;
		while (current != null) {
			if (current.val.equals(target)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	public T get(int index) {
		int size = 0;
		ListNode temp = head;
		while (temp != null) {
			size++;
			temp = temp.next;
		}
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		} else {
			int n = 0;
			ListNode current = head;
			while (current != null) {
				if (n == index) {
					return current.val;
				} else {
					current = current.next;
					n++;
				}
			}
		}
		return null;
	}

	public int indexOf(T target) {
		int index = 0;
		ListNode current = head;
		while (current != null) {
			if (!(current.val.equals(target))) {
				index++;
				current = current.next;
			} else {
				return index;
			}
		}
		return -1;
	}

	public void set(T newVal, int index) {
		int i = 0;
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		} else if (index == size) {
			add(newVal);
		} else {
			ListNode current = head;
			while (current.next != null && i < index) {
				current = current.next;
				i++;
			}
			current.val = newVal;
		}
	}

	public int size() {
		return size;
	}

	public int sizeRecursive(ListNode current) {
		if (isEmpty()) {
			return 0;
		} else {
			current = head;
			if (current.next == null) {
				return 1;
			} else if (current.next != null) {
				return 1 + sizeRecursive(current.next);
			}
		}
		return -1;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public T remove(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 0) {
			ListNode current = head;
			head = head.next;
			size--;
			return current.val;
		}
		int i = 1;
		ListNode previous = head;
		ListNode current = head.next;
		while (current != null & i < index) {
			previous = current;
			current = current.next;
			i++;
		}
		previous.next = current.next;
		if (i == size - 1) {
			tail = previous;
		}
		size--;
		return current.val;
	}

	public void add(T newVal, int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if (index == size) {
			add(newVal);
		} else if (index == 0) {
			ListNode node = new ListNode(newVal);
			node.next = head;
			head = node;
			size++;
		} else {
			int i = 1;
			ListNode current = head.next;
			while (current != null) {
				if (i + 1 == index) {
					ListNode node = new ListNode(newVal);
					node.next = current.next;
					current.next = node;
					size++;
				}
				current = current.next;
				i++;
			}
		}
	}

	@Override
	public String toString() {
		String res = "[";
		if (isEmpty()) {
			return res + "]";
		} else {
			ListNode current = head;
			while (current != null) {
				res += current.toString() + ", ";
				current = current.next;
			}
			return res.substring(0, res.length() - 2) + "]";
		}
	}
}