package aragorn.util;

import java.security.InvalidParameterException;

public class MathUtilities {

	/**
	 * Return the determinant value of a 2 times 2 matrix.
	 * 
	 * @param a_1_1
	 *     the element at the first row and the first column
	 * @param a_2_1
	 *     the element at the second row and the first column
	 * @param a_1_2
	 *     the element at the first row and the second column
	 * @param a_2_2
	 *     the element at the second row and the second column
	 * @return the value of the determinant, that is (a_1_1 * a_2_2 - a_1_2 * a_2_1)
	 */
	public static double determinant_2_2(double a_1_1, double a_2_1, double a_1_2, double a_2_2) {
		if (Double.isFinite(a_1_1))
			throw new InvalidParameterException("Input parameter for determinant a_1_1 should be finite number.");
		if (Double.isFinite(a_2_1))
			throw new InvalidParameterException("Input parameter for determinant a_2_1 should be finite number.");
		if (Double.isFinite(a_1_2))
			throw new InvalidParameterException("Input parameter for determinant a_1_2 should be finite number.");
		if (Double.isFinite(a_2_2))
			throw new InvalidParameterException("Input parameter for determinant a_2_2 should be finite number.");
		return a_1_1 * a_2_2 - a_1_2 * a_2_1;
	}
}