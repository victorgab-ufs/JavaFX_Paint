package paint;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line extends Figure {

    // ponto final da linha (o inicial 'start' é herdado da classe mãe Figure)
    private Point2D end;

    // construtor inicializando o começo, o fim e a cor
    public Line(Point2D start, Point2D end, Color color) {
        super(color, start);
        this.end = end;
    }

    // getter para o ponto final
    public Point2D getEnd() { return end; }

    // método para verificar se o clique do mouse ocorreu sobre a linha
    @Override
    public boolean contains(Point2D clickPoint){

        // range de tolerância para facilitar o clique na linha, já que ela é muito fina
        double range = 5.0;

        // se estiver fora dos limites x e y (somados ao range), já retornamos false direto
        if (clickPoint.getX() < getX1() - range || clickPoint.getX() > getX2() + range ||
                clickPoint.getY() < getY1() - range || clickPoint.getY() > getY2() + range){

            return false;
        }

        // cálculo da área do triângulo formado pelo ponto inicial, ponto final e o ponto do clique
        double area = Math.abs(
                (end.getX() - start.getX()) * (start.getY() - clickPoint.getY()) -
                        (start.getX() - clickPoint.getX()) * (end.getY() - start.getY())
        );

        // cálculo da base desse triângulo (que é exatamente o comprimento total da linha)
        double base = Math.hypot(end.getX() - start.getX(), end.getY() - start.getY());

        // caso a linha seja apenas um ponto, checamos a distância reta do clique até o ponto
        if (base == 0) { return start.distance(clickPoint) <= range; }

        // a altura do triângulo (área/base) nos dá a menor distância do ponto do clique até a reta
        // se essa distância for menor ou igual ao range, consideramos que a linha foi clicada
        return (area/base) <= range;
    }

    // métodos para descobrir os extremos da linha (usando Math.min e Math.max)
    @Override
    public double getX1(){ return Math.min(start.getX(), end.getX()); }

    @Override
    public double getY1() { return Math.min(start.getY(), end.getY()); }

    @Override
    public double getX2(){ return Math.max(start.getX(), end.getX()); }

    @Override
    public double getY2(){ return Math.max(start.getY(), end.getY()); }

    // movimentação da linha deslocando tanto o ponto inicial quanto o final
    @Override
    public void move(double movX, double movY){
        start = new Point2D(start.getX() + movX, start.getY() + movY);
        end = new Point2D(end.getX() + movX, end.getY() + movY);
    }

    @Override
    protected void draw(GraphicsContext gc) {
        // selecionamos a cor da linha
        gc.setStroke(color);
        // utilizamos o método nativo do javafx para desenhar uma linha do ponto inicial ao final
        gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
    }
}