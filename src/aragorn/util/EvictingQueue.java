package aragorn.util;

import java.util.LinkedList;
import java.util.Queue;

public class EvictingQueue<E> {

	private int max_size;

	private Queue<E> queue = new LinkedList<>();

	public EvictingQueue(int max_size) {
		this.max_size = (max_size > 0) ? max_size : -1;
	}

	public void clear() {
		queue.clear();
	}

	public boolean isEmpty() {
		return queue.isEmpty();
	}

	public boolean isFull() {
		return queue.size() == max_size;
	}

	public boolean offer(E e) {
		if (isFull()) {
			queue.poll();
		}
		return queue.offer(e);
	}

	public E peek() {
		return queue.peek();
	}

	public E poll() {
		return queue.poll();
	}
}