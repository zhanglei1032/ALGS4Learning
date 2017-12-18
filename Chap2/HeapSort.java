//
public class HeapSort{
    private static Comparable[] aux;

    public static void sort(Comparable[] a){
        int N = a.length;
        for (int k = N/2; k >= 1; k-- ) {
            sink(a, k, N);
        }
        while (N > 1) {
            exch(a, 1, N--);
            sink(a, 1, N);
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

    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a){
        for (int i = 0; i < a.length ; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a){
        for (int i = 1; i < a.length ; i++) {
            if (less(a[i], a[i-1])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        String[] a = In.readStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}