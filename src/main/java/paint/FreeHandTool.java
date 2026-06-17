package paint;

import javafx.geometry.Point2D;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;

public class FreeHandTool extends Tool{

    // atributo que guarda a figura de mão livre que estamos desenhando no momento
    private FreeHand newFreeHand;

    // construtor utilizando o da classe mãe (Tool)
    public FreeHandTool(PaintModel model, PaintController paintController){
        super(model, paintController);
    }

    @Override
    public void onMousePressed(MouseEvent e){
        // salvamos as coordenadas iniciais ao clicar
        xStart = e.getX();
        yStart = e.getY();

        // criamos uma figura do tipo FreeHand (mão livre) com o ponto inicial e a cor selecionada
        newFreeHand = new FreeHand(new Point2D(xStart, yStart), paintController.selectedColor());

        // adicionamos esse primeiro ponto à lista duplamente encadeada interna da classe FreeHand
        newFreeHand.addPoint(new Point2D(xStart, yStart));

        // adicionamos a figura completa à lista duplamente encadeada principal do modelo
        model.addFigure(newFreeHand);
    }

    @Override
    public void onMouseReleased(MouseEvent e){
        // ao soltar o mouse, apenas garantimos que a tela seja redesenhada com a forma final
        paintController.redraw();
    }

    @Override
    public void onMouseDragged(MouseEvent e){
        // enquanto arrastamos o mouse, capturamos as novas coordenadas (x, y)
        // vamos adicionando os pontos à lista da figura
        newFreeHand.addPoint(new Point2D(e.getX(), e.getY()));

        // redesenhamos a tela para que o usuário veja o traço se formando em tempo real
        paintController.redraw();
    }
}