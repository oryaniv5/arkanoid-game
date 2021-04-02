//ID:205444805

/**
 * Interface of all object that inform other object when hit.
 * the object inform implement HitListener
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl to add
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl to remove
     */
    void removeHitListener(HitListener hl);
}
