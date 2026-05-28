package paint;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Figure {

    private boolean selected = false;
    protected Color color;
    protected Point2D start;

    public Figure(Color color, Point2D start){
        this.color = color;
        this.start = start;
    }

    public boolean isSelected() { return selected; }

    public void setSelected(boolean selected){ this.selected = selected; }

    public void setColor(Color color) { this.color = color; }

    public void drawSelection(GraphicsContext gc){

        if(!selected) return;

        double x1 = getX1();
        double y1 = getY1();
        double x2 = getX2();
        double y2 = getY2();
        double squareSize = 10.0;
        gc.setStroke(Color.GRAY);

        if (color.equals(Color.GRAY)) gc.setStroke(Color.BLACK);

        gc.strokeRect(x1 - squareSize/2, y1 - squareSize/2, squareSize, squareSize);
        gc.strokeRect(x2 - squareSize/2, y1 - squareSize/2, squareSize, squareSize);
        gc.strokeRect(x1 - squareSize/2, y2 - squareSize/2, squareSize, squareSize);
        gc.strokeRect(x2 - squareSize/2, y2 - squareSize/2, squareSize, squareSize);

    }

    public void move(double movX, double movY){
        start = new Point2D(start.getX() + movX, start.getY() + movY);
    }

    public abstract boolean contains(Point2D clickPoint);
    public abstract double getX1();
    public abstract double getY1();
    public abstract double getX2();
    public abstract double getY2();

    protected abstract void draw(GraphicsContext gc);

}
