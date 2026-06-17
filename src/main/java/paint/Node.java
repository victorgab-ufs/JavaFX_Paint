package paint;

// nó genérico que utilizaremos para guardar as figuras nas listas duplamente encadeadas e pilhas
public class Node<T> {
    Node<T> nextNode; // referência para o nó seguinte
    Node<T> previousNode; // referência para o nó anterior
    T valor; // valor do nó, no nosso caso um objeto das classes filhas de Figure

    public Node(T valor){
        // construtor que inicializa as referências em null e atribui o valor ao nó
        nextNode = previousNode = null;
        this.valor = valor;
    }
}
