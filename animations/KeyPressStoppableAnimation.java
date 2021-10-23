package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean keyHasBeenPressed = false;
    private boolean isAlreadyPressed = true;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor the sensor
     * @param key the key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.key = key;
        this.animation = animation;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (keyboardSensor.isPressed(key) && !isAlreadyPressed) {
            keyHasBeenPressed = true;
            isAlreadyPressed = false;
        }
        if (!keyboardSensor.isPressed(key)) {
            isAlreadyPressed = false;
            keyHasBeenPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        if (keyHasBeenPressed) {
            keyHasBeenPressed = false;
            return true;
        }
        return animation.shouldStop();
    }
}
