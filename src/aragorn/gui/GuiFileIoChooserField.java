package aragorn.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JTextField;

/**
 * The {@code GuiFileIoChooserField} is the file chooser field within a text filed for the path of the chosen file and a button to access the file chooser.
 * 
 * @author Aragorn
 */
@SuppressWarnings("serial")
public abstract class GuiFileIoChooserField extends GuiPanel {

	/**
	 * {@code GuiFileIochooserField.Input} is the implement for the input of the file I/O chooser field.
	 * 
	 * @author Aragorn
	 */
	public static class Input extends GuiFileIoChooserField {

		/** Create {@code GuiFileIochooserField.Input}. */
		public Input() {
			super();
			is_initialization_done = true;
		}

		@Override
		protected String getActionText() {
			return "Open";
		}

		@Override
		protected FileIoActionListener getFileIOActionListener() {
			if (!is_initialization_done) { // XXX
				listener = new FileIoActionListener.Input() {
					@Override
					public void actionPerformed(ActionEvent e) {
						super.actionPerformed(e);
						action();
					}
				};
			}
			return listener;
		}
	}

	/**
	 * {@code GuiFileIochooserField.Output} is the implement for the output of the file I/O chooser field.
	 * 
	 * @author Aragorn
	 */
	public static class Output extends GuiFileIoChooserField {

		/** Create {@code GuiFileIochooserField.Output}. */
		public Output() {
			super();
			is_initialization_done = true;
		}

		@Override
		protected String getActionText() {
			return "Save";
		}

		@Override
		protected FileIoActionListener getFileIOActionListener() {
			if (!is_initialization_done) { // XXX
				listener = new FileIoActionListener.Output() {
					@Override
					public void actionPerformed(ActionEvent e) {
						super.actionPerformed(e);
						action();
					}
				};
			}
			return listener;
		}
	}

	/** The text field to print the directory of the chosen file. */
	private JTextField directory_field = new JTextField();

	/** The button to access the file chooser. */
	private JButton chooser_button = new JButton();

	/** The action listener for the button. */
	protected FileIoActionListener listener;

	protected boolean is_initialization_done = false;

	/** Create the file chooser field within a text filed for the path of the chosen file and a button to access the file chooser. */
	protected GuiFileIoChooserField() {
		super();

		directory_field.setEditable(false);
		directory_field.setBackground(Color.WHITE);

		chooser_button.setText(getActionText());
		chooser_button.addActionListener(getFileIOActionListener());

		setDefaultMargin(0);
		addComponent(directory_field, 0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 10));
		addComponent(chooser_button, 1, 0);
	}

	protected void action() { // XXX
		setDirectoryFieldText();
	}

	/** Return the action text for the button */
	protected abstract String getActionText();

	/**
	 * Get the action listener of the file chooser.
	 * 
	 * @return the listener of the file chooser
	 */
	protected abstract FileIoActionListener getFileIOActionListener();

	/**
	 * Get the selected file chosen by the file chooser.
	 * 
	 * @return the selectedFile
	 */
	public File getSelectedFile() {
		return getFileIOActionListener().getSelectFile();
	}

	/** Set directory field text after the button is pressed. */
	protected void setDirectoryFieldText() {
		if (getSelectedFile() != null) {
			directory_field.setText(getSelectedFile().getPath());
		} else {
			directory_field.setText("");
		}
	}
}