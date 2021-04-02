//ID:205444805

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * class of the level Green 3.
 * 2 ball
 * 40 block
 */

public class Green3 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {

        //create veloite for 2 balls.
        List<Velocity> velocityList = new ArrayList<Velocity>();
        Velocity v1 = new Velocity(-3, -7);
        Velocity v2 = new Velocity(3, -7);
        velocityList.add(v1);
        velocityList.add(v2);
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
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        Rectangle r = new Rectangle(new Point(0, 40), 800, 600);
        Block b = new Block(r, Color.GREEN);
        return b;
    }

    @Override
    public List<Block> blocks() {
        List blocksList = new ArrayList<Block>();

        //loop create block games.
        // every loop create one row.
        for (int i = 0; i < 5; i++) {

            //generate random color to every row of blocks.
            Color c = new Color((int) (Math.random() * 0x1000000));

            //every loop create one block.
            for (int j = i; j < 10; j++) {

                //create upper left point and block.
                Point upperLeft = new Point(280 + j * 50, 150 + i * 30);
                Block block = new Block(new Rectangle(upperLeft, 50, 30), c);

                //add block to list.
                blocksList.add(block);
            }

        }
        return blocksList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
