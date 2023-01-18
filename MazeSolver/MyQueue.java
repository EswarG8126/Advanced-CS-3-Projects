public class MyQueue<T> implements QueueADT<T> {
	MyLinkedList<T> queue;

	public MyQueue() {
		queue = new MyLinkedList<T>();
	}

	public MyQueue(T... vals) {
		queue = new MyLinkedList<T>(vals);
	}

	@Override
	public void offer(T item) {
		queue.add(item);
	}

	@Override
	public T poll() {
		return queue.remove(0);
	}

	@Override
	public T peek() {
		return queue.get(0);
	}

	@Override
	public int size() {
		return queue.size();
	}

	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}

	@Override
	public void clear() {
		while (!queue.isEmpty()) {
			queue.remove(0);
		}
	}
}