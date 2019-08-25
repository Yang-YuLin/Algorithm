package DirectedGraph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class Digraph {
    private final int V;        //顶点数目
    private int E;              //边的数目
    private Bag<Integer>[] adj; //邻接表

    /**
     * 创建一个含有V个结点但不含有边的有向图
     * @param V
     */
    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];   //创建邻接表
        for (int v = 0; v < V; v++){         //将所有链表初始化为空
            adj[v] = new Bag<Integer>();
        }
    }

    /**
     * 从标准输入流in读入一幅图
     * @param in
     */
    public Digraph(In in){
        this(in.readInt());     //读取V并将图初始化
        int E = in.readInt();   //读取E
        for(int i = 0; i < E; i++){
            //添加一条边
            int v = in.readInt();   //读取一个顶点
            int w = in.readInt();   //读取另一个顶点
            addEdge(v,w);           //添加一条连接它们的边
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
     * 向图中添加一条边v->w
     * @param v
     * @param w
     */
    public void addEdge(int v,int w){
        adj[v].add(w);      //将w添加到v的邻接表
        E++;
    }

    /**
     * 由v指出的边所连接的所有顶点
     * @param v
     * @return
     */
    public Iterable<Integer> adj(int v){
        return adj[v];
    }

    /**
     * 该图的反向图：找出指向每个顶点的所有边
     */
    public Digraph reverse(){
        Digraph R = new Digraph(V);
        for(int v=0; v<V; v++){
            for(int w :adj(v)){
                R.addEdge(w,v);
            }
        }
        return R;
    }
}
