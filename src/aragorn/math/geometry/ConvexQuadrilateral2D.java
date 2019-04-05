package aragorn.math.geometry;

import java.awt.geom.Point2D;
import java.security.InvalidParameterException;
import aragorn.util.MathUtilities;
import aragorn.util.MathVector2D;

public class ConvexQuadrilateral2D extends Polygon2D {

	public ConvexQuadrilateral2D(Point2D.Double starting_point, MathVector2D vector_0, MathVector2D vector_1, MathVector2D vector_2) {
		this(starting_point, MathVector2D.add(starting_point, vector_0), MathVector2D.add(starting_point, vector_0, vector_1),
				MathVector2D.add(starting_point, vector_0, vector_1, vector_2));
	}

	public ConvexQuadrilateral2D(Point2D.Double point_0, Point2D.Double point_1, Point2D.Double point_2, Point2D.Double point_3) {
		super(point_0, point_1, point_2, point_3);
		if (getPointNumber() < 4)
			throw new InvalidParameterException("The number of the point should be 4. Please check if there is the same points.");
		if (!isConvex())
			throw new InvalidParameterException("The quadrilateral should be convex.");
	}

	public double getArea() {
		MathVector2D v_0_1 = new MathVector2D(getPoint(0), getPoint(1));
		MathVector2D v_0_2 = new MathVector2D(getPoint(0), getPoint(2));
		MathVector2D v_0_3 = new MathVector2D(getPoint(0), getPoint(3));
		return (Math.abs(MathUtilities.determinant_2_2(v_0_1, v_0_2)) + Math.abs(MathUtilities.determinant_2_2(v_0_2, v_0_3))) / 2.0;
	}

	public boolean isConvex() {
		return MathUtilities.isIntersect(getPoint(0), getPoint(1), new MathVector2D(getPoint(0), getPoint(2)), new MathVector2D(getPoint(1), getPoint(3)));
	}

	public boolean isSurround(Point2D.Double point) {
		MathVector2D[] v = new MathVector2D[4];
		for (int i = 0; i < v.length; i++) {
			v[i] = new MathVector2D(point, getPoint(i));
		}
		double sum_area = 0.0;
		for (int i = 0; i < v.length; i++) {
			sum_area += (Math.abs(MathUtilities.determinant_2_2(v[i], v[(i + 1) % v.length])) / 2.0);
		}
		return Math.abs(getArea() - sum_area) <= 0.000000001;
	}
}