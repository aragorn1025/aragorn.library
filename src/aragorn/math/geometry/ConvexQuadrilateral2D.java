package aragorn.math.geometry;

import java.awt.geom.Point2D;
import java.security.InvalidParameterException;
import aragorn.util.MathUtilities;
import aragorn.util.MathVector2D;

public class ConvexQuadrilateral2D extends Polygon2D {

	public ConvexQuadrilateral2D(Point2D.Double point_0, Point2D.Double point_1, Point2D.Double point_2, Point2D.Double point_3) {
		super(point_0, point_1, point_2, point_3);
		if (getPointNumber() < 4)
			throw new InvalidParameterException("The number of the point should be 4. Please check if there is the same points.");
		if (!isConvex())
			throw new InvalidParameterException("The quadrilateral should be convex.");
	}

	public ConvexQuadrilateral2D(Point2D.Double starting_point, MathVector2D vector_0, MathVector2D vector_1, MathVector2D vector_2) {
		this(starting_point, MathVector2D.add(starting_point, vector_0), MathVector2D.add(starting_point, vector_0, vector_1),
				MathVector2D.add(starting_point, vector_0, vector_1, vector_2));
	}

	public boolean isSurround(Point2D.Double point) {
		MathVector2D v_0 = new MathVector2D(point, getPoint(0));
		MathVector2D v_1 = new MathVector2D(point, getPoint(1));
		MathVector2D v_2 = new MathVector2D(point, getPoint(2));
		MathVector2D v_3 = new MathVector2D(point, getPoint(3));
		double sum_area = (MathUtilities.determinant_2_2(v_0, v_1) + MathUtilities.determinant_2_2(v_1, v_2) + MathUtilities.determinant_2_2(v_2, v_3)
				+ MathUtilities.determinant_2_2(v_3, v_0)) / 2.0;
		return getArea() == sum_area;
	}

	public double getArea() {
		MathVector2D v_0_1 = new MathVector2D(getPoint(0), getPoint(1));
		MathVector2D v_0_2 = new MathVector2D(getPoint(0), getPoint(2));
		MathVector2D v_0_3 = new MathVector2D(getPoint(0), getPoint(3));
		return (MathUtilities.determinant_2_2(v_0_1, v_0_2) + MathUtilities.determinant_2_2(v_0_2, v_0_3)) / 2.0;
	}

	public boolean isConvex() {
		return MathUtilities.isIntersect(getPoint(0), getPoint(1), new MathVector2D(getPoint(0), getPoint(2)), new MathVector2D(getPoint(1), getPoint(3)));
	}
}