package problem_binary_tree;

/*
 * 打印二叉树的函数
 * 把脸逆时针转90‘看就是二叉树(HH代表头结点，^^代表左子节点，vv代表右子节点)
 */
public class Code_02_PrintBinaryTree {

	public static class Node {
		public int value;
		public Node left;
		public Node right;
		public Node parent;

		public Node(int data) {
			this.value = data;
		}
	}

	public static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	public static void printInOrder(Node head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.right, height + 1, "v", len);
		String val = to + head.value + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.left, height + 1, "^", len);
	}

	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}

	public static void main(String[] args) {

		Node head1 = new Node(6);
		head1.parent = null;
		head1.left = new Node(3);
		head1.left.parent = head1;
		head1.left.left = new Node(1);
		head1.left.left.parent = head1.left;
		head1.left.left.right = new Node(2);
		head1.left.left.right.parent = head1.left.left;
		head1.left.right = new Node(4);
		head1.left.right.parent = head1.left;
		head1.left.right.right = new Node(5);
		head1.left.right.right.parent = head1.left.right;
		head1.right = new Node(9);
		head1.right.parent = head1;
		head1.right.left = new Node(8);
		head1.right.left.parent = head1.right;
		head1.right.left.left = new Node(7);
		head1.right.left.left.parent = head1.right.left;
		head1.right.right = new Node(10);
		head1.right.right.parent = head1.right;
		printTree(head1);

		Node head = new Node(1);
		head.left = new Node(-222222222);
		head.right = new Node(3);
		head.left.left = new Node(Integer.MIN_VALUE);
		head.right.left = new Node(55555555);
		head.right.right = new Node(66);
		head.left.left.right = new Node(777);
		printTree(head);

		head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.right.left = new Node(5);
		head.right.right = new Node(6);
		head.left.left.right = new Node(7);
		printTree(head);

		head = new Node(1);
		head.left = new Node(1);
		head.right = new Node(1);
		head.left.left = new Node(1);
		head.right.left = new Node(1);
		head.right.right = new Node(1);
		head.left.left.right = new Node(1);
		printTree(head);

	}

}
