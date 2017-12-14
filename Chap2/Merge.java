//
public class Merge{
    private static Comparable[] aux;

    public static void sort(Comparable[] a){
        aux = new Comparable[a.length];
        sort(a, 0, a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi){
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo)/2;
        sort(a, lo, mid);
        sort(a, mid+1, hi);
        merge(a, lo, mid, hi);
    }

    //原地归并，借助aux数组辅助
    public static void merge(Comparable[] a, int lo, int mid, int hi){
        //i跟踪数组左半边，j跟踪数组右半边
        int i = lo, j = mid + 1;
        //复制一份到aux数组
        for (int k = lo; k <= hi; k++ ) {
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++ ) {
            //此时左半边数组已经全部比较完，所以把右半边数组直接复制到a数组
            if (i > mid) {
                a[k] = aux[j++];
            //此时右半边数组已经全部比较完，所以把左半边数组直接复制到a数组
            } else if (j > hi) {
                a[k] = aux[i++];
            //真正的比较操作，左右两边数组分别比较到了aux[i]和aux[j]，
            //把较小的元素放入a数组,然后i或j向后移动
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
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