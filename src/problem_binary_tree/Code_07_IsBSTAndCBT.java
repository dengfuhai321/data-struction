package problem_binary_tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * 搜索二叉树（一般没有相同的值）：就是在中序排序下值是升序的，picture_tree目录下tree_find
 * 完全二叉树
 */
public class Code_07_IsBSTAndCBT {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	/*
	 * 非递归中序遍历的修改成返回是否是搜索二叉树的function
	 */
	public static boolean isBST1(Node head) {
		int last = Integer.MIN_VALUE;
		boolean is = true;
		if (head != null) {
			Stack<Node> stack = new Stack<Node>();

			/*
			 * 首先当前节点是否为空，不为空，就压入栈中，往左子树走，当当前节点为空时， 弹出栈中数据 并打印，然后指向右子树，然后再次判断
			 *
			 */
			while (!stack.isEmpty() || head != null) {
				if (head != null) {
					stack.push(head);
					head = head.left;
				} else {
					head = stack.pop();
					if (head.value <= last) {
						is = false;
						break;
					} else {
						last = head.value;
					}

					head = head.right;
				}
			}
		}
		return is;
	}

	public static boolean isBST(Node head) {
		if (head == null) {
			return true;
		}
		boolean res = true;
		Node pre = null;
		Node cur1 = head;
		Node cur2 = null;
		while (cur1 != null) {
			cur2 = cur1.left;
			if (cur2 != null) {
				while (cur2.right != null && cur2.right != cur1) {
					cur2 = cur2.right;
				}
				if (cur2.right == null) {
					cur2.right = cur1;
					cur1 = cur1.left;
					continue;
				} else {
					cur2.right = null;
				}
			}
			if (pre != null && pre.value > cur1.value) {
				res = false;
			}
			pre = cur1;
			cur1 = cur1.right;
		}
		return res;
	}

	/*
	 * 判断是否是完全二叉树 解决思路：按层遍历判断，如果某节点的左节点存在，右节点不存在，return false
	 * 如果出现某节点的左节点存在，右节点不存在的状态，这时还是完全二叉树，但该节点之后的节点就是叶子节点了，
	 * 其左右子树应该都为空，才符合完全二叉树，return true
	 */
	public static boolean isCBT(Node head) {
		if (head == null) {
			return true;
		}
		Queue<Node> queue = new LinkedList<Node>();// 申请一个队列
		boolean leaf = false;// 出现那个左节点不空，右节点为空的状态时，变为true
		Node l = null;
		Node r = null;
		queue.offer(head);
		while (!queue.isEmpty()) {
			head = queue.poll();
			l = head.left;
			r = head.right;
			// 在节点的子节点左不空，右空状态下，右侧节点的子节点不为空 在节点的左子节点为空右子节点不为空
			if ((leaf && (l != null || r != null)) || (l == null && r != null)) {
				return false;
			}
			if (l != null) {
				queue.offer(l);
			}
			if (r != null) {
				queue.offer(r);
			} else {
				leaf = true;// 如果左子节点不空，右节点为空，开启那个状态
			}
		}
		return true;
	}

	// for test -- print tree
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
		Node head = new Node(4);
		head.left = new Node(2);
		head.right = new Node(6);
		head.left.left = new Node(1);
		head.left.right = new Node(3);
		head.right.left = new Node(5);

		printTree(head);
		System.out.println(isBST1(head));
		System.out.println(isCBT(head));

	}
}
