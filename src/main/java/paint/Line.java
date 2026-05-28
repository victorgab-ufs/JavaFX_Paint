package paint;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line extends Figure {

    private Point2D end;

    public Line(Point2D start, Point2D end, Color color) {
        super(color, start);
        this.end = end;
    }

    public Point2D getEnd() {
        return end;
    }

    @Override
    public boolean contains(Point2D clickPoint){

        double range = 5.0;

        if (clickPoint.getX() < getX1() - range || clickPoint.getX() > getX2() + range ||
            clickPoint.getY() < getY1() - range || clickPoint.getY() > getY2() + range){

            return false;
        }

        double area = Math.abs(
                (end.getX() - start.getX()) * (start.getY() - clickPoint.getY()) -
                (start.getX() - clickPoint.getX()) * (end.getY() - start.getY())
        );

        double base = Math.hypot(end.getX() - start.getX(), end.getY() - start.getY());

        if (base == 0) { return start.distance(clickPoint) <= range; }

        return (area/base) <= range;
    }

    @Override
    public double getX1(){ return Math.min(start.getX(), end.getX()); }

    @Override
    public double getY1() { return Math.min(start.getY(), end.getY()); }

    @Override
    public double getX2(){ return Math.max(start.getX(), end.getX()); }

    @Override
    public double getY2(){ return Math.max(start.getY(), end.getY()); }

    @Override
    public void move(double movX, double movY){
        start = new Point2D(start.getX() + movX, start.getY() + movY);
        end = new Point2D(end.getX() + movX, end.getY() + movY);
    }

    @Override
    protected void draw(GraphicsContext gc) {
        gc.setStroke(color);
        gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
    }
}
