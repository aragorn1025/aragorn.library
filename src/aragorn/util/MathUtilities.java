package aragorn.util;

import java.awt.geom.Point2D;
import java.security.InvalidParameterException;

public class MathUtilities {

	/**
	 * Return the determinant value of a 2 times 2 matrix.
	 * 
	 * @param a_0_0
	 *     the element at the first row and the first column
	 * @param a_1_0
	 *     the element at the second row and the first column
	 * @param a_0_1
	 *     the element at the first row and the second column
	 * @param a_1_1
	 *     the element at the second row and the second column
	 * @return the value of the determinant, that is (a_0_0 * a_1_1 - a_0_1 * a_1_0)
	 */
	public static double determinant_2_2(double a_0_0, double a_1_0, double a_0_1, double a_1_1) {
		if (!Double.isFinite(a_0_0))
			throw new InvalidParameterException("Input parameter for determinant a_1_1 should be a finite number.");
		if (!Double.isFinite(a_1_0))
			throw new InvalidParameterException("Input parameter for determinant a_2_1 should be a finite number.");
		if (!Double.isFinite(a_0_1))
			throw new InvalidParameterException("Input parameter for determinant a_1_2 should be a finite number.");
		if (!Double.isFinite(a_1_1))
			throw new InvalidParameterException("Input parameter for determinant a_2_2 should be a finite number.");
		return a_0_0 * a_1_1 - a_0_1 * a_1_0;
	}

	public static double determinant_2_2(MathVector2D vector_0, MathVector2D vector_1) {
		return MathUtilities.determinant_2_2(vector_0.getX(), vector_0.getY(), vector_1.getX(), vector_1.getY());
	}

	public static boolean isIntersect(Point2D.Double point_0, Point2D.Double point_1, MathVector2D vector_0, MathVector2D vector_1) {
		double determinant = MathUtilities.determinant_2_2(vector_0, vector_1);
		MathVector2D vector = new MathVector2D(point_0, point_1);
		if (determinant == 0.0) {
			if (MathUtilities.determinant_2_2(vector, vector_0) == 0.0) {
				double a = vector_0.getX() / vector.getX();
				double b = vector_1.getX() / vector.getX();
				if (a >= 1)
					return true;
				if (b <= -1)
					return true;
				if (a - b >= 1)
					return true;
			}
		} else {
			double s = determinant_2_2(vector, vector_0) / determinant;
			double t = determinant_2_2(vector, vector_1) / determinant;
			if (s >= 0 && s <= 1 && t >= 0 && t <= 1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Find the largest value of the parameters.
	 * 
	 * @param a0
	 *     the first parameter
	 * @param a1
	 *     the first parameter
	 * @param ax
	 *     the other parameters
	 * @return the largest parameter among the input parameters
	 */
	public static double max(double a0, double a1, double... ax) {
		double val = Math.max(a0, a1);
		for (int i = 0; i < ax.length; i++) {
			val = Math.max(val, ax[i]);
		}
		return val;
	}

	/**
	 * Find the smallest value of the parameters.
	 * 
	 * @param a0
	 *     the first parameter
	 * @param a1
	 *     the first parameter
	 * @param ax
	 *     the other parameters
	 * @return the smallest parameter among the input parameters
	 */
	public static double min(double a0, double a1, double... ax) {
		double val = Math.min(a0, a1);
		for (int i = 0; i < ax.length; i++) {
			val = Math.min(val, ax[i]);
		}
		return val;
	}
}