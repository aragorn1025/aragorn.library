package aragorn.util;

import java.util.LinkedList;
import java.util.Queue;

public class EvictingQueue<E> {

	private Queue<E> queue = new LinkedList<>();

	private int max_size;

	public EvictingQueue(int max_size) {
		this.max_size = max_size;
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

	public E poll() {
		return queue.poll();
	}

	public boolean offer(E e) {
		if (isFull()) {
			queue.poll();
		}
		return queue.offer(e);
	}
}