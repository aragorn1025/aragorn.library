package aragorn.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class GuiLogPanel extends GuiPanel {

	private JTextArea text_area = new JTextArea();

	private Log log = new Log();

	private int default_width;

	public GuiLogPanel() {
		super("Output");

		text_area.setEditable(false);
		text_area.setBackground(Color.WHITE);
		text_area.setLineWrap(true);
		text_area.setWrapStyleWord(true);
		JScrollPane scroll_pane = new JScrollPane(text_area);
		scroll_pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		addComponent(scroll_pane, 0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

		this.default_width = getPreferredSize().width;
	}

	public GuiLogPanel(int max_message_number) {
		this();
		setMaxMessageNumber(max_message_number);
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

	public void setMaxMessageNumber(int max_message_number) {
		log.setMaxMessageNumber(max_message_number);
	}
}