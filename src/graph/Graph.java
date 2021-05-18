package graph;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 图
 *
 */
public class Graph {
    private ArrayList<String> vertexList;//存储顶点的集合
    private int [] [] edges;//存储图对应的邻接矩阵
    private int numOfEdges;//表示边的数目
    private boolean [] isVisited;//表示是否被访问过
    public static void main(String[] args) {
        int n = 5;//结点个数为五
        String[] vertexValues = {"A","B","C","D","E"};
        //创建图对象
        Graph graph = new Graph(n);
        //循环的添加结点
        for (String vertex : vertexValues){
            graph.insertVertex(vertex);
        }
        //添加边 AB AC BC BD BE
        graph.insertEdge(0,1,1);//AB
        graph.insertEdge(0,2,1);//AC
        graph.insertEdge(1,2,1);//BC
        graph.insertEdge(1,3,1);//BD
        graph.insertEdge(1,4,1);//BE
        graph.showGraph();

        System.out.println("深度优先遍历");
        graph.dfs();
    }

    //构造器
    //n表示顶点的个数
    public Graph(int n){
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        //默认边的数目初始化为零
        numOfEdges = 0;
        //初始化是否被访问结点的数组
        isVisited = new boolean[n];
    }


    /**
     * 得到第一个邻接结点的下标w
     * @param index 传入邻接点的编号
     * @return  如果存在就返回对应的下标，否则返回-1
     */
    public int getFirstNeighbor(int index){
        for(int j = 0;j < vertexList.size(); j++){
            if (edges[index][j] > 0){//这里为了有权值的考虑，无权值只需等于1即可
                return j;
            }
        }
        return -1;
    }

    //根据前一个邻接结点的下标来获取下一个邻接结点
    public int getNextNeighbor(int v1,int v2){
        for (int j = v2 + 1; j < vertexList.size(); j++){
            if (edges[v1][j] > 0){
                return j;
            }
        }
        return -1;
    }

    /**
     * 深度优先遍历
     * @param isVisited
     * @param i 第一次就是0
     */
    public void dfs(boolean[] isVisited,int i){
        //首先我们访问该结点，输出
        System.out.print(getValueByIndex(i) + "->");
        //将结点设置为已经访问
        isVisited[i] = true;
        //查找结点i的第一个邻接结点w
        int w = getFirstNeighbor(i);
        while (w != -1){//说明有邻接结点
            if (!isVisited[w]){//w存在，且并未被访问过
                dfs(isVisited,w);
            }else {//w存在，被访问过
                //查找下一个邻接结点
                w = getNextNeighbor(i,w);
            }
        }
    }
    //对dfs进行重载，循环所有结点，并进行dfs
    public void dfs(){
        //遍历所有的结点，进行dfs
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }

    //查找图中的结点个数
    public int getNumOfVertex(){
        return vertexList.size();
    }
    //查找图中边的个数
    public int getNumOfEdges(){
        return numOfEdges;
    }
    //返回结点i(下标)对应的数据
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }
    //返回v1和v2的权值
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }
    //显示图对应的矩阵
    public void showGraph(){
        for (int[] link : edges){
            System.out.println(Arrays.toString(link));
        }
    }

    //插入结点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    /**
     * 添加边：通过矩阵来描述边
     * @param v1 表示顶点的下标，即第几个顶点
     * @param v2 表示第二个顶点对应的下标
     * @param weight 表示权值，是否有边（无向图：0/1），在有向图中就是边的长度了
     */
    public void insertEdge(int v1,int v2,int weight){
        //因为是无向图，也就是双向，要有两个
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        //将边的数目加一
        numOfEdges++;
    }

}
