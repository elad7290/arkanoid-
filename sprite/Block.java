package sprite;

import listeners.HitListener;
import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * The type sprite.Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    /**
     * The Hit listeners.
     */
    private List<HitListener> hitListeners;
    private Rectangle block;
    private Color color;
    /**
     * The Color.
     */
    static final Color COLOR = Color.darkGray.darker();

    /**
     * Instantiates a new sprite.Block.
     *
     * @param block the block
     */
    public Block(Rectangle block) {
        this.block = block;
        this.color = COLOR;
        this.hitListeners = new ArrayList<>();


    }

    /**
     * Sets color.
     *
     * @param c the color
     */
    public void setColor(Color c) {
        this.color = c;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        if ((getCollisionRectangle().getBottomLine().isInRange(collisionPoint))
                || (getCollisionRectangle().getUpperLine().isInRange(collisionPoint))) {
            dy = -dy;
        }
        if ((getCollisionRectangle().getRightLine().isInRange(collisionPoint))
                || (getCollisionRectangle().getLeftLine().isInRange(collisionPoint))) {
            dx = -dx;
        }
        this.notifyHit(hitter);
        return new Velocity(dx, dy);
    }

    @Override
    public void drawOn(DrawSurface d) {

        d.setColor(this.color);
        d.fillRectangle((int) block.getUpperLeft().getX(),
                (int) block.getUpperLeft().getY(), (int) block.getWidth(), (int) block.getHeight());

        d.setColor(Color.white);
        d.drawRectangle((int) block.getUpperLeft().getX(),
                (int) block.getUpperLeft().getY(), (int) block.getWidth(), (int) block.getHeight());


    }

    @Override
    public void timePassed() {
        return;
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Remove from game.
     *
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        if (this.hitListeners.contains(hl)) {
            this.hitListeners.remove(hl);
        }
    }
    /**
     * notify hit.
     *
     * @param hitter the ball
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
