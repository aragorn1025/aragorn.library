package aragorn.gui;

import javax.swing.JMenuBar;

/**
 * {@code MyMenuBar} is a menu bar which extends JMenuBar. Using menu name as adding the menu item to the menu.
 * 
 * @author Aragorn
 */
@SuppressWarnings("serial")
public abstract class GuiMenuBar extends JMenuBar {

	public GuiMenuBar() {
		super();
		editMenuBar();
	}

	protected abstract void editMenuBar();
}