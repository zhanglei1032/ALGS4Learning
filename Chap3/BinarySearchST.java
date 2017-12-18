//基于有序数组的二分查找
//使用两个数组，key数组存储comparable的值，value数组存储对应的值，此处存index
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
            return vals[i];
        } else {
            return null;
        }
    }

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
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        for (int j = N; j > i ; j--) {
            keys[i] = key[j-1];
            vals[j] = val[j-1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
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
}