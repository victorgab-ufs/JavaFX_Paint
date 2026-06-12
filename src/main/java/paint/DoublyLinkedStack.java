package paint;

public class DoublyLinkedStack<T> {
    // node que guarda o topo da pilha
    protected Node<T> topNode;
    protected String nome;

    // caso o primeiro seja nulo, a pilha está vazia
    public boolean isEmpty(){ return topNode == null; }

    // getter para o node no topo
    public Node<T> getTopNode(){ return topNode; }

    // construtor
    public DoublyLinkedStack (String nome){
        this.topNode = null;
        this.nome = nome;
    }

    // método para empilhar
    public void push(T valor){
        Node<T> novoNo = new Node<>(valor);
        // inserção em pilha não vazia
        if (!isEmpty()){
            topNode.previousNode = novoNo;
            novoNo.nextNode = topNode;
        }
        // se estiver vazia apenas coloca o novo node (null <- node -> null)
        topNode = novoNo;
    }

    // método para desempilhar
    public T pop() {

        if (isEmpty()) return null;

        Node<T> removido = topNode;

        if (topNode.nextNode == null) topNode = null;

        else {
            topNode = topNode.nextNode;
            topNode.previousNode = null;
        }

        removido.nextNode = null;
        removido.previousNode = null;

        // retorna o valor do node removido
        return removido.valor;
    }
}
