package paint;

public class DoublyLinkedStack<T> {
    protected Node<T> topNode;
  
    protected String nome;
    
    public boolean isEmpty(){return topNode == null;}

    public Node<T> getTopNode(){return topNode;}
    

    public DoublyLinkedStack (String nome){
        this.topNode = null;
        this.nome = nome;
    }
    public void push(T valor){
        Node<T> novoNo = new Node<>(valor);
        if(isEmpty()){
            topNode = novoNo;
        }else{
            topNode.previousNode = novoNo;
            novoNo.nextNode = topNode;
            topNode = novoNo;
        }

    }
   

    public Node<T> pop() {
    if (isEmpty()) {
        System.out.println("Pilha vazia");
        return null; 
    }

    Node<T> removido = topNode;

   
    if (topNode.nextNode == null) {
        topNode = null;
    } else {
       
        topNode = topNode.nextNode;
     
        topNode.previousNode = null; 
    }

  
    removido.nextNode = null;
    removido.previousNode = null;

    return removido;
}

   


}
