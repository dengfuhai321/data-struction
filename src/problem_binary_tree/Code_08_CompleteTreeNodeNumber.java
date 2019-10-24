package problem_binary_tree;

/*
 * 查找完全二叉树的节点数                    图解：picture_tree目录中wanquan_tree_jiedianshu .bmp
 * i层的满二叉数的几点数是2^i-1
 * 解决思路：就是看最后一行的叶子节点占位，然后通过计算满二叉树的公式每次计算将近一半的的节点，每次
 * 加上左或者右的一个满二叉树
 */

public class Code_08_CompleteTreeNodeNumber {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static int nodeNum(Node head) {
		if (head == null) {
			return 0;
		}
		return bs(head, 1, mostLeftLevel(head, 1));
	}

	public static int bs(Node node, int l, int h) {
		if (l == h) {
			return 1;
		}
		if (mostLeftLevel(node.right, l + 1) == h) {// 先判断根的右子树的层数是否等于二叉树的层数
			// 1<<(h-l)等价2^(h-l)
			return (1 << (h - l)) + bs(node.right, l + 1, h);
		} else {
			return (1 << (h - l - 1)) + bs(node.left, l + 1, h);
		}
	}

	// 计算子树的层数
	public static int mostLeftLevel(Node node, int level) {// 节点，层数
		while (node != null) {
			level++;
			node = node.left;
		}
		return level - 1;
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		System.out.println(nodeNum(head));

	}

}
