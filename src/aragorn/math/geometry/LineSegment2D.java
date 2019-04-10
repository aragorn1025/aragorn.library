package aragorn.math.geometry;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.security.InvalidParameterException;
import aragorn.util.MathVector2D;

public class LineSegment2D implements Cloneable, Paintable {

	public static LineSegment2D get(Polygon2D polygon, int index) {
		if (polygon.getPointNumber() < 0)
			throw new InternalError("Unknown polygon.");
		if (polygon.getPointNumber() == 0 && index != 0) {
			if (index == 0)
				return new LineSegment2D();
			throw new InvalidParameterException("Index out of bounds.");
		}
		if (index == polygon.getPointNumber() - 1)
			return new LineSegment2D(polygon.getPoint(index), polygon.getPoint(0));
		return new LineSegment2D(polygon.getPoint(index), polygon.getPoint(index + 1));
	}

	public static LineSegment2D get(Polyline2D polyline, int index) {
		if (polyline.getPointNumber() < 0)
			throw new InternalError("Unknown polyline.");
		if (polyline.getPointNumber() == 0 && index != 0)
			throw new InvalidParameterException("Index out of bounds.");
		if (polyline.getPointNumber() == 0 && index == 0)
			return new LineSegment2D();
		return new LineSegment2D(polyline.getPoint(index), polyline.getPoint(index + 1));
	}

	private Point2D.Double[] points = new Point2D.Double[2];

	public LineSegment2D() {
		this(new Point2D.Double(), new Point2D.Double());
	}

	public LineSegment2D(Point2D.Double starting_point, MathVector2D vector) {
		this(starting_point, MathVector2D.add(starting_point, vector));
	}

	public LineSegment2D(Point2D.Double starting_point, Point2D.Double ending_point) {
		this.points[0] = (Point2D.Double) starting_point.clone();
		this.points[1] = (Point2D.Double) ending_point.clone();
	}

	@Override
	public Object clone() {
		LineSegment2D val = null;
		try {
			val = (LineSegment2D) super.clone();
			val.points = val.points.clone();
			val.points[0] = (Point2D.Double) points[0].clone();
			val.points[1] = (Point2D.Double) points[1].clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
		return val;
	}

	@Override
	public void draw(Graphics g, Coordinate2D c) {
		Paintable.drawLine(g, c, points[0], new MathVector2D(points[0], points[1]));
	}

	@Override
	public Rectangle.Double getBounds() {
		double x_min = Math.min(points[0].getX(), points[1].getX());
		double x_max = Math.max(points[0].getX(), points[1].getX());
		double y_min = Math.min(points[0].getY(), points[1].getY());
		double y_max = Math.max(points[0].getY(), points[1].getY());
		return new Rectangle.Double(x_min, y_min, x_max - x_min, y_max - y_min);
	}

	public double getDistance(Point2D.Double point) {
		MathVector2D v0 = new MathVector2D(points[0], points[1]);
		MathVector2D v1 = new MathVector2D(points[0], point);
		double val = (v0.getX() * v1.getX() + v0.getY() * v1.getY()) / (v0.getX() * v0.getX() + v0.getY() * v0.getY());
		if (val < 0)
			return Point2D.distance(points[0].getX(), points[0].getY(), point.getX(), point.getY());
		if (val > 1)
			return Point2D.distance(points[1].getX(), points[1].getY(), point.getX(), point.getY());
		return MathVector2D.add(v1, v0.getScalarMultiply(-val)).getLength();
	}

	public Point2D.Double getPoint(int index) {
		return points[index];
	}

	public int getPointsNumber() {
		return 2;
	}

	public MathVector2D getVector() {
		return new MathVector2D(points[0], points[1]);
	}

	@Override
	public String toString() {
		return String.format("%s [(%.3f, %.3f), (%.3f, %.3f)]", getClass().getSimpleName(), points[0].x, points[0].y, points[1].x, points[1].y);
	}
}