//ID:205444805

/**
 * Class held info on collision.
 * have the point info and the object of collision with value.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * create new object using user input.
     *
     * @param collisionPoint  point.
     * @param collisionObject object value.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * the point at which the collision occurs.
     *
     * @return collision point value.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * the collidable object involved in the collision.
     *
     * @return object of collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
