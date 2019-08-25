package SPT;

import edu.princeton.cs.algs4.Bag;

/**
 * 加权有向图的数据类型
 */
public class EdgeWeightDigraph {
    private final int V;        //顶点总数
    private int E;              //边的总数
    private Bag<DirectedEdge>[] adj;    //邻接表

    /**
     * 创建一个含有V个结点但不含有边的图
     * @param V
     */
    public EdgeWeightDigraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<DirectedEdge>[]) new Bag[V];   //创建邻接表
        for (int v = 0; v < V; v++){      //将所有链表初始化为空
            adj[v] = new Bag<DirectedEdge>();
        }
    }

    /**
     * 顶点数
     * @return
     */
    public int V(){
        return V;
    }

    /**
     * 边数
     * @return
     */
    public int E(){
        return E;
    }

    /**
     * 向图中添加一条边
     * @param e
     */
    public void addEdge(DirectedEdge e){
        adj[e.from()].add(e);
        E++;
    }

    /**
     * 遍历和v相关联的所有边（遍历顺序不确定）
     * @param v
     * @return
     */
    public Iterable<DirectedEdge> adj(int v){
        return adj[v];
    }

    /**
     * 图的所有边
     * @return
     */
    public Iterable<DirectedEdge> edges(){
        Bag<DirectedEdge> b = new Bag<DirectedEdge>();
        for(int v=0; v<V; v++){
            for(DirectedEdge e :adj[v]){
                b.add(e);
            }
        }
        return b;
    }
}
