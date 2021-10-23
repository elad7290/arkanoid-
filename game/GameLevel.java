package game;

import animations.AnimationRunner;
import animations.CountdownAnimation;
import animations.KeyPressStoppableAnimation;
import animations.PauseScreen;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;
import levels.LevelInformation;
import sprite.Ball;
import listeners.BallRemover;
import sprite.Block;
import listeners.BlockRemover;
import sprite.Collidable;
import sprite.Counter;
import listeners.HitListener;
import sprite.Paddle;
import sprite.ScoreIndicator;
import listeners.ScoreTrackingListener;
import sprite.Sprite;
import sprite.SpriteCollection;
import animations.Animation;

import java.awt.Color;

/**
 * The type game.Game.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blocksCounter;
    private Counter ballCounter;
    private Counter playerScore;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation myLevels;
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboard;
    private GUI gui;

    /**
     * The constant WIDTH.
     */
    static final int WIDTH = 800;
    /**
     * The Height.
     */
    static final int HEIGHT = 600;
    /**
     * The Center.
     */
    static final Point CENTER = new Point(410, 550);
    /**
     * The sprite.Ball color.
     */
    static final Color BALL_COLOR = Color.white;
    /**
     * The Radius.
     */
    static final int RADIUS = 5;
    /**
     * The Border width.
     */
    static final int BORDER_WIDTH = 20;

    /**
     * The sprite.Paddle height.
     */
    static final int PADDLE_HEIGHT = 10;


    /**
     * Instantiates a new game.Game.
     *
     * @param levelInformation the level information
     * @param keyboardSensor the keyboard sensor
     * @param ar the ar
     * @param gui the gui
     * @param counter the counter
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboardSensor,
                     AnimationRunner ar, GUI gui, Counter counter) {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        this.blocksCounter = new Counter();
        this.ballCounter = new Counter();
        this.playerScore = counter;
        this.running = true;
        this.runner = new AnimationRunner(gui);
        this.keyboard = keyboardSensor;
        this.myLevels = levelInformation;
        this.animationRunner = ar;
        this.gui = gui;


    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        myLevels.getBackground().drawOn(d);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.blocksCounter.getValue() == 0) {
            this.playerScore.increase(100);
            this.running = false;
        }
        if (this.ballCounter.getValue() == 0) {
            this.running = false;
        }
        if (keyboard.isPressed("p")) {
            runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
        d.setColor(Color.black.darker());
        d.drawText(20, 20, "level Name: " + myLevels.levelName(), 18);
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize.
     */
// Initialize a new game: create the Blocks and sprite.Ball and sprite.Paddle
    // and add them to the game.
    public void initialize() {
        //create three on to of the paddle balls
        createBallsOnTopOfPaddle();
        //create the boarder
        createBoarder();
        //create the paddle
        createThePaddle();
        HitListener hl = new BlockRemover(this, this.blocksCounter);
        HitListener pointsForThePlayer = new ScoreTrackingListener(this.playerScore);
        for (Block block : myLevels.blocks()) {
            block.addToGame(this);
            blocksCounter.increase(1);
            block.addHitListener(hl);
            block.addHitListener(pointsForThePlayer);
        }
        Sprite scoreIndicator = new ScoreIndicator(this.playerScore);
        scoreIndicator.addToGame(this);
    }

    /**
     * Run.
     */
// Run the game -- start the animation loop.
    public void run() {
        // countdown before turn starts.
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(new CountdownAnimation(2, 3, sprites, myLevels.getBackground()));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);

    }

    /**
     * Create Balls On Top Of Paddle.
     */
    private void createBallsOnTopOfPaddle() {
        for (int i = 0; i < myLevels.numberOfBalls(); i++) {
            Ball ball = new Ball(CENTER, RADIUS, BALL_COLOR);
            ball.setVelocity(myLevels.initialBallVelocities().get(i));
            ball.addToGame(this);
            ball.setGameObstacles(this.environment);
        }
        this.ballCounter.increase(myLevels.numberOfBalls());
    }

    /**
     * Create the paddle.
     */
    public void createThePaddle() {
        Point p = new Point(WIDTH / 2 - myLevels.paddleWidth() / 2, HEIGHT - BORDER_WIDTH - PADDLE_HEIGHT);
        Rectangle r = new Rectangle(p, myLevels.paddleWidth(), PADDLE_HEIGHT);
        Block b = new Block(r);
        Paddle paddle = new Paddle(b, myLevels.paddleSpeed());
        paddle.setKeyboard(gui);
        paddle.addToGame(this);
    }


    /**
     * Create boarder.
     */
// creat the game borders
    public void createBoarder() {
        HitListener hl = new BallRemover(this, ballCounter);
        Rectangle rectangleUp = new Rectangle(new Point(0, 20), WIDTH, BORDER_WIDTH);
        Block up = new Block(rectangleUp);
        up.addToGame(this);
        Rectangle rectangleDown = new Rectangle(new Point(0, HEIGHT), WIDTH, BORDER_WIDTH);
        Block down = new Block(rectangleDown);
        down.addHitListener(hl);
        down.addToGame(this);
        Rectangle rectangleLeft = new Rectangle(new Point(0, BORDER_WIDTH), BORDER_WIDTH,
                HEIGHT - BORDER_WIDTH);
        Block left = new Block(rectangleLeft);
        left.addToGame(this);
        Rectangle rectangleRight = new Rectangle(new Point(WIDTH - BORDER_WIDTH, BORDER_WIDTH), BORDER_WIDTH,
                HEIGHT - BORDER_WIDTH);
        Block right = new Block(rectangleRight);
        right.addToGame(this);
    }

    /**
     * remove from collidables list.
     *
     * @param c collidables to remove
     */
    public void removeCollidable(Collidable c) {
        if (environment.getCollidables().contains(c)) {
            environment.getCollidables().remove(c);
        }
    }

    /**
     * remove from sprite list.
     *
     * @param s sprite to remove
     */
    public void removeSprite(Sprite s) {
        if (sprites.getSprite().contains(s)) {
            sprites.getSprite().remove(s);
        }
    }

    /**
     * Get number of balls int.
     *
     * @return the int
     */
    public int getNumberOfBalls() {
        return ballCounter.getValue();
    }

    /**
     * Get number of blocks int.
     *
     * @return the int
     */
    public int getNumberOfBlocks() {
        return blocksCounter.getValue();
    }

}

