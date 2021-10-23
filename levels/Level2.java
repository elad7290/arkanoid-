package levels;

import geometry.Circle;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import sprite.Block;
import sprite.Sprite;
import sprite.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type Level 2.
 */
public class Level2 implements LevelInformation {
    private int numberOfBalls;
    private List<Velocity> ballVelocity;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numberOfBlocksToRemove = 0;
    /**
     * The Blocks height.
     */
    static final int BLOCKS_HEIGHT = 20;
    /**
     * The constant WIDTH.
     */
//game border
    static final int WIDTH = 800;
    /**
     * The Height.
     */
    static final int HEIGHT = 600;
    /**
     * The Border width.
     */
    static final int BORDER_WIDTH = 20;
    /**
     * The Ball speed.
     */
    static final int BALL_SPEED = 3;

    /**
     * Instantiates a new Level 2.
     */
    public Level2() {
        this.blocks = new ArrayList<>();
        this.ballVelocity = new ArrayList<>();
        this.numberOfBalls = 10;
        this.paddleSpeed = 17;
        this.paddleWidth = 550;
        this.levelName = "here come the sun";
        createBallsVelocities();
        this.background = createBackground();
        createBlocks();
    }

    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;

    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.ballVelocity;

    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }

    /**
     * Create blocks.
     */
    public void createBlocks() {
        for (int j = 0; j < 10; j++) {
            Color color = creatingRandomColor();
            Rectangle rectangle = new Rectangle(new Point(BORDER_WIDTH + j * ((WIDTH - 2 * BORDER_WIDTH) / 10),
                    HEIGHT / 2), (WIDTH - 2 * BORDER_WIDTH) / 10, BLOCKS_HEIGHT);
            Block block = new Block(rectangle);
            block.setColor(color);
            blocks.add(block);
            this.numberOfBlocksToRemove++;
        }
    }

    /**
     * Creating random color color.
     *
     * @return the color
     */
    public static Color creatingRandomColor() {
        Random randomGenerator = new Random();
        int red = randomGenerator.nextInt(256);
        int green = randomGenerator.nextInt(256);
        int blue = randomGenerator.nextInt(256);
        Color randomColour = new Color(red, green, blue);
        return randomColour;
    }

    /**
     * Creating all balls velocities.
     */
    private void createBallsVelocities() {
        double openingAngel = 90 + 180 / (this.numberOfBalls + 1);
        for (int i = 0; i < this.numberOfBalls; i++) {
            this.ballVelocity.add(Velocity.fromAngleAndSpeed(openingAngel, BALL_SPEED));
            openingAngel += 180 / (this.numberOfBalls + 1);
        }
    }

    /**
     * Create background sprite.
     *
     * @return the sprite
     */
    public Sprite createBackground() {
        List<Sprite> sprites = new ArrayList<>();
        //background color
        Block block = new Block(new Rectangle(new Point(0, 0), WIDTH, HEIGHT));
        block.setColor(Color.blue.brighter());
        sprites.add(block);
        Point center = new Point(WIDTH / 5, HEIGHT / 4);
        //create the lines
        int endY = (HEIGHT / 2);
        for (int i = 0; i < 100; i++) {
            Line line = new Line(center, new Point(BORDER_WIDTH + i * 7.4, endY));
            line.setColor(Color.orange);
            sprites.add(line);
        }
        //the Sun
        int r = 242, g = 187, b = 114;
        int radius = 40 + 20 * 2;
        for (int i = 0; i < 3; i++) {
            Circle circle = new Circle(center, radius, true);
            circle.setColor(new Color(r, g, b));
            sprites.add(circle);
            g += 20;
            b -= 20;
            radius -= 10;
        }
        DrawBackground backgroundLevel = new DrawBackground(sprites);
        return backgroundLevel;

    }
}


