package geometry;

import biuoop.DrawSurface;
import game.GameLevel;
import sprite.Sprite;


import java.awt.Color;

/**
 * The type Circle.
 */
public class Circle implements Sprite {
    private double x;
    private double y;
    private double radius;
    private Color color = null;
    private Boolean fill;

    /**
     * Instantiates a new Circle.
     *
     * @param center the center
     * @param radius the radius
     * @param fill the fill
     */
    public Circle(Point center, double radius, Boolean fill) {
        this.x = center.getX();
        this.y = center.getY();
        this.radius = radius;
        this.fill = fill;
    }

    /**
     * Sets color.
     *
     * @param c the c
     */
    public void setColor(Color c) {
        this.color = c;

    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawCircle((int) this.x, (int) this.y, (int) this.radius);
        if (this.fill) {
            d.fillCircle((int) this.x, (int) this.y, (int) this.radius);
        }
    }

    @Override
    public void timePassed() {
        return;
    }

    @Override
    public void addToGame(GameLevel g) {
        return;
    }
}
