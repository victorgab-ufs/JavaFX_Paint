package paint;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

public class LineTool extends Tool {

    public LineTool(PaintModel model, PaintController paintController) {
        super(model, paintController);
    }

    @Override
    public void onMouseReleased(MouseEvent e) {
        Line newLine = new Line(new Point2D(xStart, yStart), new Point2D(e.getX(), e.getY()), paintController.selectedColor());
        model.addFigure(newLine);  
        paintController.redraw();
    }

    @Override
    public void onMouseDragged(MouseEvent e) {
        paintController.redraw();
        gc.setStroke(paintController.selectedColor());
        gc.setLineDashes(5);
        gc.setStroke(paintController.selectedColor());
        gc.strokeLine(xStart, yStart, e.getX(), e.getY());
        gc.setLineDashes(null);
    }
}
