//基于堆的优先队列
public class MaxPQ<Key extends Comparable<Key>>{
    private Key[] pq;
    //存储于pq[1...N]中，pq[0]没有使用
    private int N = 0;

    public MaxPQ(int maxN){
        pq = (Key[]) new Comparable[maxN+1];
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }
    //把值存储在右子树最末尾一个节点，然后执行上浮操作
    public void insert(Key v){
        pq[++N] = v;
        swim(N);
    }
    //从数组顶端删除最大元素并将最后一个元素放到顶端
    //然后减小堆的大小并让这个元素下沉到合适位置
    public Key delMax(){
        Key max = pq[1];
        each(1, N--);
        pq[N+1] = null;
        sink(1);
        return max;
    }

    private static boolean less(int i, int j){
        return pq[i].compareTo(pq[j]) < 0;
    }

    private static void exch(int i, int j){
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
    //上浮操作，交换k和它的父节点k/2，直到它比新的父节点小或到达root为止，
    //此处利用了堆的性质，父节点比子节点大，且其index为左子节点的一半
    private void swim(int k){
        while (k > 1 && less(k/2, k)) {
            each(k/2, k);
            k = k/2;
        }
    }
    //下沉操作，交换k和它较大的子节点（为了保证父节点大于子节点）2k或2k+1，
    //直到它比新的子节点都大或者到达堆底
    private void sink(int k){
        while (2*k <= N){
            int j = 2*k;
            //如果右节点比较大，将待交换节点设为右节点（j+1）
            if (j < N && less(j, j+1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }
}