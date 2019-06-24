package aragorn.math.geometry;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import aragorn.util.MathVector2D;

public class Polyline2D implements Cloneable, Paintable {

	private ArrayList<Point2D.Double> points = new ArrayList<>();

	public Polyline2D() {
	}

	public Polyline2D(Point2D.Double point) {
		this();
		if (point == null)
			throw new NullPointerException("The input points should not be null.");
		addPoint(point);
	}

	public Polyline2D(Point2D.Double starting_point, MathVector2D... vectors) {
		this(starting_point);
		for (int i = 0; i < vectors.length; i++) {
			if (vectors[i] == null) {
				throw new NullPointerException("The input vectors should not be null.");
			}
		}
		Point2D.Double p = (Point2D.Double) starting_point.clone();
		for (int i = 0; i < vectors.length; i++) {
			p = MathVector2D.add(p, vectors[i]);
			addPoint(p);
		}
	}

	/**
	 * Constructs and initializes a {@code Polyline} from the specified parameters.
	 * 
	 * @throws NullPointerException
	 *     if any of the points is null
	 */
	public Polyline2D(Point2D.Double point_0, Point2D.Double point_1, Point2D.Double... points) {
		this(point_0);
		if (point_1 == null)
			throw new NullPointerException("The input points should not be null.");
		for (int i = 0; i < points.length; i++) {
			if (points[i] == null) {
				throw new NullPointerException("The input points should not be null.");
			}
		}
		addPoint(point_1);
		for (int i = 0; i < points.length; i++) {
			addPoint(points[i]);
		}
	}

	public void addPoint(double x, double y) {
		points.add(new Point2D.Double(x, y));
	}

	public void addPoint(Point2D.Double point) {
		this.addPoint(point.getX(), point.getY());
	}

	@Override
	public Object clone() {
		Polyline2D val = new Polyline2D();
		for (int i = 0; i < this.getPointNumber(); i++) {
			val.addPoint((Point2D.Double) this.getPoint(i).clone());
		}
		return val;
	}

	@Override
	public void draw(Graphics g, Coordinate2D c) {
		for (int i = 1; i < getPointNumber(); i++) {
			Paintable.drawLine(g, c, getPoint(i - 1), getPoint(i));
		}
	}

	@Override
	public Rectangle2D.Double getBounds() {
		if (getPointNumber() == 0)
			return new Rectangle2D.Double();
		double x_min = points.get(0).getX();
		double x_max = points.get(0).getX();
		double y_min = points.get(0).getY();
		double y_max = points.get(0).getY();
		for (int i = 1; i < getPointNumber(); i++) {
			x_min = Math.min(x_min, points.get(i).getX());
			x_max = Math.max(x_max, points.get(i).getX());
			y_min = Math.min(y_min, points.get(i).getY());
			y_max = Math.max(y_max, points.get(i).getY());
		}
		return new Rectangle2D.Double(x_min, y_min, x_max - x_min, y_max - y_min);
	}

	public LineSegment2D getLineSegment(int index) {
		if (getPointNumber() < 0)
			throw new InternalError("Unknown polyline.");
		if (getPointNumber() == 0 && index != 0)
			throw new InvalidParameterException("Index out of bounds.");
		if (getPointNumber() == 0 && index == 0)
			throw new IllegalStateException("No segment get for the polygon with no points.");
		return new LineSegment2D(getPoint(index), getPoint(index + 1));
	}

	public Point2D.Double getPoint(int index) {
		return (Point2D.Double) points.get(index).clone();
	}

	public int getPointNumber() {
		return points.size();
	}

	@Override
	public String toString() {
		String val = getClass().getSimpleName() + " [";
		for (int i = 0; i < getPointNumber(); i++) {
			val += String.format("(%.3f, %.3f), ", getPoint(i).x, getPoint(i).y);
		}
		val = val.substring(0, val.length() - 2) + "]";
		return val;
	}
}