//基于二叉查找树的符号表
public class BST<Key extends Comparable<Key>, Value>{
    private Node root;

    private class Node{
        private Key key;//键
        private Value val;//值
        private Node left, right;//指向左右子树的链接
        private int N;//以该节点为根的子树的节点数量
        //构建一个二叉查找树节点，需要一个键、一个值、和子树节点的数量
        public Node(Key key, Value val, int N){
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public int size(){
        return size(root);
    }

    private int size(Node x){
        if (x == null) {
            return 0;
        } else {
            return x.N;
        }
    }

    public Value get(Key key){
        return get(root, key);
    }
    //在以x为根节点的子树中查找并返回key对应的值，没有返回null
    private Value get(Node x, Key key){
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

    public void put (Key key, Value val){
        root = put(root, key, val);
    }
    //如果key存在于以x为根节点的子树中则更新它的值；
    //否则将以key和val为键值对的新的节点插入到该子树中
    private Node put (Node x, Key key, Value val){
        //如果x为null，返回一个新建节点
        //当传入的root为null时，返回的新节点将作为新树的根
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
        //沿着搜索路径向上增加节点计数器的值
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min(){
        return min(root).key;
    }
    //在左子树中递归查找最小值
    private Node min(Node x){
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }


    public Key floor(Key key){
        Node x = floor(root, key);
        if (x == null) {
            reutrn null;
        }
        return x.key;
    }
    //如果key大于二叉树根节点，那么只有当根节点右子树中存在小于等于key的节点时，
    //小于等于key的最大值才存在在右子树，否则小于等于key的最大值为根节点本身
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

    public Key select(int k){
        return select(root, k).key;
    }

    private Node select(Node x, int k){
        //返回排名为k的节点
        if (x == null) {
            return null;
        }
        //t为小于节点x的节点的数量
        int t = size(x.left);
        if (t > k) {
            return select(x.left, k);
        } else if (t < k) {
        //从右子树中选择比x.right大k-t-1的节点
        //因为x.right排名本身就比x大1    
            return select(x.right, k-t-1);
        } else {
            return x;
        }
    }

    public int rank(Key key){
        return rank(key, root);
    }

    private int rank(Key key, Node x){
        if (x == null) {
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return rank(key, x.left);
        } else if (cmp > 0) {
            //因为x.right排名本身就比x大1，所以需要加1
            return 1 + size(x.left) + rank(key, x.right);
        } else {
            return size(x.left);
        }
    }
    //删除操作待注释
    public void deleteMin(){
        root = deleteMin(root);
    }

    private Node deleteMin(Node x){
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key){
       root = delete(root, key);
    }

    private Node delete(Node x, Key key){
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = delete(x.left, key);
        } else if (cmp > 0) {
            x.right = delete(x.right, key);
        } else {
            if (x.right == null) {
                return x.left;
            }
            if (x.left == null) {
                return x.right;
            }
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + -1;
        return x;
    }

    public Iterable<Key> keys(){
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi){
        Queue<Key> q = new Queue<Key>();
        keys(root, q, lo, hi);
        return q;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi){
        if (x == null) {
            return;
        }
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) {
            keys(x.left, queue, lo, hi);
        }
        if (cmplo <= 0 && cmphi >= 0) {
            queue.enqueue(x.key);
        }
        if (cmphi > 0) {
            keys(x.right, queue, lo, hi);
        }
    }
}