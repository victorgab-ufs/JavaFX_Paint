package paint;

public class PaintModel {

    private DoublyLinkedList<Figure> figures = new DoublyLinkedList<>("");

    // pilhas para gerenciar o fluxo de undo e redo
    private DoublyLinkedStack<Figure> undoStack = new DoublyLinkedStack<>("Desfazer");
    private DoublyLinkedStack<Figure> redoStack = new DoublyLinkedStack<>("Refazer");

    public void addFigure(Figure f) {
        figures.addEnd(f);
        // empilha a figura sempre que uma nova for adicionada
        undoStack.push(f);
        // Sempre que o usuário desenha algo novo, a história do "Refazer" é limpa
        redoStack = new DoublyLinkedStack<>("Refazer");
    }

    // desfazer a última ação
    public void undo() {
        if (!undoStack.isEmpty()) {
            Figure lastFigure = undoStack.pop(); // Remove da pilha de desfazer
            figures.remove(lastFigure);         // Remove da tela
            redoStack.push(lastFigure);         // Guarda na pilha de refazer
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Figure figureToRestore = redoStack.pop(); // Remove da pilha de refazer
            figures.addEnd(figureToRestore);         // Devolve para a tela
            undoStack.push(figureToRestore);         // Devolve para a pilha de desfazer
        }
    }

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
