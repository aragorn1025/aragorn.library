package aragorn.gui;

import java.awt.geom.Point2D;
import java.security.InvalidParameterException;
import aragorn.util.MathVector2D;

/**
 * {@code Coordinate2D} is a converter which convert (x, y) value on the coordinate to the panel by the center and the unit vector.
 * 
 * @author Aragorn
 */
public class Coordinate2D {

	/** The location of the center on the panel. */
	private Point2D.Double center;

	/** The unit vector of the x-axis. */
	private double unit_x;

	/** The unit vector of the y-axis. */
	private double unit_y;

	/** Create Coordinate2D that set the center by (0, 0) (the left top) and set the unit vector of x-axis to be 1, y-axis to be -1. */
	public Coordinate2D() {
		this(new Point2D.Double(0, 0), 1, -1);
	}

	/**
	 * Create Coordinate2D by the center and the unit vector of each axis.
	 * 
	 * @param center
	 *     the location of the center on the panel
	 * @param unit_x
	 *     the unit vector of the x-axis
	 * @param unit_y
	 *     the unit vector of the y-axis
	 */
	public Coordinate2D(Point2D.Double center, double unit_x, double unit_y) {
		setCenter(center);
		setUnit(unit_x, unit_y);
	}

	/**
	 * Convert the vector on the coordinate to the panel.
	 * 
	 * @param vector
	 *     the vector on the coordinate
	 * @return the vector on the panel
	 */
	public MathVector2D convertToPanel(MathVector2D vector) {
		return new MathVector2D(vector.getX() * unit_x, -vector.getY() * unit_y);
	}

	/**
	 * Convert the point on the coordinate to the panel.
	 * 
	 * @param point
	 *     the point on the coordinate
	 * @return the point on the panel
	 */
	public Point2D.Double convertToPanel(Point2D.Double point) {
		return new Point2D.Double(center.getX() + point.getX() * unit_x, center.getY() - point.getY() * unit_y);
	}

	/**
	 * Revert the vector on the panel to the coordinate.
	 * 
	 * @param vector
	 *     the vector on the panel
	 * @return the vector on the coordinate
	 */
	public MathVector2D revertToCoordinate(MathVector2D vector) {
		return new MathVector2D(vector.getX() / unit_x, vector.getY() / unit_y);
	}

	/**
	 * Revert the point on the panel to the coordinate.
	 * 
	 * @param point
	 *     the point on the panel
	 * @return the point on the coordinate
	 */
	public Point2D.Double revertToCoordinate(Point2D.Double point) {
		return new Point2D.Double((point.getX() - center.getX()) / unit_x, -(point.getY() - center.getY()) / unit_y);
	}

	/**
	 * Set the location of the center on the panel.
	 * 
	 * @param center
	 *     the location of the center on the panel
	 */
	public void setCenter(Point2D.Double center) {
		if (center == null)
			throw new NullPointerException("The pointer of the center must not be null");
		if (!Double.isFinite(center.getX()))
			throw new InvalidParameterException("The x value of the center on the panel must be a finite number.");
		if (!Double.isFinite(center.getY()))
			throw new InvalidParameterException("The y value of the center on the panel must be a finite number.");
		if (center.getX() < 0)
			throw new InvalidParameterException("The x value of the center on the panel must be a positive number.");
		if (center.getY() < 0)
			throw new InvalidParameterException("The y value of the center on the panel must be a positive number.");
		this.center = center;
	}

	/**
	 * Set the unit vector of the each axis on the panel.
	 * 
	 * @param unit_x
	 *     the unit vector of the x-axis. If the value of the vector is positive, the positive way of the coordinate will be the right of the panel. Otherwise, if the
	 *     value of the vector is negative, the positive way of the coordinate will be the left of the panel. The value can't be 0.
	 * @param unit_y
	 *     the unit vector of the y-axis. If the value of the vector is positive, the positive way of the coordinate will be the top of the panel. Otherwise, if the value
	 *     of the vector is negative, the positive way of the coordinate will be the bottom of the panel. The value can't be 0.
	 */
	public void setUnit(double unit_x, double unit_y) {
		if (!Double.isFinite(unit_x) || unit_x == 0.0)
			throw new InvalidParameterException("The x value of the unit on the panel must be a non-zero finite number.");
		if (!Double.isFinite(unit_y) || unit_y == 0.0)
			throw new InvalidParameterException("The y value of the unit on the panel must be a non-zero finite number.");
		this.unit_x = unit_x;
		this.unit_y = unit_y;
	}
}