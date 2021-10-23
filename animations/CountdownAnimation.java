package animations;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import sprite.Sprite;
import sprite.SpriteCollection;

import java.awt.Color;

/**
 * The type Countdown animation.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int currentCount;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private Sprite background;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom the count from
     * @param gameScreen the game screen
     * @param background the background
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, Sprite background) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.currentCount = this.countFrom;
        this.stop = false;
        this.background = background;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        Sleeper sleeper = new Sleeper();
        this.background.drawOn(d);
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.yellow);
        if (currentCount == 0) {
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, "GO!!", 40);
        } else {
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, this.currentCount + "...", 40);
        }
        if (currentCount == -1) {
            this.stop = true;
        }
        if (countFrom != currentCount) {
            sleeper.sleepFor((int) (numOfSeconds * 1000) / countFrom + 1);
        }

        currentCount = currentCount - 1;

    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
