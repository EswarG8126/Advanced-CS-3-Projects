
public class MinHeap {
	private Integer[] heap;
	private int size;
	private static final int DEFAULT_CAPACITY = 8;

	public MinHeap(int capacity) {
		this.size = 0;
		heap = new Integer[capacity + 1];
	}

	public MinHeap() {
		this(DEFAULT_CAPACITY);
	}

	public MinHeap(Integer... nums) {
		this.size = nums.length;
		this.heap = new Integer[nums.length + 1];
		buildHeap(nums);
	}

	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		return getSize() == 0;
	}

	public int peekMinimum() {
		return heap[1];
	}

	public int getLeftChildIndex(int index) {
		return index * 2;
	}

	public int getRightChildIndex(int index) {
		return 2 * index + 1;
	}

	public int getParentIndex(int index) {
		return index / 2;
	}

	private void doubleCapacity() {
		Integer[] doubledHeap = new Integer[heap.length * 2];
		for (int i = 1; i < heap.length; i++) {
			doubledHeap[i] = heap[i];
		}
		heap = doubledHeap;
	}

	public void insert(int value) {
		if (size + 1 == heap.length) {
			doubleCapacity();
		}
		heap[size + 1] = value;
		bubbleUp(size + 1);
		size++;
	}

	private void bubbleUp(int index) {
		if (index > 1 && heap[getParentIndex(index)] > heap[index]) {
			int parent = heap[getParentIndex(index)];
			heap[getParentIndex(index)] = heap[index];
			heap[index] = parent;
			bubbleUp(getParentIndex(index));
		}
	}

	public int popMinimum() {
		int min = heap[1];
		heap[1] = heap[size];
		heap[size] = null;
		size--;
		siftDown(1);
		return min;
	}

	private void siftDown(int index) {
		int n = 0;
		int i = 0;
		if (getLeftChildIndex(index) > 1 && getLeftChildIndex(index) < size && getRightChildIndex(index) > 1
				&& getRightChildIndex(index) < size) {
			if (heap[getLeftChildIndex(index)] < heap[getRightChildIndex(index)]) {
				n = heap[getLeftChildIndex(index)];
				i = getLeftChildIndex(index);
			} else if (heap[getLeftChildIndex(index)] > heap[getRightChildIndex(index)]) {
				n = heap[getRightChildIndex(index)];
				i = getRightChildIndex(index);
			}

		}
		if (n != 0 & i != 0) {
			Integer temp = heap[i];
			heap[i] = heap[index];
			heap[index] = temp;
			siftDown(i);
		}
	}

	public void buildHeap(Integer[] nums) {
		for (int i = 1; i <= nums.length; i++) {
			heap[i] = nums[i - 1];
		}
		for (int j = size / 2; j > 0; j--) {
			siftDown(j);
		}
	}

	@Override
	public String toString() {
		String output = "";
		for (int i = 1; i <= getSize(); i++) {
			output += heap[i] + ", ";
		}
		return output.substring(0, output.lastIndexOf(","));
	}

	public void display() {
		int nBlanks = 32, itemsPerRow = 1, column = 0, j = 1;
		String dots = "...............................";
		System.out.println(dots + dots);
		while (j <= this.getSize()) {
			if (column == 0) {
				for (int k = 0; k < nBlanks; k++) {
					System.out.print(' ');
				}
			}
			// System.out.print((heap[j] == null) ? "" : heap[j]);
			if (heap[j] == null) {
				System.out.print("");
			} else {
				System.out.print(heap[j]);
			}
			if (++column == itemsPerRow) {
				nBlanks /= 2;
				itemsPerRow *= 2;
				column = 0;
				System.out.println();
			} else {
				for (int k = 0; k < nBlanks * 2 - 2; k++) {
					System.out.print(' ');
				}
			}
			j++;
		}
		System.out.println("\n" + dots + dots);
	}
}