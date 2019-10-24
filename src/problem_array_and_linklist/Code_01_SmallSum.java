package problem_array_and_linklist;

/*	求小和问题
 * 数组元素     1  4  3  5  7     求比元素左侧小的元素之和       1+1+1+4+3+1+4+3+5
 * 比元素4小的左侧元素之和=1 +比元素3小的左侧元素之和=1 +比元素5小的左侧元素之和=1+4+3 +比元素7小的左侧元素之和=1+4+3+5
 * 结题思路：在归并排序的前提下，用mid分成左右两部分，左部分比较求得最小和+右边部分求得最小和+左右两边对比分批求得最小和
 * 左右两边分批对比：在归并排序的情况下，左右两边会在各自的范围中排好序，这样如果左边部分的某个数小于右边的某个数，
 * 左边1 3 4右边 5 7  1和5比较，1<5 因为右边5 7排好序了，7也大于1，所以直接得出比1大的右边部分的小,1*2（数量）
 * 这个2是可以r-mid得出
 */
public class Code_01_SmallSum {

	public static int smallSum(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		return mergeSort(arr, 0, arr.length - 1);
	}

	public static int mergeSort(int[] arr, int l, int r) {
		if (l == r) {
			return 0;
		}
		int mid = l + ((r - l) >> 1);
		// 这里返回的是比元素左侧大的元素之和
		return mergeSort(arr, l, mid) + mergeSort(arr, mid + 1, r) + merge(arr, l, mid, r);
		// 其中包括左侧元素结果之和 + 右侧元素结果之和 +左右两边分批对比求得比右侧元素小的元素之和
	}

	public static int merge(int[] arr, int l, int m, int r) {
		int[] help = new int[r - l + 1];
		int i = 0;
		int p1 = l;
		int p2 = m + 1;
		int res = 0;
		// 它在归并排序之前分批求和
		while (p1 <= m && p2 <= r) {
			res += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;// 分批求和，加快速度
			help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];// 归并排序
		}
		while (p1 <= m) {
			help[i++] = arr[p1++];
		}
		while (p2 <= r) {
			help[i++] = arr[p2++];
		}
		for (i = 0; i < help.length; i++) {
			arr[l + i] = help[i];
		}
		return res;
	}

	// for test绝对正确的方法
	public static int comparator(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int res = 0;
		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j < i; j++) {
				res += arr[j] < arr[i] ? arr[j] : 0;
			}
		}
		return res;
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
		int testTime = 1000;// 生成1000个数组来比较两个方法是否一致来判断方法是否正确
		int maxSize = 5;
		int maxValue = 10;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			if (smallSum(arr1) != comparator(arr2)) {
				succeed = false;
				printArray(arr1);
				printArray(arr2);
				break;
			} else {
				System.out.println(smallSum(arr1));
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}

}
