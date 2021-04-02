//ID:205444805

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * class of all obj that have screen
 * how wait for some key to accepted.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;

    /**
     * basic constructor.
     *
     * @param sensor    keyboard
     * @param key       to wait for push
     * @param animation to run while waite
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboard = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (!this.keyboard.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }

        if (this.keyboard.isPressed(this.key) && !this.isAlreadyPressed) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
