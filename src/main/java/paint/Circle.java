package paint;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Figure{

    private double width, height;

    public Circle(Point2D start, double width, double height, Color color){
        super(color, start);
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean contains(Point2D clickPoint) {

        double centerX = start.getX() + (width / 2.0);
        double centerY = start.getY() + (height / 2.0);

        double radiusX = width / 2.0;
        double radiusY = height / 2.0;

        if (radiusX <= 0 || radiusY <= 0) return false;

        double ellipseX = Math.pow(clickPoint.getX() - centerX, 2) / Math.pow(radiusX, 2);
        double ellipseY = Math.pow(clickPoint.getY() - centerY, 2) / Math.pow(radiusY, 2);

        return (ellipseX + ellipseY) <= 1;
    }

    @Override
    public double getX1() { return start.getX(); }

    @Override
    public double getY1() { return start.getY(); }

    @Override
    public double getX2() { return start.getX() + width;}

    @Override
    public double getY2() { return start.getY() + height; }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillOval(start.getX(), start.getY(), width, height);
    }
}
