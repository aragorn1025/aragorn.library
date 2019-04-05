package aragorn.math.geometry;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import aragorn.util.MathVector2D;

public class Polyline2D implements Paintable {

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
	public void draw(Graphics g, Coordinate2D c) {
		for (int i = 1; i < getPointsNumber(); i++) {
			Paintable.drawLine(g, c, getPoint(i - 1), getPoint(i));
		}
	}

	public Rectangle2D.Double getBounds() {
		if (getPointsNumber() == 0)
			return new Rectangle2D.Double();
		double x_min = points.get(0).getX();
		double x_max = points.get(0).getX();
		double y_min = points.get(0).getY();
		double y_max = points.get(0).getY();
		for (int i = 1; i < getPointsNumber(); i++) {
			x_min = Math.min(x_min, points.get(i).getX());
			x_max = Math.max(x_max, points.get(i).getX());
			y_min = Math.min(y_min, points.get(i).getY());
			y_max = Math.max(y_max, points.get(i).getY());
		}
		return new Rectangle2D.Double(x_min, y_min, x_max - x_min, y_max - y_min);
	}

	public Point2D.Double getPoint(int index) {
		return points.get(index);
	}

	public int getPointsNumber() {
		return points.size();
	}
}