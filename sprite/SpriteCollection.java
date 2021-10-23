package sprite;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The type sprite.Sprite collection.
 */
public class SpriteCollection {
    /**
     * The sprite.Sprite list.
     */
    private List<Sprite> spriteList;

    /**
     * Instantiates a new sprite.Sprite collection.
     */
    public SpriteCollection() {
        spriteList = new ArrayList<>();
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        spriteList.add(s);
    }

    /**
     * Notify all time passed.
     */
// call timePassed() on all sprites.
    public void notifyAllTimePassed() {
        List<Sprite> copyOfSpriteList = new ArrayList<>(this.spriteList);
        for (Sprite s : copyOfSpriteList) {
            s.timePassed();
        }
    }

    /**
     * Draw all on.
     *
     * @param d the d
     */
// call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : spriteList) {
            s.drawOn(d);
        }
    }

    /**
     * get Sprite.
     *
     * @return list of sprite
     */
    public List<Sprite> getSprite() {
        return this.spriteList;
    }
}
