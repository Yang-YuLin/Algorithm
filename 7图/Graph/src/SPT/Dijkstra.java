package SPT;

import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;

/**
 * 边上权重非负情形的加权有向图的最短路径
 */
public class Dijkstra {
    //索引是结点v，元素值是边,连接v和它的父结点的边（也就是从s到v的最短路径上的最后一条边）
    private DirectedEdge[] edgeTo;

    //索引是结点v，元素值是从s到v的距离,
    private double[] distTo;

    //key是结点v，value是distTo[key]
    private IndexMinPQ<Double> pq;

    /**
     * @param G
     * @param s:起始结点
     */
    public Dijkstra(EdgeWeightDigraph G,int s){
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        pq = new IndexMinPQ<Double>(G.V());

        //遍历结点，把从根节点s到顶点v的初始距离都设置成无穷大
        for(int v=0; v<G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }

        distTo[s] = 0.0;
        pq.insert(s,0.0);
        while (!pq.isEmpty()){
            relax(G, pq.delMin());//delMin返回index
        }

        //测试输出
        for(int v=0; v<G.V(); v++){
            System.out.println("edgeTo["+v+"]="+edgeTo[v]);
        }
    }

    /**
     * 顶点的松弛
     * @param G
     * @param v
     */
    private void relax(EdgeWeightDigraph G, int v){
        for(DirectedEdge e: G.adj(v)){
            int w = e.to();
            if(distTo[w] > distTo[v] + e.weight()){
                distTo[w] = distTo[v] +e.weight();
                edgeTo[w] = e;
                if(pq.contains(w)){
                    pq.changeKey(w,distTo[w]);
                }else {
                    pq.insert(w,distTo[w]);
                }
            }
        }
    }

    /**
     * 最短路径树实现中的标准查询算法
     * 从顶点s到v的距离，如果不存在则路径为无穷大
     * @param v
     * @return
     */
    public double distTo(int v){
        return distTo[v];
    }

    /**
     * 是否存在从顶点s到v的路径
     * @param v
     * @return
     */
    public boolean hasPathTo(int v){
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    /**
     * 从顶点s到v的路径，如果不存在则为null
     * @param v
     * @return
     */
    public Iterable<DirectedEdge> pathTo(int v){
        if(!hasPathTo(v)){
            return null;
        }
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for(DirectedEdge e=edgeTo[v]; e!=null; e=edgeTo[e.from()]){
            path.push(e);
        }
        return path;
    }

    public static void main(String[] args) {
        DirectedEdge directedEdge1 = new DirectedEdge(0,2,4);
        DirectedEdge directedEdge2 = new DirectedEdge(0,3,28);
        DirectedEdge directedEdge3 = new DirectedEdge(1,0,2);
        DirectedEdge directedEdge4 = new DirectedEdge(1,4,10);
        DirectedEdge directedEdge5 = new DirectedEdge(2,1,15);
        DirectedEdge directedEdge6 = new DirectedEdge(2,5,8);
        DirectedEdge directedEdge7 = new DirectedEdge(4,3,4);
        DirectedEdge directedEdge8 = new DirectedEdge(5,3,13);
        DirectedEdge directedEdge9 = new DirectedEdge(5,4,18);

        EdgeWeightDigraph edgeWeightDigraph = new EdgeWeightDigraph(6);
        edgeWeightDigraph.addEdge(directedEdge1);
        edgeWeightDigraph.addEdge(directedEdge2);
        edgeWeightDigraph.addEdge(directedEdge3);
        edgeWeightDigraph.addEdge(directedEdge4);
        edgeWeightDigraph.addEdge(directedEdge5);
        edgeWeightDigraph.addEdge(directedEdge6);
        edgeWeightDigraph.addEdge(directedEdge7);
        edgeWeightDigraph.addEdge(directedEdge8);
        edgeWeightDigraph.addEdge(directedEdge9);

        //单源最短路径：源点s到G中其他顶点的最短路径
//        int s = 0;
//        Dijkstra dijkstra = new Dijkstra(edgeWeightDigraph,s);
//        for(int v=0; v<edgeWeightDigraph.V(); v++){
//            if(v!=s){
//                System.out.print("源点"+s+"到"+v+":");
//                System.out.print(dijkstra.hasPathTo(v)+"   ");
//                System.out.print(dijkstra.pathTo(v)+"   ");
//                System.out.println(dijkstra.distTo(v));
//            }
//        }

        //每一对顶点之间的最短路径：每次以一个顶点为源点，重复执行Dijkstra算法n次
        for(int s=0; s<edgeWeightDigraph.V(); s++){
            Dijkstra dijkstra = new Dijkstra(edgeWeightDigraph,s);
            for(int v=0; v<edgeWeightDigraph.V(); v++){
            if(v!=s){
                System.out.print("源点"+s+"到"+v+":");
                System.out.print(dijkstra.hasPathTo(v)+"   ");
                System.out.print(dijkstra.pathTo(v)+"   ");
                System.out.println(dijkstra.distTo(v));
            }
        }
        }
    }
}
