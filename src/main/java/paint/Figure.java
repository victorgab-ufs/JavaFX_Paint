package paint;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Figure {

    // atributo que mostra caso a figura esteja selecionada
    private boolean selected = false;
    // atributos para cor e ponto inicial das figuras
    protected Color color;
    protected Point2D start;

    // construtor que cria figura com ponto inicial e cor
    public Figure(Color color, Point2D start){
        this.color = color;
        this.start = start;
    }

    // para sabermos se uma figura está selecionada retornamos o boolean que guarda essa informação
    public boolean isSelected() { return selected; }

    // método que atua como um setter, nos permitindo controlar se a figura está ou não selecionada
    public void setSelected(boolean selected){ this.selected = selected; }

    // método para modificação da cor da figura
    public void setColor(Color color) { this.color = color; }

    // método que desenha os quadradinhos nas bordas da figura selecionada
    public void drawSelection(GraphicsContext gc){
        // não estando selecionada apenas saímos
        if(!selected) return;

        // pegamos as coordenadas (x, y) onde a figura começa e termina
        double x1 = getX1();
        double y1 = getY1();
        double x2 = getX2();
        double y2 = getY2();
        // tamanho padronizado para o quadradinho (cinza por padrão)
        double squareSize = 10.0;
        gc.setStroke(Color.GRAY);

        // se a figura for cinza mudamos a cor do quadradinho para que ele não fique invisível
        if (color.equals(Color.GRAY)) gc.setStroke(Color.BLACK);

        // métodos do javafx para desenhar contorno dos quadradinhos
        gc.strokeRect(x1 - squareSize/2, y1 - squareSize/2, squareSize, squareSize);
        gc.strokeRect(x2 - squareSize/2, y1 - squareSize/2, squareSize, squareSize);
        gc.strokeRect(x1 - squareSize/2, y2 - squareSize/2, squareSize, squareSize);
        gc.strokeRect(x2 - squareSize/2, y2 - squareSize/2, squareSize, squareSize);

    }

    // função que muda o ponto inicial da figura através do ponto anterior mais a movimentação executada
    public void move(double movX, double movY){
        start = new Point2D(start.getX() + movX, start.getY() + movY);
    }

    // métodos abstratos (funcionando de formas diferentes para as classes filhas, as diferentes figuras)
    public abstract boolean contains(Point2D clickPoint);
    public abstract double getX1();
    public abstract double getY1();
    public abstract double getX2();
    public abstract double getY2();

    protected abstract void draw(GraphicsContext gc);

}
