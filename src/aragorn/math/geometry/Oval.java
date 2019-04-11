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
		return center;
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}
}