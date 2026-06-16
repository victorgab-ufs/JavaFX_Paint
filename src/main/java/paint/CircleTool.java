package paint;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

public class CircleTool extends Tool{

    // pontos iniciais do desenho (x e y), comprimento e altura do círculo/elipse
    private double x, y, width, height;

    // apenas utilizamos o construtor da classe mãe
    public CircleTool(PaintModel model, PaintController paintController){
        super(model, paintController);
    }

    @Override
    public void onMouseReleased(MouseEvent e){
        // ao soltar o mouse criamos um círculo com ponto inicial (x, y) e cor definidos
        // altura e comprimento são obtidos durante o ato de arrastar o mouse
        Circle newCircle = new Circle(new Point2D(x, y), width, height, paintController.selectedColor());
        // esse novo círculo é adicionado à lista duplamente encadeada
        model.addFigure(newCircle);
        // figuras são redesenhadas
        paintController.redraw();
    }

    @Override
    public void onMouseDragged(MouseEvent e){
        // redesenhamos as figuras (deve ser atualizado porque estamos "movimentando" a nova figura)
        paintController.redraw();
        // verificamos o x mais a esquerda e o y mais a direita (iniciais)
        x = Math.min(xStart, e.getX());
        y = Math.min(yStart, e.getY());
        // com as diferenças dos x e y, conseguimos saber a largura e altura (essenciais para o método do javafx utilizado no método draw da classe Rectangle)
        width = Math.abs(e.getX() - xStart);
        height = Math.abs(e.getY() - yStart);
        // selecionamos a cor da borda da figura (setStroke pertence ao javafx)
        gc.setStroke(paintController.selectedColor());
        // aplicamos essa borda a um círculo/elipse com ponto inicial e dimensões previamente calculadas
        gc.strokeOval(x, y, width, height);
    }
}