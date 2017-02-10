
import java.util.Iterator;

public class CircularLinkedList<E> implements Iterable<E> {
	
	// Your variables
	// You can include a reference to a tail if you want
	Node<E> head = new Node<E>();
	public int size = 0;  // BE SURE TO KEEP TRACK OF THE SIZE

	
	// implement this constructor
	public CircularLinkedList() {
	}


	// writing helper functions for add and remove, like the book did can help
	// but remember, the last element's next node will be the head!



	// attach a node to the end of the list
	// Be sure to handle the adding to an empty list
	// always returns true 
	public boolean add(E e) {
		Node<E> current = head;
		Node<E> temp = new Node<E>(e);
		
		if (size == 0) {
			head.element = e;
		}
		else {
			for (int i = 0; i < size-1; i++) {
				current = current.next;
			}

			current.next = temp;
			temp.next = head;
		}
		
		size++;
		return true;
	}

	
	// need to handle
	// out of bounds
	// empty list
	// adding to front
	// adding to middle
	// adding to "end"
	// REMEMBER TO INCREMENT THE SIZE
	public boolean add(int index, E e){
		Node<E> current = head;
		Node<E> temp = new Node<E>(e);
		
		if (index > size) {
			System.err.println("Index was out of bounds.");
			return false;
		}
		else if (index == 0) {
			/*Adding to head */
			for (int i = 0; i < size-1; i++) {
				current = current.next;
			}
			
			temp.next = head;
			current.next = temp;
			head = temp;
		}
		else if (index == size) {
			/*Adding to end, just call add function */
			this.add(e);
			
		}
		else {
			for (int i = 0; i < index-1; i++) {
				current = current.next;
			}
			
			temp.next = current.next;
			current.next = temp;
		}
		
		size++;
		return true;
	}

	// I highly recommend using this helper method
	// Return Node<E> found at the specified index
	// be sure to handle out of bounds cases
	public Node<E> getNode(int index ) {
		Node<E> current = head;
		for (int i =0; i < index; i++) {
			current = current.next;
		}
		return current;
	}

	
	
	// remove must handle the following cases
	// out of bounds
	// removing the only thing in the list
	// removing the first thing in the list (need to adjust the last thing in the list to point to the beginning)
	// removing the last thing (if you have a tail)
	// removing any other node.
	// REMEMBER TO DECREMENT THE SIZE
	public E remove(int index) {
		Node<E> current = head;
		E retval;
		
		if (index >= size) {
			System.err.println("Index was out of bounds.");
			return null;
		}
		/*removing the head */
		else if (index == 0) {
			/* if the head is the only element */
			if (size==1) {
				retval = head.element;
				head = new Node<E>();
			}
			/* regular cases */
			else {
				retval = head.element;
				
				for (int i = 0; i < size-1; i++) {
					current = current.next;
				}
				
				current.next = head.next;
				head = head.next;
			}
		}
		/*removing any other element */
		else {
			for (int i = 0; i < index-1; i++) {
				current = current.next;
			}
			retval = current.next.element;
			current.next = current.next.next;
		}

		size--;
		return retval;
	}
	
	
	
	
	// Turns your list into a string
	// Useful for debugging
	public String toString(){
		Node<E> current =  head;
		StringBuilder result = new StringBuilder();
		if(size == 0){
			return "";
		}
		if(size == 1) {
			return head.getElement().toString();
			
		}
		else{
			do{
				result.append(current.getElement());
				result.append(" ==> ");
				current = current.next;
			} while(current != head);
		}
		return result.toString();
	}
	
	
	public Iterator<E> iterator() {
		return new ListIterator<E>();
	}
	
	
	
	
	
	// provided code
	// read the comments to figure out how this works and see how to use it
	// you should not have to change this
	// change at your own risk!
	private class ListIterator<E> implements Iterator<E>{
		
		Node<E> nextItem;
		Node<E> prev;
		int index;
		
		@SuppressWarnings("unchecked")
		//Creates a new iterator that starts at the head of the list
		public ListIterator(){
			nextItem = (Node<E>) head;
			index = 0;
		}

		// returns true if there is a next node
		// this is always should return true if the list has something in it
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return size != 0;
		}
		
		// advances the iterator to the next item
		// handles wrapping around back to the head automatically for you
		public E next() {
			// TODO Auto-generated method stub
			prev =  nextItem;
			nextItem = nextItem.next;
			index =  (index + 1) % size;
			return prev.getElement();
	
		}
		
		// removed the last node was visted by the .next() call 
		// for example if we had just created a iterator
		// the following calls would remove the item at index 1 (the second person in the ring)
		// next() next() remove()
		public void remove() {
			int target;
			if(nextItem == head) {
				target = size - 1;
			} else{ 
				target = index - 1;
				index--;
			}
			CircularLinkedList.this.remove(target); //calls the above class
		}
		
	}
	
	
	// Solve the problem in the main method
	// The answer of n = 13,  k = 2 is 
	// the 11th person in the ring (index 10)
	public static void main(String[] args){
		CircularLinkedList<Integer> l =  new CircularLinkedList<Integer>();
		
		
		// use the iterator to iterate around the list
		Iterator<Integer> iter = l.iterator();
		
		int n = 13;
		int k = 2;
		
		for (int i=1; i <= n; i++) {
			l.add(i);
		}
		
		
		System.out.println(l);

		while (l.size != 1) {
			
			for (int i = 0; i < k; i++) {
				iter.next();
			}
			
			iter.remove();
			System.out.println(l);
		}

		
		System.out.println(l);

	}

	
}