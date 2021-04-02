//ID:205444805

/**
 * a BallRemover is in charge of removing balls from the game,
 * as well as keeping count
 * of the number of balls that remain.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * basic constructor.
     *
     * @param gameLevel      to remove block from
     * @param remainingBalls in game
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }


    @Override
    public void hitEvent(Block beingHit, Ball hitter) {

        //decrease amount of balls in game.
        this.remainingBalls.decrease(1);

        //remove ball from game.
        hitter.removeFromGame(this.gameLevel);
    }
}
