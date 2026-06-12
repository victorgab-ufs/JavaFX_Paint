package paint;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class PaintController implements Initializable {

    private PaintModel model = new PaintModel();

    private GraphicsContext gc;

    @FXML
    private Canvas canvas;
    
    @FXML
    private ColorPicker colorPicker;

    @FXML
    private ChoiceBox <String> modeChoice;

    private Tool[] tools;
    private int currentToolIndex = 0;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        gc = canvas.getGraphicsContext2D();
        tools = new Tool[] {new LineTool(model, this), new RectangleTool(model, this),
                            new CircleTool(model, this), new FreeHandTool(model, this),
                            new SelectionTool(model, this)};
        colorPicker.setValue(javafx.scene.paint.Color.BLACK);
        modeChoice.setValue("Linha");

        colorPicker.setOnAction(e -> {
            Figure selected = model.getSelectedFigure();
            if (selected != null){
                selected.setColor(colorPicker.getValue());
                redraw();
            }
        });

        canvas.setFocusTraversable(true);

        canvas.setOnKeyPressed(e -> {

            Figure selected = model.getSelectedFigure();
            if (selected != null) {
                switch (e.getCode()){
                    case DELETE:
                    case BACK_SPACE:
                        model.eraseFigure(selected);
                        break;
                    case UP:
                        model.toForth(selected);
                        break;
                    case DOWN:
                        model.toBack(selected);
                        break;
                    default:
                        break;
                }
                redraw();
            }
        });
    }


    @FXML
    public void onMousePressed(MouseEvent e) {
        canvas.requestFocus();
        currentToolIndex = modeChoice.getSelectionModel().getSelectedIndex();
        tools[currentToolIndex].onMousePressed(e);
    }

    @FXML
    public void onMouseReleased(MouseEvent e) { tools[currentToolIndex].onMouseReleased(e); }

    @FXML
    public void onMouseDragged(MouseEvent e) { tools[currentToolIndex].onMouseDragged(e); }

    public void clearCanvas() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void drawFigures() {
        Node<Figure> aux = model.getFirstNode();
        while (aux != null){
            aux.valor.draw(gc);
            if (aux.valor.isSelected())
                aux.valor.drawSelection(gc);
            aux = aux.nextNode;
        }
    }

    public void redraw() {
        clearCanvas();
        drawFigures();
    }

    public Color selectedColor() { return colorPicker.getValue(); }
    
    public GraphicsContext getGraphicsContext() { return gc; }
}
