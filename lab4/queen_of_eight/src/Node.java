
public class Node<E> {
	
	Node<E> next;
	E element;
	
	public Node() {
		this.element = null;
		this.next = null;
	}
	
	public Node(E e) {
		this.element = e;
		this.next = null;
	}

}
