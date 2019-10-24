package hashMap_problem;

/*
 * 使用hash可以使数据！均匀！分布到各个内存地址
 * hash思想：就是hash桶（数组）中，每个桶中存放链表，当链表长度超过一定长度，扩容。
 * 存放数据，数据输入，数据通过hashcode变化均匀分布到整个hash表中。
 * 相同的输入一定存放到相同的桶中，不同的输入也可能到相同的桶中
 * hash表的增删改的时间复杂度是一个常数O(1)
 */
import java.util.HashMap;

/*
 * 设计RandomPool结构【题目】设计一种结构,在该结构中有如下三个功能:
 *  insert (key) :将某个key加入到该结构,做到不重复加入。
 *  delete (key) :将原本在结构中的某个key移除。
 *  getRandom() :等概率随机返回结构中的任何一个key。
 *  【要求】 Insert, delete和getRandom方法的时间复杂度都是 0(1)
 */
public class Code_01_RandomPool {

	public static class Pool<K> {
		private HashMap<K, Integer> keyIndexMap;
		private HashMap<Integer, K> indexKeyMap;
		private int size;

		public Pool() {
			this.keyIndexMap = new HashMap<K, Integer>();
			this.indexKeyMap = new HashMap<Integer, K>();// 这个map用来获取随机key
			this.size = 0;
		}

		public void insert(K key) {
			if (!this.keyIndexMap.containsKey(key)) {// 相同的key不再存入
				this.keyIndexMap.put(key, this.size);
				this.indexKeyMap.put(this.size++, key);
			}
		}

		public void delete(K key) {
			if (this.keyIndexMap.containsKey(key)) {
				int deleteIndex = this.keyIndexMap.get(key);// 如果存在这个key
				int lastIndex = --this.size;// map的size-1，获得最后一个key的下标
				K lastKey = this.indexKeyMap.get(lastIndex);
				this.keyIndexMap.put(lastKey, deleteIndex);// 把最后的key存放到要删除的下标中
				this.indexKeyMap.put(deleteIndex, lastKey);// 把最后的
				this.keyIndexMap.remove(key);
				this.indexKeyMap.remove(lastIndex);
			}
		}

		public K getRandom() {
			if (this.size == 0) {
				return null;
			}
			int randomIndex = (int) (Math.random() * this.size); // 0 ~ size -1
			return this.indexKeyMap.get(randomIndex);
		}

	}

	public static void main(String[] args) {
		Pool<String> pool = new Pool<String>();
		pool.insert("zuo");
		pool.insert("cheng");
		pool.insert("yun");
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());

	}

}
