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

    // instância do nosso modelo que contém as listas encadeadas e pilhas
    private PaintModel model = new PaintModel();

    private GraphicsContext gc;

    @FXML
    private Canvas canvas;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private ChoiceBox <String> modeChoice;

    // vetor que armazena todas as ferramentas de desenho disponíveis
    private Tool[] tools;
    private int currentToolIndex = 0;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        gc = canvas.getGraphicsContext2D();

        // inicializamos as ferramentas passando a referência do modelo e deste controlador
        tools = new Tool[] {new LineTool(model, this), new RectangleTool(model, this),
                new CircleTool(model, this), new FreeHandTool(model, this),
                new SelectionTool(model, this)};

        colorPicker.setValue(javafx.scene.paint.Color.BLACK);
        modeChoice.setValue("Linha");

        // evento para alterar a cor de uma figura que já está selecionada
        colorPicker.setOnAction(e -> {
            Figure selected = model.getSelectedFigure();
            if (selected != null){
                selected.setColor(colorPicker.getValue());
                redraw();
            }
        });

        // essencial para que o canvas consiga "escutar" os atalhos de teclado
        canvas.setFocusTraversable(true);

        canvas.setOnKeyPressed(e -> {

            // verifica se a tecla Control (Windows) ou Command (Mac) está pressionada
            if (e.isControlDown() || e.isShortcutDown()) {
                if (e.getCode() == javafx.scene.input.KeyCode.Z) { // CTRL+Z (DESFAZER)
                    handleUndo(); // aciona a manipulação da pilha no modelo
                    e.consume(); // evita que o evento se propague
                    return;
                }
                if (e.getCode() == javafx.scene.input.KeyCode.Y) { // CTRL+Y (REFAZER)
                    handleRedo();
                    e.consume();
                    return;
                }
            }

            // manipulação da figura selecionada (movimentação nas estruturas de dados)
            Figure selected = model.getSelectedFigure();
            if (selected != null) {
                switch (e.getCode()){
                    case DELETE:
                    case BACK_SPACE:
                        // remove a figura da lista duplamente encadeada
                        model.eraseFigure(selected);
                        break;
                    case UP:
                        // move o nó para o fim da lista (trazendo a figura para a frente na tela)
                        model.toForth(selected);
                        break;
                    case DOWN:
                        // move o nó para o início da lista (enviando a figura para trás)
                        model.toBack(selected);
                        break;
                    default:
                        break;
                }
                // atualiza a tela para refletir a mudança nas listas
                redraw();
            }
        });
    }

    @FXML
    public void onMousePressed(MouseEvent e) {
        canvas.requestFocus(); // garante o foco no canvas para não perdermos os atalhos de teclado

        // captura qual ferramenta está escolhida na interface gráfica (ChoiceBox)
        currentToolIndex = modeChoice.getSelectionModel().getSelectedIndex();

        // delegação polimórfica: passa o evento para a ferramenta correta sem necessidade de if/else
        tools[currentToolIndex].onMousePressed(e);
    }

    @FXML
    public void onMouseReleased(MouseEvent e) { tools[currentToolIndex].onMouseReleased(e); }

    @FXML
    public void onMouseDragged(MouseEvent e) { tools[currentToolIndex].onMouseDragged(e); }

    // método para pintar o canvas todo de branco, "limpando" a tela
    public void clearCanvas() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void drawFigures() {
        // percorre a lista duplamente encadeada do início ao fim
        Node<Figure> aux = model.getFirstNode();
        while (aux != null){
            // renderiza a figura contida no nó atual da lista
            aux.valor.draw(gc);

            // se o status da figura for selecionado, desenha os quadradinhos de marcação
            if (aux.valor.isSelected())
                aux.valor.drawSelection(gc);

            // avança o ponteiro para o próximo nó
            aux = aux.nextNode;
        }
    }

    // método central de atualização visual (limpa tudo e desenha a lista novamente)
    public void redraw() {
        clearCanvas();
        drawFigures();
    }

    @FXML
    public void handleUndo() {
        // solicita ao modelo que desempilhe a última ação
        model.undo();
        redraw();
    }

    @FXML
    public void handleRedo() {
        // solicita ao modelo que recupere a ação da pilha de refazer
        model.redo();
        redraw();
    }

    // getter para saber qual cor o usuário selecionou na interface
    public Color selectedColor() { return colorPicker.getValue(); }

    public GraphicsContext getGraphicsContext() { return gc; }
}