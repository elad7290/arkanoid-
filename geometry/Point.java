package geometry;

/**
 * The type geometry.Point.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Instantiates a new geometry.Point.
     *
     * @param x the x
     * @param y the y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return this.y;
    }

    /**
     * Equals boolean.
     *
     * @param other the other
     * @return the boolean
     */
    public boolean equals(Point other) {
        if ((this.y == other.getY()) && (this.x == other.getX())) {
            return true;
        }
        return false;
    }

    /**
     * Distance double.
     *
     * @param other the other
     * @return the double
     */
    public double distance(Point other) {
        double distance;
        distance = Math.sqrt(Math.pow(this.x - other.getX(), 2) + Math.pow(this.y - other.getY(), 2));
        return distance;
    }

    /**
     * Defines the string output.
     *
     * @return the string output.
     */
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
