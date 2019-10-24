package tu;

/*
 * Dijkstra算法，求有向网G的v0顶点到其余顶点v最短路径及带权长度
 * 查看图片1.png
 */
public class DnjavaDijstra {
	private final static int MAXVEX = 9;
	private final static int MAXWEIGHT = 65535;
	private int shortTablePath[] = new int[MAXVEX];// 记录的是v0到某顶点的最短路径和

	/**
	 * 获取一个图的最短路径
	 */
	public void shortestPathDijstra(Graph graph) {
		int min;
		int k = 0;// 记录下标
		boolean isgetPath[] = new boolean[MAXVEX];
		
		for (int v = 0; v < graph.getVertiesSize(); v++) {
			shortTablePath[v] = graph.getMatrix()[0][v];// 获取v0这一行的权值数组
		}
		shortTablePath[0] = 0;//设置从顶点为0开始计算到各个顶点的最短路径，0本身自己就是最短距离
		isgetPath[0] = true;//设置顶点0已是最短距离
		for (int v = 1; v < graph.getVertiesSize(); v++) {//
			min = MAXWEIGHT;
			for (int w = 0; w < graph.getVertiesSize(); w++) {
				if (!isgetPath[w] && shortTablePath[w] < min) {
					k = w;
					min = shortTablePath[w];//获取shortTablePath中最小的权值路径和下标
				}
			}
			isgetPath[k] = true;//设置该下标的数据wei最短路径，设为true
			for (int j = 0; j < graph.getVertiesSize(); j++) {
				if(!isgetPath[j]&&(min+graph.getMatrix()[k][j]<shortTablePath[j])){//更新路径长度
					shortTablePath[j] = min + graph.getMatrix()[k][j];
				}
			}
		}
		for(int i = 0;i<shortTablePath.length;i++){
			System.out.println("V0到V"+i+"的最短路径为:"+shortTablePath[i]+"\n");
		}
		
	}
	
	public static void main(String[] args){
		Graph graph = new Graph(MAXVEX);
		graph.createGraph();
		DnjavaDijstra dijstra = new DnjavaDijstra();
		dijstra.shortestPathDijstra(graph);
	}
}