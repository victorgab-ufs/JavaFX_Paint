package paint;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

public class CircleTool extends Tool{

    private double x, y, width, height;

    public CircleTool(PaintModel model, PaintController paintController){ super(model, paintController); }

    @Override
    public void onMouseReleased(MouseEvent e){
        Circle newCircle = new Circle(new Point2D(x, y), width, height, paintController.selectedColor());
         model.addFigure(newCircle);
         paintController.redraw();
    }

    @Override
    public void onMouseDragged(MouseEvent e){
        paintController.redraw();
        x = Math.min(xStart, e.getX());
        y = Math.min(yStart, e.getY());
        width = Math.abs(e.getX() - xStart);
        height = Math.abs(e.getY() - yStart);
        gc.setStroke(paintController.selectedColor());
        gc.strokeOval(x, y, width, height);
    }
}
