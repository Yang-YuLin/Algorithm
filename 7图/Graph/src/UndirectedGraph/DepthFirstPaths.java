package UndirectedGraph;

/**
 * 算法4.1
 * 使用深度优先搜素查找图中的路径：单点路径
 */
public class DepthFirstPaths {
    private boolean[] marked;   //数组存储每个顶点是否被遍历过，这个顶点调用过dfs()了吗
    private int[] edgeTo;       //从起点到一个顶点的已知路径上的最后一个顶点   edgeTo[]数组是一棵用父链接表示的以s为根且含有所有与s连通的顶点的树
    private final int s;        //起点

    public DepthFirstPaths(Graph G,int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G,s);
    }

    private void dfs(Graph G,int v){
        marked[v] = true;
        for(int w :G.adj(v)){
            if(!marked[w]){
                edgeTo[w] = v;
                dfs(G,w);
            }
        }
    }

    /**
     * 是否存在从s到v的路径
     * @param v
     * @return
     */
    public boolean hasPathTo(int v){
        return marked[v];
    }

    /**
     * s到v的路径，如果不存在则返回null
     * 用变量x遍历整棵树，在到达s之前，将遇到的所有顶点都压入栈中
     * @param v
     * @return
     */
    public Iterable<Integer> pathTo(int v){
        if(!hasPathTo(v))
            return null;
        Stack<Integer> path = new Stack<Integer>();
        for(int x = v; x != s;x = edgeTo[x]){
            path.push(x);
        }
        path.push(s);
        return path;
    }
}
