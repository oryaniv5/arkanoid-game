// ID: 205444805

/**
 * The class save values of linear equatin of ine.
 * the equation format is y=ax+b.
 * slope contain the a and bValue contain the b.
 * use basic math to found the value of a and b, and intersection point with another linear equation.
 */
public class LinearEquation {

    //value of line equation, in the form: y=ax+b.
    private double slope, bValue;

    /**
     * constructor, using math to found slope and bvalue.
     *
     * @param line to create his linear equation.
     */
    public LinearEquation(Line line) {
        Point start = line.getStart();
        Point end = line.getEnd();

        //found slope using the equation m=(y1-y2)/(x1-x2).
        this.slope = (start.getY() - end.getY()) / (start.getX() - end.getX());

        //found bValue using slope and one point values, in this way:
        //slope*x+bvalue=y => bValue = -slope*x+y.
        this.bValue = ((0 - this.slope) * start.getX()) + start.getY();
    }

    /**
     * basic get methos.
     *
     * @return value of slope.
     */
    public double getSlope() {
        return this.slope;
    }

    /**
     * basic get methos.
     *
     * @return value of bValue.
     */
    public double getBvalue() {
        return this.bValue;
    }

    /**
     * Method decide if 2 linear equation are the same.
     *
     * @param other Line.
     * @return true if the same.
     */
    public boolean equals(LinearEquation other) {

        //chheck if slope and bvalue are the same.
        if ((this.slope == other.slope) && (this.bValue == other.bValue)) {
            return true;
        }
        return false;
    }

    /**
     * method found intersection pf 2 lines.
     *
     * @param other linear equation
     * @return point of intersection.
     */
    public Point intersectPoint(LinearEquation other) {

        //using:
        //a1x+b1=a2x+b2 => x= (b2-b1)/(a1-a2).
        double x = (other.bValue - this.bValue) / (this.slope - other.slope);

        //using y=ax+b
        double y = this.slope * x + this.bValue;
        Point intersection = new Point(x, y);
        return intersection;


    }

    /**
     * Method get x value and return the value of y in using current linear equation.
     * using y= ax+b.
     *
     * @param x value.
     * @return y value if put x in equation.
     */
    public double getXgiveY(double x) {
        return slope * x + bValue;
    }

    /**
     * Method print the equation in format y=ax+b.
     */
    public void printEquation() {
        System.out.println("y=" + this.slope + "x" + '+' + this.bValue);
    }
}
