//ID:205444805

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * Class run evety obj from
 * Animation interface.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;

    /**
     * basic constructor.
     *
     * @param gui to show
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = 60;
    }

    /**
     * mwthod run the animation.
     *
     * @param animation to run
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {

            // timing
            long startTime = System.currentTimeMillis();
            DrawSurface d = this.gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                Sleeper sleeper = new Sleeper();
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
