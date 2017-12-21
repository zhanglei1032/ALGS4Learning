//基于有序数组的二分查找
//使用两个数组，key数组存储comparable的值，
//value数组存储字符在字符串中最后一次出现的位置
//字符串searchexample，key=[a,c,e,h,l,m,p,r,s,x], value=[8,4,12,5,11,9,10,3,0,7]
public class BinarySearchST<Key extends Comparable<Key>, Value>{
    private Key[] keys;
    private Value[] vals;
    private int N;

    public BinarySearchST(int capacity){
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size(){
        return N;
    }

    public Value get(Key key){
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            //返回key在字符串中的索引
            return vals[i];
        } else {
            return null;
        }
    }
    //返回表中小于键key的键的数量，也就是将来键key插入数组时的位置
    //对于命中查找返回时lo=hi=mid,返回的就是命中键的index，也就是小于key的键的数量
    //对于非命中查找，返回时lo>hi=mid，
    //返回的是数组中比查找元素小的最大元素的index，也就是小于key的键的数量
    public int rank(Key key){
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
        int i = rank(key);
        //如果key已经存在，直接更新value数组key的索引即可
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        //如果key不存在，把索引i后面的数组元素右移
        for (int j = N; j > i ; j--) {
            keys[j] = key[j-1];
            vals[j] = val[j-1];
        }
        //把key和val放入对应数组的对应位置，N+1
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public Key min(){
        return keys[0];
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

    //floor为自己实现的
    public Key floor(Key key){
        int i = rank(key);
        return keys[i-1];
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

    public Iterable<Key> keys(Key lo, Key hi){
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