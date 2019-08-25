package DirectedGraph;

/**
 * 优先级限制下的调度安排
 * 使用深度优先搜索对有向无环图进行拓扑排序 AOV网：顶点表示活动
 * 一幅有向无环图的拓扑排序即为所有顶点的逆后序排列
 */
public class Topological {
    private Iterable<Integer> order;        //顶点的拓扑排序

    public Topological(Digraph G){
        DirectedCycle cyclefinder = new DirectedCycle(G);
        if(!cyclefinder.hasCycle()){
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }

    public Iterable<Integer> order(){
        return order;
    }

    /**
     * DAG:有向无环图
     * 无环图 order 迭代器
     * 有环图 order null
     * @return
     */
    public boolean isDAG(){
        return order!=null;
    }
}
