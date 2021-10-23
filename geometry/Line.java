package geometry;

import biuoop.DrawSurface;
import game.GameLevel;
import sprite.Sprite;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * The type geometry.Line.
 */
public class Line implements Sprite {
    private Point start;
    private Point end;
    private Color color;

    /**
     * Instantiates a new geometry.Line.
     *
     * @param start the start
     * @param end the end
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Instantiates a new geometry.Line.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     */
    public Line(double x1, double y1, double x2, double y2) {
        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);
        this.start = p1;
        this.end = p2;
    }

    /**
     * Length double.
     *
     * @return the double
     */
// Return the length of the line
    public double length() {
        double length;
        length = this.start.distance(this.end);
        return length;
    }

    /**
     * Middle point.
     *
     * @return the point
     */
    public Point middle() {
        Point middlePoint;
        double x;
        double y;
        x = (start.getX() + end.getX()) / 2;
        y = (start.getY() + end.getY()) / 2;
        middlePoint = new Point(x, y);
        return middlePoint;

    }

    /**
     * Start point.
     *
     * @return the point
     */
    public Point start() {
        return this.start;
    }

    /**
     * End point.
     *
     * @return the point
     */
    public Point end() {
        return this.end;
    }

    /**
     * Equals boolean.
     *
     * @param other the other
     * @return the boolean
     */
    public boolean equals(Line other) {
        if (this.start.equals(other.start)) {
            if (this.end.equals(other.end)) {
                return true;
            }
        }
        if ((this.start.equals(other.end))) {
            if (this.end.equals(other.start)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Is intersecting boolean.
     *
     * @param other the other
     * @return the boolean
     */
// Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        if (this.intersectionWith(other) == null) {
            return false;
        }
        return true;
    }

    /**
     * Find m double.
     *
     * @return the double
     */
    public Double findM() {
        if (this.start.getX() == this.end.getX()) {
            return null;
        }
        return (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
    }

    /**
     * Find b double.
     *
     * @return the double
     */
    public Double findB() {
        if (findM() == null) {
            return null;
        }
        return this.start.getY() - (findM() * this.start.getX());
    }

    /**
     * Normal lines point.
     *
     * @param m1 the m 1
     * @param b1 the b 1
     * @param m2 the m 2
     * @param b2 the b 2
     * @return the point
     */
    public Point normalLines(Double m1, Double b1, Double m2, Double b2) {
        double x, y;
        x = (b2.doubleValue() - b1.doubleValue()) / (m1.doubleValue() - m2.doubleValue());
        y = m2.doubleValue() * x + b2.doubleValue();
        return new Point(x, y);
    }

    /**
     * No incline point.
     *
     * @param x the x
     * @param m the m
     * @param b the b
     * @return the point
     */
    public Point noIncline(double x, Double m, Double b) {
        double y = m.doubleValue() * x + b.doubleValue();
        return new Point(x, y);

    }

    /**
     * Is in range boolean.
     *
     * @param p the p
     * @return the boolean
     */
    public boolean isInRange(Point p) {
        if ((isYInRange(p)) && (isXInRange(p))) {
            return true;
        }
        return false;
    }

    /**
     * Is x in range boolean.
     *
     * @param p the p
     * @return the boolean
     */
    public boolean isXInRange(Point p) {
        if ((this.start.getX() <= (int) p.getX() + 1) && ((int) p.getX() <= this.end.getX())) {
            return true;
        }
        if ((this.start.getX() >= (int) p.getX()) && ((int) p.getX() + 1 >= this.end.getX())) {
            return true;
        }
        return false;
    }

    /**
     * Is y in range boolean.
     *
     * @param p the p
     * @return the boolean
     */
    public boolean isYInRange(Point p) {
        if ((this.start.getY() <= (int) p.getY() + 1) && ((int) p.getY() <= this.end.getY())) {
            return true;
        }
        if (((this.start.getY() >= (int) p.getY())) && ((int) p.getY() + 1 >= this.end.getY())) {
            return true;
        }
        return false;

    }

    /**
     * Intersection with point.
     *
     * @param other the other
     * @return the point
     */
    public Point intersectionWith(Line other) {
        Point newIntersectionPoint = null;
        Double m1 = findM();
        Double b1 = findB();
        Double m2 = other.findM();
        Double b2 = other.findB();
        //normal lines
        if ((m1 != null) && (b1 != null) && (m2 != null) && (b2 != null) && (m1 != m2)) {
            newIntersectionPoint = normalLines(m1, b1, m2, b2);
        }
        //one line hes no incline
        if ((m1 == null) && (m2 != null)) {
            newIntersectionPoint = noIncline(this.start.getX(), m2, b2);
        }
        if ((m1 != null) && (m2 == null)) {
            newIntersectionPoint = noIncline(other.start.getX(), m1, b1);
        }
        //parallel lines

        if (((m1 != null) && (m2 != null) && (m1.equals(m2))) || ((m1 == null) && (m2 == null))) {
            if (((b1 != null) && (b2 != null) && (!b1.equals(b2)))) {
                return null;
            }
            if (isInRange(other.start)) {
                newIntersectionPoint = other.start;
            }
            if (isInRange(other.end)) {
                newIntersectionPoint = other.end;
            }
        }


        if (newIntersectionPoint == null) {
            return null;
        }
        if ((isXInRange(newIntersectionPoint)) && (isYInRange(newIntersectionPoint))) {
            if ((other.isXInRange(newIntersectionPoint)) && (other.isYInRange(newIntersectionPoint))) {
                return newIntersectionPoint;
            }
        }
        return null;
    }

    /**
     * @return returns a new line print
     */
    public String toString() {
        return "start line: " + this.start + " end line: " + this.end;
    }

    /**
     * Closest intersection to start of line point.
     *
     * @param rect the rect
     * @return the point
     */
// If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> pointList = rect.intersectionPoints(this);
        if (pointList.isEmpty()) {
            return null;
        }
        Point closestIntersectionPoint = ((LinkedList<Point>) pointList).removeFirst();
        while (!pointList.isEmpty()) {
            if (this.start.distance(((LinkedList<Point>) pointList).getFirst())
                    < this.start.distance(closestIntersectionPoint)) {
                closestIntersectionPoint = ((LinkedList<Point>) pointList).getFirst();
            }
            ((LinkedList<Point>) pointList).removeFirst();
        }
        return closestIntersectionPoint;

    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawLine((int) this.start.getX(), (int) this.start.getY(), (int) this.end.getX(), (int) this.end.getY());

    }

    @Override
    public void timePassed() {
        return;

    }

    @Override
    public void addToGame(GameLevel g) {
        return;
    }

    /**
     * Sets color.
     *
     * @param c the c
     */
    public void setColor(Color c) {
        this.color = c;
    }
}
