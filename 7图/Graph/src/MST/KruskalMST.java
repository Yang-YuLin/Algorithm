package MST;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;

/**
 * 最小生成树的KruskalMST算法
 */
public class KruskalMST {
    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightGraph G){
        mst = new Queue<Edge>();
        MinPQ<Edge> pq = new MinPQ<Edge>();
        for(Edge e: G.edges()){
            pq.insert(e);
        }
        UF uf = new UF(G.V());
        while(!pq.isEmpty() && mst.size() < G.V()-1){
            Edge e = pq.delMin();       //从pq得到权重最小的边
            int v = e.either(),w = e.other(v);
            //如果p和q存在于同一个分量中则返回true
            if(uf.connected(v,w)){
                continue;               //忽略失效的边
            }
            uf.union(v,w);              //合并分量,在v和w之间添加一条连接
            mst.enqueue(e);             //将边添加到最小生成树中
        }
    }

    public Iterable<Edge> edges(){
        return mst;
    }
}
