package levels;

import geometry.Circle;
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
 * The type Level 3.
 */
public class Level3 implements LevelInformation {

    private int numberOfBalls;
    private List<Velocity> ballVelocity;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numberOfBlocksToRemove = 0;

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
     * Instantiates a new Level 3.
     */
    public Level3() {
        this.blocks = new ArrayList<>();
        this.ballVelocity = new ArrayList<>();
        this.numberOfBalls = 3;
        this.ballVelocity.add(new Velocity(-5, -2));
        this.ballVelocity.add(new Velocity(-4, -3));
        this.ballVelocity.add(new Velocity(-3, -2));
        this.paddleSpeed = 17;
        this.paddleWidth = 150;
        this.levelName = "Skyscraper";
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
        for (int i = 0; i < 10; i++) {
            Color color = creatingRandomColor();
            for (int j = 1; j <= 12 - i; j++) {
                Block block = generateCollidableBlock(j * 50, i);
                block.setColor(color);
                blocks.add(block);
                this.numberOfBlocksToRemove++;
            }
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
     * Generate collidable block block.
     *
     * @param width the width
     * @param rNum the r num
     * @return the block
     */
    public static Block generateCollidableBlock(int width, int rNum) {
        Point upperLeft = new Point(WIDTH - 20 - width, 80 + (rNum * 20));
        int newWidth = 50;
        int height = 20;
        Rectangle r = new Rectangle(upperLeft, newWidth, height);
        return new Block(r);
    }

    /**
     * Creating background.
     *
     * @return sprites
     */
    private Sprite createBackground() {

        List<Sprite> sprites = new ArrayList<>();
        //background color
        Block block = new Block(new Rectangle(new Point(0, 0), WIDTH, HEIGHT));
        block.setColor(new Color(20, 145, 36));
        sprites.add(block);
        //create the tower
        Block block1 = new Block(new Rectangle(new Point(WIDTH / 8, WIDTH / 2),
                WIDTH / 4, HEIGHT + 10));
        block1.setColor(Color.black);
        sprites.add(block1);
        int perRow = 6;
        int numRow = 4;
        int startX = WIDTH / 8 + 15;
        int startY = WIDTH / 2 + 10;
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < perRow; j++) {
                Block windows = new Block(new Rectangle(new Point((startX + (j * 20) + (j * 10)),
                        (startY + (i * 40) + (i * 10))), 20, 40));
                windows.setColor(Color.white);
                sprites.add(windows);
            }
        }
        Block block2 = new Block(new Rectangle(new Point(WIDTH / 8 + 50, WIDTH / 2 - 30), 100,
                30));
        block2.setColor(Color.gray);
        Block block3 = new Block(new Rectangle(new Point(WIDTH / 8 + 95, WIDTH / 2 - 160), 10,
                130));
        block3.setColor(Color.gray);
        sprites.add(block2);
        sprites.add(block3);
        Point center = new Point(WIDTH / 8 + 100, WIDTH / 2 - 170);
        Circle c1 = new Circle(center, 12, true);
        c1.setColor(new Color(255, 149, 27));
        sprites.add(c1);
        Circle c2 = new Circle(center, 8, true);
        c2.setColor(new Color(255, 215, 116));
        sprites.add(c2);
        Circle c3 = new Circle(center, 4, true);
        c3.setColor(new Color(255, 255, 19));
        sprites.add(c3);
        DrawBackground backgroundLevel = new DrawBackground(sprites);
        return backgroundLevel;
    }
}
