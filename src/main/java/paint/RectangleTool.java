package paint;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

public class RectangleTool extends Tool {
    // atributos que guardam largura e altura da figura
    private double width;
    private double height;
    // posições em que o retângulo começa
    double x, y;

    // construtor que aproveita o construtor da classe mãe (Tool)
    public RectangleTool(PaintModel model, PaintController paintController) {
        super(model, paintController);
    }

    // ao soltar o mouse...
    @Override
    public void onMouseReleased(MouseEvent e) {
        // criamos uma figura da classe Rectangle
        // Point2D é o começo do desenho do retângulo (objeto com as coordenadas x e y), pelo paintController enviamos também a cor desejada
        Rectangle newRect = new Rectangle(new Point2D(x, y), width, height, paintController.selectedColor());
        // adicionamos essa nova figura à lista duplamente encadeada no modelo
        model.addFigure(newRect);
        // redesenhamos tudo
        paintController.redraw();     
    }

    // ao arrastar o mouse...
    @Override
    public void onMouseDragged(MouseEvent e) {
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
        // aplicamos essa borda a um retângulo com ponto inicial e dimensões previamente calculadas
        gc.strokeRect(x, y, width, height);
    }
}
