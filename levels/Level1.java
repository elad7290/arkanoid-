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
 * The type Level 1.
 */
public class Level1 implements LevelInformation {

    private int numberOfBalls;
    private List<Velocity> ballVelocity;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numberOfBlocksToRemove = 0;

    /**
     * The Blocks width.
     */
    static final int BLOCKS_WIDTH = 50;
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
     * Instantiates a new Level 1.
     */
    public Level1() {
        this.blocks = new ArrayList<>();
        this.ballVelocity = new ArrayList<>();
        this.numberOfBalls = 1;
        this.ballVelocity.add(new Velocity(0, -4));
        this.paddleSpeed = 17;
        this.paddleWidth = 150;
        this.levelName = "one shot one kill";
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
        Color color = creatingRandomColor();
        Block block = new Block(new Rectangle(new Point(WIDTH / 2 - BLOCKS_WIDTH / 2, HEIGHT / 3),
                BLOCKS_WIDTH, BLOCKS_HEIGHT));
        block.setColor(color);
        blocks.add(block);
        this.numberOfBlocksToRemove++;
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
     * Create background sprite.
     *
     * @return the sprite
     */
    public Sprite createBackground() {
        List<Sprite> sprites = new ArrayList<>();
        //background color
        Block b = new Block(new Rectangle(new Point(0, 0), WIDTH, HEIGHT));
        b.setColor(Color.black);
        sprites.add(b);
        //circles
        Point center = new Point(WIDTH / 2, HEIGHT / 3 + BLOCKS_HEIGHT / 2);
        for (int i = 1; i <= 3; i++) {
            Circle c = new Circle(center, BLOCKS_WIDTH + i * 30, false);
            c.setColor(Color.BLUE);
            sprites.add(c);
        }
        //lines
        Line line1 = new Line(center.getX(), center.getY() + 6 * 30, center.getX(), center.getY() - 6 * 30);
        line1.setColor(Color.BLUE);
        sprites.add(line1);
        Line line2 = new Line(center.getX() - 6 * 30, center.getY(), center.getX() + 6 * 30, center.getY());
        line2.setColor(Color.BLUE);
        sprites.add(line2);
        return new DrawBackground(sprites);
    }
}