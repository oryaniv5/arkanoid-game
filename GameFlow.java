//ID:205444805

import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * Class use loop to make and run
 * more then one level,
 * aso create end screen's.
 */
public class GameFlow {
    private GUI gui;
    private AnimationRunner animationRunner;
    private biuoop.KeyboardSensor keyboardSensor;

    private static final int GAME_WIDTH = 800;
    private static final int GAME_HEIGHT = 600;


    /**
     * basic constructor.
     *
     * @param ar help to run level.
     * @param ks keyboard sensor.
     * @param gui to show game
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.gui = gui;
        this.animationRunner = ar;
        this.keyboardSensor = ks;

    }

    /**
     * the method use list input
     * run game levels one after another.
     *
     * @param levels list of all levels
     */
    public void runLevels(List<LevelInformation> levels) {

        //massage to put in end screen.
        String massage;
        boolean isLoss = false;

        //create score indicator.
        ScoreIndicator scoreIndicator = new ScoreIndicator(new Counter(0), "Direct Hit");

        //loop on all levels in ist and run every level.
        for (LevelInformation levelInfo : levels) {

            //create game level obj.
            //use to initialize and run.
            GameLevel level = new GameLevel(levelInfo,
                    this.keyboardSensor,
                    this.animationRunner,
                    this.gui,
                    scoreIndicator);

            level.initialize();

            //loop until no balls or blocks remain.
            while (level.remainBalls() && level.remainBlocks()) {
                level.run();

            }


            //if no more balls the user loss.
            //break out of the game.
            if (!level.remainBalls()) {
                isLoss = true;
                break;

            }
        }
        int lastScore = scoreIndicator.getScoreCounter().getValue();

        //check if user loss.
        //create appropriate massage.
        if (isLoss) {
            massage = "Game Over. Your score is " + lastScore + " ";
        } else {
            massage = "You Win! Your score is " + lastScore + " ";
        }

        //create end screen and run it.
        EndScreen es = new EndScreen(massage);
        KeyPressStoppableAnimation kpsa = new KeyPressStoppableAnimation(keyboardSensor, keyboardSensor.SPACE_KEY, es);
        this.animationRunner.run(kpsa);

        //close game.
        gui.close();


    }

}
