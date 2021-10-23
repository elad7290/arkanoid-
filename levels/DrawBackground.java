package levels;

import biuoop.DrawSurface;
import game.GameLevel;
import sprite.Sprite;

import java.util.List;

/**
 * The type Draw background.
 */
public class DrawBackground implements Sprite {
    private List<Sprite> sprites;

    /**
     * Instantiates a new Draw background.
     *
     * @param sprites the sprites
     */
    public DrawBackground(List<Sprite> sprites) {
        this.sprites = sprites;

    }

    @Override
    public void drawOn(DrawSurface d) {
        List<Sprite> copyOfSprite = this.sprites;
        for (Sprite s : copyOfSprite) {
            s.drawOn(d);
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
