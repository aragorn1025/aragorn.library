package aragorn.gui;

import javax.swing.JMenuBar;

/**
 * {@code GuiMenuBar} is a menu bar which extends JMenuBar.
 * 
 * @author Aragorn
 */
@SuppressWarnings("serial")
public abstract class GuiMenuBar extends JMenuBar {

	protected GuiFrame parent;

	public GuiMenuBar(GuiFrame parent) {
		super();
		this.parent = parent;
		editMenuBar();
	}

	protected abstract void editMenuBar();
}