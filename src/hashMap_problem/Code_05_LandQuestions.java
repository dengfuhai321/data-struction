package hashMap_problem;

/*
 * 岛问题
 * 一个矩阵中只有0和1两种值,每个位置都可以和自己的上、下、左、右四个位置相连,如果有一片1连在一起,
 * 这个部分叫做一个岛,求一个矩阵中有多少个岛?
 * 例：
 * 0 0 1 0 1 0
 * 1 1 1 0 1 0
 * 1 0 0 1 0 0 
 * 0 0 0 0 0 0 其中有三个岛。
 * 下面这种方法    单cpu  单内存就可以解决
 * 就是遍历矩形数组，当遇到1的时候就上下左右开始感染，把1变成2，遇到0或者2就不感染直接跳过
 * 
 * 但是上面的方法毕竟是单cpu的，
 * 如果矩形数据量特别大，可以并行处理（多cpu多内存），把矩形分开处理，然后把获得的岛的数量总和
 * 但是对于分开的边界需要处理，就是在处理边界的时候，如果两边有岛碰见了，而且不是同一个岛，就合并岛，岛数量-1
 * 		合并就使用并查集结构
 */
public class Code_05_LandQuestions {

	public static int countIslands(int[][] m) {// 传入矩形数组
		if (m == null || m[0] == null) {
			return 0;
		}
		int N = m.length;// 矩形行数
		int M = m[0].length;// 矩形列数
		int res = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {// 遍历矩阵
				if (m[i][j] == 1) {// 当某节点为1时
					res++;// 岛的数量+1
					infect(m, i, j, N, M);// 把这个1附近的1都变成2
				}
			}
		}
		return res;
	}

	public static void infect(int[][] m, int i, int j, int N, int M) {
		if (i < 0 || i >= N || j < 0 || j >= M || m[i][j] != 1) {// 往外感染时如果越界或者值！=1时，直接返回结束
			return;
		}
		m[i][j] = 2;// 使当前节点值变成2
		infect(m, i + 1, j, N, M);// 让当前节点的上下左右的节点都开始感染，越界或者值！=1返回
		infect(m, i - 1, j, N, M);
		infect(m, i, j + 1, N, M);
		infect(m, i, j - 1, N, M);
	}

	public static void main(String[] args) {
		int[][] m1 = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 1, 1, 0, 1, 1, 1, 0 }, { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
				{ 0, 1, 1, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 1, 1, 0, 0 }, { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
		System.out.println(countIslands(m1));

		int[][] m2 = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 1, 1, 1, 1, 1, 1, 0 }, { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
				{ 0, 1, 1, 0, 0, 0, 1, 1, 0 }, { 0, 0, 0, 0, 0, 1, 1, 0, 0 }, { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
		System.out.println(countIslands(m2));

	}

}
