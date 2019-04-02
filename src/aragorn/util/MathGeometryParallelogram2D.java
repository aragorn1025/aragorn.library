package aragorn.util;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import aragorn.gui.GuiCoordinate2D;
import aragorn.gui.GuiPaintable;

public class MathGeometryParallelogram2D implements GuiPaintable {

	private MathGeometryPolyline2D polyline;

	/**
	 * Constructs and initializes an empty {@code Parallelogram}.
	 */
	public MathGeometryParallelogram2D() {
		this(new Point2D.Double(), new MathVector2D(), new MathVector2D());
	}

	/**
	 * Constructs and initializes a {@code Parallelogram} from the specified parameters.
	 * 
	 * @param point
	 *     the origin point
	 * @param vector_0
	 *     the first vector
	 * @param vector_1
	 *     the second vector
	 */
	public MathGeometryParallelogram2D(Point2D.Double point, MathVector2D vector_0, MathVector2D vector_1) {
		polyline = new MathGeometryPolyline2D(point, vector_0, vector_1, vector_0.getNegative(), vector_1.getNegative());
	}

	public boolean isSurround(Point2D.Double point) {
		MathVector2D vector_0 = new MathVector2D(polyline.getPoint(0), polyline.getPoint(1));
		MathVector2D vector_1 = new MathVector2D(polyline.getPoint(1), polyline.getPoint(2));
		double delta = MathUtilities.determinant_2_2(vector_0.getX(), vector_0.getY(), vector_1.getX(), vector_1.getY());
		if (delta == 0)
			return false;
		MathVector2D vector = new MathVector2D(polyline.getPoint(0), point);
		double a = (vector_0.getX() * vector.getX() + vector_0.getY() * vector.getY()) / (vector_0.getX() * vector_0.getX() + vector_0.getY() * vector_0.getY());
		if (a < 0 || a > 1)
			return false;
		double b = (vector_1.getX() * vector.getX() + vector_1.getY() * vector.getY()) / (vector_1.getX() * vector_1.getX() + vector_1.getY() * vector_1.getY());
		if (b < 0 || b > 1)
			return false;
		return true;
	}

	@Override
	public void draw(Graphics g, GuiCoordinate2D c) {
		polyline.draw(g, c);
	}

	public Rectangle2D.Double getBounds() {
		return polyline.getBounds();
	}
}