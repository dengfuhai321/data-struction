package problem_binary_tree;

/*
 * 判断一个二叉树是否是平衡二叉树（平衡二叉树：二叉树的每一个节点的左右子树的高度差<=1）
 * 
 * 解题思路：在判断每一个节点的左右子树，你应该想到递归，来把二叉树的每个节点递归判断
 * 
 * 1.判断左子树是否平衡2.判断右子树是否平衡3.左子树的高度4.右子树的高度
 * 设计递归返回结构，得到子树的信息，整合子树的信息，返回整合的信息
 * 返回的结果应该一致，返回是否平衡和高度，因为返回值是两个，可以写一个类来分装两个信息
 */
public class Code_06_IsBalancedTree {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	/*
	 * 封装返回的信息
	 */
	public static class ReturnDate {
		public boolean isB;
		public int h;

		public ReturnDate(boolean isB, int h) {
			this.isB = isB;
			this.h = h;
		}
	}

	/*
	 * 方法二
	 */
	public static boolean isBalance1(Node head) {
		return process(head).isB;
	}

	public static ReturnDate process(Node head) {
		if (head == null) {
			return new ReturnDate(true, 0);// 如果子树节点为空，是平衡树true
		}
		ReturnDate leftDate = process(head.left);// 返回左子树的信息
		if (!leftDate.isB) {// 如果子树不是平衡二叉树，直接返回false,高度不用管，因为只要有一个false出现，整个树之后就都是false
			return new ReturnDate(false, 0);
		}
		ReturnDate rightDate = process(head.right);// 返回右子树的信息
		if (!rightDate.isB) {// 如果子树不是平衡二叉树，直接返回false,高度不用管，因为只要有一个false出现，整个树之后就都是false
			return new ReturnDate(false, 0);
		}
		if (Math.abs(leftDate.h - rightDate.h) > 1) {
			return new ReturnDate(false, 0);// 如果左右子树的高度差大于1，就不是平衡二叉树，false
		}
		// 以上条件都符合，就返回正确的信息
		return new ReturnDate(true, Math.max(leftDate.h, rightDate.h) + 1);

	}

	/*
	 * 方法二
	 */
	public static boolean isBalance(Node head) {
		boolean[] res = new boolean[1];
		res[0] = true;
		getHeight(head, 1, res);
		return res[0];
	}

	public static int getHeight(Node head, int level, boolean[] res) {
		if (head == null) {
			return level;
		}
		int lH = getHeight(head.left, level + 1, res);
		if (!res[0]) {
			return level;
		}
		int rH = getHeight(head.right, level + 1, res);
		if (!res[0]) {
			return level;
		}
		if (Math.abs(lH - rH) > 1) {
			res[0] = false;
		}
		return Math.max(lH, rH);
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);
		head.right.right.right = new Node(7);
		head.right.right.right.right = new Node(7);

		System.out.println(isBalance(head));

	}

}
