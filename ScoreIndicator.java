//ID:205444805

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Class draw score on screen.
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreCounter;
    private String levelName;

    /**
     * basic constructor.
     * using counter that change in ScoreTrackingListener.
     *
     * @param levelName    to show
     * @param scoreCounter counter of score
     */
    public ScoreIndicator(Counter scoreCounter, String levelName) {
        this.scoreCounter = scoreCounter;
        this.levelName = levelName;
    }

    /**
     * basic get method.
     *
     * @return score counter
     */
    public Counter getScoreCounter() {
        return this.scoreCounter;
    }

    /**
     * basic set method.
     *
     * @param newLevelName string
     */
    public void setLevelName(String newLevelName) {
        this.levelName = newLevelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        String score = "score:" + Integer.toString(this.scoreCounter.getValue());
        d.setColor(Color.GRAY);
        d.drawRectangle(0, 0, 800, 20);
        d.drawText(190, 18, score, 12);
        d.drawText(590, 18, this.levelName, 12);

    }

    @Override
    public void timePassed() {

    }
}
