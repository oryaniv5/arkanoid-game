//ID:205444805

import java.util.List;

/**
 * interface contain info
 * about every level.
 */
public interface LevelInformation {

    /**
     * return amount of balls in game.
     *
     * @return number of balls.
     */
    int numberOfBalls();

    /**
     * initial velocity of all balls.
     *
     * @return list of all balls velocity.
     */
    List<Velocity> initialBallVelocities();

    /**
     * return paddle speed to initial.
     *
     * @return paddle speed.
     */
    int paddleSpeed();

    /**
     * return paddle width to initial.
     *
     * @return paddle width.
     */
    int paddleWidth();

    /**
     * the level name will be displayed
     * at the top of the screen.
     *
     * @return string level name
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     *
     * @return sprite background
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return list of all blocks
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     *
     * @return number of blacks in level
     */
    int numberOfBlocksToRemove();
}
