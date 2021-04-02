// ID: 205444805

import java.util.List;

/**
 * Line in coordinate system.
 * the class support few basic metohod, and few methos for compare with other lines.
 */
public class Line {

    //The line has start and end point.
    private Point start, end;

    //Every line have linear Equation.
    private LinearEquation le;

    /**
     * Constructor create new line with start and end point.
     * Also calculate the linear equation value.
     *
     * @param start point.
     * @param end   point.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        //using the line created to create the linear equation.
        this.le = new LinearEquation(this);
    }

    /**
     * Constructor constructor create new line with start and end point, using x and y values.
     * Also calculate the linear equation value.
     *
     * @param x1 value.
     * @param y1 value.
     * @param x2 value.
     * @param y2 value.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        //using the line created to create the linear equation.
        this.le = new LinearEquation(this);
    }

    /**
     * Get the start point of tha line.
     *
     * @return start point.
     */
    public Point getStart() {
        return start;
    }

    /**
     * Get the end point of tha line.
     *
     * @return end point.
     */
    public Point getEnd() {
        return end;
    }

    /**
     * find the length of the line using point method distance.
     *
     * @return double variable contain the length of the line.
     */
    public double length() {
        //using point distance method to find and return the length of line.
        return start.distance(end);
    }

    /**
     * The method calculate the the x and y value of the point in muddle of the line.
     *
     * @return the point in the middle of the line.
     */
    public Point middle() {

        //calculate the middle point values, using simple average equation.
        double xMiddle = (start.getX() + end.getX()) / 2;
        double yMiddle = (start.getY() + end.getY()) / 2;
        //create new point with middle point value, and return it.
        Point middle = new Point(xMiddle, yMiddle);
        return middle;
    }

    /**
     * basic get method.
     *
     * @return start point of the line.
     */
    public Point start() {
        return start;
    }

    /**
     * basic get method.
     *
     * @return end point of the line.
     */
    public Point end() {
        return end;
    }

    /**
     * Method check if some point is on the line.
     *
     * @param p point
     * @return true if the point on the line.
     */
    public boolean onLine(Point p) {
        if (p == null) {
            return false;
        }
        //get start and end point of this line.
        Point currentStart = this.start;
        Point currentEnd = this.end;

        //check if the distance between p to end and p to start
        // equal to line length.
        double epsilon = Math.pow(10, -12);
        if (Math.abs(currentStart.distance(p) + p.distance(currentEnd) - this.length()) < epsilon) {
            return true;
        }
        return false;
    }


    /**
     * Returns true if the lines intersect, false otherwise.
     *
     * @param other line
     * @return true if intersect.
     */
    public boolean isIntersecting(Line other) {
        Point intersection = this.intersectionWith(other);

        //if the point not null, return true.
        return intersection != null;
    }

    /**
     * Find from 2 given lines there intersection point.
     *
     * @param other line.
     * @return rtue of line intersect, null otherwise.
     */
    public Point intersectionWith(Line other) {
        Point intersection;
        LinearEquation otherLe = new LinearEquation(other);

        //using LinearEquation class to find intersection point.
        intersection = this.le.intersectPoint(otherLe);

        //if the line parallel to the y's, using another method to find the intersect point.
        if (this.start.getX() == this.end.getX()) {

            //put the x value of current line to find y value.
            double y = otherLe.getXgiveY(this.start.getX());
            intersection = new Point(this.start.getX(), y);
        }
        if (other.start.getX() == other.end.getX()) {

            //put the x value of other line to find y value.
            double y = this.le.getXgiveY(other.start.getX());
            intersection = new Point(other.start.getX(), y);
        }

        //check if intersection point on both lines
        if (this.onLine(intersection) && other.onLine(intersection)) {
            return intersection;
        }
        return null;

    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rect to found intersection points.
     * @return the point intersect close to start point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {

        //found all intersection point.
        List<Point> l = rect.intersectionPoints(this);

        //if no intersection point return null.
        if (l.size() == 0) {
            return null;
        }

        //of ony 1 intersection point return her.
        if (l.size() == 1) {
            return l.get(0);
        }

        //decide wich point is the most close to start point.
        Line l0 = new Line(this.start, l.get(0));
        Line l1 = new Line(this.start, l.get(1));
        if (l0.length() < l1.length()) {
            return l.get(0);
        }
        return l.get(1);
    }

    /**
     * get list of points and decide witch point is the
     * closet to start point.
     *
     * @param list of points.
     * @return the most close point to start.
     */
    public CollisionInfo getClosestFromList(List<CollisionInfo> list) {

        //if list size is 0 return null.
        if (list.size() == 0) {
            return null;
        }

        //define closet point as the first in the list.
        CollisionInfo closetInfo = list.get(0);

        //loop go all over the list and decide if one of the point is more close.
        // using line length to decide.
        for (int i = 1; i < list.size(); i++) {

            //define close line by now closet point.
            // also define the line with current point, to compare between.
            Line shortLine = new Line(this.start, closetInfo.collisionPoint());
            Line currentLine = new Line(this.start, list.get(i).collisionPoint());
            if (currentLine.length() < shortLine.length()) {

                //if current line is shorter, put current point as the most close.
                closetInfo = list.get(i);
            }
        }
        return closetInfo;
    }


    /**
     * Find if the 2 lines are the same, using point method equals.
     *
     * @param other line to compare.
     * @return true if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        //check if start and end equale, or if start equale end and end equale start.
        if ((this.start.equals(other.start)) && (this.end.equals(other.end))
                || (this.start.equals(other.end)) && (this.end.equals(other.start))) {
            return true;
        }
        return false;
    }
}
