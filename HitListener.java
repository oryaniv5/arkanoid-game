//ID:205444805

/**
 * Iterface of all object that listen to hits
 * in object that implement HitNotifier.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit block
     * @param hitter   ball
     */
    void hitEvent(Block beingHit, Ball hitter);
}
