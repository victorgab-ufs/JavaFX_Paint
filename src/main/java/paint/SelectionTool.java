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

    // construtor (apenas utiliza o construtor da classe mãe)
    public SelectionTool(PaintModel model, PaintController paintController){
        super(model, paintController);
    }

    @Override
    public void onMousePressed(MouseEvent e){
        // criamos um ponto (x, y) usando a classe do javafx através do que coletamos no clique do mouse
        Point2D click = new Point2D(e.getX(), e.getY());

        // modifica os valores dos atributos dos pontos para que eles tenham o valor do último clique
        lastX = e.getX();
        lastY = e.getY();
        // deixamos em null, a figura selecionada será buscada por um loop
        selectedFigure = null;

        // primeiro nó da lista duplamente encadeada com as figuras
        Node<Figure> aux = model.getFirstNode();

        // busca em lista duplamente encadeada (percorremos até que o nó seja nulo)
        while (aux != null){
            // removemos a seleção de todas figuras
            aux.valor.setSelected(false);
            aux = aux.nextNode;
        }

        // ultima figura desenhada
        aux = model.getLastNode();

        // busca em lista duplamente encadeada (do fim para o começo)
        while (aux != null){
            // buscamos se houve um clique em alguma figura
           if (aux.valor.contains(click)){
               // para aquela figura que foi clicada, mudamos o estado selected dela para true
               aux.valor.setSelected(true);
               // o seu valor é passado para o atributo da classe SelectionTool e saímos do loop
               selectedFigure = aux.valor;
               break;
           }
           aux = aux.previousNode;
        }

        // chamamos o método redraw para redesenhar todas as figuras no Canvas
        paintController.redraw();
    }

    // quando arrastamos o mouse para esse caso, movemos a figura selecionada de posição
    @Override
    public void onMouseDragged(MouseEvent e){
        // se houver alguma figura selecionada
        if (selectedFigure != null){
            // calculamos a nova posição através da diferença entre o fim do "arrasto" e de onde a figura começou a ser movida
            double movX = e.getX() - lastX;
            double movY = e.getY() - lastY;
            // alteramos o ponto onde a figura começa lá na classe Figure
            selectedFigure.move(movX, movY);
            // alteramos os valores onde a figura termina aqui nessa classe
            lastX = e.getX();
            lastY = e.getY();
            // chamamos o método redraw para redesenhar todas as figuras no Canvas
            paintController.redraw();
        }
    }

    // não é necessário fazer nada ao soltar o mouse nesse caso
    @Override
    public void onMouseReleased(MouseEvent e){ }
}
