package paint;

// classe responsável por guardar a nossa lista com todas as figuras desenhadas
public class PaintModel {

    // criamos uma lista duplamente encadeada de Figuras que guardará todas as que estão desenhadas (os nós da lista são do tipo Node<Figure>)
    private DoublyLinkedList<Figure> figures = new DoublyLinkedList<>("");

    // criamos duas pilhas baseadas em lista duplamente encadeada para as ações de desfazer e refazer
    private DoublyLinkedStack<Figure> undoStack = new DoublyLinkedStack<>("Desfazer");
    private DoublyLinkedStack<Figure> redoStack = new DoublyLinkedStack<>("Refazer");

    // método para adicionar figuras à nossa lista duplamente encadeada
    public void addFigure(Figure f) {
        // chamamos o método que adiciona a figura crida ao fim da lista (explicado no arquivo correspondente à lista)
        figures.addEnd(f);
        // empilha a figura sempre que uma nova for adicionada
        undoStack.push(f);
        // sempre que o usuário desenha algo novo, a história do "Refazer" é limpa
        redoStack = new DoublyLinkedStack<>("Refazer");
    }

    // desfazer a última ação
    public void undo() {
        // se a lista de desfazer não tiver vazia
        if (!undoStack.isEmpty()) {
            Figure lastFigure = undoStack.pop(); // Remove a última figura que entrou na pilha de desfazer
            figures.remove(lastFigure);         // Remove essa mesma figura da lista duplamente encadeada (some da tela)
            redoStack.push(lastFigure);         // Guarda essa figura na pilha de refazer
        }
    }

    // refazer figuras desfeitas
    public void redo() {
        // se a pilha de refazer não estiver vazia
        if (!redoStack.isEmpty()) {
            Figure figureToRestore = redoStack.pop(); // Remove última figura a entrar na pilha de refazer
            figures.addEnd(figureToRestore);         // Devolve a figura para a lista duplamente encadeada (para a tela)
            undoStack.push(figureToRestore);         // Devolve para a pilha de desfazer
        }
    }

    // getter do nó que contém a primeira figura que foi desenhada
    public Node<Figure> getFirstNode(){
        return figures.getFirstNode();
    }
    // getter do nó que contém a última figura que foi desenhada
    public Node<Figure> getLastNode(){ return figures.getLastNode(); }

    // método para localizarmos qual figura está selecionada (se houver)
    public Figure getSelectedFigure() {
        // auxiliar para percorrer a lista duplamente encadeada
        Node<Figure> aux = figures.getFirstNode();
        // buscamos até que o auxiliar chegue em null (fim da lista)
        while (aux != null){
            // se o valor do nó estiver selecionado, retornamos ele (objeto da classe Figure)
            if (aux.valor.isSelected()) return aux.valor;
            aux = aux.nextNode;
        }
        // caso não encontremos, retornamos null
        return null;
    }

    // método para apagar figuras da lista duplamente encadeada (chama o remove criado na classe DoublyLinkedList)
    public void eraseFigure(Figure selectedFigure){
        figures.remove(selectedFigure);
    }

    // método para colocar figuras atrás de outras (down arrow)
    public void toBack(Figure selectedFigure){
        // caso a figura esteja na lista duplamente encadeada, removemos ela de lá
        if (figures.remove(selectedFigure)){
            // adicionamos a figura novamente no começo (no redraw ela fica atrás das outras)
            figures.addBegin(selectedFigure);
        }
    }

    // método para colocar figuras a frente de outras (up arrow)
    public void toForth(Figure selectedFigure){
        // mesmo caso da anterior, mas adicionamos a figura ao final da lista duplamente encadeada
        if (figures.remove(selectedFigure)){
            figures.addEnd(selectedFigure);
        }
    }
}
