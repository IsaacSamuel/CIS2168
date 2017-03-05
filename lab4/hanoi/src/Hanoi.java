import java.util.ArrayList;


public class Hanoi {
	Stack<Integer> first_base = new Stack<Integer>();
	Stack<Integer> second_base = new Stack<Integer>();
	Stack<Integer> third_base = new Stack<Integer>();
	int size;
	
	
	
	public Hanoi(int n) {
		size = n;
		
		for (int i = n; i > 0; i--) {
			first_base.push(i);
		}
	}
	
	public boolean isFinished(){
		if (third_base.size() == size) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void move_disks(int n, Stack<Integer> a, Stack<Integer> b, Stack<Integer> c) {
				
		if (n != 0) {
			move_disks(n-1, a, c, b);
			System.out.println(this);
			c.push(a.pop());
			move_disks(n-1, b, a, c);
			
		}
		
		
	}
	
	public String toString() {
		String retval = "";
		
		retval += stringifyBase(first_base, "Tower A");
		retval += stringifyBase(second_base, "Tower B");
		retval += stringifyBase(third_base, "Tower C");
		
		
		return retval;
		
	}
	
	private String stringifyBase(Stack<Integer> base, String tower_name) {
		String retval = "";
		ArrayList<Integer> a = new ArrayList<Integer>();

		//pulls out of the elements of the base and puts them into array
		while (base.size() != 0) {
			a.add(base.pop());
		}
		
		for (Integer each : a) {
			retval += " ____ " + each + " ____\n";
		}
		
		retval += "___"+ tower_name + "___\n\n";
		
		//push back onto the stack
		for (int i = a.size() - 1; i >= 0; i--) {
			base.push(a.get(i));
		}
		
		
		return retval;
	}
	
	
	
	public static void main(String args[]) {
		Hanoi a = new Hanoi(5);
		
		
		a.move_disks(a.size, a.first_base, a.second_base, a.third_base);
		
		System.out.println(a); 
		
	}

}
