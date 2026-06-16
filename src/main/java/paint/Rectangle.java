package paint;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Figure {

    // atributos que armazenam largura e altura do retângulo
    private double width, height;

    // construtor que envia cor e ponto inicial para a classe Figure, além de inicializar comprimento e altura
    public Rectangle(Point2D start, double width, double height, Color color){
        super(color, start);
        this.width = width;
        this.height = height;
    }

    //verifica se o retângulo está na zona que recebeu clique do mouse durante o Selection
    @Override
    public boolean contains(Point2D clickPoint){
        // retorna true caso o clique esteja no retângulo (entre os 2x e os 2y, incluindo os próprios)
        return clickPoint.getX() >= start.getX() && clickPoint.getX() <= getX2() &&
               clickPoint.getY() >= start.getY() && clickPoint.getY() <= getY2();

    }

    // getters para as coordenadas iniciais (start é protected na classe mãe, por isso conseguimos acessar)
    @Override
    public double getX1(){ return start.getX(); }

    @Override
    public double getY1() { return start.getY(); }

    // calculo das coordenadas finais (diagonal oposta)
    @Override
    public double getX2(){ return start.getX() + width; }

    @Override
    public double getY2(){ return start.getY() + height; }

    // método draw abstraído em Figure especifico para retângulos
    @Override
    protected void draw(GraphicsContext gc) {
        // selecionamos o preenchimento do retângulo (setFill do javafx)
        gc.setFill(color);
        // preenchemos o retângulo através das coordenadas iniciais (esquerda e cima) e comprimento/altura
        gc.fillRect(start.getX(), start.getY(), width, height);
    }
}
