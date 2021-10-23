package geometry;

import java.util.LinkedList;
import java.util.List;

/**
 * The type geometry.Rectangle.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Instantiates a new geometry.Rectangle.
     *
     * @param upperLeft the upper left
     * @param width the width
     * @param height the height
     */
// Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.height = height;
        this.width = width;
        this.upperLeft = upperLeft;
    }

    /**
     * Sets width.
     *
     * @param newWidth the width.
     */
    public void setWidth(double newWidth) {
        this.width = newWidth;
    }

    /**
     * Gets upper line.
     *
     * @return the upper line
     */
    public Line getUpperLine() {
        Point upperRight = new Point(getUpperLeft().getX() + getWidth(), getUpperLeft().getY());
        return new Line(getUpperLeft(), upperRight);
    }

    /**
     * Gets bottom line.
     *
     * @return the bottom line
     */
    public Line getBottomLine() {
        Point downRight = new Point(getUpperLeft().getX() + getWidth(), getUpperLeft().getY() + getHeight());
        Point downLeft = new Point(getUpperLeft().getX(), getUpperLeft().getY() + getHeight());
        return new Line(downLeft, downRight);

    }

    /**
     * Gets right line.
     *
     * @return the right line
     */
    public Line getRightLine() {
        Point downRight = new Point(getUpperLeft().getX() + getWidth(), getUpperLeft().getY() + getHeight());
        Point upperRight = new Point(getUpperLeft().getX() + getWidth(), getUpperLeft().getY());
        return new Line(upperRight, downRight);
    }

    /**
     * Gets left line.
     *
     * @return the left line
     */
    public Line getLeftLine() {
        Point downLeft = new Point(getUpperLeft().getX(), getUpperLeft().getY() + getHeight());
        return new Line(getUpperLeft(), downLeft);
    }

    /**
     * Intersection points java . util . list.
     *
     * @param line the line
     * @return the java . util . list
     */
// Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> pointList = new LinkedList<>();
        Line up = getUpperLine();
        Line down = getBottomLine();
        Line right = getRightLine();
        Line left = getLeftLine();
        if (line.isIntersecting(up)) {
            pointList.add(line.intersectionWith(up));
        }
        if (line.isIntersecting(down)) {
            pointList.add(line.intersectionWith(down));
        }
        if (line.isIntersecting(right)) {
            pointList.add(line.intersectionWith(right));
        }
        if (line.isIntersecting(left)) {
            pointList.add(line.intersectionWith(left));
        }
        return pointList;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
// Return the width and height of the rectangle
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets upper left.
     *
     * @return the upper left
     */
// Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * @return Returns a new rectangle print
     */
    public String toString() {
        return "upper left: " + getUpperLeft() + " width: " + getWidth() + " height: " + getHeight();
    }

}