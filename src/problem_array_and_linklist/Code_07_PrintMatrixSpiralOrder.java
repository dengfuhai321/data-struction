package problem_array_and_linklist;

/*
 * 经典问题：转圈输出矩阵的数据
 * 解决办法：找到左上角（a，b）和右下角的坐标（c，d）
 * 由内而外，一圈一圈地输出，每输出一圈，左上角++和右下角--，
 * 然后每一圈从行或列头输出到末尾剩下一个，然后从哪个剩下的再输出，直到一圈结束
 */
public class Code_07_PrintMatrixSpiralOrder {

	public static void spiralOrderPrint(int[][] matrix) {
		int tR = 0;
		int tC = 0;
		int dR = matrix.length - 1;// 获取行数-1
		int dC = matrix[0].length - 1;// 获取列数-1
		// 每一圈输出的时候判断左上角位置ab是否<=右下角的cd,成立才输出
		while (tR <= dR && tC <= dC) {
			printEdge(matrix, tR++, tC++, dR--, dC--);
		}
	}

	public static void printEdge(int[][] m, int tR, int tC, int dR, int dC) {
		// 当左上角的行号和右下角的行号相等时（两点就是在同一行），直接输出行
		if (tR == dR) {
			for (int i = tC; i <= dC; i++) {
				System.out.print(m[tR][i] + " ");
			}
			// 当左上角的列号和右下角的列号相等时（两点就是在同一列），直接输出列
		} else if (tC == dC) {
			for (int i = tR; i <= dR; i++) {
				System.out.print(m[i][tC] + " ");
			}
			//
		} else {
			// 定义一个当前运行的位置，操作方便点
			int curC = tC;// 当前列
			int curR = tR;// 当前行
			while (curC != dC) {
				System.out.print(m[tR][curC] + " ");
				curC++;
			}
			while (curR != dR) {
				System.out.print(m[curR][dC] + " ");
				curR++;
			}
			while (curC != tC) {
				System.out.print(m[dR][curC] + " ");
				curC--;
			}
			while (curR != tR) {
				System.out.print(m[curR][tC] + " ");
				curR--;
			}
		}
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
		spiralOrderPrint(matrix);

	}
}
