package test;

import java.util.Arrays;

public class Code_05_MergeSort {

	public static void mergeSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		mergeSort(arr, 0, arr.length - 1);
	}

	// 这里往下采用了递归调用的算法，可以使用样本容量公式求时间复杂度,!!!!!记住只分析最表面的数据
	public static void mergeSort(int[] arr, int l, int r) {
		if (l == r) {
			return;
		}
		int mid = l + ((r - l) >> 1);// 这里是求L和R的重点位置，mid=（L+R）/2
		// 这里采用递归，把左半部分排好序
		mergeSort(arr, l, mid);// 这里样本容量是 T(N/2)
		// 这里把有半部分排好序
		mergeSort(arr, mid + 1, r);// 也是T（N/2）
		// 核心：1.用来比较2.按顺序把元素存放到新建的数组中，3.把排好序的数组存放到原来要排序的数组中
		merge(arr, l, mid, r);// 这里的时间复杂度 O（N）
		// T(N)=2T(N/2)+O(N) ->log(2,2)=1 -> 时间复杂度 O（N*logN） 这个时间复杂度永远比O(N^2)高级
	}

	public static void merge(int[] arr, int l, int m, int r) {
		int[] help = new int[r - l + 1];// 创建一个新数组，用来暂时存放排好序的元素
		int i = 0;
		int p1 = l;
		int p2 = m + 1;
		while (p1 <= m && p2 <= r) {// 在左下标和右下标同时都没有越界时
			help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];// 左右比较，小的填入新建的数组中，并且下标++
		}
		while (p1 <= m) {// 当泡p1<=m说明p2中的数据都己经填到新的数组中，并且最后下标越界了，所以把p1下标之后的数据都填到新数组中
			help[i++] = arr[p1++];// 直到两个下标都越界，就说明数据填写完整了
		}
		while (p2 <= r) {// 同上
			help[i++] = arr[p2++];
		}
		for (i = 0; i < help.length; i++) {// 这里把排好序的数组元素遍历到原来需要排序的数组中相应的部分
			arr[l + i] = help[i];
		}
	}

	// for test
	public static void comparator(int[] arr) {
		Arrays.sort(arr);
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}

	// for test
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	// for test
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 10;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			mergeSort(arr1);
			comparator(arr2);
			if (!isEqual(arr1, arr2)) {
				succeed = false;
				printArray(arr1);
				printArray(arr2);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

		int[] arr = generateRandomArray(maxSize, maxValue);
		printArray(arr);
		mergeSort(arr);
		printArray(arr);

	}

}
