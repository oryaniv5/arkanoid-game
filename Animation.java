//ID:205444805

import biuoop.DrawSurface;

/**
 * interface of obj that
 * run in class AnimationRunner.
 */
public interface Animation {

    /**
     * do logic of one frame.
     *
     * @param d to draw.
     */
    void doOneFrame(DrawSurface d);

    /**
     * return false if need to stop animation.
     * otherwise -- return true.
     *
     * @return true or false
     */
    boolean shouldStop();
}
