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
 * The type Level 4.
 */
public class Level4 implements LevelInformation {

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
     * The Border width.
     */
    static final int BORDER_WIDTH = 20;
    /**
     * The Ball speed.
     */
    static final int BALL_SPEED = 3;
    /**
     * The Blocks height.
     */
    static final int BLOCKS_HEIGHT = 30;

    /**
     * Instantiates a new Level 4.
     */
    public Level4() {
        this.blocks = new ArrayList<>();
        this.ballVelocity = new ArrayList<>();
        this.numberOfBalls = 4;
        this.paddleSpeed = 17;
        this.paddleWidth = 300;
        this.levelName = "a rainy day";
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
        double width = (WIDTH - 2 * BORDER_WIDTH) / 10;
        for (int i = 0; i < 6; i++) {
            Color color = creatingRandomColor();
            for (int j = 1; j <= 10; j++) {
                Block block = new Block(new Rectangle(new Point(WIDTH - BORDER_WIDTH - j * width,
                        WIDTH / 10 + i * BLOCKS_HEIGHT), width, BLOCKS_HEIGHT));
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
     * Creating background.
     *
     * @return sprites
     */
    private Sprite createBackground() {

        List<Sprite> sprites = new ArrayList<>();
        //background color
        Block block = new Block(new Rectangle(new Point(0, 0), WIDTH, HEIGHT));
        block.setColor(new Color(29, 190, 255));
        sprites.add(block);
        //rain&clouds 1
        Point center = new Point(WIDTH / 4, HEIGHT / 1.5);
        //rain
        for (int i = 0; i < 5; i++) {
            Line rainDrop = new Line(new Point(center.getX() - (10 * i), center.getY()),
                    new Point(center.getX() - (30 * i), HEIGHT));
            rainDrop.setColor(Color.white);
            sprites.add(rainDrop);
        }
        //create the one cloud
        //Circle number 1 of the clouds
        Circle c1 = new Circle(center, 30, true);
        c1.setColor(new Color(140, 140, 140));
        sprites.add(c1);
        //Circle number 2 of the clouds
        Point center2 = new Point(center.getX() - 50, center.getY());
        Circle c2 = new Circle(center2, 30, true);
        c2.setColor(new Color(110, 110, 110));
        sprites.add(c2);
        //Circle number 3 of the clouds
        Point center3 = new Point(center.getX() - 30, center.getY());
        Circle c3 = new Circle(center3, 30, true);
        c3.setColor(new Color(140, 140, 140));
        sprites.add(c3);
        //Circle number 4 of the clouds
        Point center4 = new Point(center.getX() + 15, center.getY() + 20);
        Circle c4 = new Circle(center4, 30, true);
        c4.setColor(new Color(156, 156, 156));
        sprites.add(c4);
        //Circle number 5 of the clouds
        Point center5 = new Point(center.getX() - 30, center.getY() + 15);
        Circle c5 = new Circle(center5, 30, true);
        c5.setColor(new Color(140, 140, 140));
        sprites.add(c5);
        //rain&clouds 2
        center = new Point(WIDTH / 1.2, HEIGHT / 1.3);
        //rain
        for (int i = 0; i < 5; i++) {
            Line rainDrop = new Line(new Point(center.getX() - (10 * i), center.getY()),
                    new Point(center.getX() - (20 * i), HEIGHT));
            rainDrop.setColor(Color.white);
            sprites.add(rainDrop);
        }
        //create the one cloud
        Point cloudNumTwo = new Point(center.getX() - 20, center.getY());
        c1 = new Circle(cloudNumTwo, 30, true);
        c1.setColor(new Color(140, 140, 140));
        sprites.add(c1);


        cloudNumTwo = new Point(center.getX() - 30, center.getY());
        c2 = new Circle(cloudNumTwo, 30, true);
        c2.setColor(new Color(110, 110, 110));
        sprites.add(c2);

        cloudNumTwo = new Point(center.getX() - 40, center.getY() + 15);
        c3 = new Circle(cloudNumTwo, 30, true);
        c3.setColor(new Color(140, 140, 140));
        sprites.add(c3);

        cloudNumTwo = new Point(center.getX() + 20, center.getY() + 20);
        c4 = new Circle(cloudNumTwo, 30, true);
        c4.setColor(new Color(156, 156, 156));
        sprites.add(c4);

        cloudNumTwo = new Point(center.getX(), center.getY() + 5);
        c5 = new Circle(cloudNumTwo, 30, true);
        c5.setColor(new Color(156, 156, 156));
        sprites.add(c5);

        DrawBackground backgroundLevel = new DrawBackground(sprites);
        return backgroundLevel;
    }
    /**
     * Creating all balls velocities.
     *
     */
    private void createBallsVelocities() {
        double openingAngel = 90 + 180 / (this.numberOfBalls + 1);
        for (int i = 0; i < this.numberOfBalls; i++) {
            this.ballVelocity.add(Velocity.fromAngleAndSpeed(openingAngel, BALL_SPEED));
            openingAngel += 180 / (this.numberOfBalls + 1);
        }
    }
}
