package problem_array_and_linklist;

/*
 * 荷兰国旗问题：给定一个数组arr,和一个数num，请把小于num的数放到数组的左边，等于num的数放到数组的中间，
 * 大于num的数放到数组的右边
 * 解决思路：左右两边分别划分一个虚拟的范围，小于num的范围less和大于num的范围more，同时获得一个数组中从左往右的指针l，
 * 在数组中待排序的元素(l<more)范围中进行一下步骤
 * 小于num的元素与less+1的元素交换，并且less范围+1，l++,等于num的元素不操作，l++,
 * 大于num的元素与more-1的元素交换，并且more范围-1，因为more-1是待排序的元素，不知道是否大于或者小于，所以l不变
 */
public class Code_02_NetherlandsFlag {
	public static int[] netherlandsFlag(int[] arr, int l, int r, int num) {
		int less = l - 1;
		int more = r + 1;
		while (l < more) {
			if (arr[l] < num) {
				swap(arr, l++, ++less);
			} else if (arr[l] > num) {
				swap(arr, l, --more);
			} else {
				l++;
			}
		}
		// 这里返回分割大于小于等于范围的两个数组下标(就是等于num的元素范围 的下标)
		return new int[] { less + 1, more - 1 };
	}

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;

	}

	// for test
	public static int[] generateArray() {
		int[] arr = new int[10];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 3);
		}
		return arr;
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

	public static void main(String[] args) {
		int[] test = generateArray();

		printArray(test);
		int[] res = netherlandsFlag(test, 0, test.length - 1, 1);
		printArray(test);
		System.out.println(res[0]);
		System.out.println(res[1]);

	}

}
