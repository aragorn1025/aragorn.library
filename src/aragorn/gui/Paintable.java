package aragorn.gui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
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
			g.drawLine((int) point_0.getX(), (int) point_0.getY(), (int) point_1.getX(), (int) point_1.getY());
		} else {
			Point2D.Double converted_point_0 = c.convertToPanel(point_0);
			Point2D.Double converted_point_1 = c.convertToPanel(point_1);
			g.drawLine((int) converted_point_0.getX(), (int) converted_point_0.getY(), (int) converted_point_1.getX(), (int) converted_point_1.getY());
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
	 * Returns an integer Rectangle that completely encloses the Paintable object.
	 * 
	 * @return an integer Rectangle that completely encloses the Paintable object
	 */
	public Rectangle getBounds();
}