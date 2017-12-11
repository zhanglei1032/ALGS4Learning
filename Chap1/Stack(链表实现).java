public class Stack<Item> implements Iterable<Item>{
    private Node first;
    private int N;
    //
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
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }
    public Item pop(){
        Item item = first.item;
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