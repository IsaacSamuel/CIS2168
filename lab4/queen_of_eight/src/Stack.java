/*
 * Implement a reference based stack
 */
public class Stack<E> {
	
	private Node<E> top;
	private int size = 0;

	public Stack() {
		top = null;
	}
	
	
	/*
	 * places element on the top of the stack
	 */
	public void push(E e){
		Node<E> temp = new Node<E>(e);
		temp.next = top;
		top = temp;

		size++;
	}
	
	/*
	 * remove the top node and return its contents
	 */
	public E pop(){
		E retval = top.element;
		top = top.next;
		size--;
		return retval; //replace
	}
	
	/*
	 * Look at the top element of the Stack and return it, without removing
	 */
	public E peek(){
		return top.element; //replace
	}
	
	//returns the size of the stack
	public int size(){
		return size;  //replace
	}
	
	public static void main(String args[]) {
		Stack<String> isaac = new Stack<String>();
		isaac.push("Meh");
		isaac.push("Heh");
		isaac.push("Keh");
		System.out.println(isaac.peek());
		System.out.println(isaac.pop());
		System.out.println(isaac.pop());
	}

}
