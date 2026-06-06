package paint;

public class DoublyLinkedList<T> {
    
    protected Node<T> firstNode;
    protected Node<T> lastNode;
    protected String nome;
    
    public boolean isEmpty(){return firstNode == null;}

    public Node<T> getFirstNode(){return firstNode;}
    public Node<T> getLastNode(){return lastNode;}

    public DoublyLinkedList (String nome){
        this.firstNode = this.lastNode = null;
        this.nome = nome;
    }
    public void addBegin(T valor){
        Node<T> novoNo = new Node<>(valor);
        if(isEmpty()){
            firstNode = lastNode = novoNo;
        }else{
            firstNode.previousNode = novoNo;
            novoNo.nextNode = firstNode;
            firstNode = novoNo;
        }

    }
    public void addEnd(T valor){
        Node<T> novoNo = new Node<>(valor);
        if(isEmpty()){
            firstNode = lastNode = novoNo;
        }else{
            lastNode.nextNode = novoNo;
            novoNo.previousNode = lastNode;
            lastNode = novoNo;
        }

    }

    public void removeBegin(){
        if(isEmpty()){
            System.out.println("Lista vazia");
        }else if(firstNode == lastNode){
            firstNode = lastNode = null;
        }else{
            
            firstNode = firstNode.nextNode;
            firstNode.previousNode = null;
        }
    }

    public void removeEnd(){
         if(isEmpty()){
            System.out.println("Lista vazia");
        }else if(firstNode == lastNode){
            firstNode = lastNode = null;
    }else{
        lastNode = lastNode.previousNode;
        lastNode.nextNode = null;
    }
    
}

public boolean remove(T valor) {
    if (isEmpty()) {
        System.out.println("Lista vazia");
        return false;
    }

    Node<T> atual = firstNode;
    while (atual != null) {
        if (atual.valor.equals(valor)) {
            break; 
        }
        atual = atual.nextNode;
    }

    
    if (atual == null) {
        System.out.println("Valor não encontrado na lista.");
        return false;
    }

   
    
    if (atual == firstNode) {
       
        removeBegin();
    } else if (atual == lastNode) {
       
        removeEnd();
    } else {
       
        atual.previousNode.nextNode = atual.nextNode;
        atual.nextNode.previousNode = atual.previousNode;
    }

    return true; 
}
}
