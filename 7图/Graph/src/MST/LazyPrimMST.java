package MST;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

/**
 * 最小生成树的Prim算法的延时实现
 */
public class LazyPrimMST {
    private boolean[] marked;           //最小生成树的顶点
    private Queue<Edge> mst;            //最小生成树的边
    private MinPQ<Edge> pq;             //横切边（包括失效的边）

    public LazyPrimMST(EdgeWeightGraph G){
        marked = new boolean[G.V()];
        mst = new Queue<Edge>();
        pq = new MinPQ<Edge>();

        visit(G,0);                              //假设G是连通的
        while(!pq.isEmpty()){
            Edge e = pq.delMin();                   //从pq中得到权重最小的边
            int v = e.either(),w = e.other(v);
            if(marked[v] && marked[w]){             //跳过失效的边
                continue;
            }
            mst.enqueue(e);                         //将边添加到树中
            if(!marked[v]){
                visit(G,v);                         //将顶点(v或w)添加到树中
            }
            if(!marked[w]){
                visit(G,w);
            }
        }
    }

    /**
     * 为树添加一个顶点v，标记顶点v为已访问并将所有连接v和未被标记顶点(未失效)的边加入pq
     * @param G
     * @param v
     */
    private void visit(EdgeWeightGraph G,int v){
        marked[v] = true;
        for(Edge e: G.adj(v)){
            if(!marked[e.other(v)]){
                pq.insert(e);
            }
        }
    }

    public Iterable<Edge> edges(){
        return mst;
    }

//    /**
//     * 遍历树的所有边并得到它们的权重之和
//     * @return
//     */
//    public double weight(){
//    }
}
