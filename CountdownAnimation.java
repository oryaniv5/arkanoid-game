//ID:205444805

import biuoop.DrawSurface;

/**
 * The CountdownAnimation will display the given gameScreen
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean running;

    /**
     * basic constructor.
     * @param numOfSeconds of all the run
     * @param countFrom start from
     * @param gameScreen draw the screen and then the countdown
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.running = true;

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.drawText(365, d.getHeight() / 2, " " + (int) numOfSeconds + " ", 70);
        this.numOfSeconds--;
        if (this.numOfSeconds == 0) {
            this.running = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;

    }
}
