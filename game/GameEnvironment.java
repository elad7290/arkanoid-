package game;

import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import sprite.Collidable;
import sprite.CollisionInfo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The type game.Game environment.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * Instantiates a new game.Game environment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Gets collidables.
     *
     * @return the collidables
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
// add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Gets closest collision.
     *
     * @param trajectory the trajectory
     * @return the closest collision
     */
// Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) {
        LinkedList<Point> collisionPoint = new LinkedList<>();
        Rectangle r;
        Point point;
        Point minPoint = null;
        double minDistance = 0;
        Collidable closestObject = null;
        boolean flag = false;
        for (Collidable c : collidables) {
            r = c.getCollisionRectangle();
            point = trajectory.closestIntersectionToStartOfLine(r);
            if (point != null) {
                if (!flag) {
                    minDistance = trajectory.start().distance(point);
                    closestObject = c;
                    minPoint = trajectory.closestIntersectionToStartOfLine(r);
                    flag = true;
                }
                if (minDistance > trajectory.start().distance(point)) {
                    minDistance = trajectory.start().distance(point);
                    closestObject = c;
                    minPoint = trajectory.closestIntersectionToStartOfLine(r);
                }
            }

        }
        if (flag) {
            return new CollisionInfo(minPoint, closestObject);
        }
        return null;
    }

}