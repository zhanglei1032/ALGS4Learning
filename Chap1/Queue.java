public class Queue<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int N;
    private class Node{
        Item item;
        Node next;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        return N;
    }

    public void enqueue(Item item){
        Node oldlast = last;
        //构造新的last，这是item，并把next置为null
        last = new Node();
        last.item = item;
        last.next = null;
        //对于空队列，把新加入的元素设为first
        if (isEmpty()) {
            first = last;
        //非空队列，把以前的last的next连接到新节点
        } else {
            oldlast.next = last;
        }
        N++;
    }

    public Item dequeue(){
        //原实现没有这个判断，是否有必要，待验证
        if (isEmpty()) {
            return null;
        }
        Item item = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        N--;
        return item;
    }
}