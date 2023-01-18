import java.util.EmptyStackException;

public class MyStack implements StackADT {
	private Square[] stack;
	private int size;

	public MyStack() {
		this(2);
	}

	public MyStack(int initCap) {
		stack = new Square[initCap];
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Square peek() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return stack[size - 1];
	}

	public Square pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		Square s = stack[size - 1];
		stack[size - 1] = null;
		size--;
		return s;
	}

	public void push(Square s) {
		if (size == stack.length)
			doubleCapacity();
		stack[size] = s;
		size++;
	}

	public void doubleCapacity() {
		Square[] doubledStack = new Square[stack.length * 2];
		for (int i = 0; i < size; i++) {
			doubledStack[i] = stack[i];
		}
		stack = doubledStack;
	}

	@Override
	public String toString() {
		String rep = "";
		if (!isEmpty()) {
			rep += stack[size - 1] + "   <-- TOP\n";
		}
		for (int i = size - 2; i >= 0; i--) {
			rep += stack[i] + "\n";
		}
		return rep;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
        stack = new Square[2];
        size = 0;
	}
}
