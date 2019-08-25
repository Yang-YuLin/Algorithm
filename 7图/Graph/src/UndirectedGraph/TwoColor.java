package UndirectedGraph;

/**
 * 使用深度优先搜索：双色问题
 * 给定的图G是二分图嘛
 * 二分图是一种能够将所有结点分为两部分的图，其中图的每条边所连接的两个顶点都分别属于不同的部分
 */
public class TwoColor {
    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColorable = true;

    public TwoColor(Graph G){
        marked = new boolean[G.V()];
        color = new boolean[G.V()];
        for(int s=0; s<G.V(); s++){
            if(!marked[s]){
                dfs(G,s,s);
            }
        }
    }

    private void dfs(Graph G,int v,int u){
        marked[v] = true;
        for(int w :G.adj(v)){
            if(!marked[w]){
                color[w] = !color[v];
                dfs(G,w,v);
            }else if(color[w] == color[v]){
                isTwoColorable = false;
            }
        }
    }

    public boolean isBipartite(){
        return isTwoColorable;
    }
}
