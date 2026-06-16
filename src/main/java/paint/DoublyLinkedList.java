package paint;

// a classe que permite as figuras estarem desenhadas e afirma o uso das Estruturas de Dados
// implementação da lista duplamente encadeada (é genérica, podemos trabalhar com qualquer tipo/classe nos seus nós)
public class DoublyLinkedList<T> {

    // armazenamos primeiro e último nó da lista (nos permite inserção rápida, praticamente O(1) para ambos os lados)
    protected Node<T> firstNode;
    protected Node<T> lastNode;
    // nome da lista
    protected String nome;

    // caso o primeiro nó não tenha ninguém a lista está vazia
    public boolean isEmpty(){ return firstNode == null; }

    // getters para o primeiro e o último nó
    public Node<T> getFirstNode(){ return firstNode; }
    public Node<T> getLastNode(){ return lastNode; }

    // construtor que inicializa os nés em nulo e dá o nome à lista
    public DoublyLinkedList (String nome){
        this.firstNode = this.lastNode = null;
        this.nome = nome;
    }

    // adicionar no começo da lista
    public void addBegin(T valor){
        // criação de novo nó com o valor passado (no nosso caso, um objeto do tipo Figure)
        Node<T> novoNo = new Node<>(valor);
        // lista vazia
        if(isEmpty())
            // nó inicial e nó final são iguais
            firstNode = lastNode = novoNo;
        // lista não vazia
        else {
            // inserimos o novo antes do primeiro
            firstNode.previousNode = novoNo;
            // apontamos o novo para o primeiro e definimos o novo como primeiro
            novoNo.nextNode = firstNode;
            firstNode = novoNo;
        }
    }

    // adicionar no fim
    public void addEnd(T valor){
        Node<T> novoNo = new Node<>(valor);
        if(isEmpty())
            // mesmo caso da anterior
            firstNode = lastNode = novoNo;
        else{
            // apontamos o último para o novo
            lastNode.nextNode = novoNo;
            // apontamos (por trás) o novo para o último
            novoNo.previousNode = lastNode;
            // definimos o último como sendo o novo
            lastNode = novoNo;
        }
    }

    // remove do começo
    public void removeBegin(){
        if(isEmpty())
            System.out.println("Lista vazia");
        // lista unitária, destruímos
        else if(firstNode == lastNode)
            firstNode = lastNode = null;

        else{
            //definimos o segundo como primeiro e apontamos para null atrás dele
            firstNode = firstNode.nextNode;
            firstNode.previousNode = null;
        }
    }

    // remove do fim
    public void removeEnd(){
         if(isEmpty())
            System.out.println("Lista vazia");

         else if(firstNode == lastNode){
             // lista unitária: destruímos
            firstNode = lastNode = null;
         }

         else{
            // penúltimo agora é o último e aponta para null
            lastNode = lastNode.previousNode;
            lastNode.nextNode = null;
         }
    }

    // remoção por valor
    public boolean remove(T valor) {

        if (isEmpty()) return false;
        // percorremos a lista até encontrarmos o valor ou saírmos no null
        Node<T> atual = firstNode;
        while (atual != null) {
            if (atual.valor.equals(valor))
                break; // valor encontrado
            atual = atual.nextNode;
        }

        if (atual == null) return false; // o valor não foi encontrado após lista totalmente percorrida

        if (atual == firstNode) removeBegin(); // se o que queremos está no começo já temos método para isso

        else if (atual == lastNode) removeEnd(); // o mesmo do condicional acima ocorre para o final

        else {
            // anterior e próximo ao nó encontrado se apontam
            atual.previousNode.nextNode = atual.nextNode;
            atual.nextNode.previousNode = atual.previousNode;
        }

        return true; // se não caiu no return false acima chega aqui
    }
}
