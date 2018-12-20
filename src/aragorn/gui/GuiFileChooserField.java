package aragorn.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

/**
 * The {@code GuiFileChooserField} is the file chooser field within a text filed for the path of the chosen file and a button to access the file chooser.
 * 
 * @author Aragorn
 */
@SuppressWarnings("serial")
public class GuiFileChooserField extends GuiPanel {

	/**
	 * {@code GuiFileChooserField.Open} is the implement for the input of the file I/O chooser field.
	 * 
	 * @author Aragorn
	 */
	public static class Open extends GuiFileChooserField {

		/** Create {@code GuiFileChooserField.Open}. */
		public Open(GuiFrame parent) {
			super();
			setFileChooser(new GuiFileChooser.Open(parent) {
				@Override
				public void actionPerformed(ActionEvent event) {
					super.actionPerformed(event);
					if (this.getSelectedFile() == null) {
						field.setText("");
					} else {
						field.setText(this.getSelectedFile().getAbsolutePath());
					}
					actionAfter(event);
				}
			});
			button.setText(getFileChooser().getActionString());
			button.addActionListener(getFileChooser());
		}
	}

	/**
	 * {@code GuiFileChooserField.Save} is the implement for the output of the file chooser field.
	 * 
	 * @author Aragorn
	 */
	public static class Save extends GuiFileChooserField {

		/** Create {@code GuiFileChooserField.Save}. */
		public Save(GuiFrame parent) {
			super();
			setFileChooser(new GuiFileChooser.Save(parent) {
				@Override
				public void actionPerformed(ActionEvent event) {
					super.actionPerformed(event);
					if (this.getSelectedFile() == null) {
						field.setText("");
					} else {
						field.setText(this.getSelectedFile().getAbsolutePath());
					}
					actionAfter(event);
				}
			});
			button.setText(getFileChooser().getActionString());
			button.addActionListener(getFileChooser());
		}
	}

	private ArrayList<ActionListener> listeners = new ArrayList<>();

	protected JButton button = new JButton();

	private GuiFileChooser chooser;

	/** The text field to print the directory of the chosen file. */
	protected JTextField field = new JTextField();

	/** Create the file chooser field within a text filed for the path of the chosen file and a button to access the file chooser. */
	protected GuiFileChooserField() {
		super();

		field.setEditable(false);
		field.setBackground(Color.WHITE);

		setDefaultMargin(0);
		addComponent(field, 0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 10));
		addComponent(button, 1, 0);
	}

	public void actionAfter(ActionEvent event) {
		for (int i = 0; i < listeners.size(); i++) {
			listeners.get(i).actionPerformed(event);
		}
	}

	public void addActionListener(ActionListener listener) {
		listeners.add(listener);
	}

	protected GuiFileChooser getFileChooser() {
		return chooser;
	}

	/**
	 * Get the selected file chosen by the file chooser.
	 * 
	 * @return the selected file
	 */
	public File getSelectedFile() {
		return getFileChooser().getSelectedFile();
	}

	public void reset() {
		getFileChooser().setSelectedFile(null);
	}

	public void setCurrentDirectory(File directory) {
		chooser.setCurrentDirectory(directory);
	}

	public void setDialogTitle(String title) {
		chooser.setDialogTitle(title);
	}

	protected void setFileChooser(GuiFileChooser chooser) {
		this.chooser = chooser;
	}

	public void setFileFilter(FileFilter filter) {
		chooser.setFileFilter(filter);
	}
}