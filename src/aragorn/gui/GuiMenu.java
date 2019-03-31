package aragorn.gui;

import javax.swing.JMenu;

/**
 * {@code GuiMenu} is a menu which extends {@code javax.swing.JMenu} with custom setMnemonic.
 * 
 * @author Aragorn
 */
@SuppressWarnings("serial")
public class GuiMenu extends JMenu {

	private String text = "";

	/**
	 * Create {@code GuiMenu} with the text and set mnemonic by the first character.
	 * 
	 * @param text
	 *     the text of the {@code GuiMenu}
	 */
	public GuiMenu(String text) {
		this(text, text.charAt(0));
	}

	/**
	 * Create {@code GuiMenu} with the text and specific mnemonic.
	 * 
	 * @param text
	 *     the text of the {@code GuiMenu}
	 * @param mnemonic
	 *     the mnemonic character to be set
	 */
	public GuiMenu(String text, char mnemonic) {
		this.text = text;
		setMnemonic(mnemonic);
	}

	/** Set mnemonic and set text in specific format at the same time. */
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