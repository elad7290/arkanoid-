package game;

import animations.AnimationRunner;
import animations.EndScreen;
import animations.KeyPressStoppableAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import levels.LevelInformation;
import sprite.Counter;

import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private GUI gui;
    private Counter playerScore;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar the ar
     * @param ks the ks
     * @param gui the gui
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gui = gui;
        this.playerScore = new Counter();
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner,
                    this.gui, this.playerScore);
            level.initialize();
            while (level.getNumberOfBalls() > 0 && level.getNumberOfBlocks() > 0) {
                level.run();
            }
            if (level.getNumberOfBalls() == 0) {
                this.animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor,
                        KeyboardSensor.SPACE_KEY, new EndScreen(this.playerScore.getValue(), false)));
                this.gui.close();
                break;
            }

        }
        this.animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY,
                new EndScreen(this.playerScore.getValue(), true)));
        this.gui.close();
    }
}