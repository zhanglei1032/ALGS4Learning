//基于链表的无序查找
public class SequentialSearchST<Key, Value>{
    private Node first;
    private class Node {
        Key key;
        Value val;
        Node next;
        public Node(Key key, Value val, Node next){
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public Value get(Key key){
        for (Node x = first; x != null; x = x.next ) {
            //命中
            if (key.equeals(x.key)) {
                return x.val
            }
            //未命中
            return null;
        }
    }

    public void put (Key key, Value val){
        for (Node x = first; x != null ; x = x.next ) {
            //命中，更新
            if (key.equeals(x.key)) {
                x.val = val;
                return;
            }
        }
        //未命中，新建节点
        first = new Node(key, val, first);
    }
}