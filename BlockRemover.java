//ID:205444805

/**
 * a BlockRemover is in charge of removing blocks from the game,
 * as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * basic constructor.
     *
     * @param gameLevel       to remove block from
     * @param remainingBlocks amount pf block remain
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Blocks that are hit removed from game.
     *
     * @param beingHit block
     * @param hitter   ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {

        //decrease amount of blocks in game.
        this.remainingBlocks.decrease(1);

        //remove block from game.
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.gameLevel);
    }
}
