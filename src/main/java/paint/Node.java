package paint;

public class Node<T> {
    Node<T> nextNode;
    Node<T> previousNode;
    T valor;

    public Node(T valor){
        nextNode = previousNode = null;
        this.valor = valor;
    }
}
