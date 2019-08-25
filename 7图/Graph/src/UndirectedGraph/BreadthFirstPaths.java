package UndirectedGraph;

import edu.princeton.cs.algs4.Stack;

/**
 * 算法4.2
 * 使用广度优先搜索查找图中的路径：单点最短路径
 * 图的广度优先搜索类似于树的层次遍历
 */
public class BreadthFirstPaths {
    private boolean[] marked;   //数组存储每个顶点是否被遍历过，到达该顶点的最短路径已知嘛
    private int[] edgeTo;       //到达该顶点的已知路径上的最后一个顶点
    private final int s;        //起点

    public BreadthFirstPaths(Graph G,int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G,s);
    }

    /**
     * 会标记所有与s连通的顶点
     * @param G
     * @param s
     */
    private void bfs(Graph G,int s){
        Queue<Integer> queue = new Queue<Integer>();    //保存所有已经被标记过但其邻接表还未被检查过的顶点
        marked[s] = true;               //标记起点
        queue.enqueue(s);               //先将起点加入队列
        //重复以下步骤直至队列为空
        while(!queue.isEmpty()){
            int v= queue.dequeue();     //从队列中删去下一顶点
            for(int w:G.adj(v))
                if(!marked[w]){         //对于每个未被标记的相邻顶点
                    edgeTo[w] = v;      //保存最短路径的最后一条边
                    marked[w] = true;   //标记它，因为最短路径已知
                    queue.enqueue(w);   //并将它添加到队列中
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
     * s到v的路径，如果存在则找出最短路径，如果不存在则返回null
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
