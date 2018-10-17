package aragorn.gui;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.InvalidParameterException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public abstract class GuiFieldPanel extends GuiPanel {

	public static abstract class Double extends GuiFieldPanel.RangedDouble {

		public Double(String prefix, String suffix, String button_text, double default_value) {
			super(prefix, suffix, button_text, default_value, java.lang.Double.NEGATIVE_INFINITY, java.lang.Double.POSITIVE_INFINITY);
		}

		@Override
		protected String getValueTypeName() {
			return "number";
		}

		@Override
		protected void setValueByField() {
			double value = java.lang.Double.valueOf(field.getText());
			this.value = value;
		}
	}

	public static abstract class Integer extends GuiFieldPanel.RangedInteger {

		public Integer(String prefix, String suffix, String button_text, int default_value) {
			super(prefix, suffix, button_text, default_value, java.lang.Integer.MIN_VALUE, java.lang.Integer.MAX_VALUE);
		}

		@Override
		protected String getValueTypeName() {
			return "integer";
		}

		@Override
		protected void setValueByField() {
			int value = java.lang.Integer.valueOf(field.getText());
			this.value = value;
		}
	}

	public static abstract class RangedDouble extends GuiFieldPanel {

		protected double value;

		private double lower_bound;

		private double upper_bound;

		public RangedDouble(String prefix, String suffix, String button_text, double default_value, double lower_bound, double upper_bound) {
			super(prefix, suffix, button_text);
			if (lower_bound > default_value || upper_bound < default_value) {
				throw new InvalidParameterException("The error happened for the bounds.");
			}
			this.value = default_value;
			this.lower_bound = lower_bound;
			this.upper_bound = upper_bound;
			field.setText(getValue().toString()); // XXX
		}

		@Override
		public java.lang.Double getValue() {
			return value;
		}

		@Override
		protected String getValueTypeName() {
			return String.format("number in range[%s, %s]", lower_bound + "", upper_bound + "");
		}

		@Override
		protected void setValueByField() {
			double value = java.lang.Double.valueOf(field.getText());
			if (lower_bound < value && value < upper_bound) {
				this.value = value;
			} else {
				throw new NumberFormatException(String.format("The input must be %s.", getValueTypeNameWithIndefiniteArticle()));
			}
		}
	}

	public static abstract class RangedInteger extends GuiFieldPanel {

		protected int value;

		private int lower_bound;

		private int upper_bound;

		public RangedInteger(String prefix, String suffix, String button_text, int default_value, int lower_bound, int upper_bound) {
			super(prefix, suffix, button_text);
			if (lower_bound > default_value || upper_bound < default_value) {
				throw new InvalidParameterException("The error happened for the bounds.");
			}
			this.value = default_value;
			this.lower_bound = lower_bound;
			this.upper_bound = upper_bound;
			field.setText(getValue().toString()); // XXX
		}

		@Override
		public java.lang.Integer getValue() {
			return value;
		}

		@Override
		protected String getValueTypeName() {
			return String.format("integer in range[%s, %s]", lower_bound + "", upper_bound + "");
		}

		@Override
		protected void setValueByField() {
			int value = java.lang.Integer.valueOf(field.getText());
			if (lower_bound < value && value < upper_bound) {
				this.value = value;
			} else {
				throw new NumberFormatException(String.format("The input must be %s.", getValueTypeNameWithIndefiniteArticle()));
			}
		}
	}

	private static String getIndefiniteArticle(String object) {
		switch (object.toLowerCase().charAt(0)) {
			case 'a':
			case 'e':
			case 'i':
			case 'o':
			case 'u':
				return "an";
			default:
				return "a";
		}
	}

	protected JTextField field;

	private JButton button;

	private int added_components_number = 0;

	protected GuiFieldPanel(String prefix, String suffix, String button_text) {
		field = new JTextField(getValue().toString());
		field.setColumns(3);
		field.setHorizontalAlignment(SwingConstants.RIGHT);
		button = new JButton(button_text);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					setValueByField();
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, String.format("The input must be %s.", getValueTypeNameWithIndefiniteArticle()), "Warning",
							JOptionPane.WARNING_MESSAGE);
				} finally {
					field.setText(getValue().toString());
				}
				set();
			}
		});

		if (prefix != null)
			addComponent(new JLabel(prefix), 0);
		addComponent(field, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 10, 0, 10));
		if (suffix != null)
			addComponent(new JLabel(suffix), 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 10));
		addComponent(button, 0);
	}

	private void addComponent(JComponent component, int grid_y) {
		addComponent(component, added_components_number, grid_y);
		added_components_number++;
	}

	@SuppressWarnings("unused")
	private void addComponent(JComponent component, int grid_y, int grid_width, int grid_height, double weight_x, double weight_y, int anchor, int fill) {
		addComponent(component, added_components_number, grid_y, grid_width, grid_height, weight_x, weight_y, anchor, fill, new Insets(0, 0, 0, 0));
		added_components_number++;
	}

	private void addComponent(JComponent component, int grid_y, int grid_width, int grid_height, double weight_x, double weight_y, int anchor, int fill, Insets insets) {
		addComponent(component, added_components_number, grid_y, grid_width, grid_height, weight_x, weight_y, anchor, fill, insets);
		added_components_number++;
	}

	public abstract Number getValue();

	protected abstract String getValueTypeName();

	protected String getValueTypeNameWithIndefiniteArticle() {
		return String.format("%s %s", GuiFieldPanel.getIndefiniteArticle(getValueTypeName()), getValueTypeName());
	}

	protected abstract void set();

	protected abstract void setValueByField();
}