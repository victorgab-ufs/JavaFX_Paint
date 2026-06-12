package paint;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FreeHand extends Figure{

    private DoublyLinkedList<Point2D> freeHandLine = new DoublyLinkedList<>("");

    public FreeHand(Point2D start, Color color){
        super(color, start);
    }

    public void addPoint(Point2D point){ freeHandLine.addEnd(point); }

    @Override
    public boolean contains(Point2D clickPoint){
        double range = 8.0;

        Node<Point2D> aux = freeHandLine.getFirstNode();

        while (aux != null){
            if (aux.valor.distance(clickPoint) <= range)
                return true;
            aux = aux.nextNode;
        }

        return false;
    }

    @Override
    public double getX1(){
        if (freeHandLine.isEmpty()) return start.getX();

        Node<Point2D> aux = freeHandLine.getFirstNode();
        double xMin = aux.valor.getX();

        while (aux != null){
            if (aux.valor.getX() < xMin)
                xMin = aux.valor.getX();
            aux = aux.nextNode;
        }

        return xMin;
    }

    @Override
    public double getY1() {

        if (freeHandLine.isEmpty()) return start.getY();

        Node<Point2D> aux = freeHandLine.getFirstNode();
        double yMin = aux.valor.getY();

        while (aux != null) {
            if (aux.valor.getY() < yMin)
                yMin = aux.valor.getY();
            aux = aux.nextNode;
        }

        return yMin;
    }

    @Override
    public double getX2(){
        if (freeHandLine.isEmpty()) return start.getX();

        Node<Point2D> aux = freeHandLine.getFirstNode();
        double xMax = aux.valor.getX();

        while (aux != null) {
            if (aux.valor.getX() > xMax)
                xMax = aux.valor.getX();
            aux = aux.nextNode;
        }

        return xMax;
    }

    @Override
    public double getY2() {
        if (freeHandLine.isEmpty()) return start.getY();

        Node<Point2D> aux = freeHandLine.getFirstNode();
        double yMax = aux.valor.getY();

        while (aux != null){
            if (aux.valor.getY() > yMax)
                yMax = aux.valor.getY();
            aux = aux.nextNode;
        }

        return yMax;
    }

    @Override
    public void move(double movX, double movY){
        start = new Point2D(start.getX() + movX, start.getY() + movY);

        Node<Point2D> aux = freeHandLine.getFirstNode();

        while (aux != null){
            aux.valor = new Point2D(aux.valor.getX()+movX,
                                    aux.valor.getY()+movY);
            aux = aux.nextNode;
        }
    }

    @Override
    public void draw(GraphicsContext gc){

        if (freeHandLine.isEmpty()) return;

        gc.setStroke(color);
        gc.beginPath();

        Node<Point2D> aux = freeHandLine.getFirstNode();
        gc.moveTo(aux.valor.getX(), aux.valor.getY());

        while (aux != null){
            gc.lineTo(aux.valor.getX(), aux.valor.getY());
            aux = aux.nextNode;
        }

        gc.stroke();
    }
}
