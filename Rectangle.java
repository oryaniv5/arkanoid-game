// ID: 205444805

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * The class represent rectangle on coordinate system.
 * using the upper left point and his height and width.
 * include basic method; set ,get and draw. and method used to found
 * rectangle intersection point with given line.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft point.
     * @param width     of rectangle.
     * @param height    of rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {

        //using input to initial the rectangle.
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Return a (possibly empty) List of intersection points
     * with the specified line.
     *
     * @param line to found the intersection point with.
     * @return list of intersection point with the line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {

        //create list.
        List<Point> listOfPPoints = new LinkedList<>();

        // found rectangle vertices.
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point downLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Point downRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);

        //create arr to save rectangle edges.
        Line[] arr = new Line[4];

        //create left edge  in rectangle.
        arr[0] = new Line(this.upperLeft, downLeft);

        //create right edge in rectangle.
        arr[1] = new Line(upperRight, downRight);

        //create up edge in rectangle.
        arr[2] = new Line(this.upperLeft, upperRight);

        //create down edge in rectangle.
        arr[3] = new Line(downLeft, downRight);

        //if found 2 intersection point, or go all over the array,
        // break loop.
        int sumOfIntersection = 0, i = 0;
        while (sumOfIntersection < 2 && i < 4) {

            //try to find the intersect point with any rectangle edge.
            Point p = line.intersectionWith(arr[i]);

            //if found intersection point add to list.
            // also add 1 to sum of intersection counter.
            if (p != null) {
                listOfPPoints.add(p);
                sumOfIntersection++;
            }

            //add 1 to i when loop ends.
            i++;
        }

        //return the list of intersection.
        return listOfPPoints;
    }

    /**
     * Basic get method.
     *
     * @return width value.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Basic get method.
     *
     * @return height value.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Basic get method.
     *
     * @return upper left point.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Basic set method.
     *
     * @param p point to set in upperLeft field.
     */
    public void setUpperLeft(Point p) {
        this.upperLeft = p;
    }


    /**
     * the method draw the rectangle on gui screen.
     *
     * @param surface to draw
     * @param color   of rectangle.
     */
    public void drawOn(DrawSurface surface, Color color) {

        //get value x and y of the rectangle starting point.
        double x = this.upperLeft.getX();
        double y = this.upperLeft.getY();

        //draw frame.
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) x, (int) y, (int) width, (int) height);


        //using current rectangle value and fillRectangle to draw our rectangle.
        surface.setColor(color);
        surface.fillRectangle((int) x + 1, (int) y + 1, (int) width - 1, (int) height - 1);
    }

}