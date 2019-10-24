package sort;
/*
 * 插入排序
 * 工作原理：每步将一个待排序的记录，按其关键码值的大小插入前面已经排序的文件中适当位置上，直到全部插入完为止。
 * 
 * 时间复杂度：（取最差的时间） O(n^2)
 * 如果数组上来的排序就是正确的，每次循环的时候判断状态不成立，就不循环，那么时间复杂度就是O(n) n代表外层循环的 次数
 * 但是如果数组的培训是逆序的，那么每次循环的时候状态都会成立，然后交换数据，又是一个等差数列，时间复杂度就是O(n^2)
 * 
 * 可以做到稳定性
 * 
 * 插入排序虽然时间复杂度比较高，大样本的时候不行，但在一定范围内（60），小样本的时候，它的常数项很低，所以速度非常快.
 */

public class Code_02_InsertionSort {
	public static void insertionSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		for (int i = 1; i < arr.length; i++) {
			for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {// 这里看一个状态，如果不成立，这次循环就什么也不干
				swap(arr, j, j + 1);
			}
		}
	}

	public static void swap(int[] arr, int i, int j) {
		arr[i] = arr[i] ^ arr[j];
		arr[j] = arr[i] ^ arr[j];
		arr[i] = arr[i] ^ arr[j];

	}

}
