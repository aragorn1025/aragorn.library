package aragorn.gui.action.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import aragorn.gui.GuiFrame;

public class CloseGuiFrameActionListener implements ActionListener {

	private GuiFrame frame;

	public CloseGuiFrameActionListener(GuiFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.close();
	}
}