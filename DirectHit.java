//ID:205444805

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * class of the level Direct hit.
 * 1 ball
 * 1 block
 */
public class DirectHit implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {

        //create one velocity with only up and down.
        Velocity v = new Velocity(0, -5);
        List<Velocity> velocityList = new ArrayList<Velocity>();
        velocityList.add(v);
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        Rectangle r = new Rectangle(new Point(0, 40), 800, 600);
        Block b = new Block(r, Color.BLACK);
        return b;
    }

    @Override
    public List<Block> blocks() {

        //create one block sith direct hit.
        List blocksList = new ArrayList<Block>();
        Rectangle r = new Rectangle(new Point(390, 100), 20, 20);
        Block b = new Block(r);
        blocksList.add(b);
        return blocksList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
