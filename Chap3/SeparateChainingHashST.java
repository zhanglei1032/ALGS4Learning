public class SeparateChainingHashST<Key, Value>{
    private int N;
    private int M;
    private SeparateChainingHashST<Key, Value>[] st;

    public SeparateChainingHashST(){
        this(997);
    }

    public SeparateChainingHashST(int M){
        this.M = M;
        st = (SeparateChainingHashST<Key, Value>[]) new SeparateChainingHashST[M];
        for (int i = 0; i< M; i++) {
            st[i] = new SeparateChainingHashST();
        }
    }

    private int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key){
        return (Value) st[hash(key)].get(key);
    }

    public void put(Key key, Value val){
        st[hash(key)].put(key, val);
    }

}