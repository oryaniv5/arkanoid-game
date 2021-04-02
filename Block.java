// ID: 205444805

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Block is rectangle on gui screen.
 * have the method of interface collidable and sprite.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private List<HitListener> hitListeners;
    private Rectangle r;
    private Color color;

    /**
     * create new block, with rectangle value and red color.
     *
     * @param r rectangle.
     */
    public Block(Rectangle r) {
        this.hitListeners = new ArrayList<>();
        this.r = r;
        this.color = Color.RED;
    }

    /**
     * create block, with rectangle value and color define by user.
     *
     * @param r rectangle.
     * @param c color pf rectangle.
     */
    public Block(Rectangle r, Color c) {
        this.hitListeners = new ArrayList<>();
        this.r = r;
        this.color = c;
    }

    /**
     * method give the rectangle value.
     *
     * @return block rectangle value.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.r;
    }

    /**
     * method decide the impact of collision with block on the
     * object that collies with.
     *
     * @param collisionPoint  point of collision.
     * @param currentVelocity of the object.
     * @return the new velocity value.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        //define epsilon value.
        // used to help get throw java technical problem.
        double epsilon = Math.pow(10, -10);

        //if the hit is at the up or down edge of the rectangle.
        if (Math.abs(collisionPoint.getX() - this.r.getUpperLeft().getX()) < epsilon
                || (Math.abs(collisionPoint.getX() - (this.r.getUpperLeft().getX() + this.r.getWidth()))) < epsilon) {

            //if the hit is at the corner, change dx and dy value.
            if (Math.abs(collisionPoint.getY() + 1 - this.r.getUpperLeft().getY()) < epsilon
                    || Math.abs(collisionPoint.getY() + 1 - (this.r.getUpperLeft().getY() + this.r.getHeight()))
                    < epsilon) {
                this.notifyHit(hitter);
                return new Velocity(currentVelocity.getDx() * -1, currentVelocity.getDy() * -1);
            } else {

                //if the hit is at the up or down edge, change dx value.
                this.notifyHit(hitter);
                return new Velocity(currentVelocity.getDx() * -1, currentVelocity.getDy());
            }
        }

        //if the hit is at the up or down edge, change dy value.
        if (Math.abs(collisionPoint.getY() - this.r.getUpperLeft().getY()) < epsilon
                || Math.abs(collisionPoint.getY() - (this.r.getUpperLeft().getY() + this.r.getHeight())) < epsilon) {
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * -1);
        }

        //if no hit, return null.
        return null;
    }

    /**
     * method draw the block, using the rectangle draw method.
     *
     * @param surface to draw with.
     */
    @Override
    public void drawOn(DrawSurface surface) {

        //use rectangle draw method.
        this.r.drawOn(surface, color);
    }

    /**
     * method don't work at this moment.
     */
    @Override
    public void timePassed() {

    }

    /**
     * add block to game lists of collidable and sprite.
     *
     * @param g game to add the block.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * remove block from game
     * remove from collidable and sprite.
     *
     * @param gameLevel to remove from.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        int listSize = this.hitListeners.size();
        for (int i = 0; i < listSize; i++) {
            if (this.hitListeners.get(i).equals(hl)) {
                this.hitListeners.remove(i);
                break;
            }
        }
    }

    /**
     * notify all listeners that block have been hit.
     *
     * @param hitter ball
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
