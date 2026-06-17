package paint;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

public class LineTool extends Tool {

    // construtor da ferramenta de linha usando a classe mãe
    public LineTool(PaintModel model, PaintController paintController) {
        super(model, paintController);
    }

    // ao soltar o mouse a linha definitiva é criada
    @Override
    public void onMouseReleased(MouseEvent e) {
        // instanciamos a linha com os pontos inicial (xStart, yStart) e final (onde o mouse foi solto)
        Line newLine = new Line(new Point2D(xStart, yStart), new Point2D(e.getX(), e.getY()), paintController.selectedColor());
        // adicionamos a linha à lista duplamente encadeada de figuras do modelo
        model.addFigure(newLine);
        // redesenhamos o canvas
        paintController.redraw();
    }

    // durante o arrasto, criamos um efeito de "preview" da linha
    @Override
    public void onMouseDragged(MouseEvent e) {
        // limpamos e redesenhamos as figuras para o preview não deixar "rastros" na tela
        paintController.redraw();

        // definimos a cor do traço
        gc.setStroke(paintController.selectedColor());

        // aplicamos um efeito tracejado temporário usando ferramenta do javafx para indicar que é um preview
        gc.setLineDashes(5);

        // desenhamos a linha tracejada do ponto inicial até a posição atual do mouse
        gc.strokeLine(xStart, yStart, e.getX(), e.getY());

        // removemos o efeito tracejado (setando para null) para não afetar as figuras definitivas depois
        gc.setLineDashes(null);
    }
}