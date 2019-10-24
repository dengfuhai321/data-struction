package sort;

import java.util.Arrays;
/*
 * 堆排序： 做不到稳定性（在建立大根堆，顺序就会被破坏）
 *初始化建堆的时间复杂度为O(n)，排序重建堆的时间复杂度为nlog(n)，所以总的时间复杂度为O(n+nlogn)=O(nlogn)
 */

public class Code_05_HeapSort {
	public static void heapSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		// 初始化建堆O(n)
		for (int i = 0; i < arr.length; i++) {// 依次把数组的下一个元素放到前面的已经排好的大根堆中
			// 其时间复杂度为：在0-arr.length
			// 每次调整i元素时，i前的元素已经调整是大根堆，需要调整的时间是前面的大根堆的层数logi-1
			// 每个元素的和就是log1+log2+log3+......+logN-1得出时间复杂度为O(N) N就是arr.length
			// !!!!!!!!!!!!!! 这样每进来一个数形成大根堆的代价是logN !!!!!!!!!!!!!!!!!
			heapInsert(arr, i);
		}
		// 排序重建堆nlog(n)
		// 排序思想：从小到大排序
		// 当前是大根堆，数组的首地址元素一定是最大的值，也就是根节点，把根节点和数组最后的元素交换位置，
		// 这样最大值到了最后，大根堆长度-1，最后把新的范围重新变成大根堆
		int size = arr.length;
		swap(arr, 0, --size);// 这里的size是下标
		while (size > 0) {// 0是最后一个节点不再执行
			heapify(arr, 0, size);// 这里的size是范围大小
			swap(arr, 0, --size);
		}
	}

	// 这是把数组变成大根堆结构排序
	public static void heapInsert(int[] arr, int index) {
		// 数组下标为0的父节点还是0，或者在判断中小于父节点，while条件不成立就直接返回
		while (arr[index] > arr[(index - 1) / 2]) {// 数组当前元素与其父节点比较，如果比父节点大就交换，下标变成其父节点的下标，直到完全二叉树的根节点
			swap(arr, index, (index - 1) / 2);// 条件成立，交换当前值
			index = (index - 1) / 2;// 下标变成其父节点，向上判断该元素是否还大于其的爷爷节点。。。。
		}
	}

	/*
	 * 当把二叉树中某节点的值变小，后对二叉树进行重新排序达到大根堆 ,这个方法的时间复杂度是log(N)
	 */
	public static void heapify(int[] arr, int index, int size) {
		int left = index * 2 + 1;// 获得下标为index的左子树
		while (left < size) {// 如果左子树在大堆根的范围中，就
			int largest = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;// 判断index的左右节点谁大
			largest = arr[largest] > arr[index] ? largest : index;// 判断下标为index的值有没有其左右子节点大的值大
			if (largest == index) {// 如果上一行，index的值没有发生改变，说明自己的值大于左右子节点的值，结束不用改变任何位置
				break;
			}
			swap(arr, largest, index);// 如果没有通过if条件，就是左右子节点的值比改变后的index的值大，交换index和左右节点值大的
			index = largest;// 然后把index变成交换值元素的下标，继续判断
			left = index * 2 + 1;// 改变其左节点下标
		}
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
			heapSort(arr1);
			comparator(arr2);
			if (!isEqual(arr1, arr2)) {
				succeed = false;
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

		int[] arr = generateRandomArray(maxSize, maxValue);
		printArray(arr);
		heapSort(arr);
		printArray(arr);
	}

}
