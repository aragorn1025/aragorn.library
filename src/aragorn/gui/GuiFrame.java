package aragorn.gui;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.InvalidParameterException;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * {@code GuiFrame} is a frame which extends {@code javax.swing.JFrame} and ask before close it.
 * 
 * @author Aragorn
 */
@SuppressWarnings("serial")
public class GuiFrame extends JFrame {

	public static final int PLAIN_MESSAGE = JOptionPane.PLAIN_MESSAGE;

	public static final int ERROR_MESSAGE = JOptionPane.ERROR_MESSAGE;

	public static final int INFORMATION_MESSAGE = JOptionPane.INFORMATION_MESSAGE;

	public static final int WARNING_MESSAGE = JOptionPane.WARNING_MESSAGE;

	public static final int QUESTION_MESSAGE = JOptionPane.QUESTION_MESSAGE;

	/**
	 * Set default look and feel decorated for frames and dialogs.
	 * 
	 * @param default_look_and_feel_decorated
	 *            parameter to be set
	 * @see <a target="_blank" href=
	 *      "https://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html#setDefaultLookAndFeelDecorated(boolean)">JFrame.setDefaultLookAndFeelDecorated(boolean)</a>
	 */
	public static void setDefaultLookAndFeelDecorated(boolean default_look_and_feel_decorated) {
		JFrame.setDefaultLookAndFeelDecorated(default_look_and_feel_decorated);
		JDialog.setDefaultLookAndFeelDecorated(default_look_and_feel_decorated);
	}

	/** The timer using in {@code GuiFrame}. */
	private GuiTimer timer = null;

	protected GuiLogPanel log_panel = null;

	/**
	 * Create a {@code GuiFrame} which extends {@code JFrame} with {@code GuiPanel} as the content pane.
	 * 
	 * @param title
	 *            the title of the {@code GuiFrame}
	 * @param dimension
	 *            the dimension of the {@code GuiFrame}
	 * @param is_maximized_while_launch
	 *            to maximize frame while launch
	 */
	public GuiFrame(Dimension dimension, boolean is_maximized_while_launch) {
		this(dimension, is_maximized_while_launch, -1);
	}

	/**
	 * Create a {@code GuiFrame} which extends {@code JFrame} with {@code GuiPanel} as the content pane and using {@code java.util.Timer}.
	 * 
	 * @param title
	 *            the title of the {@code GuiFrame}
	 * @param dimension
	 *            the dimension of the {@code GuiFrame}
	 * @param is_maximized_while_launch
	 *            is maximized frame while launch
	 * @param updating_period
	 *            the period for timer that updates content pane on {@code GuiFrame}<br>
	 *            Disable the timer if it's non-positive.
	 */
	public GuiFrame(Dimension dimension, boolean is_maximized_while_launch, int updating_period) {
		setSize(dimension);
		setLocationRelativeTo(null);

		if (is_maximized_while_launch) {
			setExtendedState(JFrame.MAXIMIZED_BOTH);
		}

		setDefaultCloseOperation(0);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				GuiFrame.this.close();
			}
		});

		if (updating_period > 0) {
			timer = new GuiTimer(updating_period) {
				@Override
				protected void run() {
					_run();
					getContentPane().repaint();
				}
			};
		}
	}

	private void _run() {
		run();
	}

	public void clearLogPanel() {
		getLogPanel().clear();
	}

	/**
	 * Ask before close the {@code GuiFrame}.<br>
	 * Cancel timer before close frame.<br>
	 * Additionally, pause timer as the option pane shown and continue the state of the timer if return to the GuiFrame.
	 */
	public void close() {
		boolean is_playing_before = isPlaying();
		this.pause();
		String[] exit_pane_buttons = { "Yes", "No" };
		int n = JOptionPane.showOptionDialog(this, "Do you really want to exit?", "Exit", 0, 2, null, exit_pane_buttons, exit_pane_buttons[0]);
		if (n == 0) {
			terminate();
			dispose();
		} else {
			if (is_playing_before) {
				this.play();
			}
			echo("Frame closing command cancelled by user.", GuiFrame.WARNING_MESSAGE);
		}
	}

	public void echo(String message, int message_type) {
		String message_type_string;
		switch (message_type) {
			case GuiFrame.PLAIN_MESSAGE:
				if (getLogPanel() != null) {
					getLogPanel().echo(message);
				} else {
					JOptionPane.showMessageDialog(this, message, "Information", message_type);
				}
				return;
			case GuiFrame.ERROR_MESSAGE:
				message_type_string = "Error";
				break;
			case GuiFrame.INFORMATION_MESSAGE:
				message_type_string = "Information";
				break;
			case GuiFrame.WARNING_MESSAGE:
				message_type_string = "Warning";
				break;
			case GuiFrame.QUESTION_MESSAGE:
				message_type_string = "Question";
				break;
			default:
				throw new InvalidParameterException("Unknown message type.");
		}
		if (getLogPanel() != null) {
			getLogPanel().echo(String.format("[%s] %s", message_type_string, message));
		} else {
			JOptionPane.showMessageDialog(this, message, message_type_string, message_type);
		}
	}

	private GuiLogPanel getLogPanel() {
		if (log_panel == null)
			throw new NullPointerException("No log panel being set.");
		return log_panel;
	}

	public void setLogPanel(GuiLogPanel log_panel) {
		this.log_panel = log_panel;
	}

	public boolean isPlaying() {
		return (timer != null && timer.isPlaying());
	}

	/** Pause the timer of the frame. */
	public void pause() {
		if (timer != null) {
			timer.pause();
		}
	}

	/** Play the timer of the frame. */
	public void play() {
		if (timer != null) {
			timer.play();
		}
	}

	/** Edit timer task run. */
	protected void run() {
	}

	public int terminate() {
		if (timer == null) {
			return 0;
		}
		return timer.terminate();
	}
}