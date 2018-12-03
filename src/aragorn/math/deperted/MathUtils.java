package aragorn.math.deperted;

import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * {@code MathOperation} is a library within useful math function.
 * 
 * @author Aragorn
 * @see <a target="_blank" href="https://docs.oracle.com/javase/7/docs/api/java/lang/Math.html">Math</a>
 */
public class MathUtils {

	/**
	 * Return the determinant value of a 2 times 2 matrix.
	 * 
	 * @param a_1_1
	 *            the element at the first row and the first column
	 * @param a_2_1
	 *            the element at the second row and the first column
	 * @param a_1_2
	 *            the element at the first row and the second column
	 * @param a_2_2
	 *            the element at the second row and the second column
	 * @return the value of the determinant, that is (a_1_1 * a_2_2 - a_1_2 * a_2_1)
	 */
	public static double determinant(double a_1_1, double a_2_1, double a_1_2, double a_2_2) {
		return a_1_1 * a_2_2 - a_1_2 * a_2_1;
	}

	/**
	 * Find the largest value of the array form {@code fromIndex} to {@code toIndex}.
	 * 
	 * @param array
	 *            the reference of the array
	 * @param fromIndex
	 *            the starting index, {@code fromIndex} including
	 * @param toIndex
	 *            the ending index, {@code toIndex} excluding
	 * @return the largest value of the array form fromIndex to toIndex
	 */
	public static double getMax(double[] array, int fromIndex, int toIndex) {
		if (array == null)
			throw new NullPointerException("Input array should not be null.");
		if (array.length == 0 || toIndex > array.length || fromIndex >= toIndex)
			throw new IndexOutOfBoundsException("Index out of bounds.");
		double val = array[fromIndex];
		for (int i = fromIndex + 1; i < toIndex; i++) {
			val = Math.max(val, array[i]);
		}
		return val;
	}

	/**
	 * /** Find the largest value of the array form {@code fromIndex} to {@code toIndex}.
	 * 
	 * @param <N>
	 *            the class of array elements
	 * @param array
	 *            the reference of the array
	 * @param fromIndex
	 *            the starting index, {@code fromIndex} including
	 * @param toIndex
	 *            the ending index, {@code toIndex} excluding
	 * @return the largest value of the array form fromIndex to toIndex
	 */
	public static <N extends Number> double getMax(ArrayList<N> array, int fromIndex, int toIndex) {
		if (array == null)
			throw new NullPointerException("Input array should not be null.");
		if (array.size() == 0 || toIndex > array.size() || fromIndex >= toIndex)
			throw new IndexOutOfBoundsException("Index of of bounds.");
		double val = array.get(fromIndex).doubleValue();
		for (int i = fromIndex + 1; i < toIndex; i++) {
			val = Math.max(val, array.get(i).doubleValue());
		}
		return val;
	}

	/**
	 * Find the smallest value of the array form {@code fromIndex} to {@code toIndex}.
	 * 
	 * @param array
	 *            the reference of the array
	 * @param fromIndex
	 *            the starting index, {@code fromIndex} including
	 * @param toIndex
	 *            the ending index, {@code toIndex} excluding
	 * @return the smallest value of the array form fromIndex to toIndex
	 */
	public static double getMin(double[] array, int fromIndex, int toIndex) {
		if (array == null)
			throw new NullPointerException("Input array should not be null.");
		if (array.length == 0 || toIndex > array.length || fromIndex >= toIndex)
			throw new IndexOutOfBoundsException("Index of of bounds.");
		double val = array[fromIndex];
		for (int i = fromIndex + 1; i < toIndex; i++) {
			val = Math.min(val, array[i]);
		}
		return val;
	}

	/**
	 * Find the smallest value of the array form {@code fromIndex} to {@code toIndex}.
	 * 
	 * @param <N>
	 *            the class of array elements
	 * @param array
	 *            the reference of the array
	 * @param fromIndex
	 *            the starting index, {@code fromIndex} including
	 * @param toIndex
	 *            the ending index, {@code toIndex} excluding
	 * @return the smallest value of the array form fromIndex to toIndex
	 */
	public static <N extends Number> double getMin(ArrayList<N> array, int fromIndex, int toIndex) {
		if (array == null)
			throw new NullPointerException("Input array should not be null.");
		if (array.size() == 0 || toIndex > array.size() || fromIndex >= toIndex)
			throw new IndexOutOfBoundsException("Index of of bounds.");
		double val = array.get(fromIndex).doubleValue();
		for (int i = fromIndex + 1; i < toIndex; i++) {
			val = Math.min(val, array.get(i).doubleValue());
		}
		return val;
	}

	/**
	 * Calculate the Greatest Common Divisor (GCD) of two integers using Euclidean Algorithm.
	 * 
	 * @param a
	 *            non-negative integer input for GCD
	 * @param b
	 *            non-negative integer input for GCD
	 * @throws IllegalArgumentException
	 *             a, b should be non-negative integer.
	 * @return the greatest common divisor of a and b.
	 */
	public static int gcd(int a, int b) {
		if (a < 0)
			throw new IllegalArgumentException("The value of a should be non-negative.");
		if (b < 0)
			throw new IllegalArgumentException("The value of b should be non-negative.");
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}

	/**
	 * Calculate the Greatest Common Divisor (GCD) of integers using Euclidean Algorithm.
	 * 
	 * @param a
	 *            non-negative integer input for GCD
	 * @param b
	 *            non-negative integer input for GCD
	 * @param c
	 *            non-negative integer inputs for GCD
	 * @return the greatest common divisor of integers.
	 */
	public static int gcd(int a, int b, int... c) {
		int gcd = gcd(a, b);
		for (int i = 0; gcd != 1 && i < c.length; i++) {
			gcd = gcd(gcd, c[i]);
		}
		return gcd;
	}

	/**
	 * Tell p is a prime or not.
	 * 
	 * @param p
	 *            positive integer input
	 * @return true if p is prime, false if not
	 */
	public static boolean isPrime(int p) {
		if (p <= 0)
			throw new IllegalArgumentException("The value of p should be positive.");
		if (p <= 3)
			return true;
		if (p % 2 == 0)
			return false;
		for (int i = 3; i * i <= p; i += 2) {
			if (p % i == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Calculate the Least Common Multiple (LCM) of two integers using the formula: {@code a * b = GCD * LCM}.
	 * 
	 * @param a
	 *            non-negative integer input for LCM
	 * @param b
	 *            non-negative integer input for LCM
	 * @return the least common multiple of a and b.
	 */
	public static long lcm(int a, int b) throws ArithmeticException {
		return Math.multiplyExact((long) a, (long) b / gcd(a, b));
	}

	/**
	 * To find the minimal value of v that satisfied:<br>
	 * (a * v) % m = 1<br>
	 * then a * v + m * k = 1 where k is an integer<br>
	 * If gcd(a, m) != 1 then there is no such value for v.
	 * 
	 * @param a
	 *            input parameter
	 * @param m
	 *            input parameter
	 * @return The modular multiplicative inverse of integer a respect to the modulus m.
	 * @see <a target="_blank" href="https://en.wikipedia.org/wiki/Modular_multiplicative_inverse">Modular Multiplicative Inverse</a>
	 */
	public static int modInverse(int a, int m) {
		int[] x = new int[1];
		int[] y = new int[1];
		int gcd = xgcd(a, m, x, y);
		if (gcd != 1) {
			return -1;
		} else {
			return x[0];
		}
	}

	/**
	 * Calculate the value of (a ^ b) % m.
	 * 
	 * @param a
	 *            non-negative integer as the base
	 * @param b
	 *            non-negative integer as the power
	 * @param m
	 *            non-negative integer as the modulus
	 * @return the value (a ^ b) % m
	 */
	public static int modPower(int a, int b, int m) {
		long val = 1;
		long n = a % m;
		for (int i = b; i > 0; i >>= 1) {
			val = (i % 2 == 1) ? (val * n % m) : val;
			n = n * n % m;
		}
		return (int) val;
	}

	/**
	 * Tell if value is in the range.<br>
	 * <br>
	 * The range must in the format of (left notation)(left value)(comma)(right value)(right notation).<br>
	 * - left notation: must be {@code '('}(is smaller than) or {@code '['}(is smaller than or equals to)<br>
	 * - left value: must be {@code "-infinity"}, {@code "-inf"} or real number smaller than the right value<br>
	 * - comma: comma with space or without space <br>
	 * - right value: must be {@code "+infinity"}, {@code "+inf"} or real number larger than the left value<br>
	 * - right notation: must be {@code ')'}(is larger than) or {@code ']'}(is larger than or equals to)<br>
	 * 
	 * @param value
	 *            the value to check
	 * @param range
	 *            the range to check
	 * @return true if the value is in the range
	 */
	public static boolean valueInRange(double value, String range) {
		boolean is_leftt_equals, is_right_equals;
		String trimed_range = range.trim();
		switch (trimed_range.charAt(0)) {
			case '[':
				is_leftt_equals = true;
				break;
			case '(':
				is_leftt_equals = false;
				break;
			default:
				throw new InvalidParameterException("Range must start with '[' or '('.");
		}
		switch (trimed_range.charAt(trimed_range.length() - 1)) {
			case ']':
				is_right_equals = true;
				break;
			case ')':
				is_right_equals = false;
				break;
			default:
				throw new InvalidParameterException("Range must end with ']' or ')'.");
		}
		int comma_count = trimed_range.length() - trimed_range.replace(",", "").length();
		if (comma_count != 1)
			throw new InvalidParameterException("Range must contain with only two value.");
		int comma_index = trimed_range.indexOf(",");
		String leftt_string = trimed_range.substring(1, comma_index).trim();
		String right_string = trimed_range.substring(comma_index + 1, trimed_range.length() - 1).trim();
		double leftt_value, right_value;
		if (leftt_string.equals("-infinity") || leftt_string.equals("-inf")) {
			leftt_value = Double.NEGATIVE_INFINITY;
		} else {
			leftt_value = Double.parseDouble(leftt_string);
		}
		if (right_string.equals("+infinity") || right_string.equals("+inf")) {
			right_value = Double.POSITIVE_INFINITY;
		} else {
			right_value = Double.parseDouble(right_string);
		}
		if (leftt_value > right_value)
			throw new InvalidParameterException("The first value must be larger or equal then the second value.");
		if (is_leftt_equals && value == leftt_value)
			return true;
		if (is_right_equals && value == right_value)
			return true;
		if (leftt_value < value && value < right_value)
			return true;
		return false;
	}

	/**
	 * Calculate the Greatest Common Divisor (GCD) of two integers using extended Euclidean Algorithm.
	 * 
	 * @param a
	 *            non-negative integer input for GCD
	 * @param b
	 *            non-negative integer input for GCD
	 * @param x
	 *            an array which size is {@code 1} as {@code pointer}
	 * @param y
	 *            an array which size is {@code 1} as {@code pointer}
	 * @throws IllegalArgumentException
	 *             a, b should be non-negative integer.
	 * @throws InvalidParameterException
	 *             x, y are used as {@code pointer}, and their size must be {@code 1}.
	 * @return the greatest common divisor of a and b.
	 */
	public static int xgcd(int a, int b, int[] x, int[] y) {
		if (a < 0) {
			throw new IllegalArgumentException("The value of a should be non-negative.");
		}
		if (b < 0) {
			throw new IllegalArgumentException("The value of b should be non-negative.");
		}
		if (x.length != 1 || y.length != 1) {
			throw new InvalidParameterException("Array x, y are used as pointer, and their size must be 1.");
		}
		if (b == 0) {
			x[0] = 1;
			y[0] = 0;
			return a;
		} else {
			int gcd = xgcd(b, a % b, y, x);
			y[0] -= a / b * x[0];
			return gcd;
		}
	}
}