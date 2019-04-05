package aragorn.util;

import java.util.Arrays;

/**
 * {@code MathVector} is a vector in mathematics.
 * 
 * @author Aragorn
 */
public class MathVector implements Cloneable {

	/** The value of the components. */
	protected double[] n;

	/**
	 * Create a vector in mathematics
	 * 
	 * @param x
	 *     the x value of the vector
	 * @param y
	 *     the y value of the vector
	 */
	public MathVector(double n0, double... nx) {
		n = new double[1 + nx.length];
		n[0] = n0;
		for (int i = 0; i < nx.length; i++) {
			n[i + 1] = nx[i];
		}
	}

	@Override
	public Object clone() {
		MathVector val;
		try {
			val = (MathVector) super.clone();
			val.n = this.n.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
		return val;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		return Arrays.equals(n, ((MathVector) obj).n);
	}

	/**
	 * Get the component value of the vector by the index
	 * 
	 * @param index
	 *     the target index
	 * @return the component value by the index
	 */
	public double getComponent(int index) {
		return n[index];
	}

	/**
	 * Get the dimension of the vector.
	 * 
	 * @return the dimension of the vector
	 */
	public int getDimension() {
		return n.length;
	}

	/**
	 * Get Euclidean distance of the vector.
	 * 
	 * @return the length of the vector
	 */
	public double getLength() {
		double val = 0;
		for (int i = 0; i < n.length; i++) {
			val += (n[i] * n[i]);
		}
		return Math.pow(val, 0.5);
	}

	/**
	 * Get the negative of the vector.
	 * 
	 * @return the negative one of the vector
	 */
	public MathVector getNegative() {
		return getScalarMultiply(-1);
	}

	/**
	 * Get the scalar multiplied vector.
	 * 
	 * @return the scalar multiplied vector
	 */
	public MathVector getScalarMultiply(double multiplier) {
		MathVector val = (MathVector) this.clone();
		for (int i = 0; i < val.n.length; i++) {
			val.n[i] *= multiplier;
		}
		return val;
	}

	@Override
	public String toString() {
		return String.format("%s %s", getClass().getSimpleName(), Arrays.toString(n));
	}
}