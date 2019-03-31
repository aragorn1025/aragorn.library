package aragorn.util;

import java.awt.geom.Point2D;

/**
 * {@code MathVector2D} is a vector in mathematics.
 * 
 * @author Aragorn
 */
public class MathVector2D extends MathVector {

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
	public static MathVector2D add(MathVector2D vector_0, MathVector2D vector_1, MathVector2D... vectors) {
		if (vector_0 == null)
			throw new NullPointerException("The input vectors for add() must not be null.");
		if (vector_1 == null)
			throw new NullPointerException("The input vectors for add() must not be null.");
		for (MathVector2D vector : vectors) {
			if (vector == null) {
				throw new NullPointerException("The input vectors for add() must not be null.");
			}
		}
		double x = vector_0.getX() + vector_1.getX();
		double y = vector_0.getY() + vector_1.getY();
		for (MathVector2D vector : vectors) {
			x += vector.getX();
			y += vector.getY();
		}
		return new MathVector2D(x, y);
	}

	/**
	 * Add vector from the starting point.
	 * 
	 * @param starting_point
	 *     the starting point
	 * @param vector_0
	 *     the first vector to add
	 * @param vectors
	 *     (optional) the other vectors to add
	 * @return the end point
	 */
	public static Point2D.Double add(Point2D.Double starting_point, MathVector2D vector_0, MathVector2D... vectors) {
		MathVector2D vector = MathVector2D.add(vector_0, new MathVector2D(0, 0), vectors);
		return new Point2D.Double(starting_point.getX() + vector.getX(), starting_point.getY() + vector.getY());
	}

	/**
	 * Create the vector (0, 0) in mathematics.
	 */
	public MathVector2D() {
		this(0, 0);
	}

	/**
	 * Create a vector in mathematics.
	 * 
	 * @param x
	 *     the x value of the vector
	 * @param y
	 *     the y value of the vector
	 */
	public MathVector2D(double x, double y) {
		super(x, y);
	}

	public MathVector2D(MathVector2D math_vector_2d) {
		this(math_vector_2d.getX(), math_vector_2d.getY());
	}

	public MathVector2D(Point2D.Double starting_point, Point2D.Double end_point) {
		this(end_point.getX() - starting_point.getX(), end_point.getY() - starting_point.getY());
	}

	/**
	 * Add other vectors to this vector.
	 */
	public MathVector2D add(MathVector2D vector_0, MathVector2D... vectors) {
		return MathVector2D.add(this, vector_0, vectors);
	}

	@Override
	public MathVector2D getNegative() {
		return (MathVector2D) super.getNegative();
	}

	@Override
	public MathVector2D getScalarMultiply(double multiplier) {
		return (MathVector2D) super.getScalarMultiply(multiplier);
	}

	/**
	 * Get the x value of the vector.
	 * 
	 * @return the x value of the vector
	 */
	public double getX() {
		return getComponent(0);
	}

	/**
	 * Get the y value of the vector.
	 * 
	 * @return the y value of the vector
	 */
	public double getY() {
		return getComponent(1);
	}

	/**
	 * Set the vector by the value of (x, y)
	 * 
	 * @param x
	 *     the new x value of the vector
	 * @param y
	 *     the new y value of the vector
	 */
	public void set(double x, double y) {
		this.n[0] = x;
		this.n[1] = y;
	}

	/**
	 * Set the vector by the other vector.
	 * 
	 * @param vector
	 *     the new vector to be set
	 */
	public void set(MathVector2D vector) {
		if (vector == null)
			throw new NullPointerException();
		if (!vector.equals(this)) {
			this.set(vector.getX(), vector.getY());
		}
	}

	@Override
	public String toString() {
		return String.format("MathVector2D [x=%f, y=%f]", getX(), getY());
	}
}