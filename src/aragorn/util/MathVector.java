package aragorn.util;

import java.util.Arrays;

/**
 * {@code MathVector} is a vector in mathematics.
 * 
 * @author Aragorn
 */
public class MathVector implements Cloneable {

	/**
	 * Add vectors.
	 * 
	 * @param vector_0
	 *     the first vector to add
	 * @param vector_1
	 *     the second vector to add
	 * @param vectors
	 *     (optional) the other vectors to add
	 */
	public static MathVector add(MathVector vector_0, MathVector vector_1, MathVector... vectors) {
		if (vector_0 == null)
			throw new NullPointerException("The input vectors for add() must not be null.");
		if (vector_1 == null)
			throw new NullPointerException("The input vectors for add() must not be null.");
		if (vector_0.getDimension() != vector_1.getDimension())
			throw new IllegalArgumentException("The dimension of the vectors for add() should be the same.");
		for (MathVector vector : vectors) {
			if (vector == null) {
				throw new NullPointerException("The input vectors for add() must not be null.");
			}
			if (vector_0.getDimension() != vector.getDimension()) {
				throw new IllegalArgumentException("The dimension of the vectors for add() should be the same.");
			}
		}
		double[] val = new double[vector_0.getDimension()];
		for (int i = 0; i < val.length; i++) {
			val[i] = vector_0.getComponent(i) + vector_1.getComponent(i);
			for (int j = 0; j < vectors.length; j++) {
				val[i] += vectors[j].getComponent(i);
			}
		}
		return new MathVector(val);
	}

	public static double getInnerProduct(MathVector vector_0, MathVector vector_1) {
		if (vector_0.getDimension() != vector_1.getDimension())
			throw new IllegalArgumentException("The dimension of the vectors for getInnerProduct() should be the same.");
		double val = 0;
		for (int i = 0; i < vector_0.getDimension(); i++) {
			val += (vector_0.getComponent(i) * vector_1.getComponent(i));
		}
		return val;
	}

	/** The value of the components. */
	protected double[] n;

	public MathVector() {
		this(1);
	}

	/** Create a vector in mathematics */
	public MathVector(double[] n) {
		if (n == null)
			throw new NullPointerException("The input for the math vector should not be null.");
		if (n.length == 0)
			throw new IllegalArgumentException("The input for the math vector should not be nothing");
		for (int i = 0; i < n.length; i++) {
			this.n[i] = n[i];
		}
	}

	public MathVector(int dimension) {
		if (dimension <= 0)
			throw new IllegalArgumentException("The dimension should be positive integer.");
		this.n = new double[dimension];
		Arrays.fill(n, 0.0);
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
		return Math.pow(MathVector.getInnerProduct(this, this), 0.5);
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

	/**
	 * Set the vector by the array.
	 * 
	 * @param n
	 *     the array to be set
	 */
	public void set(double[] n) {
		if (n == null)
			throw new NullPointerException("The array to be set should not be null.");
		if (n.length <= 0)
			throw new IllegalArgumentException("The array to be set should not be nothing.");
		if (n.equals(this.n))
			return;
		this.n = n.clone();
	}

	/**
	 * Set the vector by the other vector.
	 * 
	 * @param vector
	 *     the new vector to be set
	 */
	public void set(MathVector vector) {
		if (vector == null)
			throw new NullPointerException("The vector to be set should not be null.");
		if (vector.equals(this))
			return;
		this.n = vector.n.clone();
	}

	public void setComponent(int index, double value) {
		n[index] = value;
	}

	@Override
	public String toString() {
		return String.format("%s %s", getClass().getSimpleName(), Arrays.toString(n));
	}
}