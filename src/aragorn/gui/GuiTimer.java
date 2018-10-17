package aragorn.gui;

import java.util.Timer;
import java.util.TimerTask;

/**
 * {@code GuiTimer} is the timer that can switch state between playing and pausing.
 * 
 * @author Aragorn
 * @see <a target="_blank" href="http://stackoverflow.com/questions/11550561/pause-the-timer-and-then-continue-it">TaskState</a>
 * @see <a target="_blank" href="https://docs.oracle.com/javase/8/docs/api/java/util/Timer.html">java.util.Timer</a>
 */
public abstract class GuiTimer extends Timer {

	/** The task state that hold the current timer state. */
	private GuiTimerState current_state;

	/** The {@code java.util.TimerTask} for the timer. */
	private TimerTask task;

	/**
	 * Create timer with the updating period for the task.
	 * 
	 * @param updating_period
	 */
	public GuiTimer(int updating_period) {
		super(true);
		GuiTimerState.Pause pause_state = new GuiTimerState.Pause();
		GuiTimerState.Play play_state = new GuiTimerState.Play() {
			@Override
			public void run() {
				// addTimerTaskAction();
			}
		};
		pause_state.setPlayState(play_state);
		play_state.setPauseState(pause_state);

		current_state = pause_state;
		task = new TimerTask() {
			@Override
			public void run() {
				play();
				taskRun();
			}
		};
		scheduleAtFixedRate(task, 0, updating_period);
	}

	/** Return true if the state of the timer is playing, else return false. */
	public boolean isPlaying() {
		return current_state.getClass().equals(GuiTimerState.Play.class);
	}

	/** Stop timer by switching task state from playing to pausing. */
	public void pause() {
		if (isPlaying()) {
			current_state.switchState();
		}
	}

	/** Start timer by switching task state from pausing to playing. */
	public void play() {
		if (!isPlaying()) {
			current_state.switchState();
		}
	}

	/** The task run once for each period. */
	protected abstract void taskRun();

	/**
	 * Terminates this timer, discarding any currently scheduled tasks and removes all cancelled tasks from this timer's task queue.
	 * 
	 * @return the number of tasks removed from the queue.
	 * @see <a target="_blank" href="https://docs.oracle.com/javase/8/docs/api/java/util/Timer.html#cancel--">java.util.Timer.cancel()</a>
	 * @see <a target="_blank" href="https://docs.oracle.com/javase/8/docs/api/java/util/Timer.html#purge--">java.util.Timer.purge()</a>
	 */
	public int terminate() {
		cancel();
		return purge();
	}
}