package aragorn.math.geometry;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

import aragorn.util.MathVector2D;

public class LineSegment2D implements Paintable {

	private Point2D.Double starting_point;

	private Point2D.Double ending_point;

	public LineSegment2D() {
		this(new Point2D.Double(), new Point2D.Double());
	}

	public LineSegment2D(Point2D.Double starting_point, Point2D.Double ending_point) {
		this.starting_point = (Point2D.Double) starting_point.clone();
		this.ending_point = (Point2D.Double) ending_point.clone();
	}

	@Override
	public void draw(Graphics g, Coordinate2D c) {
		Paintable.drawLine(g, c, starting_point, new MathVector2D(starting_point, ending_point));
	}

	@Override
	public Rectangle.Double getBounds() {
		double x_min = Math.min(starting_point.getX(), ending_point.getX());
		double x_max = Math.max(starting_point.getX(), ending_point.getX());
		double y_min = Math.min(starting_point.getY(), ending_point.getY());
		double y_max = Math.max(starting_point.getY(), ending_point.getY());
		return new Rectangle.Double(x_min, y_min, x_max - x_min, y_max - y_min);
	}
}