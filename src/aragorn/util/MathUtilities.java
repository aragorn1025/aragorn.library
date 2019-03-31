package aragorn.util;

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