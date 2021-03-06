package aragorn.math.geometry;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Oval implements Paintable {

	private Point2D.Double center;

	private double width;

	private double height;

	public Oval(Point2D.Double center, double radius) {
		this(center, radius * 2.0, radius * 2.0);
	}

	public Oval(Point2D.Double center, double width, double height) {
		this.center = center;
		this.width = width;
		this.height = height;
	}

	@Override
	public void draw(Graphics g, Coordinate2D c) {
		Paintable.drawOval(g, c, center, width, height);
	}

	@Override
	public Rectangle2D.Double getBounds() {
		return new Rectangle2D.Double(center.getX() - width / 2.0, center.getY() - height / 2.0, width, height);
	}

	public Point2D.Double getCenter() {
		return (Point2D.Double) center.clone();
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}

	public boolean isSurround(Point2D.Double point) {
		double dx = point.getX() - center.getX();
		double dy = point.getY() - center.getY();
		return ((4.0 * dx * dx) / (width * width) + (4.0 * dy * dy) / (height * height)) <= 1;
	}
}