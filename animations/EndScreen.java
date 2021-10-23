package animations;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type End screen.
 */
public class EndScreen implements Animation {
    private int score;
    private boolean win;
    private boolean stop;

    /**
     * Instantiates a new End screen.
     *
     * @param score the score
     * @param win the win
     */
    public EndScreen(int score, boolean win) {
        this.score = score;
        this.win = win;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.win) {
            d.setColor(Color.black);
            d.drawText(175, d.getHeight() / 2 - 200, "Congratulations You Win! ", 40);
            d.setColor(Color.red);
            d.drawText(178, d.getHeight() / 2 - 200, "Congratulations You Win! ", 40);
            d.setColor(Color.yellow);
            d.drawText(230, d.getHeight() / 2, "Your score is: " + this.score, 40);
            d.setColor(Color.blue);
            d.drawText(233, d.getHeight() / 2, "Your score is: " + this.score, 40);
        }
        if (!this.win) {
            d.drawText(180, d.getHeight() / 2 - 200, "You Lose! Game Over.", 40);
            d.setColor(Color.red);
            d.drawText(230, d.getHeight() / 2, "Your score is: " + this.score, 40);
            d.setColor(Color.blue);
            d.drawText(233, d.getHeight() / 2, "Your score is: " + this.score, 40);
        }

    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
