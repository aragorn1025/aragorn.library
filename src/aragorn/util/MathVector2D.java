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
		MathVector val = MathVector.add(vector_0, vector_1, vectors);
		if (val.getDimension() != 2)
			throw new InternalError("Unknown error.");
		return new MathVector2D(val.getComponent(0), val.getComponent(1));
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

	public static MathVector2D getScalarMultiply(MathVector2D vector, double multiplier) {
		return vector.getScalarMultiply(multiplier);
	}

	/**
	 * Create the vector (0, 0) in mathematics.
	 */
	public MathVector2D() {
		this(0, 0);
	}

	/**
	 * Create a unit vector which length is 1 in mathematics by phase.
	 * 
	 * @param phase
	 *     the phase of the vector
	 */
	public MathVector2D(double phase) {
		this(Math.cos(phase), Math.sin(phase));
	}

	/**
	 * Create a vector in mathematics by x, y.
	 * 
	 * @param x
	 *     the x value of the vector
	 * @param y
	 *     the y value of the vector
	 */
	public MathVector2D(double x, double y) {
		super(2);
		setComponent(0, x);
		setComponent(1, y);
	}

	public MathVector2D(MathVector2D math_vector_2d) {
		this(math_vector_2d.getX(), math_vector_2d.getY());
	}

	public MathVector2D(Point2D.Double starting_point, Point2D.Double end_point) {
		this(end_point.getX() - starting_point.getX(), end_point.getY() - starting_point.getY());
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
		setComponent(0, x);
		setComponent(1, y);
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
		if (vector.equals(this))
			return;
		this.set(vector.getX(), vector.getY());
	}

	@Override
	public String toString() {
		return String.format("%s [x=%f, y=%f]", getClass().getSimpleName(), getX(), getY());
	}
}