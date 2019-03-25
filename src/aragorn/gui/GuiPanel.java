package aragorn.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.security.InvalidParameterException;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * {@code GuiPanel} is a panel which extends {@code javax.swing.JPanel} and uses {@code java.awt.GridBagLayout}.
 * 
 * @author Aragorn
 * @see <a target="_blank" href= "https://docs.oracle.com/javase/8/docs/api/java/awt/GridBagConstraints.html">GridBagConstraints</a>
 */
@SuppressWarnings("serial")
public class GuiPanel extends JPanel {

	/**
	 * Set default margin between components.<br>
	 * The margin should be non-negative and its default value is {@code 0}.
	 */
	private int margin = 0;

	/**
	 * Creates a {@code GuiPanel} which extends {@code JPanel} and uses {@code GridBagLayout}.
	 */
	public GuiPanel() {
		this(null);
	}

	/**
	 * Creates a {@code GuiPanel} which extends {@code JPanel} with {@code TitledBorder} and uses {@code GridBagLayout}.
	 * 
	 * @param title
	 *     the title for {@code TitledBorder}
	 */
	public GuiPanel(String title) {
		setLayout(new GridBagLayout());
		if (title != null) {
			setBorder(new TitledBorder(null, title));
		}
	}

	/**
	 * Add component to the chosen grid at the center.
	 * 
	 * @param component
	 *     the component to be add
	 * @param grid_x
	 *     the grid location of x which should be non-negative, and the first cell in a row has {@code grid_x=0}
	 * @param grid_y
	 *     the grid location of y which should be non-negative, and the first cell in a column has {@code grid_y=0}
	 * @see #addComponent(JComponent, int, int, int, int, double, double, int, int, Insets)
	 * @see #addComponent(JComponent, int, int, int, int, double, double, int, int)
	 */
	public void addComponent(JComponent component, int grid_x, int grid_y) {
		int anchor = GridBagConstraints.CENTER;
		int fill = GridBagConstraints.NONE;
		addComponent(component, grid_x, grid_y, 1, 1, 0, 0, anchor, fill);
	}

	/**
	 * Add component on the grid with chosen anchor, chosen fill and default margin.
	 * 
	 * @param component
	 *     the component to be add
	 * @param grid_x
	 *     the grid location of x which should be non-negative, and the first cell in a row has {@code grid_x=0}
	 * @param grid_y
	 *     the grid location of y which should be non-negative, and the first cell in a column has {@code grid_y=0}
	 * @param grid_width
	 *     the non-negative number of grids used by the component in the row, and its default value is {@code 1}
	 * @param grid_height
	 *     the non-negative number of grids used by the component in the column, and its default value is {@code 1}
	 * @param weight_x
	 *     the non-negative weight value in row, and its default value is {@code 0}
	 * @param weight_y
	 *     the non-negative weight value in column, and its default value is {@code 0}
	 * @param anchor
	 *     determines where, within the display area, to place the component, and its default value is {@code CENTER}
	 * @param fill
	 *     determines whether and how to resize the component,and its default value is {@code NONE}
	 * @see #addComponent(JComponent, int, int, int, int, double, double, int, int, Insets)
	 */
	public void addComponent(JComponent component, int grid_x, int grid_y, int grid_width, int grid_height, double weight_x, double weight_y, int anchor, int fill) {
		Insets insets = new Insets((grid_y == 0 ? this.margin : 0), (grid_x == 0 ? this.margin : 0), this.margin, this.margin);
		addComponent(component, grid_x, grid_y, grid_width, grid_height, weight_x, weight_y, anchor, fill, insets);
	}

	/**
	 * Add component with almost all constraints to the grid.
	 * 
	 * @param component
	 *     the component to be add
	 * @param grid_x
	 *     the grid location of x which should be non-negative, and the first cell in a row has {@code grid_x=0}
	 * @param grid_y
	 *     the grid location of y which should be non-negative, and the first cell in a column has {@code grid_y=0}
	 * @param grid_width
	 *     the non-negative number of grids used by the component in the row, and its default value is {@code 1}
	 * @param grid_height
	 *     the non-negative number of grids used by the component in the column, and its default value is {@code 1}
	 * @param weight_x
	 *     the non-negative weight value in row, and its default value is {@code 0}
	 * @param weight_y
	 *     the non-negative weight value in column, and its default value is {@code 0}
	 * @param anchor
	 *     determines where, within the display area, to place the component, and its default value is {@code CENTER}
	 * @param fill
	 *     determines whether and how to resize the component,and its default value is {@code NONE}
	 * @param insets
	 *     the custom insets, for setting horizontal and vertical margin length
	 * @see <a target="_blank" href= "https://docs.oracle.com/javase/8/docs/api/java/awt/GridBagConstraints.html">GridBagConstraints</a>
	 */
	public void addComponent(JComponent component, int grid_x, int grid_y, int grid_width, int grid_height, double weight_x, double weight_y, int anchor, int fill,
			Insets insets) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = grid_x;
		gbc.gridy = grid_y;
		gbc.gridwidth = grid_width;
		gbc.gridheight = grid_height;
		gbc.weightx = weight_x;
		gbc.weighty = weight_y;
		gbc.anchor = anchor;
		gbc.fill = fill;
		gbc.insets = insets;
		add(component, gbc);
	}

	/**
	 * Set default margin between components.<br>
	 * The margin should be non-negative and its default value is {@code 0}.
	 * 
	 * @param margin
	 *     the new margin to be set
	 * @throws InvalidParameterException
	 *     if the value of the margin is negative
	 */
	public void setDefaultMargin(int margin) {
		if (margin < 0)
			throw new InvalidParameterException("The value of the margin for GuiPanel must be non-negative.");
		this.margin = margin;
	}

	/** Set panel size for grid bag layout. */
	@Override
	public void setSize(Dimension dimension) {
		setMaximumSize(dimension);
		setMinimumSize(dimension);
	}

	/** Set panel size for grid bag layout. */
	@Override
	public void setSize(int width, int height) {
		this.setSize(new Dimension(width, height));
	}
}