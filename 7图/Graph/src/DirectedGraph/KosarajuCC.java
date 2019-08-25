package DirectedGraph;

/**
 * 使用深度优先搜索找出有向图中的所有强连通分量
 */
public class KosarajuCC {
    private boolean[] marked;   //已访问过的顶点     寻找一个顶点作为每个连通分量中深度优先搜索的起点
    private int[] id;           //强连通分量的标识符  以顶点作为索引的数组，将同一个连通分量中的顶点和连通分量的标识符关联起来
    private int count;          //强连通分量的数量

    public KosarajuCC(Digraph G){
        marked = new boolean[G.V()];
        id = new int[G.V()];
        DepthFirstOrder order = new DepthFirstOrder(G.reverse());
        for(int s: order.reversePost()){
            if(!marked[s]){
                dfs(G,s);
                count++;
            }
        }
    }

    private void dfs(Digraph G,int v){
        marked[v] = true;
        id[v] = count;
        for(int w :G.adj(v)){
            if(!marked[w])
                dfs(G,w);
        }
    }

    /**
     * v和w连通嘛
     * @param v
     * @param w
     * @return
     */
    public boolean stronglyConnected(int v,int w){
        return id[v] == id[w];
    }

    /**
     * v所在的连通分量的标识符（0~count()-1)
     * 将连通分量用数组保存
     * @param v
     * @return
     */
    public int id(int v){
        return id[v];
    }

    /**
     * 连通分量数
     * @return
     */
    public int count(){
        return count;
    }
}
