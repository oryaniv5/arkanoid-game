//ID:205444805

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * class of the level Final four.
 * 3 ball
 * 112 block
 */
public class FinalFour implements LevelInformation {

    private static final int BLOCK_HEIGHT = 25;
    private static final double BLOCK_WIDTH = 48;


    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {

        //create velocity for 3 balls.
        List<Velocity> velocityList = new ArrayList<Velocity>();
        Velocity v1 = new Velocity(0, -7);
        Velocity v2 = new Velocity(-3, -7);
        Velocity v3 = new Velocity(3, -7);
        velocityList.add(v1);
        velocityList.add(v2);
        velocityList.add(v3);
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
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        Rectangle r = new Rectangle(new Point(0, 40), 800, 600);
        Block b = new Block(r, Color.WHITE);
        return b;
    }

    @Override
    public List<Block> blocks() {
        List blocksList = new ArrayList<Block>();
        //loop create block games.
        // every loop create one row.
        for (int i = 0; i < 7; i++) {

            //generate random color to every row of blocks.
            Color c = new Color((int) (Math.random() * 0x1000000));

            //every loop create one block.
            for (int j = 0; j < 16; j++) {

                //create upper left point and block.
                Point upperLeft = new Point(20 + j * BLOCK_WIDTH, 90 + i * BLOCK_HEIGHT);
                Block block = new Block(new Rectangle(upperLeft, BLOCK_WIDTH, BLOCK_HEIGHT), c);

                //add block to list.
                blocksList.add(block);
            }

        }
        return blocksList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 112;
    }
}
