package aragorn.gui;

import aragorn.util.EvictingQueue;

public class GuiLog {

	private String log = new String("");

	private EvictingQueue<String> queue;

	public GuiLog(int max_message_number) {
		queue = new EvictingQueue<String>(max_message_number);
	}

	public void add(String message) {
		if (queue.isFull()) {
			log = log.substring(queue.peek().length() + 2);
		}
		log += (message + "\r\n");
		queue.offer(message);
	}

	public void clear() {
		queue.clear();
	}

	public String get() {
		return log;
	}
}