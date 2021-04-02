//ID:205444805

import biuoop.DrawSurface;

/**
 * class show pause screen when p accepted.
 */
public class PauseScreen implements Animation {

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(150, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return true;
    }
}
