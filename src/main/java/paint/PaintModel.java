package paint;

public class PaintModel {

    private DoublyLinkedList<Figure> figures = new DoublyLinkedList<>("");

    public void addFigure(Figure f) { figures.addEnd(f);}

    public Node<Figure> getFirstNode(){
        return figures.getFirstNode();
    }

    public Node<Figure> getLastNode(){
        return figures.getLastNode();
    }

    public Figure getSelectedFigure() {
        Node<Figure> aux = figures.getFirstNode();

        while (aux != null){
            if (aux.valor.isSelected()) return aux.valor;
            aux = aux.nextNode;
        }

        return null;
    }

    public void eraseFigure(Figure selectedFigure){
        figures.remove(selectedFigure);
    }

    public void toBack(Figure selectedFigure){
        if (figures.remove(selectedFigure)){
            figures.addBegin(selectedFigure);
        }
    }

    public void toForth(Figure selectedFigure){
        if (figures.remove(selectedFigure)){
            figures.addEnd(selectedFigure);
        }
    }
}
