package MST;

import edu.princeton.cs.algs4.Bag;

/**
 * 加权无向图的数据类型
 */
public class EdgeWeightGraph {
    private final int V;        //顶点数目
    private int E;              //边的数目
    private Bag<Edge>[] adj;    //邻接表

    /**
     * 创建一个含有V个结点但不含有边的图
     * @param V
     */
    public EdgeWeightGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];   //创建邻接表
        for (int v = 0; v < V; v++){      //将所有链表初始化为空
            adj[v] = new Bag<Edge>();
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
     * 向图中添加一条边v-w
     * @param e
     */
    public void addEdge(Edge e){
        int v = e.either(),w = e.other(v);
        adj[v].add(e);      //将e添加到v的邻接表
        adj[w].add(e);      //将e添加到w的邻接表
        E++;
    }

    /**
     * 遍历和v相关联的所有边（遍历顺序不确定）
     * @param v
     * @return
     */
    public Iterable<Edge> adj(int v){
        return adj[v];
    }

    /**
     * 图的所有边
     * @return
     */
    public Iterable<Edge> edges(){
        Bag<Edge> b = new Bag<Edge>();
        for(int v=0; v<V; v++){
            for(Edge e :adj[v]){
                if(e.other(v) > v){
                    b.add(e);
                }
            }
        }
        return b;
    }
}
