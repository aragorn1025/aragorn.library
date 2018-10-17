package aragorn.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * The {@code FileIoActionListener} is the action listener that contains the file chooser within the specified parameters for it.
 * 
 * @author Aragorn
 */
public abstract class FileIoActionListener implements ActionListener {

	/**
	 * The {@code FileIoActionListener.Input} is the action listener for file input that contains the file chooser within the specified parameters for it.
	 * 
	 * @author Aragorn
	 */
	public static class Input extends FileIoActionListener {

		/** The file chooser to choose the file. */
		private GuiFileIoChooser chooser = new GuiFileIoChooser.Input();

		/** Create a action listener to choose file. */
		public Input() {
			super();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (showDialog(null) == JFileChooser.APPROVE_OPTION) {
				setSelectedFile(getFileChooser().getSelectedFile());
				JOptionPane.showMessageDialog(null, "Open file: " + getSelectFile().getAbsolutePath());
			} else {
				setSelectedFile(null);
				JOptionPane.showMessageDialog(null, "File input command cancelled by user.");
			}
		}

		@Override
		protected GuiFileIoChooser getFileChooser() {
			return chooser;
		}
	}

	/**
	 * The {@code FileIoActionListener.Output} is the action listener for file output that contains the file chooser within the specified parameters for it.
	 * 
	 * @author Aragorn
	 */
	public static class Output extends FileIoActionListener {
		/**
		 * To normalize the output file name with extension if user did not type extension.
		 * 
		 * @param file
		 *            the file pointer
		 * @param extension
		 *            the file extension
		 * @return If user typed file extension correctly, return the file name, else return the file name with extension.
		 */
		private static String toNormalizedFilePathString(File file, String extension) {
			if (file.isDirectory()) {
				throw new IllegalArgumentException("File should not be directory.");
			}
			String file_path = file.getAbsolutePath();
			String file_name = file.getName();
			String file_directory = file_path.substring(0, file_path.length() - file_name.length());
			System.out.println("file_path: " + file_path);
			System.out.println("file_name: " + file_name);
			System.out.println("file_directory: " + file_directory);
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

		/** The file chooser to choose the file. */
		private GuiFileIoChooser chooser = new GuiFileIoChooser.Output();

		/** Create a action listener to choose file. */
		public Output() {
			super();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (showDialog(null) == JFileChooser.APPROVE_OPTION) {
				if (getFileChooser().getChoosableFileFilters().length > 0) {
					setSelectedFile(new File(FileIoActionListener.Output.toNormalizedFilePathString(getFileChooser().getSelectedFile(),
							((FileNameExtensionFilter) (getFileChooser().getChoosableFileFilters()[0])).getExtensions()[0])));
				} else {
					setSelectedFile(getFileChooser().getSelectedFile());
				}
				JOptionPane.showMessageDialog(null, "Save file: " + getSelectFile().getPath());
			} else {
				setSelectedFile(null);
				JOptionPane.showMessageDialog(null, "File output command cancelled by user.");
			}
		}

		@Override
		protected GuiFileIoChooser getFileChooser() {
			return chooser;
		}
	}

	/** The selected file chosen by the file chooser. */
	private File selected_file = null;

	/** Create a action listener to choose file. */
	protected FileIoActionListener() {
		super();
	}

	/**
	 * Get the pointer of the file chooser.
	 * 
	 * @return the pointer point to the file chooser
	 */
	protected abstract GuiFileIoChooser getFileChooser();

	/**
	 * Get the selected file chosen by the file chooser.
	 * 
	 * @return selected file
	 */
	public File getSelectFile() {
		return selected_file;
	}

	/**
	 * Set the selected file chosen by the file chooser.
	 * 
	 * @param selected_file
	 *            the selected file
	 */
	protected void setSelectedFile(File selected_file) {
		this.selected_file = selected_file;
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
	public int showDialog(Component parent) {
		return getFileChooser().showDialog(parent);
	}
}