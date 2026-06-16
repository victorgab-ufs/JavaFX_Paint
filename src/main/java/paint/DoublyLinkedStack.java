package paint;

public class DoublyLinkedStack<T> {
    // node que guarda o topo da pilha
    protected Node<T> topNode;
    protected String nome;

    // caso o primeiro seja nulo, a pilha está vazia
    public boolean isEmpty(){ return topNode == null; }

    // getter para o node no topo
    public Node<T> getTopNode(){ return topNode; }

    // construtor que inicializa o topo em null e dá o nome à pilha
    public DoublyLinkedStack (String nome){
        this.topNode = null;
        this.nome = nome;
    }

    // método para empilhar
    public void push(T valor){
        Node<T> novoNo = new Node<>(valor);
        // inserção em pilha não vazia
        if (!isEmpty()){
            // antigo topo aponta por trás para o nó criado
            topNode.previousNode = novoNo;
            // novo nó aponta para o antigo topo
            novoNo.nextNode = topNode;
        }
        // se estiver vazia apenas coloca o novo node (null <- node -> null)
        // não estando vazia também define o novo nó como o topo atual
        topNode = novoNo;
    }

    // método para desempilhar
    public T pop() {
        // não podemos desempilhar pilha vazia
        if (isEmpty()) return null;
        // guardamos o nó removido (sempre é o topo) para poder retornar
        Node<T> removido = topNode;

        // caso a pilha só tivesse um elemento destruímos
        if (topNode.nextNode == null) topNode = null;

        else { // caso não
            // mudamos o topo para o nó seguinte
            topNode = topNode.nextNode;
            // removemos a referência para o antigo nó que existia no seguinte a ele
            topNode.previousNode = null;
        }

        // tratamos o nó removido para garantir que não haja referências externas a ele
        removido.nextNode = null;
        removido.previousNode = null;

        // retorna o valor do node removido
        return removido.valor;
    }
}
