public class QuickUnion{
    private int[] id;
    private int count;

    //初始化分量id数组，长度为元素个数
    public QuickUnion(int N){
        count = N;
        id = new int [N];
        for (int i = 0; i < N ; i++) {
            id[i] = i;
        }
    }

    public int count(){
        return count;
    }

    public boolean connected(int p, int q){
        return find(p) == find(q);
    }
    //因为只有根节点满足p=id[p]，所以find返回的是p的根节点
    public int find(int p){
        while (p != id[p]){
            p = id[p];
        }
        return p;
    }

    public void union(int p, int q){
        int pRoot = find(p);
        int qRoot = find(q);
        //pq具有相同根节点，在同一个树上，已经连通，函数返回
        if (pRoot == qRoot) {
            return;
        }
        //pq不连通，把q的根节点赋给id[pRoot]，pRoot成为qRoot的子树
        id[pRoot] = qRoot;
        count--;
    }

    public static void main(String[] args){
        int N = StdIn.readInt();
        UF uf = new UF(N);
        while(!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            //pq连通，跳出本次循环
            if (uf.connected(p, q)) {
                continue;
            }
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + "components");
    }
}