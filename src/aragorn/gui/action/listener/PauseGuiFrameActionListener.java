package aragorn.gui.action.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import aragorn.gui.GuiFrame;

public class PauseGuiFrameActionListener implements ActionListener {

	private GuiFrame frame;

	public PauseGuiFrameActionListener(GuiFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.pause();
	}
}