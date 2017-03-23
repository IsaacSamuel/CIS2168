import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class IndexTree<E extends Comparable<E>> {
	protected Node<E> root;
	int size;
	
	public IndexTree() {
		this.root = null;
	}
	
	public IndexTree(Node<E> root) {
		this.root = root;
	}
	
	
	public boolean isLeaf(Node<E> localRoot) {
		return (localRoot.left == localRoot.right && localRoot.right == null);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		inOrder(root, sb);
		return sb.toString();
	}
	
	private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
		for (int i = 1; i < depth; i++) {
			sb.append(" ");
		}
		if (node == null) {
			sb.append("null\n");
		}
		else {
			sb.append(node.toString() + "\n");
			preOrderTraverse(node.left, depth+1, sb);
			preOrderTraverse(node.right, depth+1, sb);
		}
	}
	
	private void inOrder(Node<E> node, StringBuilder sb) {
		if (node == null) {
			return;
		}
		
		inOrder(node.left, sb);
		sb.append(" "+node+" ");
		inOrder(node.right, sb);
	}
	
	
	public void add(E item, int location) {
		root = add(root, item, location);
	}
	
	private Node<E> add(Node<E> localRoot, E item, int location) {
		if (localRoot == null) {
			//if item is not in tree, insert it
			return new Node<E>(item, location);
		} else if (item.compareTo(localRoot.element) == 0) {
			//item is equal to localRoot
			localRoot.count++;
			localRoot.loc.add(location);
			return localRoot;
		} else if (item.compareTo(localRoot.element) < 0) {
			//item is less than localRoot
			localRoot.left = add(localRoot.left, item, location);
			return localRoot;
		} else {
			//item is greater than localroot
			localRoot.right = add(localRoot.right, item, location);
			return localRoot;
		}
	}
	
	public void remove(E item) {
		root = remove(root, item);
	}
	
	private Node<E> remove(Node<E> localRoot, E item) {
		if (localRoot == null) { //item not found
			return null;
		}
		
		int comparison = item.compareTo(localRoot.element);
		if (comparison < 0) { //item is less than localRoot
			localRoot.left = remove(localRoot.left, item);
			return localRoot;
		}
		else if (comparison > 0) {
			localRoot.right = remove(localRoot.right, item);
			return localRoot;
		}
		else {
			//the item is in the localroot
			if (isLeaf(localRoot)) {
				//if the local root has no children, just delete it
				return null;
			}
			else if (localRoot.right == null && localRoot.left != null) {
				//if localRoot only has a left child
				return localRoot.left;
			} 
			else if (localRoot.left == null && localRoot.right != null) {
				//if localRoot only has a right child
				return localRoot.right;
			}
			else {
				//localroot has two children
				Node<E> current = localRoot.right;
				while (current.left != null) {
					current = current.left;
				}
				
				E temp = localRoot.element;
				localRoot.element = current.element;
				current.element = temp;
				
				localRoot.right = remove(localRoot.right, item);
				return localRoot;
			}
		}
	}
	
	public void find(E item) {
		Node<E> node;
		node = find(item, root);
		if (node != null) {
			System.out.print("Word: " + node + " Number of occurences: " + node.count + " Byte locations: ");
			for (int each : node.loc) {
				System.out.print(each + " ");
			}
			System.out.print("\n");
		}
		else {
			System.out.println("Could not find word");
		}
		
	}
	
	private Node<E> find(E item, Node<E> localRoot) {
		while (localRoot != null) {
			int comparison = item.compareTo(localRoot.element);
			if (comparison < 0) {
				localRoot = localRoot.left;
			}
			else if (comparison > 0) {
				localRoot = localRoot.right;
			}
			else { //found it!
				return localRoot;
			}
		}
		//Did not find the word, return null
		return null;
	}
	
	
	
	public static void main(String args[]) {
		IndexTree<String>  tree = new IndexTree<String>();
		String word;
		Path file = Paths.get("book.txt");
		byte[] fileArray;
		int location = 0;
		int loc;
		
		try {
			fileArray = Files.readAllBytes(file);
			while (location < Files.size(file)) {
				word = "";
				//if statement includes a-z, A-Z
				if ((fileArray[location] >= 65 && fileArray[location] <= 90) || (fileArray[location] >= 97 && fileArray[location] <= 122)) {
					//mark location of first character of byte
					loc = location;
					//while statement includes a-z, A-Z, and ' for conjunctions (though it also catches possessives)
					while ((fileArray[location] >= 65 && fileArray[location] <= 90) || (fileArray[location] >= 97 && fileArray[location] <= 122) || (fileArray[location] == 39) ) {
						word += Character.toString( (char) fileArray[location]);
						location++;
					}

					tree.add(word, loc);
				}
			location++;
			}
			/* Use this function to prove that the location stored in index is true.
			System.out.println("\n" + (char) fileArray[122614]);
			*/
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
		tree.find("Hymen");
		tree.remove("Hymen");
		tree.find("Hymen");
		//It's somehow not surprising at all that Shakespeare used this word.
		
		
		
		System.out.println(tree);
	}
	
	
}
