package paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public abstract class Tool {

    protected PaintModel model;

    protected PaintController paintController;

    protected GraphicsContext gc;

    public Tool(PaintModel model, PaintController paintController) {
        this.model = model;
        this.paintController = paintController;
        gc = paintController.getGraphicsContext();
    }

    protected double xStart;
    protected double yStart;

    public void onMousePressed(MouseEvent e) {
        xStart = e.getX();
        yStart = e.getY();
    }

    public abstract void onMouseReleased(MouseEvent e);

    public abstract void onMouseDragged(MouseEvent e);
}
