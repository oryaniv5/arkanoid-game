/**
 * interface of all object that can have
 * collidable with another object.
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     *
     * @return collision shape.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param hitter          ball
     * @param collisionPoint  point.
     * @param currentVelocity velocity.
     * @return new velocity after hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}