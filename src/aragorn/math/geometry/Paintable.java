package aragorn.math.geometry;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import aragorn.util.MathVector2D;

/**
 * A class implements the {@code Paintable} interface indicates that it can be painted on the panel.
 * 
 * @author Aragorn
 */
public interface Paintable {

	/**
	 * Draw a segment with starting point and a vector.
	 * 
	 * @param g
	 *     the {@code Graphics} reference
	 * @param c
	 *     the {@code Coordinate2D} reference
	 * @param starting_point
	 *     the starting point
	 * @param vector
	 *     the vector
	 */
	public static void drawLine(Graphics g, Coordinate2D c, Point2D.Double starting_point, MathVector2D vector) {
		Paintable.drawLine(g, c, starting_point, MathVector2D.add(starting_point, vector));
	}

	/**
	 * Draw a segment with two end point.
	 *
	 * @param g
	 *     the {@code Graphics} reference
	 * @param c
	 *     the {@code Coordinate2D} reference
	 * @param point_0
	 *     the first point
	 * @param point_1
	 *     the second point
	 */
	public static void drawLine(Graphics g, Coordinate2D c, Point2D.Double point_0, Point2D.Double point_1) {
		if (c == null) {
			drawLine(g, new Coordinate2D(), point_0, point_1);
			return;
		}
		Point2D.Double converted_point_0 = c.convertToPanel(point_0);
		Point2D.Double converted_point_1 = c.convertToPanel(point_1);
		g.drawLine((int) converted_point_0.getX(), (int) converted_point_0.getY(), (int) converted_point_1.getX(), (int) converted_point_1.getY());
	}

	public static void drawOval(Graphics g, Coordinate2D c, Point2D.Double center, double width, double height) {
		paintOval(g, c, false, center, width, height);
	}

	public static void drawRectangle(Graphics g, Coordinate2D c, Point2D.Double reference_point, double width, double height) {
		paintRectangle(g, c, false, reference_point, width, height);
	}

	public static void fillOval(Graphics g, Coordinate2D c, Point2D.Double center, double width, double height) {
		paintOval(g, c, true, center, width, height);
	}

	public static void fillRectangle(Graphics g, Coordinate2D c, Point2D.Double reference_point, double width, double height) {
		paintRectangle(g, c, true, reference_point, width, height);
	}

	public static void paintOval(Graphics g, Coordinate2D c, boolean to_fill, Point2D.Double center, double width, double height) {
		if (c == null) {
			paintOval(g, new Coordinate2D(), to_fill, center, width, height);
			return;
		}
		Point2D.Double converted_point_center = c.convertToPanel(center);
		MathVector2D convert_size = c.convertToPanel(new MathVector2D(width, height));
		int x_min = (int) Math.round(converted_point_center.getX() - convert_size.getX() / 2.0);
		int x_max = (int) Math.round(converted_point_center.getX() + convert_size.getX() / 2.0);
		int y_min = (int) Math.round(converted_point_center.getY() - convert_size.getY() / 2.0);
		int y_max = (int) Math.round(converted_point_center.getY() + convert_size.getY() / 2.0);
		if (to_fill) {
			g.fillOval(x_min, y_min, x_max - x_min, y_max - y_min);
		} else {
			g.drawOval(x_min, y_min, x_max - x_min, y_max - y_min);
		}
	}

	public static void paintRectangle(Graphics g, Coordinate2D c, boolean to_fill, Point2D.Double reference_point, double width, double height) {
		if (c == null) {
			paintRectangle(g, new Coordinate2D(), to_fill, reference_point, width, height);
			return;
		}
		Point2D.Double converted_point_reference_point = c.convertToPanel(reference_point);
		MathVector2D convert_size = c.convertToPanel(new MathVector2D(width, height));
		int x_min = (int) Math.round(converted_point_reference_point.getX());
		int x_max = (int) Math.round(converted_point_reference_point.getX() + convert_size.getX());
		int y_min = (int) Math.round(converted_point_reference_point.getY());
		int y_max = (int) Math.round(converted_point_reference_point.getY() + convert_size.getY());
		if (to_fill) {
			g.fillRect(x_min, y_min, x_max - x_min, y_max - y_min);
		} else {
			g.drawRect(x_min, y_min, x_max - x_min, y_max - y_min);
		}
	}

	/**
	 * To implement how to draw object.
	 * 
	 * @param g
	 *     the {@code Graphics} reference
	 * @param c
	 *     the {@code Coordinate2D} reference
	 */
	public void draw(Graphics g, Coordinate2D c);

	/**
	 * Returns an Rectangle that completely encloses the {@code Paintable) object. @return an Rectangle that completely encloses the {@code Paintable) object
	 */
	public Rectangle2D.Double getBounds();
}