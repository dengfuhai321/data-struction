package problem_array_and_linklist;

import java.util.Stack;

/*
 * 实现一个特殊的栈 ，在实现栈的基础功能的前提下，再实现返回栈中最小元素的操作
 * 要求pop，push，getMin操作的时间复杂度都是O（1）
 * 设计的栈类型可以使用现成的栈结构
 * 
 * 解题思路：弄两个栈，一个正常存放数据，另外一个只入比之前的栈顶小的元素
 */
public class Code_05_GetMinStack {
	public static class MyStack1 {
		private Stack<Integer> stackData;
		private Stack<Integer> stackMin;

		public MyStack1() {
			this.stackData = new Stack<Integer>();
			this.stackMin = new Stack<Integer>();
		}

		public void push(int newNum) {
			if (this.stackMin.isEmpty()) {
				this.stackMin.push(newNum);
			} else if (newNum <= this.getmin()) {
				this.stackMin.push(newNum);
			}
			this.stackData.push(newNum);
		}

		public int pop() {
			if (this.stackData.isEmpty()) {
				throw new RuntimeException("Your stack is empty.");
			}
			int value = this.stackData.pop();
			if (value == this.getmin()) {
				this.stackMin.pop();
			}
			return value;
		}

		public int getmin() {
			if (this.stackMin.isEmpty()) {
				throw new RuntimeException("Your stack is empty.");
			}
			return this.stackMin.peek();
		}
	}

	public static class MyStack2 {
		// 正常存放数据的栈
		private Stack<Integer> stackData;
		// 求最小值得栈
		private Stack<Integer> stackMin;

		public MyStack2() {
			this.stackData = new Stack<Integer>();
			this.stackMin = new Stack<Integer>();
		}

		// 入栈
		public void push(int newNum) {
			// 如果栈为空，最小栈直接入栈
			if (this.stackMin.isEmpty()) {
				this.stackMin.push(newNum);
				// 如果要入栈的元素比栈顶的元素小，就把该元素（小的元素）入栈
			} else if (newNum < this.getmin()) {
				this.stackMin.push(newNum);
				// 如果要入栈的元素>=栈顶的元素，就把该栈顶的元素再入栈
			} else {
				int newMin = this.stackMin.peek();// 返回栈顶的元素，但并不删除
				this.stackMin.push(newMin);
			}
			// 元素正常入普通的栈
			this.stackData.push(newNum);
		}

		public int pop() {
			if (this.stackData.isEmpty()) {
				throw new RuntimeException("Your stack is empty.");
			}
			this.stackMin.pop();
			// 返回正常的出栈元素
			return this.stackData.pop();
		}

		// 求最小值，最小栈的栈顶永远都是最小的，直接出栈就行
		public int getmin() {

			if (this.stackMin.isEmpty()) {
				throw new RuntimeException("Your stack is empty.");
			}
			return this.stackMin.peek();
		}
	}

	public static void main(String[] args) {
		MyStack1 stack1 = new MyStack1();
		stack1.push(3);
		System.out.println(stack1.getmin());
		stack1.push(4);
		System.out.println(stack1.getmin());
		stack1.push(1);
		System.out.println(stack1.getmin());
		System.out.println(stack1.pop());
		System.out.println(stack1.getmin());

		System.out.println("=============");

		MyStack1 stack2 = new MyStack1();
		stack2.push(3);
		System.out.println(stack2.getmin());
		stack2.push(4);
		System.out.println(stack2.getmin());
		stack2.push(1);
		System.out.println(stack2.getmin());
		System.out.println(stack2.pop());
		System.out.println(stack2.getmin());
	}

}
