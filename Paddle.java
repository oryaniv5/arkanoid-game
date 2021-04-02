//ID:205444805

import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;

/**
 * Game paddle have collidable and sprite method.
 * used as moving block to save balls.
 */
public class Paddle implements Sprite, Collidable {
    private int speed;
    private int width;
    private biuoop.KeyboardSensor keyboard;
    private Rectangle r;

    static final int PADDLE_START_X = 360;
    static final int PADDDLE_START_Y = 560;

    /**
     * create new paddle.
     * paddle have default value's.
     * create keyboard sensor.
     *
     * @param gui to create keyboard.
     */
    public Paddle(GUI gui) {
        this.keyboard = gui.getKeyboardSensor();
        this.width = 100;
        this.speed = 10;
        this.r = new Rectangle(new Point(PADDLE_START_X, PADDDLE_START_Y), this.width, 20);

    }

    /**
     * another constructor.
     * get all the value's from user;
     * spped, width and rectangle upper left point.
     *
     * @param gui   screen
     * @param speed of paddle
     * @param width of paddle
     */
    public Paddle(GUI gui, int speed, int width) {
        this.keyboard = gui.getKeyboardSensor();
        this.speed = speed;
        this.width = width;
        Point p = new Point(400 - this.width / 2, 560);
        this.r = new Rectangle(p, this.width, 20);

    }

    /**
     * Make paddle move left.
     */
    public void moveLeft() {

        //set new upper left point in the value x-10
        Point p = new Point(this.r.getUpperLeft().getX() - this.speed, 560);
        this.r.setUpperLeft(p);

    }

    /**
     * Make paddle move right.
     */
    public void moveRight() {

        //set new upper left point in the value x+10
        Point p = new Point(this.r.getUpperLeft().getX() + this.speed, 560);
        this.r.setUpperLeft(p);
    }

    /**
     * sprite method.
     * if found right or left key, move left/right.
     */
    @Override
    public void timePassed() {

        //call move left.
        if (keyboard.isPressed(keyboard.LEFT_KEY) && this.r.getUpperLeft().getX() > 20) {
            this.moveLeft();
        }

        //call move right.
        if (keyboard.isPressed(keyboard.RIGHT_KEY) && this.r.getUpperLeft().getX() + this.width < 780) {
            this.moveRight();
        }

    }

    /**
     * sprite method.
     * draw the paddle.
     *
     * @param d to draw paddle.
     */
    @Override
    public void drawOn(DrawSurface d) {
        this.r.drawOn(d, Color.YELLOW);
    }

    /**
     * Collidable method.
     * give the collidable object value's.
     *
     * @return the paddle rectangle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.r;
    }

    /**
     * collidable method.
     * change velocity, after finding the
     * place the ball hit paddle
     * the paddle have 5 area with different velocity value.
     *
     * @param collisionPoint  point.
     * @param currentVelocity velocity.
     * @return new velocity after hit.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        //create epsilon.
        double epsilon = Math.pow(10, -11);

        //if hit in left or right line, return velocity dx*-1.
        if (Math.abs(collisionPoint.getX() - this.r.getUpperLeft().getX()) < epsilon
                || (Math.abs(collisionPoint.getX() - (this.r.getUpperLeft().getX() + this.r.getWidth()))) < epsilon) {
            return new Velocity(currentVelocity.getDx() * -1, currentVelocity.getDy());
        }

        //if hit in up line, decide new velocity by the area that hit.
        if (Math.abs(collisionPoint.getY() - this.r.getUpperLeft().getY()) < epsilon
                || Math.abs(collisionPoint.getY() - (this.r.getUpperLeft().getY() + this.r.getHeight())) < epsilon) {

            double collPointX = collisionPoint.getX();
            int paddleStart = (int) this.r.getUpperLeft().getX();
            int areaLength = this.width / 5;

            //area 1 -- 300 degree.
            if (collPointX >= paddleStart && collPointX < paddleStart + areaLength) {
                return Velocity.fromAngleAndSpeed(300, 7);

            }

            //area 2 -- 330 degree.
            if (collPointX >= paddleStart + areaLength && collPointX < paddleStart + areaLength * 2) {
                return Velocity.fromAngleAndSpeed(330, 7);

            }

            //area 3 -- 0 degree.
            if (collPointX >= paddleStart + areaLength * 2 && collPointX < paddleStart + areaLength * 3) {
                return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * -1);

            }

            //area 4 -- 30 degree.
            if (collPointX >= paddleStart + areaLength * 3 && collPointX < paddleStart + areaLength * 4) {
                return Velocity.fromAngleAndSpeed(30, 7);

            }
            //area 5 -- 60 degree.
            if (collPointX >= paddleStart + areaLength * 4 && collPointX <= paddleStart + this.width) {
                return Velocity.fromAngleAndSpeed(60, 7);

            }
        }
        return null;


    }

    /**
     * Add this paddle to the game.
     * Add to collidable and sprite collection.
     *
     * @param g game to add paddle.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
