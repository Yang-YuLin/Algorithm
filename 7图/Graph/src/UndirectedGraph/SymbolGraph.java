package UndirectedGraph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

/**
 * 符号图的数据类型(符号作为图的顶点名)
 * 代码将图的定义处理两遍（定义的每一行都包含一个顶点及它的相邻顶点列表，用分隔符sp隔开)
 */
public class SymbolGraph {
    private ST<String,Integer> st;                  //符号名(顶点名)->索引
    private String[] keys;                          //索引->符号名(顶点名)
    private Graph G;                                //使用索引表示顶点的图

    private SymbolGraph(String stream, String sp){
        st = new ST<String, Integer>();
        In in = new In(stream);                     //第一遍
        while(in.hasNextLine()){                    //构造索引
            String[] a = in.readLine().split(sp);   //读取字符串
            for(int i=0; i<a.length; i++){          //为每个不同的字符串关联一个索引
                if(!st.contains(a[i])){
                    st.put(a[i],st.size());
                }
            }
        }
        keys = new String[st.size()];               //用来获得顶点名的反向索引是一个数组
        for(String name:st.keys()){
            keys[st.get(name)] = name;
        }
        G = new Graph(st.size());
        in = new In(stream);                        //第二遍
        while(in.hasNextLine()){                    //构造图
            String[] a = in.readLine().split(sp);   //将每一行的顶点和该行的其他顶点相连
            int v = st.get(a[0]);
            for(int i=1; i<a.length; i++){
                G.addEdge(v,st.get(a[i]));
            }
        }
    }

    /**
     * s是一个顶点嘛
     * @param s
     * @return
     */
    public boolean contains(String s){
        return st.contains(s);
    }

    /**
     * 将顶点名s转化为索引
     * @param s
     * @return
     */
    public int index(String s){
        return st.get(s);
    }

    /**
     * 将索引v转化为顶点名
     */
    public String name(int v){
        return keys[v];
    }

    public Graph G(){
        return G;
    }
}
