package paint;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

import java.util.List;

// classe para a ferramenta de seleção (note que estende Tool)
public class SelectionTool extends Tool{

    // atributos que guardam últimos valores de x e y
    public double lastX, lastY;
    // atributo que guarda a figura selecionada no momento (inicia em null)
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
