import animations.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.GameFlow;
import levels.Level1;
import levels.Level2;
import levels.Level3;
import levels.Level4;
import levels.LevelInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Ass 6 game.
 */
public class Ass6Game {
    /**
     * The constant WIDTH.
     */
    static final int WIDTH = 800;
    /**
     * The Height.
     */
    static final int HEIGHT = 600;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid ", WIDTH, HEIGHT);
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        AnimationRunner animationRunner = new AnimationRunner(gui);
        List<LevelInformation> allMyLevels = new ArrayList<>();
        GameFlow gameFlow = new GameFlow(animationRunner, keyboardSensor, gui);
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                try {
                    int levelInsertToList = Integer.parseInt(args[i]);
                    if (levelInsertToList == 1) {
                        allMyLevels.add(new Level1());
                    }
                    if (levelInsertToList == 2) {
                        allMyLevels.add(new Level2());
                    }
                    if (levelInsertToList == 3) {
                        allMyLevels.add(new Level3());
                    }
                    if (levelInsertToList == 4) {
                        allMyLevels.add(new Level4());
                    }
                } catch (Exception e) {
                    System.out.println();
                }
            }
        }
        if (allMyLevels.size() == 0) {
            allMyLevels.add(new Level1());
            allMyLevels.add(new Level2());
            allMyLevels.add(new Level3());
            allMyLevels.add(new Level4());
        }
        gameFlow.runLevels(allMyLevels);
    }

}
