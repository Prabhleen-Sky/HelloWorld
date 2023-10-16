// Java program to print top
// view of binary tree
import java.util.*;

class GFG {

	// Structure of binary tree
	static class Node {
		Node left;
		Node right;
		int data;
	}
	/*
			1
			/ \
			2 3
			\
			4
			\
				5
				\
				6
		Top view of the above binary tree is
		2 1 3 6
	*/
	static class pair {
		Node node;
		int hd;

		pair() {}
		pair(Node node, int hd)
		{
			this.node = node;
			this.hd = hd;
		}
	}

	// function to create a new node
	static Node newNode(int key)
	{
		Node node = new Node();
		node.left = node.right = null;
		node.data = key;
		return node;
	}

	// function should print the topView of
	// the binary tree
	static void topView(Node root)
	{
		// queue for holding nodes and their horizontal
		// distance from the root node
		Queue<pair> q = new LinkedList<>();

		// pushing root node with distance 0
		q.add(new pair(root, 0));

		// hd is current node's horizontal distance from
		// root node l is current left min horizontal
		// distance (or max in magnitude) so far from the
		// root node r is current right max horizontal
		// distance so far from the root node

		int hd = 0, l = 0, r = 0;

		// stack is for holding left node's data because
		// they will appear in reverse order that is why
		// using stack

		Stack<Integer> left = new Stack<>();

		// ArrayList is for holding right node's data
		ArrayList<Integer> right = new ArrayList<>();
		Node node = null;

		while (!q.isEmpty()) {
			node = q.peek().node;
			hd = q.peek().hd;

			if (hd < l) {
				left.push(node.data);
				l = hd;
			}

			if (hd > r) {
				right.add(node.data);
				r = hd;
			}

			if (node.left != null) {
				q.add(new pair(node.left, hd - 1));
			}
			if (node.right != null) {
				q.add(new pair(node.right, hd + 1));
			}

			q.poll();
		}

		// printing the left node's data in reverse order
		while (!left.isEmpty()) {
			System.out.print(left.peek() + " ");
			left.pop();
		}

		// then printing the root node's data
		System.out.print(root.data + " ");

		// finally printing the right node's data
		for (int d : right) {
			System.out.print(d + " ");
		}
	}

	// Driver Code
	public static void main(String args[])
	{
		Node root = newNode(1);
		root.left = newNode(2);
		root.right = newNode(3);
		root.left.right = newNode(4);
		root.left.right.right = newNode(5);
		root.left.right.right.right = newNode(6);
		topView(root);
	}
}

// This code is contributed by Snigdha Patil
