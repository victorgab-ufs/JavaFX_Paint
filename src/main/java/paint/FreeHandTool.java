package paint;

import javafx.geometry.Point2D;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;

public class FreeHandTool extends Tool{

    private FreeHand newFreeHand;

    public FreeHandTool(PaintModel model, PaintController paintController){
        super(model, paintController);
    }

    @Override
    public void onMousePressed(MouseEvent e){
        xStart = e.getX();
        yStart = e.getY();
        newFreeHand = new FreeHand(new Point2D(xStart, yStart), paintController.selectedColor());

        newFreeHand.addPoint(new Point2D(xStart, yStart));
        model.addFigure(newFreeHand);
    }

    @Override
    public void onMouseReleased(MouseEvent e){
        paintController.redraw();
    }

    @Override
    public void onMouseDragged(MouseEvent e){
        newFreeHand.addPoint(new Point2D(e.getX(), e.getY()));
        paintController.redraw();
    }
}
