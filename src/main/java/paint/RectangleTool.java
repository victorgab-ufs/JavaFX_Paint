package paint;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
public class RectangleTool extends Tool {
    private double width;
    private double height;

    double x, y;

    public RectangleTool(PaintModel model, PaintController paintController) {
        super(model, paintController);
    }

    @Override
    public void onMouseReleased(MouseEvent e) {
        Rectangle newRect = new Rectangle(new Point2D(x, y), width, height, paintController.selectedColor());
        model.addFigure(newRect);   
        paintController.redraw();     
    }

    @Override
    public void onMouseDragged(MouseEvent e) {
        paintController.redraw();
        x = Math.min(xStart, e.getX());
        y = Math.min(yStart, e.getY());
        width = Math.abs(e.getX() - xStart);
        height = Math.abs(e.getY() - yStart);
        gc.setStroke(paintController.selectedColor());
        gc.strokeRect(x, y, width, height);
    }
}
