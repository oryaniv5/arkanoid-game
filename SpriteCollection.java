//ID:205444805

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * class svae sprite object in list
 * can draw all sprite and time passed to all sprite
 * using method in the sprite object class.
 */
public class SpriteCollection {

    private List<Sprite> l;

    /**
     * create collection of sprite
     * using array list.
     */
    public SpriteCollection() {
        this.l = new ArrayList<>();
    }

    /**
     * add sprite object input to the list.
     *
     * @param s sprite object.
     */
    public void addSprite(Sprite s) {
        l.add(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> listCopy = new ArrayList<Sprite>(l);
        for (Sprite s : listCopy) {
            s.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d to draw.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : l) {
            s.drawOn(d);
        }
    }

    /**
     * remove spirte from list.
     *
     * @param s to remove
     */
    public void removeSprite(Sprite s) {
        this.l.remove(s);
    }
}
