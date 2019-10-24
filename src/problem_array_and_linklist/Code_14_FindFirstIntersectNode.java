package problem_array_and_linklist;
/*
 * 查找单链表是否存在一个相交的节点
 * 判断单链表是否存在环结构，并找出环开始的节点：
 * 		先定义一个F指针和一个S指针，F一次走两步，S一次走一步，如果相遇说明是有环结构，然后
 * 		F指针回到起点，并且开始每次走一步，F和S指针相遇的时候就是那个环的起始节点
 * 有三种情况：1.两个单链表都是无环结构
 * 			2.一个单链表有环，另外一个无环。：这种情况不可能出现相交的节点
 * 			3.两个单链表都有环
 * 对于第一种情况：由于是单链表，所以只会存在一个next指针，存在两种情况，相交和不相交，如果存在相交
 * 			  的节点，会呈现   Y 这种情况先求出两个单链表的最后一个节点是否内存地址相同（就是是否是
 * 			同一个节点），相同说明有交点，所以求出单链表的长度如果单链表长度一个100一个80，
 * 			100-20=80开始对比找交点。
 * 对于第二种情况不可能出现相交的节点
 * 对于第三种情况：也存在三种情况
 * 		1.两个单链表各自成环，不存在交点
 * 		2.两个单链表有相同的环，环的起点都一样
   		3.两个环结构的起点都是第一个相交的点

 * 				
 */

public class Code_14_FindFirstIntersectNode {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Node getIntersectNode(Node head1, Node head2) {
		if (head1 == null || head2 == null) {
			return null;
		}
		// 判断两个链表是否存在环结构，并返回环开始节点
		Node loop1 = getLoopNode(head1);
		Node loop2 = getLoopNode(head2);
		// 两个单链表都是无环结构
		if (loop1 == null && loop2 == null) {
			return noLoop(head1, head2);
		}
		// 两个单链表都有环
		if (loop1 != null && loop2 != null) {
			return bothLoop(head1, loop1, head2, loop2);
		}
		return null;
	}

	public static Node getLoopNode(Node head) {
		// 两个节点才有可能存在环
		if (head == null || head.next == null || head.next.next == null) {
			return null;
		}
		Node n1 = head.next; // n1 -> slow
		Node n2 = head.next.next; // n2 -> fast
		while (n1 != n2) {
			if (n2.next == null || n2.next.next == null) {
				return null;
			}
			n2 = n2.next.next;
			n1 = n1.next;
		}
		n2 = head; // n2 -> walk again from head
		while (n1 != n2) {
			n1 = n1.next;
			n2 = n2.next;
		}
		return n1;
	}

	// 两个都没有环的找到相交的点
	public static Node noLoop(Node head1, Node head2) {
		if (head1 == null || head2 == null) {
			return null;
		}
		Node cur1 = head1;
		Node cur2 = head2;
		int n = 0;
		while (cur1.next != null) {
			n++;
			cur1 = cur1.next;
		}
		while (cur2.next != null) {
			n--;
			cur2 = cur2.next;
		}
		// 这里判断两个单链表的最后一个节点是否相等（是否是同一个），
		if (cur1 != cur2) {
			return null;// 如果不是同一个，就没有相交的点，返回null
		}
		// 需要把两个单链表从头节点开始截取到头结点长度一致，才能两个单链表一一对质，否则找不到交点
		cur1 = n > 0 ? head1 : head2;// cur1存放长的单链表
		cur2 = cur1 == head1 ? head2 : head1;// cur2存放另外一个
		n = Math.abs(n);
		while (n != 0) {
			n--;
			cur1 = cur1.next;
		}
		while (cur1 != cur2) {
			cur1 = cur1.next;
			cur2 = cur2.next;
		}
		return cur1;
	}

	public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
		Node cur1 = null;
		Node cur2 = null;
		// 如果两个环相等，说明是第二种情况，找到两个单链表对比的起始点和终止点一一对比就行了
		if (loop1 == loop2) {
			cur1 = head1;
			cur2 = head2;
			int n = 0;
			while (cur1 != loop1) {
				n++;
				cur1 = cur1.next;
			}
			while (cur2 != loop2) {
				n--;
				cur2 = cur2.next;
			}
			cur1 = n > 0 ? head1 : head2;
			cur2 = cur1 == head1 ? head2 : head1;
			n = Math.abs(n);
			while (n != 0) {
				n--;
				cur1 = cur1.next;
			}
			while (cur1 != cur2) {
				cur1 = cur1.next;
				cur2 = cur2.next;
			}
			return cur1;
		} else {
			cur1 = loop1.next;
			while (cur1 != loop1) {// 让其中一个环遍历一遍，看是否有等于另外一个环的某个节点
				if (cur1 == loop2) {// 是同一个环，但是两环起点不一致
					return loop1;// 这里也可以返回loop2
				}
				cur1 = cur1.next;
			}
			return null;// 这里是连个环不相等，两环各自成环
		}
	}

	public static void main(String[] args) {
		// 1->2->3->4->5->6->7->null
		Node head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		head1.next.next.next.next.next.next = new Node(7);

		// 0->9->8->6->7->null
		Node head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next.next.next.next.next; // 8->6
		System.out.println(getIntersectNode(head1, head2).value);

		// 1->2->3->4->5->6->7->4...
		head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		head1.next.next.next.next.next.next = new Node(7);
		head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

		// 0->9->8->2...
		head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next; // 8->2
		System.out.println(getIntersectNode(head1, head2).value);

		// 0->9->8->6->4->5->6..
		head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next.next.next.next.next; // 8->6
		System.out.println(getIntersectNode(head1, head2).value);

	}

}
