package problem_array_and_linklist;

/*
 * 经典问题：在排好序的矩形数组中查找某个数据
 * 解决思路：如果在一个从左往右从上往下逐渐增大的数组中，可以从右上角出发，如果当前数比你要找的数据大
 * 就往左走继续找，如果当前数比要找的数小，就往下走。
 * 
 * 
 */
public class Code_10_FindNumInSortedMatrix {
	public static boolean isContains(int[][] matrix, int K) {
		int row = 0;// 第一行
		int col = matrix[0].length - 1;// 最后一列
		while (row < matrix.length && col > -1) {// 当在数组行列的范围内
			if (matrix[row][col] == K) {
				System.out.println("行：" + row + "列：" + col);
				return true;
			} else if (matrix[row][col] > K) {
				col--;
			} else {
				row++;
			}
		}

		return false;

	}

	public static void main(String[] args) {
		int[][] matrix = new int[][] {
				// 这是一个从左往右从上往下逐渐增大的数组
				{ 0, 1, 2, 3, 4, 5, 6 }, // 0
				{ 10, 12, 13, 15, 16, 17, 18 }, // 1
				{ 23, 24, 25, 26, 27, 28, 29 }, // 2
				{ 44, 45, 46, 47, 48, 49, 50 }, // 3
				{ 65, 66, 67, 68, 69, 70, 71 }, // 4
				{ 96, 97, 98, 99, 100, 111, 122 }, // 5
				{ 166, 176, 186, 187, 190, 195, 200 }, // 6
				{ 233, 243, 321, 341, 356, 370, 380 } // 7
		};
		int K = 233;
		System.out.println(isContains(matrix, K));
	}

}
