package paint;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FreeHand extends Figure{

    // instanciamos uma lista duplamente encadeada por conta de que a ferramenta mão livre nada mais é que uma lista de pontos 2D
    private DoublyLinkedList<Point2D> freeHandLine = new DoublyLinkedList<>("");

    // utilizamos o construtor de figura
    public FreeHand(Point2D start, Color color){
        super(color, start);
    }

    // addPoint se utiliza da rápida inserção ao final da classe DoublyLinkedList
    public void addPoint(Point2D point){ freeHandLine.addEnd(point); }

    // verificação se o clique de seleção foi na figura do tipo FreeHand
    @Override
    public boolean contains(Point2D clickPoint){
        // range considerado agradável para verificarmos a distância
        double range = 8.0;
        // auxiliar para percorrermos a lista
        Node<Point2D> aux = freeHandLine.getFirstNode();

        // percorrer lista duplamente encadeada
        while (aux != null){
            // se algum ponto tiver uma distância do clickPoint menor ou igual ao range consideramos que o clique foi válido
            if (aux.valor.distance(clickPoint) <= range)
                return true;
            aux = aux.nextNode;
        }
        // se nenhum estava assim tão próximo, não consideramos a figura como selecionada
        return false;
    }

    @Override
    public double getX1(){
        // o x mais a esquerda é o inicial caso a lista esteja vazia
        if (freeHandLine.isEmpty()) return start.getX();

        Node<Point2D> aux = freeHandLine.getFirstNode();
        // xMin guarda o menor (mais a esquerda) x que conhecemos no momento
        double xMin = aux.valor.getX();

        while (aux != null){
            // percorremos a lista e trocamos quem é o xMin caso encontremos algum mais à esquerda
            if (aux.valor.getX() < xMin)
                xMin = aux.valor.getX();
            aux = aux.nextNode;
        }

        return xMin; // retornamos o mais à esquerda encontrado
    }

    @Override
    public double getY1() {
        // o y mais acima é o inicial caso a lista esteja vazia
        if (freeHandLine.isEmpty()) return start.getY();

        Node<Point2D> aux = freeHandLine.getFirstNode();
        // yMin guarda o menor (mais acima) y que conhecemos no momento
        double yMin = aux.valor.getY();

        while (aux != null) {
            if (aux.valor.getY() < yMin)
                // percorremos a lista e trocamos quem é o yMin caso encontremos algum mais acima
                yMin = aux.valor.getY();
            aux = aux.nextNode;
        }

        return yMin; // retornamos o mais acima encontrado
    }

    @Override
    public double getX2(){
        // o x mais a direita é o inicial caso a lista esteja vazia
        if (freeHandLine.isEmpty()) return start.getX();

        Node<Point2D> aux = freeHandLine.getFirstNode();
        // xMax guarda o maior (mais a direita) x que conhecemos no momento
        double xMax = aux.valor.getX();

        while (aux != null) {
            if (aux.valor.getX() > xMax)
                // percorremos a lista e trocamos quem é o xMax caso encontremos algum mais à direita
                xMax = aux.valor.getX();
            aux = aux.nextNode;
        }

        return xMax; // retornamos o x mais à direita encontrado
    }

    @Override
    public double getY2() {
        // o y mais abaixo é o inicial caso a lista esteja vazia
        if (freeHandLine.isEmpty()) return start.getY();

        Node<Point2D> aux = freeHandLine.getFirstNode();
        // yMax guarda o maior (mais abaixo) y que conhecemos no momento
        double yMax = aux.valor.getY();

        while (aux != null){
            if (aux.valor.getY() > yMax)
                // percorremos a lista e trocamos quem é o yMax caso encontremos algum mais abaixo
                yMax = aux.valor.getY();
            aux = aux.nextNode;
        }

        return yMax; // retornamos o y mais abaixo encontrado
    }

    @Override
    public void move(double movX, double movY){
        // o ponto de início é modificado para o ponto inicial mais as distâncias alteradas
        start = new Point2D(start.getX() + movX, start.getY() + movY);

        Node<Point2D> aux = freeHandLine.getFirstNode();

        while (aux != null){
            // passamos por cada ponto do FreeHand fazendo a movimentação deles
            aux.valor = new Point2D(aux.valor.getX()+movX,
                                    aux.valor.getY()+movY);
            aux = aux.nextNode;
        }
    }

    @Override
    public void draw(GraphicsContext gc){

        if (freeHandLine.isEmpty()) return; // lista vazia simplesmente saímos

        gc.setStroke(color); // definimos a cor da linha
        gc.beginPath(); // método do javafx para que criemos um "caminho vazio" por onde ainda vamos desenhar

        Node<Point2D> aux = freeHandLine.getFirstNode();
        gc.moveTo(aux.valor.getX(), aux.valor.getY()); // registramos as movimentações do mouse arrastando

        while (aux != null){
            // cria minúsculas linhas que formam a linha desenhada
            gc.lineTo(aux.valor.getX(), aux.valor.getY());
            aux = aux.nextNode;
        }

        gc.stroke(); // renderiza o caminho de linhas conectado no Canvas
    }
}
