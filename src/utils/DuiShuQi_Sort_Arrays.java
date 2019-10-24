package utils;

import java.util.Arrays;
/*
 * 判断自定义数组排序是否正确
 */

import sort.Code_01_SelectionSort;
import sort.Code_02_InsertionSort;

public class DuiShuQi_Sort_Arrays {
	// for test 准备的绝对正确的排序
	public static void comparator(int[] arr) {
		Arrays.sort(arr); // 系统给的排序是从小到大排序的
	}

	// for test 生成随机数组
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		// Math.random()——>double[0,1)
		// (int)(maxSize+1)*Math.random())-> 生成[0,maxSize]范围的int整数
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}

	// for test 复制数组
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

	// for test 比较两个数组是否相等
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

	// for test 输出数组
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// for test 对数器的流程
	public static void main(String[] args) {
		int testTime = 500000; // 测试的次数
		int maxSize = 10; // 测试生成的随机数组的长度 100
		int maxValue = 100; // 测试生成随机数组元素的值得范围-100 ~100
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue); // 生成随机数组
			int[] arr2 = copyArray(arr1); // 复制生成的数组
			// Code_00_BubbleSort.bubbleSort(arr1); // 冒泡排序
			// Code_01_SelectionSort.selectionSort(arr1); // 选择排序
			Code_02_InsertionSort.insertionSort(arr1); // 插入排序
			// Code_03_MergeSort.mergeSort(arr1); // 归并排序

			comparator(arr2); // 你准备的绝对正确的排序
			if (!isEqual(arr1, arr2)) {
				succeed = false;
				break;
			}
		}
		// 控制台打印
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

		int[] arr = generateRandomArray(maxSize, maxValue);
		printArray(arr);
		// Code_00_BubbleSort.bubbleSort(arr);
		Code_01_SelectionSort.selectionSort(arr);
		// Code_02_InsertionSort.insertionSort(arr);
		// Code_03_MergeSort.mergeSort(arr);

		printArray(arr);
	}

}
