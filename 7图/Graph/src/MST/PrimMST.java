package MST;

import edu.princeton.cs.algs4.IndexMinPQ;

/**
 * 最小生成树的Prim算法的即时实现
 */
public class PrimMST {
    private Edge[] edgeTo;              //距离树最近的边
    private double[] distTo;            //distTo[w]=edgeTo[w].weight()
    private boolean[] marked;           //如果v在树中则为true
    private IndexMinPQ<Double> pq;      //有效的横切边

    public PrimMST(EdgeWeightGraph G){
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        for(int v=0; v<G.V(); v++){
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        pq = new IndexMinPQ<Double>(G.V());
        distTo[0] = 0.0;
        pq.insert(0,0.0);       //用顶点0和权重0初始化pq
        while (!pq.isEmpty()){
            visit(G,pq.delMin());       //将最近的顶点添加到树中
        }
    }

    /**
     * 将顶点加入到树中，更新数据
     * @param G
     * @param v
     */
    private void visit(EdgeWeightGraph G,int v){
        marked[v] = true;
        for(Edge e: G.adj(v)){
            int w = e.other(v);
            if(marked[w]){              //v-w失效
                continue;
            }
            if(e.weight() < distTo[w]){
                //连接w和树的最佳边Edge变为e
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if(pq.contains(w)){
                    pq.change(w,distTo[w]);
                }else {
                    pq.insert(w,distTo[w]);
                }
            }
        }
    }
}
