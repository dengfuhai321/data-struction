package hashMap_problem;

import java.util.HashMap;
import java.util.List;

/*       并差集结构
 * 查询次数+合并次数 等于O(n)及其以上，它的时间复杂度就会是O(1)
 * 用途：(1)非常快地检查两个元素是否是同一个集合
 * 	    (2)把两个元素各自所在的集合合并在一起
 *	解决思路：(1)让每个元素所在的那个集合存在一个指向自己的头部。如：|1| <- 3 <- 4 <- 2 <- 6 其中1指向自己
 *这样只要让元素一致往上指，指到自己，判断该元素是否是同一个，就可以判断是否是同一个集合 
 *		(2)在合并两个集合时，可以使用(1)的解决办法，然后让集合元素少的集合头部指向多的集合头部
 *		集合的优化：在每次查找其中某个节点的头结点时，把该节点的父节点爷爷节点等都直接指向头结点，下面的节点可以先不管，
 *以后该结构就可以快速找到自己的头结点，判断自己属于哪个集合
 */
public class Code_04_UnionFind {

	public static class Node {
		// whatever you like
	}

	public static class UnionFindSet {
		public HashMap<Node, Node> fatherMap;
		public HashMap<Node, Integer> sizeMap;

		public UnionFindSet(List<Node> nodes) {
			makeSets(nodes);
		}

		// 初始化fatherMap代表每个节点的集合和每个节点的长度
		// 这里只是让List集合中的每个元素都变成一个长度为1的集合
		private void makeSets(List<Node> nodes) {
			fatherMap = new HashMap<Node, Node>();
			sizeMap = new HashMap<Node, Integer>();
			for (Node node : nodes) {
				fatherMap.put(node, node);
				sizeMap.put(node, 1);
			}
		}

		// 找到每个集合的头结点，并把途中的节点直接指向头结点
		private Node findHead(Node node) {
			Node father = fatherMap.get(node);// 得到传入节点的父节点
			if (father != node) {// 如果该节点不等于自己本身，说明
				father = findHead(father);
			}
			fatherMap.put(node, father);
			return father;
		}

		// 判断两个元素节点是否是同一个集合
		public boolean isSameSet(Node a, Node b) {
			return findHead(a) == findHead(b);
		}

		// 合并集合，短的集合接到长的集合下面
		public void union(Node a, Node b) {
			if (a == null || b == null) {
				return;
			}
			Node aHead = findHead(a);
			Node bHead = findHead(b);
			if (aHead != bHead) {
				int aSetSize = sizeMap.get(aHead);
				int bSetSize = sizeMap.get(bHead);
				if (aSetSize <= bSetSize) {
					fatherMap.put(aHead, bHead);
					sizeMap.put(bHead, aSetSize + bSetSize);
				} else {
					fatherMap.put(bHead, aHead);
					sizeMap.put(aHead, aSetSize + bSetSize);
				}
			}
		}

	}

}
