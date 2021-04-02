// ID: 205444805

/**
 * Point in coordinate system.
 * the class support basic method; set\get xy,
 * and few method for compare to another point, like distance and equals.
 * Every point has x and y double variable.
 */
public class Point {

    private double x, y;


    /**
     * constructor using input of x and y.
     *
     * @param x value.
     * @param y value.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * return the distance of this point to the other point.
     *
     * @param other point.
     * @return the distance between points, using pythagorean theorem.
     */
    public double distance(Point other) {
        //find the distance between the x and y coordinates.
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        //using pythagorean theorem to find and return the distance.
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * decide if 2 points are tha same, by compare the x and the y values.
     *
     * @param other point.
     * @return true is the points are equal, false otherwise.
     */
    public boolean equals(Point other) {

        //if x and y values are the same, return true
        if ((this.x == other.x) && (this.y == other.y)) {
            return true;
        }
        //otherwise, return false.
        return false;
    }

    /**
     * print point value, held with debug.
     */
    public void printPoint() {
        System.out.println("x value: " + this.x + " y value: " + this.y);
    }

    /**
     * the method give the value of x.
     *
     * @return x value.
     */
    public double getX() {
        return this.x;
    }

    /**
     * the method give the value of y.
     *
     * @return y value.
     */
    public double getY() {
        return this.y;
    }
}