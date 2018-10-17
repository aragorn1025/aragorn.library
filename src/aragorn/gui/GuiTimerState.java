package aragorn.gui;

/**
 * {@code GuiTimerState} is a state that can switch between each other.
 * 
 * @author Aragorn
 * @see <a target="_blank" href="http://stackoverflow.com/questions/11550561/pause-the-timer-and-then-continue-it">TaskState</a>
 */
public interface GuiTimerState {

	/** The task state that do nothing for pausing timer. */
	public class Pause implements GuiTimerState {

		/** The next state for the pause state */
		private GuiTimerState.Play play_state = null;

		@Override
		public void run() {
			// do nothing
		}

		/**
		 * Set the next state which must be a play state.
		 * 
		 * @param play_state
		 *            the next state for the pause state, which must be a play state
		 */
		public void setPlayState(GuiTimerState.Play play_state) {
			this.play_state = play_state;
		}

		@Override
		public GuiTimerState switchState() {
			return play_state;
		}
	}

	/** The task state for playing timer. */
	public abstract class Play implements GuiTimerState {

		/** The next state for the pause state */
		private GuiTimerState.Pause pause_state = null;

		/**
		 * Set the next state which must be a pause state.
		 * 
		 * @param pause_state
		 *            the next state for the play state, which must be a pause state
		 */
		public void setPauseState(GuiTimerState.Pause pause_state) {
			this.pause_state = pause_state;
		}

		@Override
		public GuiTimerState switchState() {
			return pause_state;
		}
	}

	/** Action for TimerTask for each period. */
	public void run();

	/**
	 * Return the reference of the next state switching to.
	 * 
	 * @return next task state
	 */
	public GuiTimerState switchState();
}