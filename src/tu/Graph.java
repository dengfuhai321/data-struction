package tu;

import java.util.LinkedList;

public class Graph {
	public static final int MAX_WEIGHT=8000; //表示无穷的权值
	private int[] verties;//顶点
	private  int vertiesSize;//顶点的数量
	private int[][] matrix;//图的节点的边
	private boolean [] isVisited;//判断该节点是否已经被遍历
	
	
	public int[] getVerties() {
		return verties;
	}
	public void setVerties(int[] verties) {
		this.verties = verties;
	}
	public int getVertiesSize() {
		return vertiesSize;
	}
	public void setVertiesSize(int vertiesSize) {
		this.vertiesSize = vertiesSize;
	}
	public int[][] getMatrix() {
		return matrix;
	}
	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}
	public Graph(int vertiesSize) {
		this.vertiesSize = vertiesSize;
		this.verties=new int[vertiesSize];
		this.matrix=new int[vertiesSize][vertiesSize];
		
		  for(int i=0;i<vertiesSize;i++){
			  verties[i]=i;
	        }
		  isVisited = new boolean[vertiesSize];
	}
	/**
	 * 获取所有顶点
	 * @return
	 */
	public int[] getVertices(){
	    return verties;
	}
	/**
	 * 计算某个顶点的出度
	 * @param v
	 * @return
	 */
	public int getOutDegree(int v){
	    int count=0;
	    for(int i=0;i<matrix[v].length;i++){
	        if(matrix[v][i]!=0 && matrix[v][i]!=MAX_WEIGHT){
	            count++;
	        }
	    }
	    return count;
	}
	/**
	 * 计算某个顶点的入度
	 * @param v
	 * @return
	 */
	public int getInDegree(int v){
	    int count=0;
	    for(int i=0;i<matrix[v].length;i++){
	        if(matrix[i][v]!=0 && matrix[i][v]!=MAX_WEIGHT){
	            count++;
	        }
	    }
	    return count;
	}
	/**
	 * 计算v1到v2的权重(路径长度)
	 * @param v1 顶点
	 * @param v2 顶点
	 * @return
	 */
	public int getWeight(int v1,int v2){
	    int weight=matrix[v1][v2];
	    return weight==0 ? 0 :( weight==MAX_WEIGHT ? -1 : weight);
	}
	
	/**
	 * 
	 * 1.返回某个顶点的第一个邻接点
	 * @return
	 */
	public int getFirstNeightBor(int v){
	    for(int i=0;i<vertiesSize;i++){
	        if(matrix[v][i]>0 && matrix[v][i] !=MAX_WEIGHT){
	            return i;
	        }
	    }
	    return -1;
	}

	   
	/**
	 * 查找接点v的临界点index的下一个邻接点
	 * @param v   节点
	 * @param index  节点
	 * @return
	 */
	public int getNextNeightBor(int v,int index){
	    for(int i=index+1;i<vertiesSize;i++){
	        if(matrix[v][i]>0 && matrix[v][i] != MAX_WEIGHT){
	            return i;
	        }
	    }
	    return -1;
	}
	/**
	 * 图的深度优先遍历算法	
	 */
	private void depthFirstSearch(int i){
		isVisited[i] = true;//表示该节点已经被遍历
		
		int w = getFirstNeightBor(i);//
		while(w!=-1){
			if(!isVisited[w]){
				//需要遍历该顶点
				System.out.println("访问到了："+w+"顶点");
				depthFirstSearch(w);
			}
			w = getNextNeightBor(i, w);//第一个相对于w的邻接点
		}
	}
	/**
	 * 对外公开的深度优先遍历
	 */
	
	public void depthFirstSearch(){
		isVisited = new boolean[vertiesSize];
		for(int i = 0;i<vertiesSize;i++){
			if(!isVisited[i]){
				System.out.println("访问到了："+i+"顶点");
				depthFirstSearch(i);
			}
		}
		isVisited = new boolean[vertiesSize];
	}
	
	public void broadFirstSearch(){
		isVisited = new boolean[vertiesSize];
		for(int i =0;i<vertiesSize;i++){
			if(!isVisited[i]){
				broadFirstSearch(i);
			}
		}
	}
	/**
	 * 实现广度优先遍历
	 * @param i
	 */
	private void broadFirstSearch(int i) {
		int u,w;
		LinkedList<Integer> queue = new LinkedList<Integer>();
		System.out.println("访问到："+i+"顶点");
		isVisited[i] = true;
		queue.add(i);//第一次把v0加到队列
		while(!queue.isEmpty()){
			u = (Integer)(queue.removeFirst()).intValue();
			w = getFirstNeightBor(u);
			while(w!=-1){
				if(!isVisited[w]){
					System.out.println("访问到了："+w+"顶点");
					isVisited[w] = true;
					queue.add(w);
				}
				w = getNextNeightBor(u, w);
			}
		}
	}
	
	/**
	 * 创建图的过程
	 *  查看 	图.jpg
	 */
	public void createGraph(){
		int [] a1 = new int[]{0,1,5,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT};
		int [] a2 = new int[]{1,0,3,7,5,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT};
		int [] a3 = new int[]{5,3,0,MAX_WEIGHT,1,7,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT};
		int [] a4 = new int[]{MAX_WEIGHT,7,MAX_WEIGHT,0,2,MAX_WEIGHT,3,MAX_WEIGHT,MAX_WEIGHT};
		int [] a5 = new int[]{MAX_WEIGHT,5,1,2,0,3,6,9,MAX_WEIGHT};
		int [] a6 = new int[]{MAX_WEIGHT,MAX_WEIGHT,7,MAX_WEIGHT,3,0,MAX_WEIGHT,5,MAX_WEIGHT};
		int [] a7 = new int[]{MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,3,6,MAX_WEIGHT,0,2,7};
		int [] a8 = new int[]{MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,9,5,2,0,4};
		int [] a9 = new int[]{MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,7,4,0};
		
		matrix[0] = a1;
		matrix[1] = a2;
		matrix[2] = a3;
		matrix[3] = a4;
		matrix[4] = a5;
		matrix[5] = a6;
		matrix[6] = a7;
		matrix[7] = a8;
		matrix[8] = a9;
	}
	
	/**
	 * prim 普里姆算法，目的获取最小生成树（就是连接图所有节点的最小距离）
	 */
		public void prim(){
			int [] lowcost = new int[vertiesSize];//最小代价顶点权值的数组,为0表示已经获取最小权值
			int [] adjvex = new int[vertiesSize];//放顶点权值
			int min,minId,sum = 0;//min存放最小权值，minid存放最小权值的下标，sum记录最小权值的和
			//初始化该图的的数组，设置该图的第一排数据存放到该数组中
			for(int i = 1;i<vertiesSize;i++){
				lowcost[i] = matrix[0][i];
			}
			for(int i = 1;i<vertiesSize;i++){
				min = MAX_WEIGHT;
				minId = 0;
				for(int j = 1;j<vertiesSize;j++){
					if(lowcost[j]<min&&lowcost[j]>0){//获取该数组中最小的权值和其下标，但不包括0和MAX_WEIGHT的值
						min = lowcost[j];
						minId = j;
					}
				}
				System.out.println("顶点："+adjvex[minId]+"权值："+min);//输出顶点和权值
				sum+=min;//最小生成树权值累加
				lowcost[minId] = 0;//把权值确定的顶点下标该为0，不再更改
				for(int j = 1;j<vertiesSize;j++){//把刚确定权值的顶点的一排数据中权值在0-MAX_WEIGHT之间，且小于存放在
					if(lowcost[j]!=0&&matrix[minId][j]<lowcost[j]){//lowcost下标数组中的不为0的权值
						lowcost[j] = matrix[minId][j];
						adjvex[j] = minId;
					}
				}
			}
			System.out.println("最小生成树权值和:"+sum);
		}

	
	
	/*public static void main(String[] args) {
		//构建一个图,顶点为5
		Graph graph = new Graph(5);
		 	int[] a0=new int[]{0,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,6 };
	        int[] a1=new int[]{9,0,3,MAX_WEIGHT,MAX_WEIGHT };
	        int[] a2=new int[]{2,MAX_WEIGHT,0,5,MAX_WEIGHT };
	        int[] a3=new int[]{MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,0,1 };
	        int[] a4=new int[]{MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT};
	        graph.matrix[0]=a0;
	        graph.matrix[1]=a1;
	        graph.matrix[2]=a2;
	        graph.matrix[3]=a3;
	        graph.matrix[4]=a4;
	     int[] vertices = graph.getVertices();
	     for (int i : vertices) {
			System.out.println("顶点："+i);
		}
	     int outDegree = graph.getOutDegree(4);//查看4的出度是多少
	     System.out.println("4的出度是"+outDegree);
	     int inDegree = graph.getInDegree(4);//查看4的入度是多少
	     System.out.println("4的入度是"+inDegree);
	     int weight = graph.getWeight(2, 3);//查看2和3之间的权重
	     System.out.println("2和3之间的权重是"+weight);
	     int firstNeightBor = graph.getFirstNeightBor(2);
	     System.out.println("2的第一个领接顶点是"+firstNeightBor);
	     int nextNeightBor = graph.getNextNeightBor(2,3);
	     System.out.println("2的第二个领接顶点是"+nextNeightBor);
	     
		
	}*/
	public static void main(String[] args) {
		
	
	Graph graph = new Graph(9);
	
	int [] a1 = new int[]{0,10,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,11,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT};
	int [] a2 = new int[]{10,0,18,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,16,MAX_WEIGHT,12};
	int [] a3 = new int[]{MAX_WEIGHT,MAX_WEIGHT,0,22,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,8};
	int [] a4 = new int[]{MAX_WEIGHT,MAX_WEIGHT,22,0,20,MAX_WEIGHT,MAX_WEIGHT,16,21};
	int [] a5 = new int[]{MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,20,0,26,MAX_WEIGHT,7,MAX_WEIGHT};
	int [] a6 = new int[]{11,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,26,0,17,MAX_WEIGHT,MAX_WEIGHT};
	int [] a7 = new int[]{MAX_WEIGHT,16,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,17,0,19,MAX_WEIGHT};
	int [] a8 = new int[]{MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,16,7,MAX_WEIGHT,19,0,MAX_WEIGHT};
	int [] a9 = new int[]{MAX_WEIGHT,12,8,21,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,0};
	
	graph.matrix[0] = a1;
	graph.matrix[1] = a2;
	graph.matrix[2] = a3;
	graph.matrix[3] = a4;
	graph.matrix[4] = a5;
	graph.matrix[5] = a6;
	graph.matrix[6] = a7;
	graph.matrix[7] = a8;
	graph.matrix[8] = a9;
	//graph.depthFirstSearch();
	graph.prim();
	
	}

}
