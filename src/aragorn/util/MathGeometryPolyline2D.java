package aragorn.util;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import aragorn.gui.GuiCoordinate2D;
import aragorn.gui.GuiPaintable;

public class MathGeometryPolyline2D implements GuiPaintable {

	private ArrayList<Point2D.Double> points = new ArrayList<>();

	/**
	 * Constructs and initializes a {@code Polyline} from the specified parameters.
	 * 
	 * @param points
	 *     the points in order
	 * @throws NullPointerException
	 *     if any of the points is null
	 */
	public MathGeometryPolyline2D(Point2D.Double... points) {
		for (int i = 0; i < points.length; i++) {
			if (points[i] == null) {
				throw new NullPointerException("The input points should not be null.");
			}
		}
		for (int i = 0; i < points.length; i++) {
			addPoint(points[i]);
		}
	}

	public MathGeometryPolyline2D(Point2D.Double starting_point, MathVector2D... vectors) {
		if (starting_point == null)
			throw new NullPointerException("The starting point should not be null.");
		for (int i = 0; i < vectors.length; i++) {
			if (vectors[i] == null) {
				throw new NullPointerException("The input vectors should not be null.");
			}
		}
		addPoint(starting_point);
		Point2D.Double p = (Point2D.Double) starting_point.clone();
		for (int i = 0; i < vectors.length; i++) {
			p = MathVector2D.add(p, vectors[i]);
			addPoint(p);
		}
	}

	public void addPoint(double x, double y) {
		points.add(new Point2D.Double(x, y));
	}

	public void addPoint(Point2D.Double point) {
		this.addPoint(point.getX(), point.getY());
	}

	@Override
	public void draw(Graphics g, GuiCoordinate2D c) {
		for (int i = 1; i < getPointsNumber(); i++) {
			GuiPaintable.drawLine(g, c, getPoint(i - 1), getPoint(i));
		}
	}

	@Override
	public Rectangle getBounds() {
		if (getPointsNumber() == 0)
			return new Rectangle();
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
		int x = (int) Math.floor(x_min);
		int y = (int) Math.floor(y_min);
		int w = (int) Math.ceil(x_max) - x;
		int h = (int) Math.ceil(y_max) - y;
		return new Rectangle(x, y, w, h);
	}

	public Point2D.Double getPoint(int index) {
		return points.get(index);
	}

	public int getPointsNumber() {
		return points.size();
	}
}