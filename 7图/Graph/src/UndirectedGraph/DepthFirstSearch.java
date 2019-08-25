package UndirectedGraph;

/**
 * 连通图的深度优先搜索：单点连通性
 * 图的深度优先搜索类似于树的先序遍历
 */
public class DepthFirstSearch {
    private boolean[] marked;   //数组存储每个顶点是否被遍历过
    private int count;

    /**
     * 从顶点s开始对G进行深度优先搜素
     * @param G
     * @param s
     */
    public DepthFirstSearch(Graph G,int s){
        marked = new boolean[G.V()];
        dfs(G,s);
    }

    /**
     * 深度优先搜素
     * @param G
     * @param v
     */
    private void dfs(Graph G,int v){
        marked[v] = true;
        count++;
        for(int w :G.adj(v)){
            if(!marked[w]){
                dfs(G,w);
            }
        }
    }

    /**
     * 顶点w是否和起点s相连通
     * @param w
     * @return
     */
    public boolean marked(int w){
        return marked[w];
    }

    /**
     * 与起点s连通的顶点数量
     * @return
     */
    public int count(){
        return count;
    }
}
