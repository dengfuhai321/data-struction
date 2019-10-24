package string;

/*
 * 实现快速查找字符串的功能，以及查找以某字符串为前缀的数量，插入字符串，删除字符串等等
 * 解决思路：没有把字符串的字母放到每个节点中
 * 而是先定义一个头结点，然后有26个字母，就是头节点可以指向26个节点，存在26个路径，每个路径对应着一个字母
 * 然后定义其他的属性
 */
public class Code_00_TrieTree {
	// 定义节点类
	public static class TrieNode {
		public int path;// 有几个单词通过这个节点
		public int end;// 有几个是以这个节点结尾
		public TrieNode[] nexts;// 指向下一个节点

		public TrieNode() {
			path = 0;
			end = 0;
			nexts = new TrieNode[26];// 指向26个字母
		}
	}

	// 定义该特殊功能字符串的结构
	public static class Trie {
		private TrieNode root;// 头节点

		public Trie() {
			root = new TrieNode();
		}

		// 插入一个字符串
		public void insert(String word) {
			if (word == null) {
				return;
			}
			char[] chs = word.toCharArray();// 字符串变成字符数组
			TrieNode node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				if (node.nexts[index] == null) {
					node.nexts[index] = new TrieNode();
				}
				node = node.nexts[index];// 指向下一个节点
				node.path++;// 节点的长度+1
			}
			node.end++;// 一个字符串结束就++
		}

		// 删除某个 单词
		public void delete(String word) {
			if (search(word) != 0) {
				char[] chs = word.toCharArray();
				TrieNode node = root;
				int index = 0;
				for (int i = 0; i < chs.length; i++) {
					index = chs[i] - 'a';
					if (--node.nexts[index].path == 0) {
						node.nexts[index] = null;
						return;// 如果path已经等于0，下面的节点就不用看了，直接为null,然后结束就行
					}
					node = node.nexts[index];// 如果path不为0指向下一个节点
				}
				node.end--;
			}
		}

		// 查询有几个这样的字符串
		public int search(String word) {
			if (word == null) {
				return 0;
			}
			char[] chs = word.toCharArray();
			TrieNode node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				if (node.nexts[index] == null) {
					return 0;
				}
				node = node.nexts[index];
			}
			return node.end;
		}

		// 查某个字符串的前缀有多少
		public int prefixNumber(String pre) {
			if (pre == null) {
				return 0;
			}
			char[] chs = pre.toCharArray();
			TrieNode node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				if (node.nexts[index] == null) {
					return 0;
				}
				node = node.nexts[index];
			}
			return node.path;
		}
	}

	public static void main(String[] args) {
		Trie trie = new Trie();
		System.out.println(trie.search("zuo"));
		trie.insert("zuo");
		System.out.println(trie.search("zuo"));
		trie.delete("zuo");
		System.out.println(trie.search("zuo"));
		trie.insert("zuo");
		trie.insert("zuo");
		trie.delete("zuo");
		System.out.println(trie.search("zuo"));
		trie.delete("zuo");
		System.out.println(trie.search("zuo"));
		trie.insert("zuoa");
		trie.insert("zuoac");
		trie.insert("zuoab");
		trie.insert("zuoad");
		trie.delete("zuoa");
		System.out.println(trie.search("zuoa"));
		System.out.println(trie.prefixNumber("zuo"));

	}

}
