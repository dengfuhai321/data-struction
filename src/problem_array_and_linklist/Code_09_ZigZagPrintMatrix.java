package problem_array_and_linklist;

/*
 * 经典问题：之字打印矩阵（就是从左上角s型打印到右下角）
 * 
 * 解决思路：定义两个坐标，行坐标和列坐标，每次列坐标往右移动，若到头就往下移动，行坐标同时也往下移动
 * ，到头了就往右移动，每次行坐标和列坐标连成的直线就是要打印的数据，再控制方向就可以s型打印了
 * 
 */
public class Code_09_ZigZagPrintMatrix {

	public static void printMatrixZigZag(int[][] matrix) {
		// 从左上角开始
		int tR = 0;// 行坐标的行
		int tC = 0;// 行坐标的列
		int dR = 0;// 列坐标的行
		int dC = 0;// 列坐标的列
		int endR = matrix.length - 1;// 获取行数
		int endC = matrix[0].length - 1;// 获取列
		boolean fromUp = false;// 打印的时候控制方向的
		while (tR != endR + 1) {// 行坐标的行在不等于最后一行+1的情况下执行以下
			printLevel(matrix, tR, tC, dR, dC, fromUp);// 先打印之前准备好的
			// 分别确定两个坐标的行号和列号
			tR = tC == endC ? tR + 1 : tR;// 行坐标的列是否等于最后一列+1，是，行数开始+1，否则行不变
			tC = tC == endC ? tC : tC + 1;// 行坐标的列是否等于最后一列+1，是，列数不变，行数+1
			dC = dR == endR ? dC + 1 : dC;
			dR = dR == endR ? dR : dR + 1;
			fromUp = !fromUp;// 每次改变boolean值，
		}
		System.out.println();
	}

	// 打印的方法
	public static void printLevel(int[][] m, int tR, int tC, int dR, int dC, boolean f) {
		if (f) {
			while (tR != dR + 1) {// 重要行坐标不越界（行坐标的行！=列坐标的行+1）就执行从右上往坐下输出
				System.out.print(m[tR++][tC--] + " ");
			}
		} else {
			while (dR != tR - 1) {// 列坐标的行！=行坐标的行-1
				System.out.print(m[dR--][dC++] + " ");
			}
		}
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		printMatrixZigZag(matrix);

	}

}
