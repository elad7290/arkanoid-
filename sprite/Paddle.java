package sprite;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;


/**
 * The type sprite.Paddle.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Block paddle;
    private int paddleSpeed;
    /**
     * The sprite.Paddle color.
     */
    static final Color PADDLE_COLOR = Color.red;
    /**
     * The sprite.Paddle movment.
     */
    /**
     * The Width.
     */
    static final int WIDTH = 800;
    /**
     * The Fifth paddle.
     */
    static final double FIFTH_PADDLE = 0.2;
    /**
     * The Border width.
     */
    static final int BORDER_WIDTH = 20;

    /**
     * Instantiates a new sprite.Paddle.
     *
     * @param player the player
     * @param  speed the speed
     */
    public Paddle(Block player, int speed) {
        this.paddle = player;
        this.paddleSpeed = speed;

    }

    /**
     * Sets keyboard.
     *
     * @param gui the gui
     */
    public void setKeyboard(GUI gui) {
        this.keyboard = gui.getKeyboardSensor();
    }

    /**
     * Move left.
     */
    public void moveLeft() {
        if (this.paddle.getCollisionRectangle().getUpperLeft().getX() > BORDER_WIDTH) {
            double newXPoint = this.paddle.getCollisionRectangle().getUpperLeft().getX() - paddleSpeed;
            if (newXPoint < 0 + BORDER_WIDTH) {
                newXPoint = 0 + BORDER_WIDTH;
            }
            double y = this.paddle.getCollisionRectangle().getUpperLeft().getY();
            double width = this.paddle.getCollisionRectangle().getWidth();
            double height = this.paddle.getCollisionRectangle().getHeight();
            Point newUpperPoint = new Point(newXPoint, y);
            Rectangle rectangle = new Rectangle(newUpperPoint, width, height);
            this.paddle = new Block(rectangle);
        }
    }


    /**
     * Move right.
     */
    public void moveRight() {
        if (this.paddle.getCollisionRectangle().getUpperLeft().getX()
                + this.paddle.getCollisionRectangle().getWidth() < WIDTH - BORDER_WIDTH) {
            double newXPoint = this.paddle.getCollisionRectangle().getUpperLeft().getX() + paddleSpeed;
            if (newXPoint + paddle.getCollisionRectangle().getWidth() > WIDTH - BORDER_WIDTH) {
                newXPoint = WIDTH - BORDER_WIDTH - paddle.getCollisionRectangle().getWidth();
            }
            double y = this.paddle.getCollisionRectangle().getUpperLeft().getY();
            double width = this.paddle.getCollisionRectangle().getWidth();
            double height = this.paddle.getCollisionRectangle().getHeight();
            Point newUpperPoint = new Point(newXPoint, y);
            Rectangle rectangle = new Rectangle(newUpperPoint, width, height);
            this.paddle = new Block(rectangle);
        }
    }

    /**
     * Checks whether the right key is pressed or left.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
    }

    /**
     * @param d Draws the paddle.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(PADDLE_COLOR);
        double x = this.paddle.getCollisionRectangle().getUpperLeft().getX();
        double y = this.paddle.getCollisionRectangle().getUpperLeft().getY();
        double width = this.paddle.getCollisionRectangle().getWidth();
        double height = this.paddle.getCollisionRectangle().getHeight();
        d.fillRectangle((int) x, (int) y, (int) width, (int) height);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return paddle.getCollisionRectangle();
    }

    /**
     * Gets fifth paddle.
     *
     * @return the fifth paddle
     */
    public double getFifthPaddle() {
        return FIFTH_PADDLE * this.paddle.getCollisionRectangle().getWidth();
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double x = getCollisionRectangle().getUpperLeft().getX() + getFifthPaddle();
        double y = getCollisionRectangle().getUpperLeft().getY();
        //line 1
        Line section1 = new Line(getCollisionRectangle().getUpperLeft(), new Point(x, y));
        //line 2
        x = section1.end().getX();
        Line section2 = new Line(section1.end(), new Point(x + getFifthPaddle(), y));
        //line 3
        x = section2.end().getX();
        Line section3 = new Line(section2.end(), new Point(x + getFifthPaddle(), y));
        //line 4
        x = section3.end().getX();
        Line section4 = new Line(section2.end(), new Point(x + getFifthPaddle(), y));
        //line 5
        x = section4.end().getX();
        Line section5 = new Line(section2.end(), new Point(x + getFifthPaddle(), y));
        Velocity newV = currentVelocity;
        if (getCollisionRectangle().getUpperLine().isInRange(collisionPoint)) {
            if (section1.isInRange(collisionPoint)) {
                newV = Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
            }
            if (section2.isInRange(collisionPoint)) {
                newV = Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
            }
            if (section3.isInRange(collisionPoint)) {
                newV = Velocity.fromAngleAndSpeed(360, currentVelocity.getSpeed());
            }
            if (section4.isInRange(collisionPoint)) {
                newV = Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
            }
            if (section5.isInRange(collisionPoint)) {
                newV = Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
            }
        }
        return new Velocity(newV.getDx(), -newV.getDy());
    }

    /**
     * Add this paddle to the game.
     *
     * @param g the game.Game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}