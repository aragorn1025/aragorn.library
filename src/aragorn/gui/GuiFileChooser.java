package aragorn.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * {@code GuiFileChooser} is a file chooser that extends {@code JFIleChooser}.
 * 
 * @author Aragorn
 */
@SuppressWarnings("serial")
public abstract class GuiFileChooser extends JFileChooser implements ActionListener {

	/**
	 * {@code GuiFileChooser.Open} is the implement for the file input of the file chooser.
	 * 
	 * @author Aragorn
	 */
	public static class Open extends GuiFileChooser {

		/** Create the {@code GuiFileChooser.Open}. */
		public Open(GuiFrame parent) {
			super(parent);
			setDialogTitle("Open file...");
		}

		@Override
		protected void action() {
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (showDialog(parent) != JFileChooser.APPROVE_OPTION) {
				setSelectedFile(null);
				parent.echo("File input command cancelled by user.", GuiFrame.WARNING_MESSAGE);
				return;
			}
			if (!getSelectedFile().exists()) {
				setSelectedFile(null);
				parent.echo("File is not found.", GuiFrame.ERROR_MESSAGE);
				return;
			}
			action();
			parent.echo("Open file: " + getSelectedFile().getAbsolutePath(), GuiFrame.INFORMATION_MESSAGE);
		}

		@Override
		public String getActionString() {
			return "Open";
		}

		@Override
		public int showDialog(Component parent) {
			return super.showOpenDialog(this.parent);
		}
	}

	/**
	 * {@code GuiFileChooser.Save} is the implement for the file output of the file chooser.
	 * 
	 * @author Aragorn
	 */
	public static class Save extends GuiFileChooser {

		/**
		 * To normalize the output file name with extension if user did not type extension.
		 * 
		 * @param file
		 *     the file pointer
		 * @param extension
		 *     the file extension
		 * @return If user typed file extension correctly, return the file name, else return the file name with extension.
		 */
		private static String toNormalizedFilePathString(File file, String extension) {
			if (file.isDirectory()) {
				throw new IllegalArgumentException("File should not be directory.");
			}
			String file_path = file.getAbsolutePath();
			String file_name = file.getName();
			String file_directory = file_path.substring(0, file_path.length() - file_name.length());
			if (file_name.length() <= 0) {
				file_name = "output";
			} else if (file_name.length() <= extension.length() + 1) {
				if (file_name.length() == extension.length() + 1 && file_name.equals(String.format(".%s", extension))) {
					file_name = "output";
				}
			} else {
				if (file_name.endsWith(String.format(".%s", extension))) {
					return String.format("%s%s", file_directory, file_name);
				}
			}
			return String.format("%s%s.%s", file_directory, file_name, extension);
		}

		/** Create the {@code GuiFileChooser.Save}. */
		public Save(GuiFrame parent) {
			super(parent);
			setDialogTitle("Save file...");
		}

		@Override
		protected void action() {
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (showDialog(null) != JFileChooser.APPROVE_OPTION) {
				setSelectedFile(null);
				parent.echo("File output command cancelled by user.", GuiFrame.WARNING_MESSAGE);
				return;
			}
			if (this.getChoosableFileFilters().length > 0) {
				setSelectedFile(new File(GuiFileChooser.Save.toNormalizedFilePathString(this.getSelectedFile(),
						((FileNameExtensionFilter) (this.getChoosableFileFilters()[0])).getExtensions()[0])));
			}
			if (getSelectedFile().exists()) {
				if (!getSelectedFile().canWrite()) {
					setSelectedFile(null);
					parent.echo("The file is read only.", GuiFrame.ERROR_MESSAGE);
					return;
				}
				if (JOptionPane.showConfirmDialog(parent, "The file will be overwrite.", "Warning", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
					setSelectedFile(null);
					parent.echo("File output command cancelled by user.", GuiFrame.WARNING_MESSAGE);
					return;
				}
				getSelectedFile().delete();
			}
			try {
				getSelectedFile().createNewFile();
			} catch (IOException exception) {
				throw new InternalError(exception.toString());
			}
			action();
			parent.echo("Save file: " + getSelectedFile().getPath(), GuiFrame.INFORMATION_MESSAGE);
		}

		@Override
		public String getActionString() {
			return "Save";
		}

		@Override
		public int showDialog(Component parent) {
			return super.showSaveDialog(parent);
		}
	}

	public static final String FILE_SEPARATOR = System.getProperty("file.separator");

	public static final File USER_HOME = new File(System.getProperty("user.home"));

	public static final File USER_DESKTOP = new File(USER_HOME + FILE_SEPARATOR + "Desktop");

	protected GuiFrame parent;

	/** Create the file chooser. */
	protected GuiFileChooser(GuiFrame parent) {
		super();
		this.parent = parent;
		setMultiSelectionEnabled(false);
		for (int i = getChoosableFileFilters().length; i > 0; i--) {
			removeChoosableFileFilter(getChoosableFileFilters()[i - 1]);
		}
	}

	protected abstract void action();

	/** Return the action text for the button */
	public abstract String getActionString();

	@Override
	public void setCurrentDirectory(File directory) {
		super.setCurrentDirectory(directory == null ? USER_DESKTOP : directory);
	}

	/**
	 * Show the open dialog or the save dialog.
	 * 
	 * @param parent
	 *     the parent
	 * @return the return state of the file chooser
	 * @see <a target="_blank" href=
	 * "https://docs.oracle.com/javase/8/docs/api/javax/swing/JFileChooser.html#showOpenDialog-java.awt.Component-">JFileChooser.showOpenDialog</a>
	 * @see <a target="_blank" href=
	 * "https://docs.oracle.com/javase/8/docs/api/javax/swing/JFileChooser.html#showSaveDialog-java.awt.Component-">JFileChooser.showSaveDialog</a>
	 */
	public abstract int showDialog(Component parent);
}