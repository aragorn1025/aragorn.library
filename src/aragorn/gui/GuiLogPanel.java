package aragorn.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class GuiLogPanel extends GuiPanel {

	private int default_width;

	private GuiLog log;

	private JTextArea text_area = new JTextArea();

	public GuiLogPanel() {
		super("Log");

		text_area.setEditable(false);
		text_area.setBackground(Color.WHITE);
		text_area.setLineWrap(true);
		text_area.setWrapStyleWord(true);
		JScrollPane scroll_pane = new JScrollPane(text_area);
		scroll_pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		addComponent(scroll_pane, 0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

		this.default_width = getPreferredSize().width;
	}

	public GuiLogPanel(int max_message_number) {
		this();
		log = new GuiLog(max_message_number);
	}

	public void clear() {
		log.clear();
	}

	public void echo(String message) {
		log.add(message);
		text_area.setText(log.get());
	}

	@Override
	public Dimension getMaximumSize() {
		return new Dimension(default_width, super.getMaximumSize().height);
	}

	@Override
	public Dimension getMinimumSize() {
		return new Dimension(default_width, super.getMinimumSize().height);
	}
}