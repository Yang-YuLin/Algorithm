package UndirectedGraph;

import java.util.Iterator;

/**
 * 背包（链表实现）后进先出
 * @param <Item>
 */
public class Bag<Item> implements Iterable<Item> {
    private Node first;     //链表的首结点
    private int N;          //元素数量

    /**
     * 定义了结点的嵌套类
     */
    private class Node{
        Item item;
        Node next;
    }

    /**
     * 创建一个空背包
     */
    public Bag() {
    }

    /**
     * 添加一个元素
     * @param item
     */
    public void add(Item item){
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }

    /**
     * 背包是否为空
     * @return
     */
    public boolean isEmpty(){
        return first == null;   //return N == 0;
    }

    /**
     * 背包中的元素数量
     * @return
     */
    public int size(){
        return N;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{
        private Node current = first;   //记录链表的当前结点
        public boolean hasNext(){
            return current != null;
        }
        public void remove(){
        }
        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
