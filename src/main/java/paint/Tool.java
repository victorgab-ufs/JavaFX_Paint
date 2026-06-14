package paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

/*
Classe abstrata que define o contrato para todas as ferramentas de desenho
Permite ao PaintController delegar os eventos de mouse sem necessidade de if/else
*/
public abstract class Tool {

    // Modelo -> Usado para acessar a pilha e a lista duplamente encadeada
    protected PaintModel model;

    // Controlador -> Usado para atualizar a tela (redraw) e acessar cores
    protected PaintController paintController;

    // classe do JavaFX usada para renderizar as formas no Canvas
    // gc -> abreviação de graphics context
    protected GraphicsContext gc;

    // construtor da classe Tool inicializando modelo, controlador e GraphicsContext
    public Tool(PaintModel model, PaintController paintController) {
        this.model = model;
        this.paintController = paintController;
        gc = paintController.getGraphicsContext();
    }

    // coordenadas iniciais do clique do mouse
    protected double xStart;
    protected double yStart;

    // método para guardar posição inicial do x e do y ao clicar
    // e -> evento do mouse
    public void onMousePressed(MouseEvent e) {
        xStart = e.getX();
        yStart = e.getY();
    }

    // método abstrato para quando soltarmos o mouse
    public abstract void onMouseReleased(MouseEvent e);

    // método abstrato para quando estivermos movendo o mouse enquanto pressionamos
    public abstract void onMouseDragged(MouseEvent e);
}
