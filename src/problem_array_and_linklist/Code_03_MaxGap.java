package problem_array_and_linklist;

import java.util.Arrays;
/* 经典问题
 * 给定一个数组，求在排序后，相邻两数的最大差值，要求时间复杂度是O(N),且要求不能用基于比较的排序
 * 
 * 准备一个长度是数组长度+1的桶，每个桶包含最大值和最小值和是否为空。
 * 然后把数组中的元素按照每个桶的范围放到该桶中，该桶的范围可以根据数组元素最大值和最小值差除以桶数量。
 * 
 * 答题思路：求最大差值，可以利用桶思想，n个元素就准备n+1个桶，每个桶中值存放最大值，最小值和是否
 * 为空，可以想象成每个桶有一个范围，然后数组中的元素对照范围入桶（专门的方法！记住！）
 * 使第一个桶和最后一个桶有值，所以中间一定会至少有一个桶为空值，这样就可以把同一个桶中的数据最大差值排除掉，
 * 最小差值就在其中两个桶中元素的差值，具体就是左边的非空桶的最大值和下一个非空桶的最小值
 * 
 */

public class Code_03_MaxGap {

	public static int maxGap(int[] nums) {// 传入数组
		if (nums == null || nums.length < 2) {// 当数组长度为空或者为1，不用排序，也没有差值
			return 0;
		}
		int len = nums.length;// 求数组的长度
		int min = Integer.MAX_VALUE;// 默认给它最大值
		int max = Integer.MIN_VALUE;// 默认给它最小值
		for (int i = 0; i < len; i++) {
			min = Math.min(min, nums[i]);
			max = Math.max(max, nums[i]);
		}
		if (min == max) {// 最大值和最小值都是一个数
			return 0;
		}
		// 每一个桶对应的是否为空，这里桶的数量比数组长度多一个
		boolean[] hasNum = new boolean[len + 1];
		// 每一个桶（每个下标就代表一个桶）对应的最大值
		int[] maxs = new int[len + 1];
		// 每一个桶对应的小最值
		int[] mins = new int[len + 1];
		int bid = 0;
		for (int i = 0; i < len; i++) {
			// 计算一个数应该去几号桶
			bid = bucket(nums[i], len, min, max);
			// 判断这个桶是否为空，true非空，返回比桶中小的数， false,空桶，直接把当前数放到桶中
			mins[bid] = hasNum[bid] ? Math.min(mins[bid], nums[i]) : nums[i];
			// 判断这个桶是否为空，true非空，返回比桶中大的数， false,空桶，直接把当前数放到桶中
			maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], nums[i]) : nums[i];
			// 把该桶设置为非空
			hasNum[bid] = true;
		}
		int res = 0;// 记录最大差值
		int lastMax = maxs[0];// 记录第一个桶中最大的数，如果为空，数组默认为0
		int i = 1;
		for (; i <= len; i++) {
			if (hasNum[i]) {
				// 找到每一个非空桶的最小值-前一个非空桶的最大值得差值，然后和res比较
				res = Math.max(res, mins[i] - lastMax);
				// 改变前一个桶的最大值为当前桶的最大值
				lastMax = maxs[i];
			}
		}
		return res;
	}

	/*
	 * 计算一个数应该去几号桶， 参数：len数组长度，数组最大值和最小值
	 */
	public static int bucket(long num, long len, long min, long max) {
		// 这句话它同时把最小值放到第一个桶中，最大值放到最后一个桶中
		return (int) ((num - min) * len / (max - min));// 最小值本身-最小值就是0，然后就会返回0号桶，它的maxs[0]不可能为空

	}

	// for test
	public static int comparator(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}
		Arrays.sort(nums);
		int gap = Integer.MIN_VALUE;
		for (int i = 1; i < nums.length; i++) {
			gap = Math.max(nums[i] - nums[i - 1], gap);
		}
		return gap;
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
	public static void main(String[] args) {
		int testTime = 10000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			if (maxGap(arr1) != comparator(arr2)) {
				succeed = false;
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}

}
