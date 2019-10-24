package sort;

/*
 * 冒泡排序
 * 前提：寻址，取数，交换都是常数,常数的时间复杂度都是 O(1)
 * 第一次循环arr.length次，第二次循环arr.length-1次   。。。。。
 * 所以时间复杂度就是一个等差数列的前n项和an^2+bn+1,
 * 省略系数 
 * 时间复杂度：O(n^2)   n应该就是arr.length
 * 稳定的
 */
public class Code_00_BubbleSort {
	public static void bubbleSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		// 冒泡排序的循环，每次循环两两比较找出最大的或者最小的，然后再把剩下的在两两比较，知道没有了
		for (int a = arr.length - 1; a > 0; a--) {// 两两比较的循环几次，并且获得每次循环的两两比较的次数
			for (int i = 0; i < a; i++) {// 没有状态，每次把循环进行到底
				if (arr[i] > arr[i + 1]) {// 这里是稳定性的关键，在两两比较的时候只有大于的时候才交换，沉到最后，等于的值不加换，所以稳定
					swap(arr, i, i + 1);
				}

			}
		}

	}

	// 交换数组arr的两个下标的值(！！！！！！！！！！！！！！记忆)
	public static void swap(int[] arr, int i, int j) {
		arr[i] = arr[i] ^ arr[j];// 与(&)、非(~)、或(|)、异或(^)_
		arr[j] = arr[i] ^ arr[j];
		arr[i] = arr[i] ^ arr[j];

	}

}
