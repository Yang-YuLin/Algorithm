package DirectedGraph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 有向图的可达性
 */
public class DirectedDFS {
    private boolean[] marked;   //数组存储每个顶点是否被遍历过

    /**
     * 单点的可达性
     * 从顶点s开始对G进行深度优先搜素
     * 在G中找到从s可达的所有顶点
     * @param G
     * @param s
     */
    public DirectedDFS(Digraph G,int s){
        marked = new boolean[G.V()];
        dfs(G,s);
    }

    /**
     * 多点的可达性
     * 在G中找到从sources中的所有顶点可达的所有顶点
     * @param G
     * @param sources
     */
    public DirectedDFS(Digraph G,Iterable<Integer> sources){
        marked = new boolean[G.V()];
        for(int s:sources){
            if(!marked[s]){
                dfs(G,s);
            }
        }
    }

    /**
     * 深度优先搜素
     * @param G
     * @param v
     */
    private void dfs(Digraph G,int v){
        marked[v] = true;
        for(int w :G.adj(v)){
            if(!marked[w]){
                dfs(G,w);
            }
        }
    }

    /**
     * v是可达的嘛
     * @param v
     * @return
     */
    public boolean marked(int v){
        return marked[v];
    }

    public static void main(String[] args) {
        Digraph G = new Digraph(new In(args[0]));
        Bag<Integer> sources = new Bag<Integer>();
        for(int i=1; i<args.length; i++){
            sources.add(Integer.parseInt(args[i]));
        }
        DirectedDFS reachable = new DirectedDFS(G,sources);
        for(int v=0; v<G.V(); v++){
            if(reachable.marked(v)){
                StdOut.print(v+" ");
            }
        }
        StdOut.println();
    }
}
