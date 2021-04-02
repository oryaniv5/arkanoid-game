//ID:205444805

/**
 * Class tracking after score.
 * add 5 point whenever block hit.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * basic constructor.
     *
     * @param scoreCounter counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {

        //add 5 points to score.
        this.currentScore.increase(5);
    }
}
