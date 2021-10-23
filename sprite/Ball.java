package sprite;

import game.GameEnvironment;
import game.GameLevel;
import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;


/**
 * The type sprite.Ball.
 */
public class Ball implements Sprite {
    /**
     * The constant DEFAULT_DX_VELOCITY.
     */
// constructor
    static final double DEFAULT_DX_VELOCITY = -5;
    /**
     * The Default dy velocity.
     */
    static final double DEFAULT_DY_VELOCITY = -2;
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity = new Velocity(DEFAULT_DX_VELOCITY, DEFAULT_DY_VELOCITY);
    private GameEnvironment gameObstacles;


    /**
     * Instantiates a new sprite.Ball.
     *
     * @param center the center
     * @param radius the radius
     * @param color the color
     */
    public Ball(Point center, int radius, java.awt.Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;

    }

    /**
     * Instantiates a new sprite.Ball.
     *
     * @param x the x
     * @param y the y
     * @param radius the radius
     * @param color the color
     */
    public Ball(double x, double y, int radius, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = radius;
        this.color = color;
    }

    /**
     * Instantiates a new sprite.Ball.
     *
     * @param center the center
     * @param radius the radius
     * @param color the color
     * @param gameObstacles the game obstacles
     */
    public Ball(Point center, int radius, java.awt.Color color, GameEnvironment gameObstacles) {
        this(center, radius, color);
        this.gameObstacles = gameObstacles;
    }

    /**
     * Gets game obstacles.
     *
     * @return the game obstacles
     */
    public GameEnvironment getGameObstacles() {
        return this.gameObstacles;
    }

    /**
     * Move one step.
     */
    public void moveOneStep() {
        Point nextCenter = new Point(getX() + this.velocity.getDx(), getY() + this.velocity.getDy());
        Line trajectory = new Line(getX(), getY(), getX() + this.velocity.getDx(),
                getY() + this.velocity.getDy());
        if (this.gameObstacles.getClosestCollision(trajectory) == null) {
            this.center = this.velocity.applyToPoint(this.center);
        } else {
            CollisionInfo c = this.gameObstacles.getClosestCollision(trajectory);
            this.velocity = c.collisionObject().hit(this, c.collisionPoint(), getVelocity());
            this.center = this.velocity.applyToPoint(this.center);


        }
    }

    /**
     * Gets x.
     *
     * @return the x
     */
// accessors
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draw on.
     *
     * @param surface the surface
     */
// draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Remove from game.
     *
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }

    /**
     * Sets velocity.
     *
     * @param v the v
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Gets velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }


    /**
     * defines the border.
     *
     * @param left the left
     * @param down the down
     * @param right the right
     * @param up the up
     */
    private void border(int left, int down, int right, int up) {
        double currentX = this.center.getX();
        double currentY = this.center.getY();
        double newDx = this.velocity.getDx();
        double newDy = this.velocity.getDy();
        double upBall = this.center.getY() + this.radius + this.velocity.getDy();
        double downBall = this.center.getY() - this.radius + this.velocity.getDy();
        double rightBall = this.center.getX() + this.radius + this.velocity.getDx();
        double leftBall = this.center.getX() - this.radius + this.velocity.getDx();
        //Checks whether the ball is within proper upper and lower bounds
        if (upBall >= up) {
            newDy = -newDy;
            currentY = up - this.radius - newDy;
        }
        if (downBall <= down) {
            newDy = -newDy;
            currentY = down + this.radius - newDy;
        }
        //Checks whether the ball is within the right and left boundaries
        if (rightBall >= right) {
            newDx = -newDx;
            currentX = right - this.radius - newDx;

        }
        if (leftBall <= left) {
            newDx = -newDx;
            currentX = left + this.radius - newDx;
        }
        this.velocity = new Velocity(newDx, newDy);
        this.center = new Point(currentX, currentY);

    }

    /**
     * Border.
     *
     * @param height the height
     * @param width the width
     */
    public void border(int height, int width) {
        double currentX = this.center.getX();
        double currentY = this.center.getY();
        double newDx = this.velocity.getDx();
        double newDy = this.velocity.getDy();
        double up = this.center.getY() + this.radius + this.velocity.getDy();
        double down = this.center.getY() - this.radius + this.velocity.getDy();
        double right = this.center.getX() + this.radius + this.velocity.getDx();
        double left = this.center.getX() - this.radius + this.velocity.getDx();
        //Checks whether the ball is within proper upper and lower bounds
        if (up > height) {
            newDy = -newDy;
            currentY = height - this.radius - newDy;
        }
        if (down < 0) {
            newDy = -newDy;
            currentY = 0 + this.radius - newDy;
        }
        //Checks whether the ball is within the right and left boundaries
        if (right > width) {
            newDx = -newDx;
            currentX = width - this.radius - newDx;

        }
        if (left < 0) {
            newDx = -newDx;
            currentX = 0 + this.radius - newDx;
        }
        this.velocity = new Velocity(newDx, newDy);
        this.center = new Point(currentX, currentY);

    }

    /**
     * Sets game obstacles.
     *
     * @param g the game obstacles
     */
    public void setGameObstacles(GameEnvironment g) {
        this.gameObstacles = g;
    }

}





