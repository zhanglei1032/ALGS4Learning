//基于二叉查找树的符号表
public class BST<Key extends Comparable<Key>, Value>{
    private Node root;

    private class Node{
        private Key key;
        private Value val;
        private Node left, right;
        private int N;

        public Node(Key key, Value val, int N){
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public int size(){
        return siez(root);
    }

    public int size(Node x){
        if (x == null) {
            return 0;
        } else {
            return x.N;
        }
    }

    public Value get(Key key){
        return get(root, key);
    }

    public Value get(Node x, Key key){
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.val;
        }
    }

    private int rank(Key key){
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            } else if( cmp > 0){
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    public void put (Key key, Value val){
        root = put(root, key, val);
    }

    private Node put (Node x, Key key, Value val){
        if (x == null) {
            return new Node(key, val, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min(){
        return min(root).key;
    }

    private Node min(Node x){
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    public Key max(){
        return keys[N-1];
    }

    public Key select(int k){
        return keys[k];
    }

    public Key ceiling(Key key){
        int i = rank(key);
        return keys[i];
    }

    public Key floor(Key key){
        Node x = floor(root, key);
        if (x == null) {
            reutrn null;
        }
        return x.key;
    }

    private Node floor(Node x, Key key){
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        } else if (cmp < 0) {
            return floor(x.left, key);
        }
        Node t = floor(x.right, key);
        if (t != null) {
            return t;
        } else {
            return x;
        }
    }

    //delete为自己实现的
    public void delete(Key key){
        if (isEmpty()) {
            return null;
        }
        int index = rank(key)
        if ( index == 0) {
            return null;
        } else {
           keys[i] = null;
           vals[i] = null;
        }
    }

    public Iterable<key> keys(Key lo, Key hi){
        Queue<Key> q = new Queue<Key>();
        for (int i = rank(lo); i < rank(hi) ; i++) {
            q.enqueue(keys[i]);
        }
        if (contains(hi)) {
            q.enqueue(keys[rank(hi)]);
        }
        return q;
    }
}