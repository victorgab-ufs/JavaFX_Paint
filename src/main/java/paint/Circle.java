package paint;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Figure{

    // comprimento e altura do círculo (elas sendo diferentes temos uma elipse)
    private double width, height;

    // construtor que envia cor e ponto inicial para a classe Figure, além de inicializar comprimento e altura
    public Circle(Point2D start, double width, double height, Color color){
        super(color, start);
        this.width = width;
        this.height = height;
    }

    // método para checarmos se houve clique na região ocupada por um círculo
    @Override
    public boolean contains(Point2D clickPoint) {
        // achamos o centro do círculo através da soma das coordenadas iniciais com suas equivalentes do centro
        double centerX = start.getX() + (width / 2.0);
        double centerY = start.getY() + (height / 2.0);

        // calculo do raio para cada coordenada
        double radiusX = width / 2.0;
        double radiusY = height / 2.0;

        // tratamento para caso os raios não tivessem valores válidos
        if (radiusX <= 0 || radiusY <= 0) return false;

        // calculamos a distância de x e y até o ponto do clique
        double ellipseX = Math.pow(clickPoint.getX() - centerX, 2) / Math.pow(radiusX, 2);
        double ellipseY = Math.pow(clickPoint.getY() - centerY, 2) / Math.pow(radiusY, 2);

        // através da equação da elipse conferimos se a distância é válida
        return (ellipseX + ellipseY) <= 1;
    }

    // getters para as coordenadas iniciais (start é protected na classe mãe, por isso conseguimos acessar)
    @Override
    public double getX1() { return start.getX(); }

    @Override
    public double getY1() { return start.getY(); }

    // calculo das coordenadas finais (diagonal oposta, já que método do javafx
    //  cria o círculo/elipse dentro de um retângulo invisível)
    @Override
    public double getX2() { return start.getX() + width;}

    @Override
    public double getY2() { return start.getY() + height; }


    @Override
    public void draw(GraphicsContext gc) {
        // selecionamos a cor de preenchimento
        gc.setFill(color);
        // método para preenchimento de figuras ovais do javafx
        gc.fillOval(start.getX(), start.getY(), width, height);
    }
}
