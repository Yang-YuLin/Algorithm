package DirectedGraph;

import edu.princeton.cs.algs4.Stack;

/**
 * 使用深度优先搜索：检测环
 * 给定的有向图G是有环图嘛(假设不存在自环或平行边)
 */
public class DirectedCycle {
    private boolean[] marked;       //是否被访问过
    private int[] edgeTo;
    private boolean[] onStack;      //递归调用期间栈上的所有顶点
    private Stack<Integer> cycle;   //有向环中的所有顶点(如果存在)

    public DirectedCycle(Digraph G){
        marked = new boolean[G.V()];    //false
        edgeTo = new int[G.V()];        //0
        onStack = new boolean[G.V()];   //false
        //对每个未遍历过的结点执行一次深度优先搜索
        for(int v=0; v<G.V(); v++){
            if(!marked[v]){
                dfs(G,v);
            }
        }

    }

    private void dfs(Digraph G,int v){
        //每次走到一个结点，该结点入栈
        onStack[v] = true;
        marked[v] = true;
        for(int w :G.adj(v)){
            if(this.hasCycle()){
                return;
            }
            //如果还未找到环，继续深度搜索
            else if(!marked[w]){
                edgeTo[w] = v;
                dfs(G,w);
            }
            //当一个结点的周围结点全都被标记后执行这
            //如果最深结点的下一个结点已经被标记过了并且在栈中则说明已经构成了一个环,于是把该环的结点依次压入栈
            else if(onStack[w]){
                cycle = new Stack<Integer>();
                for(int x=v; x!=w; x=edgeTo[x]){
                    cycle.push(x);
                }
                //然后让w入栈,最后让当前结点v入栈(关门)
                cycle.push(w);
                cycle.push(v);
            }
        }
        //当前结点从标记栈数组中移除
        onStack[v] = false;
    }

    /**
     * G是否含有有向环
     * 有环：true  无环：flase
     * @return
     */
    public boolean hasCycle(){
        return cycle!=null;
    }

    /**
     * 有向环中的所有顶点（如果存在的话）
     * @return
     */
    public Iterable<Integer> cycle(){
        return cycle;
    }
}
