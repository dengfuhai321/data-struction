package problem_binary_tree;

/*
 * 	例子：某个二叉树的中序遍历    1 4 3 5 2 7 55 88 22 
 * 后继节点：4是1的后继节点，3是4的后继节点，5是3的后继节点。。。。。。
 * 前驱结点：1是4的前驱结点，4是3的前驱结点，3是5的前驱结点。。。。。。
 * 
 * 问题：在二叉树中找到一个节点的后继节点
 * 解题思路：如果一个节点的右子树存在，那么它的第一个节点（最左边最下面的节点）就是后继节点
 * 如果一个节点的右子树不存在，找该节点是哪个父节点左子树最后一个节点（最右边最下面的节点），那个节点就是后继节点
 * 否则没有后继节点
 * 查看图片picture中的binary_tree_zhongxu_houjijiedian.bmp
 */
public class Code_03_SuccessorNode {

	public static class Node {
		public int value;
		public Node left;
		public Node right;
		public Node parent;

		public Node(int data) {
			this.value = data;
		}
	}

	/*
	 * 找后继节点
	 */
	public static Node getSuccessorNode(Node node) {
		if (node == null) {
			return node;
		}
		if (node.right != null) {// 右子树存在
			return getRightMost(node.right);// 返回右子树的第一个节点
		} else {// 右子树不存在
			Node parent = node.parent;
			while (parent != null && parent.left != node) {// 首先判断它的父节点是否为空，若不为空
				node = parent;// 再看它是否是其父节点的左子树的最后一个节点
				parent = node.parent;
			}
			return parent;
		}
	}

	/*
	 * 返回右子树的最左面最下面的节点（就是某个二叉树的右子树的第一个节点）
	 */
	public static Node getRightMost(Node node) {
		if (node == null) {
			return node;
		}
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

	/*
	 * 找前驱节点
	 */
	public static Node getSuccessorNode2(Node node) {
		if (node == null) {
			return node;
		}
		if (node.left != null) {// 左子树存在
			return getLefMost(node.left);// 返回左子树的最后一个节点
		} else {// 左子树不存在
			Node parent = node.parent;
			while (parent != null && parent.right != node) {// 首先判断它的父节点是否为空，若不为空
				node = parent;// 再看它是否是其父节点所在右子树的第一个节点
				parent = node.parent;
			}
			return parent;
		}
	}

	/*
	 * 返回左子树的最右面最下面的节点（就是某个二叉树的左子树的最后一个节点）
	 */
	public static Node getLefMost(Node node) {
		if (node == null) {
			return node;
		}
		while (node.right != null) {
			node = node.right;
		}
		return node;
	}

	public static void main(String[] args) {
		Node head = new Node(6);
		head.parent = null;
		head.left = new Node(3);
		head.left.parent = head;
		head.left.left = new Node(1);
		head.left.left.parent = head.left;
		head.left.left.right = new Node(2);
		head.left.left.right.parent = head.left.left;
		head.left.right = new Node(4);
		head.left.right.parent = head.left;
		head.left.right.right = new Node(5);
		head.left.right.right.parent = head.left.right;
		head.right = new Node(9);
		head.right.parent = head;
		head.right.left = new Node(8);
		head.right.left.parent = head.right;
		head.right.left.left = new Node(7);
		head.right.left.left.parent = head.right.left;
		head.right.right = new Node(10);
		head.right.right.parent = head.right;

		Node test = head.left.left;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		System.out.println(test.value + " last: " + getSuccessorNode2(test));
		test = head.left.left.right;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		System.out.println(test.value + " last: " + getSuccessorNode2(test).value);
		test = head.left;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		System.out.println(test.value + " last: " + getSuccessorNode2(test).value);
		test = head.left.right;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		System.out.println(test.value + " last: " + getSuccessorNode2(test).value);
		test = head.left.right.right;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		System.out.println(test.value + " last: " + getSuccessorNode2(test).value);
		test = head;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		System.out.println(test.value + " last: " + getSuccessorNode2(test).value);
		test = head.right.left.left;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		System.out.println(test.value + " last: " + getSuccessorNode2(test).value);
		test = head.right.left;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		System.out.println(test.value + " last: " + getSuccessorNode2(test).value);
		test = head.right;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		System.out.println(test.value + " last: " + getSuccessorNode2(test).value);
		test = head.right.right; // 10's next is null
		System.out.println(test.value + " next: " + getSuccessorNode(test));
		System.out.println(test.value + " last: " + getSuccessorNode2(test).value);
	}

}
