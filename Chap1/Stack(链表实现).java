public class Stack<Item> implements Iterable<Item>{
    private Node first;
    private int N;
    //链表的一个节点，包含一个数据存储单元和一个指向下个元素的“指针”
    private class Node{
        Item item;
        Node next;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public void push(Item item){
        //保存原链表的头节点到oldfirst，保证整个链表不会丢失
        Node oldfirst = first;
        //新建一个节点，并把first指向它
        first = new Node();
        //把入栈的数据存储在新的节点first里
        first.item = item;
        //把first的next指向oldfirst，也就是与原链表链接到一起
        first.next = oldfirst;
        N++;
    }

    public Item pop(){
        //拷贝first节点数据
        Item item = first.item;
        //把first节点的下一个节点指向first，相当于删除了first节点
        first = first.next;
        N--;
        return item;
    }

    public Iterator<Item> iterator(){
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item>
    {
        private int i = N;
        public boolean hasNext(){
            return i > 0;
        }
        public Item next(){
            return a[--i];
        }
        public void remove(){
        }
    }
}