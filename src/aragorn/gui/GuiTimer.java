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

	/**
	 * {@code State} is a state for the {@code GuiTimer} that can switch between each other.
	 * 
	 * @author Aragorn
	 * @see <a target="_blank" href="http://stackoverflow.com/questions/11550561/pause-the-timer-and-then-continue-it">TaskState</a>
	 */
	public static interface State {

		/** The task state that do nothing for pausing timer. */
		public static class Pause implements GuiTimer.State {

			/** The next state for the pause state */
			private GuiTimer.State.Play play_state;

			public Pause(GuiTimer.State.Play play_status) {
				play_status.setPauseState(this);
				setPlayState(play_status);
			}

			@Override
			public GuiTimer.State getNextState() {
				return play_state;
			}

			@Override
			public void run() {
				// do nothing
			}

			/**
			 * Set the next state which must be a play state.
			 * 
			 * @param play_state
			 *     the next state for the pause state, which must be a play state
			 */
			public void setPlayState(GuiTimer.State.Play play_state) {
				this.play_state = play_state;
			}
		}

		/** The task state for playing timer. */
		public static abstract class Play implements GuiTimer.State {

			/** The next state for the pause state */
			private GuiTimer.State.Pause pause_state;

			public Play() {
				setPauseState(null);
			}

			@Override
			public GuiTimer.State getNextState() {
				return pause_state;
			}

			/**
			 * Set the next state which must be a pause state.
			 * 
			 * @param pause_state
			 *     the next state for the play state, which must be a pause state
			 */
			public void setPauseState(GuiTimer.State.Pause pause_state) {
				this.pause_state = pause_state;
			}
		}

		/**
		 * Return the reference of the next state switching to.
		 * 
		 * @return next task state
		 */
		public GuiTimer.State getNextState();

		/** Action for TimerTask for each period. */
		public void run();
	}

	/** The task state that hold the current timer state. */
	private GuiTimer.State current_state;

	/**
	 * Create timer with the updating period for the task.
	 * 
	 * @param updating_period
	 */
	public GuiTimer(int updating_period) {
		super(true);
		current_state = new State.Pause(new State.Play() {
			@Override
			public void run() {
				run_();
			}
		});

		this.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				getCurrentState().run();
			}
		}, 0, updating_period);
	}

	private GuiTimer.State getCurrentState() {
		return current_state;
	}

	/** Return true if the state of the timer is playing, else return false. */
	public boolean isPlaying() {
		return current_state.getClass().equals(GuiTimer.State.Play.class);
	}

	/** Stop timer by switching task state from playing to pausing. */
	public void pause() {
		if (isPlaying()) {
			current_state = current_state.getNextState();
		}
	}

	/** Start timer by switching task state from pausing to playing. */
	public void play() {
		if (!isPlaying()) {
			current_state = current_state.getNextState();
		}
	}

	/** The task run once for each period. */
	protected abstract void run();

	private void run_() {
		run();
	}

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