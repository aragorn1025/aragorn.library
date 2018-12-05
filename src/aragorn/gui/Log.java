package aragorn.gui;

import java.util.LinkedList;
import java.util.Queue;

public class Log {

	private Queue<String> messages = new LinkedList<>();

	private int message_number = 0;

	private int max_message_number;

	private String log = new String("");

	public Log() {
		this(200);
	}

	public Log(int max_message_number) {
		setMaxMessageNumber(max_message_number);
	}

	public void add(String message) {
		messages.add(message);
		log += (message + "\r\n");
		if (message_number == max_message_number) {
			String removed_message = messages.poll();
			log = log.substring(removed_message.length() + 2);
		} else {
			message_number++;
		}
	}

	public void clear() {
		messages.clear();
		message_number = 0;
	}

	public String get() {
		return log;
	}

	public void setMaxMessageNumber(int max_message_number) {
		if (max_message_number <= 0) {
			this.max_message_number = -1;
		} else {
			this.max_message_number = max_message_number;
		}
	}
}