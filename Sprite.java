import biuoop.DrawSurface;

/**
 * interface of all object,
 * that can move on screen.
 */
public interface Sprite {
    /**
     * draw the sprite object.
     *
     * @param d to draw.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
