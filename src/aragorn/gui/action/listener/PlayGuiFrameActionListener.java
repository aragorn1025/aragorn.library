package aragorn.gui.action.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import aragorn.gui.GuiFrame;

public class PlayGuiFrameActionListener implements ActionListener {

	private GuiFrame frame;

	public PlayGuiFrameActionListener(GuiFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.play();
	}
}