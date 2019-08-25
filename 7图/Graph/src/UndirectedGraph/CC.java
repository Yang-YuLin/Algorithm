package UndirectedGraph;

/**
 * 算法4.3
 * 使用深度优先搜索找出图中的所有连通分量
 */
public class CC {
    private boolean[] marked;   //寻找一个顶点作为每个连通分量中深度优先搜索的起点
    private int[] id;           //以顶点作为索引的数组，将同一个连通分量中的顶点和连通分量的标识符关联起来
    private int count;

    public CC(Graph G){
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for(int s=0; s<G.V(); s++){
            if(!marked[s]){
                dfs(G,s);
                count++;
            }
        }
    }

    private void dfs(Graph G,int v){
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
    public boolean connected(int v,int w){
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
