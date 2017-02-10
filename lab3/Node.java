
public class Node<E> {

	E element;
	Node<E> next;

	
	
	public Node() {

		this.element = null;
		this.next = null;
		
	}
	
	public Node(E e) {
		this.element = e;
		this.next = null;
	}
	
	
	public E getElement() {
		return this.element;
	}
	
	
	public void setElement(E element) {
		this.element= element;
	}


}