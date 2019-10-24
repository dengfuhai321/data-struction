package problem_array_and_linklist;
/*
 * 简单地实现数组转化成队列和栈
 */

public class Code_04_Array_To_Stack_Queue {
	// 转化成栈
	public static class ArrayStack {
		private Integer[] arr;
		private Integer index;

		// 初始化栈的空间,index并指向数组下标为0
		public ArrayStack(int initSize) {
			if (initSize < 0) {
				throw new IllegalArgumentException("The init size is less than 0");
			}

			arr = new Integer[initSize];
			index = 0;
		}

		public Integer peek() {
			if (index == 0) {
				return null;
			}
			return arr[index - 1];
		}

		// 入栈，把对象放到当前下标arr[index]中，index++
		public void push(int obj) {
			if (index == arr.length) {
				throw new ArrayIndexOutOfBoundsException("The queue is full");
			}
			arr[index++] = obj;
		}

		// 出栈，先index--,然后弹出arr[index]的元素
		public Integer pop() {
			if (index == 0) {
				throw new ArrayIndexOutOfBoundsException("The queue is empty");
			}
			return arr[--index];
		}
	}

	// 数组变成队列
	public static class ArrayQueue {
		private Integer[] arr;// 数组
		private Integer size;// 数组中存在元素的长度
		private Integer first;// 出队的指针
		private Integer last;// 入队的指针
		// 初始化数组

		public ArrayQueue(int initSize) {
			if (initSize < 0) {
				throw new IllegalArgumentException("The init size is less than 0");
			}
			arr = new Integer[initSize];
			size = 0;
			first = 0;
			last = 0;
		}

		public Integer peek() {
			if (size == 0) {
				return null;
			}
			return arr[first];
		}

		// 入队，
		public void push(int obj) {
			// 如果数组中存放的元素size=数组的长度了，队列就满了，不在入队
			if (size == arr.length) {
				throw new ArrayIndexOutOfBoundsException("The queue is full");
			}
			// 否则队列不满，元素长度+1，元素放到下标为last的数组位置
			size++;
			arr[last] = obj;
			last = last == arr.length - 1 ? 0 : last + 1;// 然后last+1,如果到队尾了，就返回到数组的0位置
		}

		// 出队
		public Integer poll() {
			// 如果数组中存放的元素size=0，队列元素为空，不在出队
			if (size == 0) {
				throw new ArrayIndexOutOfBoundsException("The queue is empty");
			}
			size--;// 元素-1
			int tmp = first;// 弹出first所在位置的元素
			first = first == arr.length - 1 ? 0 : first + 1;// 然后last+1,如果到数组的队尾了，就返回到数组的0位置
			return arr[tmp];
		}
	}

	public static void main(String[] args) {

	}

}
