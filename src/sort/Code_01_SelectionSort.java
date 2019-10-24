package sort;

/* 选择排序
 * 
 * 工作原理是：第一次从待排序的数据元素中选出最小（或最大）的一个元素， 
 * 存放在序列的起始位置，然后再从剩余的未排序元素中寻找到最小（大）元素，
 * 然后放到已排序的序列的末尾。以此类推，直到全部待排序的数据元素的个数为零。 
 * 选择排序是不稳定的排序方法。
 * 
 * 时间复杂度：有0-arr.length-1次循环，第一次是比较arr.length次，第二次arr.length-1次，还是等差数列所以a1=arr.length,d=1,n是次数
 * 时间复杂度就是求前n项和。（就是arr.length-a次的比较和）
 * 时间复杂度：O(n^2)   n代表arr.length-1
 * 可以做到稳定性
 */

public class Code_01_SelectionSort {

	public static void selectionSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		// 从小到大排序
		for (int i = 0; i < arr.length - 1; i++) {
			int minIndex = i; // 记录最小值得下标
			for (int j = i + 1; j < arr.length; j++) {
				minIndex = arr[j] < arr[minIndex] ? j : minIndex;// 没有状态，每次把循环进行到底
			}
			swap(arr, i, minIndex);
		}

	}

	public static void swap(int[] arr, int i, int j) {
		// arr[i] = arr[i] ^ arr[j];
		// arr[j] = arr[i] ^ arr[j];
		// arr[i] = arr[i] ^ arr[j];
		// ！！！切记选择排序不能使用上面的交换方式
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;

	}

}
