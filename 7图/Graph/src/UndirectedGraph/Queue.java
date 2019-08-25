package UndirectedGraph;

import java.util.Iterator;

/**
 * 队列（链表实现）先进先出
 */
public class Queue<Item> implements Iterable<Item> {
    private Node first;     //指向最早添加的结点的链接,即指向队列的开头
    private Node last;      //指向最近添加的结点的链接,即指向队列的结尾
    private int N;          //队列中的元素数量

    /**
     * 定义了结点的嵌套类
     */
    private class Node{
        Item item;
        Node next;
    }

    /**
     * 队列是否为空
     * @return
     */
    public boolean isEmpty(){
        return first == null;   //return N == 0;
    }

    /**
     * 队列中的元素数量
     * @return
     */
    public int size(){
        return N;
    }

    /**
     * 向表尾添加元素，即进队
     */
    public void enqueue(Item item){
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()){
            first = last;
        }else {
            oldlast.next = last;
        }
        N++;
    }

    /**
     * 从表头删除元素，即出队
     */
    public Item dequeue(){
        Item item = first.item;
        first = first.next;
        if(isEmpty()){
            last = null;
        }
        N--;
        return item;
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
