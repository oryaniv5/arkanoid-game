//ID:205444805


/**
 * Class decide witch way the ball will go.
 * using dx and dy, adding to current values of the ball.
 * contain basic method and apply method to move the ball in the coordinate system.
 */
public class Velocity {

    private double dx, dy;

    /**
     * constructor to class.
     *
     * @param dx int.
     * @param dy int.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Basic set method.
     *
     * @param dxVal value.
     */
    public void setDx(double dxVal) {
        this.dx = dxVal;
    }

    /**
     * Basic set method.
     *
     * @param dyVal value.
     */
    public void setDy(double dyVal) {
        this.dy = dyVal;
    }

    /**
     * Basic get method.
     *
     * @return dx value.
     */
    public double getDx() {
        return dx;
    }

    /**
     * Basic get method.
     *
     * @return dy value.
     */
    public double getDy() {
        return dy;
    }

    /**
     * Take a point with position (x,y) and return a new point.
     * with position (x+dx, y+dy)
     *
     * @param p point.
     * @return new point value.
     */
    public Point applyToPoint(Point p) {

        //create new point in value (x+dx,y+dy).
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * Method get angle and speed and build new velocity, using math convert to dx and dy.
     *
     * @param angle int
     * @param speed int
     * @return new velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {

        //convert angle to radians.
        double radians = Math.toRadians(angle);
        // using:
        //sin(x) = dx\speed => dx = sin(x)*speed.
        //cos(x) = dx\speed => dx = cos(x)*speed.
        double dx = Math.sin(radians) * speed;
        double dy = Math.cos(radians) * speed * -1;

        //build new velocity.
        return new Velocity(dx, dy);
    }
}