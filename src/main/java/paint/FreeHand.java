package paint;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.*;

public class FreeHand extends Figure{

    private List<Point2D> freeHandLine = new ArrayList<>();

    public FreeHand(Point2D start, Color color){
        super(color, start);
    }

    public void addPoint(Point2D point){ freeHandLine.add(point); }

    @Override
    public boolean contains(Point2D clickPoint){

        double range = 8.0;

        for (Point2D p : freeHandLine){ if(p.distance(clickPoint) <= range) return true; }

        return false;
    }

    @Override
    public double getX1(){
        if (freeHandLine.isEmpty()) return start.getX();
        double xMin = freeHandLine.get(0).getX();

        for (Point2D p : freeHandLine){
            if (p.getX() < xMin) xMin = p.getX();
        }

        return xMin;
    }

    @Override
    public double getY1() {
        if (freeHandLine.isEmpty()) return start.getY();
        double yMin = freeHandLine.get(0).getY();

        for (Point2D p : freeHandLine){
            if (p.getY() < yMin) yMin = p.getY();
        }

        return yMin;
    }

    @Override
    public double getX2(){
        if (freeHandLine.isEmpty()) return start.getX();
        double xMax = freeHandLine.get(0).getX();

        for (Point2D p : freeHandLine){
            if (p.getX() > xMax) xMax = p.getX();
        }

        return xMax;
    }

    @Override
    public double getY2() {
        if (freeHandLine.isEmpty()) return start.getY();
        double yMax = freeHandLine.get(0).getY();

        for (Point2D p : freeHandLine){
            if (p.getY() > yMax) yMax = p.getY();
        }

        return yMax;
    }

    @Override
    public void move(double movX, double movY){
        start = new Point2D(start.getX() + movX, start.getY() + movY);
        int size = freeHandLine.size();

        for (int i = 0; i < size; i++){
            Point2D p = freeHandLine.get(i);
            freeHandLine.set(i, new Point2D(p.getX() + movX, p.getY() + movY));
        }
    }

    @Override
    public void draw(GraphicsContext gc){

        if (freeHandLine.isEmpty()) return;

        gc.setStroke(color);
        gc.beginPath();

        Point2D pStart = freeHandLine.get(0);
        gc.moveTo(pStart.getX(), pStart.getY());

        for (Point2D p : freeHandLine){
            gc.lineTo(p.getX(), p.getY());
        }

        gc.stroke();
    }
}
