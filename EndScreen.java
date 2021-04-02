//ID:205444805

import biuoop.DrawSurface;

/**
 * class show end screen when game ends..
 */
public class EndScreen implements Animation {
    private String massage;

    /**
     * basic constructor.
     *
     * @param massage to show
     */
    public EndScreen(String massage) {
        this.massage = massage;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(150, d.getHeight() / 2, this.massage, 32);
    }

    @Override
    public boolean shouldStop() {
        return true;
    }
}
