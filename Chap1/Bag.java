import java.util.Iterator

public class Bag<Item> implements Iterable<Item>{

    private Node first;

    private class Node{
        Item item;
        Node next;
    }
    //与队列的enqueue实现相同
    public void add(Item item){
        Node oldlast = first;
        first = new Node();
        first.item = item;
        first.next = oldlast;
    }

    public Iterator<Item> iterator(){
        return new ListIterator();
    }
    //链表实现的数据结构的通用遍历方法
    private class ListIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext(){
            return current != null;
        }
        public void remove(){}
        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}