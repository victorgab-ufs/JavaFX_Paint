package paint;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Figure {

    private double width, height;

    public Rectangle(Point2D start, double width, double height, Color color){
        super(color, start);
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean contains(Point2D clickPoint){
        return clickPoint.getX() >= start.getX() && clickPoint.getX() <= getX2() &&
               clickPoint.getY() >= start.getY() && clickPoint.getY() <= getY2();

    }

    @Override
    public double getX1(){ return start.getX(); }

    @Override
    public double getY1() { return start.getY(); }

    @Override
    public double getX2(){ return start.getX() + width; }

    @Override
    public double getY2(){ return start.getY() + height; }

    @Override
    protected void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(start.getX(), start.getY(), width, height);
    }
}
