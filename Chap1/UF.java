public class UF{
    private int[] id;
    private int count;

    //初始化分量id数组，长度为元素个数
    public UF(int N){
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

    public int find(int p){
        return id[p];
    }

    public void union(int p, int q){
        int pID = find(p);
        int qID = find(q);
        //如果pq已经连通，退出
        if (pID == qID) {
            return;
        }
        //pq不连通，遍历id数组，把值与p相同的元素都改为和q相同
        for (int i = 0; i < id.length ; i++) {
            if (id[i] == pID) {
                id[i] = qID;
            }
        }
        count--;
    }

    public static void main(String[] args){
        int N = StdIn.readInt();
        UF uf = new UF(N);
        while(!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            //如果pq已经连通，则忽略
            if (uf.connected(p, q)) {
                continue;
            }
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + "components");
    }
}