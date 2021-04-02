//ID:205444805

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * class of the level Wide easy.
 * 10 ball
 * 16 block
 */
public class WideEasy implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {

        //create velocity for 10 balls.
        List<Velocity> velocityList = new ArrayList<Velocity>();
        for (int i = 0; i < 5; i++) {
            Velocity v1 = Velocity.fromAngleAndSpeed(20 - i * 15, 4);
            Velocity v2 = Velocity.fromAngleAndSpeed(-20 + i * 15, 4);
            velocityList.add(v1);
            velocityList.add(v2);
        }
        return velocityList;

    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 400;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        Rectangle r = new Rectangle(new Point(0, 40), 800, 600);
        Block b = new Block(r, Color.WHITE);
        return b;
    }

    @Override
    public List<Block> blocks() {

        //create one row of 16 blocks.
        List blocksList = new ArrayList<Block>();
        for (int i = 0; i < 16; i++) {
            Color c = new Color((int) (Math.random() * 0x1000000));
            Rectangle r = new Rectangle(new Point(20 + i * 47.5, 100), 47.5, 20);
            Block b = new Block(r, c);
            blocksList.add(b);
        }
        return blocksList;

    }

    @Override
    public int numberOfBlocksToRemove() {
        return 16;
    }
}
