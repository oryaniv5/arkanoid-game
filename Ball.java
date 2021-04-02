//ID:205444805

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The class of ball contain point of the centet on coordinate system,
 * radios color and velocity.
 * the class has some basic method, and method to use gui, like moving and drawing.
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment game;

    /**
     * constructor.
     *
     * @param center Point
     * @param r      radios of ball.
     * @param color  of ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * constructor.
     *
     * @param x     value of center point.
     * @param y     value of center point.
     * @param r     rodios of ball.
     * @param color of ball.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    /**
     * basic get method.
     *
     * @return x value of center point.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * basic get method.
     *
     * @return y value of center point.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * basic get method, calculate the size radios*2.
     *
     * @return radio value.
     */
    public int getSize() {
        return this.r * 2;
    }

    /**
     * basic get method.
     *
     * @return color of ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * basic get method.
     *
     * @return velocity of ball.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * basic set method.
     *
     * @param vel velocity to set.
     */
    public void setVelocity(Velocity vel) {
        this.v = vel;
    }

    /**
     * basic set method.
     *
     * @param dx to set in dx velocity
     * @param dy to set in dy velocity
     */
    public void setVelocity(double dx, double dy) {
        Velocity vel = new Velocity(dx, dy);
        this.v = vel;
    }

    /**
     * move the ball one step.
     * if found in the line movment collision point change velocity.
     */
    public void moveOneStep() {

        //create the line movement of the ball.
        Line l = new Line(this.center,
                new Point(this.center.getX() + this.v.getDx(), this.center.getY() + this.v.getDy()));

        //get collision point info.
        CollisionInfo info = this.game.getClosestCollision(l);

        //if found collision point change velocity before change ball spot.
        if (info != null) {
//                info.collisionPoint().printPoint();
//                info.collisionObject().getCollisionRectangle().getUpperLeft().printPoint();
            this.v = info.collisionObject().hit(this, info.collisionPoint(), this.v);
//                this.moveOneStep();
            Line l2 = new Line(this.center,
                    new Point(this.center.getX() + this.v.getDx(), this.center.getY() + this.v.getDy()));
            CollisionInfo info2 = this.game.getClosestCollision(l2);
            if (info2 != null) {
                this.v = info2.collisionObject().hit(this, info2.collisionPoint(), this.v);

            }
        }
        //change ball spot.
        this.center = this.getVelocity().applyToPoint(this.center);
    }


    /**
     * Method draw the ball on the given DrawSurface.
     *
     * @param surface to fraw on.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        int x = this.getX();
        int y = this.getY();

        //draw ball frame.
        surface.setColor(Color.BLACK);
        surface.drawCircle(x, y, this.r * 2);

        //using current ball value and fillCircle to draw our ball.
        surface.setColor(this.color);
        surface.fillCircle(x, y, (this.r - 1) * 2);
    }

    /**
     * Basic get method.
     *
     * @return game in ball.
     */
    public GameEnvironment getGame() {
        return this.game;
    }

    /**
     * Basic set method.
     *
     * @param newGame to put the ball.
     */
    public void setGame(GameEnvironment newGame) {
        this.game = newGame;
    }

    /**
     * move the ball when tin=me ends.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * add the ball into given game.
     *
     * @param g game to add the ball.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * remove ball from given game.
     *
     * @param gameLevelToRemoveFrom to remove from.
     */
    public void removeFromGame(GameLevel gameLevelToRemoveFrom) {
        gameLevelToRemoveFrom.removeSprite(this);

    }
}