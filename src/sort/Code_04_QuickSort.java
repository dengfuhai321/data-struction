package sort;

import java.util.Arrays;

/*
 * 快速排序：   做不到稳定性
 * 排序思路：取数组中最后一个数作为num,把数组按照num分成大于num放到左边，小于num放到右边，等于num放到中间
 * 
 * 在快速排序的时候，选择要比较的数，但是在经典排序时可能出现问题。例：坏情况
 *  6 5 4 3 2 1 给这快速收排序时，如果每次选择r(就是最右面的数来比较)，就会出现每次左右分区的时候小于r的区域不存在
 *  ，而大于r的区域的数据每次排序都只是-1，所以这样的例子，就会出现时间复杂度变成O（N^2）
 * 
 * 所以在选择要比较的数，可以随机选择数组中一个数，这样的话时间复杂度就是一个概率的表达式，
 * 就是一个长期期望的时间复杂度O(N*logN)，不解释
 * 
 * 额外时间复杂度是O(logN),这个额外的时间复杂度是记录中间值num的,因为它必须记录下num，执行完左半部分，才可以释放num,
 * 执行右半部分，所以最坏的期望是O(N),就是上面的坏情况，最好的期望是logN,就是N可以二分拆几次，所以额外时间复杂度又是
 * 一个概率，长期期望是O(logN)
 * 
 * 长期期望就是时间复杂度
 */

public class Code_04_QuickSort {
	public static void quickSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		quickSort(arr, 0, arr.length - 1);
	}

	public static void quickSort(int[] arr, int l, int r) {
		if (l < r) {// 如果l<r，就继续排序，否则return
			// 下面这句是一个随机排序的，加上就会在数组中每次随机选择一个数作为num，避免了一种特殊情况
			// swap(arr, l + (int) (Math.random() * (r - l + 1)),
			// r);就是在数组中随机选择了一个数和最后一个数交换，之后最后一个数作为num

			swap(arr, r, (int) (Math.random() * (r - l + 1)));
			int[] p = partition(arr, l, r);// 排序，大的放左边，小的放右边，等于放中间的函数，返回等于r的下标范围
			quickSort(arr, l, p[0] - 1);// 在把小的范围排序，递归
			quickSort(arr, p[1] + 1, r);// 把大的范围排序，递归
		}
	}

	public static int[] partition(int[] arr, int l, int r) {
		int less = l - 1;
		int more = r;
		while (l < more) {// 这里是把数组中l和r范围中的数和arr[r]比较，小的放左边，大的放右边，等于放中间
			if (arr[l] < arr[r]) {// 它把arr[r]作为比较的数
				swap(arr, ++less, l++);
			} else if (arr[l] > arr[r]) {
				swap(arr, --more, l);
			} else {// 等于比较的数会自动交换到中间不用处理，直接下标后移就可
				l++;
			}
		}
		swap(arr, more, r);// 把再r位置放的作为比较的数与得出比r大的位置的数more交换，把自身放到数组中间，大的放到右边
		return new int[] { less + 1, more };// 返回等于arr[r]中间的下标位置
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
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
		int testTime = 1;
		int maxSize = 10;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			quickSort(arr1);
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
		quickSort(arr);
		printArray(arr);

	}

}
