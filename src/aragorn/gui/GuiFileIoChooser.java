package aragorn.gui;

import java.awt.Component;
import java.io.File;

import javax.swing.JFileChooser;

/**
 * {@code GuiFileIoChooser} is a file chooser that extends {@code JFIleChooser}.
 * 
 * @author Aragorn
 */
@SuppressWarnings("serial")
public abstract class GuiFileIoChooser extends JFileChooser {
	// TODO public void addChoosableFileFilter(FileFilter filter)
	// TODO public void setDialogTitle(String title)
	// TODO public void setFileNameExtensionFilter(FileNameExtensionFilter fileNameExtensionFilter)

	/**
	 * {@code GuiFileIoChooser.Input} is the implement for the file input of the file chooser.
	 * 
	 * @author Aragorn
	 */
	public static class Input extends GuiFileIoChooser {

		/** Create the {@code GuiFileIoChooser.Input}. */
		public Input() {
			super();
		}

		@Override
		public int showDialog(Component parent) {
			return super.showOpenDialog(parent);
		}
	}

	/**
	 * {@code GuiFileIoChooser.Output} is the implement for the file output of the file chooser.
	 * 
	 * @author Aragorn
	 */
	public static class Output extends GuiFileIoChooser {

		/** Create the {@code GuiFileIoChooser.Output}. */
		public Output() {
			super();
		}

		@Override
		public int showDialog(Component parent) {
			return super.showSaveDialog(parent);
		}
	}

	/** Create the file chooser. */
	protected GuiFileIoChooser() {
		super();
		setCurrentDirectory(null);
		setMultiSelectionEnabled(false);
		for (int i = getChoosableFileFilters().length; i > 0; i--) {
			removeChoosableFileFilter(getChoosableFileFilters()[i - 1]);
		}
	}

	public void setCurrentDirectory(File directory) {
		super.setCurrentDirectory(directory == null ? new File(System.getProperty("user.home") + "/Desktop") : directory);
	}

	/**
	 * Show the open dialog or the save dialog.
	 * 
	 * @param parent
	 *            the parent
	 * @return the return state of the file chooser
	 * @see <a target="_blank" href=
	 *      "https://docs.oracle.com/javase/8/docs/api/javax/swing/JFileChooser.html#showOpenDialog-java.awt.Component-">JFileChooser.showOpenDialog</a>
	 * @see <a target="_blank" href=
	 *      "https://docs.oracle.com/javase/8/docs/api/javax/swing/JFileChooser.html#showSaveDialog-java.awt.Component-">JFileChooser.showSaveDialog</a>
	 */
	public abstract int showDialog(Component parent);
}