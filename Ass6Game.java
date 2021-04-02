//ID:205444805

import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

/**
 * class run game.
 */
public class Ass6Game {

    private static final int GAME_WIDTH = 800;
    private static final int GAME_HEIGHT = 600;

    /**
     * method create new game variable
     * and run new game.
     *
     * @param args no value.
     */
    public static void main(String[] args) {

        GUI gui = new biuoop.GUI("arkanoid", GAME_WIDTH, GAME_HEIGHT);

        //create game key board sensor
        KeyboardSensor ks = gui.getKeyboardSensor();

        //create game runner.
        AnimationRunner ar = new AnimationRunner(gui);
        List<LevelInformation> lifList = new ArrayList<LevelInformation>();

            //loop create list by args input.
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("1")) {
                lifList.add(new DirectHit());
            }
            if (args[i].equals("2")) {
                lifList.add(new WideEasy());
            }
            if (args[i].equals("3")) {
                lifList.add(new Green3());
            }
            if (args[i].equals("4")) {
                lifList.add(new FinalFour());
            }
        }
        
        //if no input create lif list.
        if (lifList.size() == 0) {
            lifList.add(new DirectHit());
            lifList.add(new WideEasy());
            lifList.add(new Green3());
            lifList.add(new FinalFour());
        }


        //create gameFlow obj and run game.
        GameFlow gameFlow = new GameFlow(ar, ks, gui);
        gameFlow.runLevels(lifList);

    }
}
