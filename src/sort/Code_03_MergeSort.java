package sort;

/*
 * 归并排序   归并->merge		速度仅次于快速排序，为稳定排序算法，
 * 
 * 排序思想：把要排序的数组分成左右两部分，再把有序的两部分对比，从小到大，挨个放到暂时的新数组中，排好序再放回到原数组中，
 * 利用递归给两部分分别再按照以上步骤排序
 * 
 * 可以做到稳定性：每次相等取左边区域的值放入数组
 * 时间复杂度 O（N*logN）
 */
public class Code_03_MergeSort {
	public static void mergeSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		mergeSort(arr, 0, arr.length - 1);
	}

	// 这里往下采用了递归调用的算法，可以使用样本容量公式求时间复杂度,!!!!!记住只分析最表面的数据
	public static void mergeSort(int[] arr, int l, int r) {
		if (l == r) {// 左右都是同一个数，不操作直接返回
			return;
		}
		// 计算中点mid（！！！！！！！！！！！！！！！记忆）
		int mid = l + ((r - l) >> 1);// 这里是求L和R的重点位置，mid=（L+R）/2
		// 排序左部分
		mergeSort(arr, l, mid);// 这里样本容量是 T(N/2)
		// 排序右部分
		mergeSort(arr, mid + 1, r);// 这里样本容量也是 T(N/2)
		// 核心：1.用来比较2.按顺序把元素存放到新建的数组中，3.把排好序的数组存放到原来要排序的数组中
		merge(arr, l, mid, r);// 这里的时间复杂度 O（N）
		// T(N)=2T(N/2)+O(N) ->log(2,2)=1 -> 时间复杂度 O（N*logN） 这个时间复杂度永远比O(N^2)高级
	}

	// 给两个数组排序，定义一个暂时的数组，左右两个数组同时从第一个元素比较，小的填入暂时数组中，
	// 然后改数组下标后移，继续比较。当其中一个数组比较完之后，把另外的数组中的元素直接放到后面
	public static void merge(int[] arr, int l, int m, int r) {
		int[] help = new int[r - l + 1];// 创建一个新数组，用来暂时存放排好序的元素
		int i = 0;// 给定新数组的下标
		int p1 = l;// 比较元素，左半部分的起始位置
		int p2 = m + 1;// 比较元素，右半部分的起始位置
		while (p1 <= m && p2 <= r) {// 在左下标和右下标同时都没有越界时
			help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];// 左右比较，小的填入新建的数组中，并且下标++
		}
		while (p1 <= m) {// 当p1<=m说明p2中的数据都己经填到新的数组中，并且最后下标越界了，所以把p1下标之后的数据都填到新数组中
			help[i++] = arr[p1++];// 直到两个下标都越界，就说明数据填写完整了
		}
		while (p2 <= r) {
			help[i++] = arr[p2++];
		}
		for (i = 0; i < help.length; i++) {// 这里把排好序的数组元素遍历到原来需要排序的数组中相应的部分
			arr[l + i] = help[i];
		}
	}

}
