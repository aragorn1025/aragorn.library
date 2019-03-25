package aragorn.util;

import java.awt.geom.Point2D;

/**
 * {@code MathVector2D} is a vector in mathematics.
 * 
 * @author Aragorn
 */
public class MathVector2D implements Cloneable {

	/**
	 * Add vectors.
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
	 * @param vector
	 *     the vector to add
	 * @return the end point
	 */
	public static Point2D.Double add(Point2D.Double starting_point, MathVector2D vector) {
		return new Point2D.Double(starting_point.getX() + vector.getX(), starting_point.getY() + vector.getY());
	}

	/**
	 * The end point of the vector assume that the start point is the origin (0, 0).
	 */
	private Point2D.Double end_point;

	/**
	 * Create a vector in mathematics
	 * 
	 * @param x
	 *     the x value of the vector
	 * @param y
	 *     the y value of the vector
	 */
	public MathVector2D(double x, double y) {
		this.end_point = new Point2D.Double(x, y);
	}

	public MathVector2D(Point2D starting_point, Point2D end_point) {
		this(end_point.getX() - starting_point.getX(), end_point.getY() - starting_point.getY());
	}

	public MathVector2D(MathVector2D math_vector_2d) {
		this(math_vector_2d.getX(), math_vector_2d.getY());
	}

	/**
	 * Add other vectors to this vector.
	 */
	public MathVector2D add(MathVector2D vector_0, MathVector2D... vectors) {
		return MathVector2D.add(this, vector_0, vectors);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		MathVector2D clone_object = (MathVector2D) super.clone();
		clone_object.end_point = (Point2D.Double) end_point.clone();
		return clone_object;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MathVector2D other = (MathVector2D) obj;
		if (end_point == null) {
			if (other.end_point != null) {
				return false;
			}
		} else if (!end_point.equals(other.end_point)) {
			return false;
		}
		return true;
	}

	/**
	 * Get Euclidean distance of the vector.
	 * 
	 * @return the length of the vector
	 */
	public double getLength() {
		return end_point.distance(new Point2D.Double(0, 0));
	}

	/**
	 * Get the x value of the vector.
	 * 
	 * @return the x value of the vector
	 */
	public double getX() {
		return end_point.getX();
	}

	/**
	 * Get the y value of the vector.
	 * 
	 * @return the y value of the vector
	 */
	public double getY() {
		return end_point.getY();
	}

	@Override
	public int hashCode() {
		return 31 + ((end_point == null) ? 0 : end_point.hashCode());
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
		this.end_point.setLocation(x, y);
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