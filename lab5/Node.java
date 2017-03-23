import java.util.ArrayList;


public class Node<E extends Comparable<E>> {
	
	Node<E> right;
	Node<E> left;
	E element;
	int count = 0;
	ArrayList<Integer> loc = new ArrayList<Integer>();
	
	
	public Node(E e, int location) {
		this.element = e;
		this.loc.add(location);
		this.count += 1;
		this.left = null;
		this.right = null;
	}
	
	public String toString() {
		return this.element.toString();
	}

}
