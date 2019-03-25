package aragorn.gui;

import javax.swing.JMenuItem;

/**
 * {@code GuiMenuItem} is a menu item which extends {@code javax.swing.JMenuItem} with custom setMnemonic.
 * 
 * @author Aragorn
 */
@SuppressWarnings("serial")
public class GuiMenuItem extends JMenuItem {
	private String text = "";

	/**
	 * Create {@code GuiMenuItem} with the text and set mnemonic by the first character.
	 * 
	 * @param text
	 *     the text of the {@code GuiMenuItem}
	 */
	public GuiMenuItem(String text) {
		this(text, text.charAt(0));
	}

	/**
	 * Create {@code GuiMenuItem} with the text and specific mnemonic.
	 * 
	 * @param text
	 *     the text of the {@code GuiMenuItem}
	 * @param mnemonic
	 *     the mnemonic character to be set
	 */
	public GuiMenuItem(String text, char mnemonic) {
		this.text = text;
		setMnemonic(mnemonic);
	}

	/** Set mnemonic and then set text in specific format at the same time. */
	@Override
	public void setMnemonic(char mnemonic) {
		super.setMnemonic(mnemonic);
		setText(this.text);
	}

	/** Set text in specific format if mnemonic is set. */
	@Override
	public void setText(String text) {
		this.text = text;
		if (getMnemonic() == 0) {
			super.setText(this.text);
			setDisplayedMnemonicIndex(-1);
		} else {
			super.setText(String.format("%s (%c)", this.text, (char) getMnemonic()));
			setDisplayedMnemonicIndex(this.text.length() + 2);
		}
	}
}