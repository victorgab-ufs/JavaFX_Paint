package paint;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class SelectionTool extends Tool{

    public double lastX, lastY;
    private Figure selectedFigure = null;

    public SelectionTool(PaintModel model, PaintController paintController){ super(model, paintController); }

    @Override
    public void onMousePressed(MouseEvent e){

        Point2D click = new Point2D(e.getX(), e.getY());

        lastX = e.getX();
        lastY = e.getY();
        selectedFigure = null;

        Node<Figure> aux = model.getFirstNode();

        while (aux != null){
            aux.valor.setSelected(false);
            aux = aux.nextNode;
        }

        aux = model.getLastNode();

        while (aux != null){
           if (aux.valor.contains(click)){
               aux.valor.setSelected(true);
               selectedFigure = aux.valor;
               break;
           }
           aux = aux.previousNode;
        }

        paintController.redraw();
    }

    @Override
    public void onMouseDragged(MouseEvent e){

        if (selectedFigure != null){
            double movX = e.getX() - lastX;
            double movY = e.getY() - lastY;
            selectedFigure.move(movX, movY);
            lastX = e.getX();
            lastY = e.getY();
            paintController.redraw();
        }
    }

    @Override
    public void onMouseReleased(MouseEvent e){ }
}
