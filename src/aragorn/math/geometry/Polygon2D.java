package aragorn.math.geometry;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import aragorn.gui.GuiCoordinate2D;
import aragorn.gui.GuiPaintable;
import aragorn.util.MathVector2D;

public class Polygon2D implements GuiPaintable {

	private static Point2D.Double[] getPoints(Point2D.Double point, MathVector2D... vectors) {
		Point2D.Double[] points = new Point2D.Double[1 + vectors.length];
		points[0] = point;
		for (int i = 0; i < vectors.length; i++) {
			points[i + 1] = MathVector2D.add(points[i], vectors[i]);
		}
		return points;
	}

	private Polyline2D polyline = new Polyline2D();

	/**
	 * Constructs and initializes a {@code Polyline2D} by points.
	 */
	public Polygon2D(Point2D.Double... points) {
		if (points[0].equals(points[points.length - 1])) {
			polyline = new Polyline2D(points[0], points[1], Arrays.copyOfRange(points, 2, points.length - 1));
		} else {
			polyline = new Polyline2D(points[0], points[1], Arrays.copyOfRange(points, 2, points.length));
		}
	}

	/**
	 * Constructs and initializes a {@code Polyline2D} by starting point and following vectors.
	 * 
	 * @param point
	 *     the origin point
	 * @param vectors
	 *     the vectors
	 */
	public Polygon2D(Point2D.Double point, MathVector2D... vectors) {
		this(Polygon2D.getPoints(point, vectors));
	}

	@Override
	public void draw(Graphics g, GuiCoordinate2D c) {
		polyline.draw(g, c);
		GuiPaintable.drawLine(g, c, getPoint(0), getPoint(getPointNumber() - 1));
	}

	public Rectangle2D.Double getBounds() {
		return polyline.getBounds();
	}

	public Point2D.Double getPoint(int index) {
		return polyline.getPoint(index);
	}

	public int getPointNumber() {
		return polyline.getPointsNumber();
	}
}