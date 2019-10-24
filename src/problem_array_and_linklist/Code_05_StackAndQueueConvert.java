package problem_array_and_linklist;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Code_05_StackAndQueueConvert {
	/*
	 * 实现两个栈变成队列
	 * 解题思路：存放数据的时候 （就是入队），就正常进栈，
	 * 但是出队的时候，先把数据都出栈，出到另外一个栈中，留下最后一个数，就是出队。
	 * 比如:12345入队就是（队尾）54321（队头），出队就是 1。
	 * 两个栈，12345入栈就是 （栈底）12345（栈顶），出栈，然后入栈到另一个栈中，（栈底）54321（栈顶）
	 * 最后出栈 就是 1，
	 */
	public static class TwoStacksQueue {
		private Stack<Integer> stackPush;
		private Stack<Integer> stackPop;

		public TwoStacksQueue() {
			stackPush = new Stack<Integer>();
			stackPop = new Stack<Integer>();
		}
		//入栈正常入栈其中一个
		public void push(int pushInt) {
			stackPush.push(pushInt);
		}

		public int poll() {
			if (stackPop.empty() && stackPush.empty()) {
				throw new RuntimeException("Queue is empty!");
	//当stackPop为空时，需要把stackPush栈中的数据出栈到stackPop栈中，然后返回Stackpop出栈的元素就ok	
			} else if (stackPop.empty()) {
				while (!stackPush.empty()) {
					stackPop.push(stackPush.pop());
				}
			}
	//当已经从一个栈中出到另外一个栈中，就不为空，就不再执行了，直接返回Stackpop出栈就ok
			return stackPop.pop();
		}

		public int peek() {
			if (stackPop.empty() && stackPush.empty()) {
				throw new RuntimeException("Queue is empty!");
			} else if (stackPop.empty()) {
				while (!stackPush.empty()) {
					stackPop.push(stackPush.pop());
				}
			}
			return stackPop.peek();
		}
	}
	/*
	 * 实现两个队列变成栈
	 * 解题思路：存放数据的时候 （就是入队），就正常入队，
	 * 但是出队的时候，先把数据都出队，出到另外一个队中，留下最后一个数，就是出队。
	 * 比如:12345入栈就是（栈底）12345（栈顶），出栈就是5。
	 * 两个队列，12345入队就是 （队尾）54321（队头），出队至留下一个数，剩下入队到另一个队列中，（队尾）4321（队头）
	 * 最后出栈 就是 1，然后把两个队列指针交换，重新指向有数据的队列
	 */
	public static class TwoQueuesStack {
		private Queue<Integer> queue;
		private Queue<Integer> help;

		public TwoQueuesStack() {
			queue = new LinkedList<Integer>();
			help = new LinkedList<Integer>();
		}

		public void push(int pushInt) {
			queue.add(pushInt);
		}

		public int peek() {
			if (queue.isEmpty()) {
				throw new RuntimeException("Stack is empty!");
			}
			while (queue.size() != 1) {
				help.add(queue.poll());
			}
			int res = queue.poll();
			help.add(res);
			swap();
			return res;
		}

		public int pop() {
			if (queue.isEmpty()) {
				throw new RuntimeException("Stack is empty!");
			}
			while (queue.size() > 1) {
				help.add(queue.poll());
			}
			int res = queue.poll();//这是出队的最后一个数，就相当于出栈
			swap();
			return res;
		}

		private void swap() {
			Queue<Integer> tmp = help;
			help = queue;
			queue = tmp;
		}

	}

}
