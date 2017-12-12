//插入排序对于非随机数组很有效
//对于1到N-1之间的每一个i，将a[i]与a[0]到a[i-1]中比它大的所有
//元素依次有序的交换。在i从左向右变化的过程中，它左侧的元素总是有序的，
//所以当i到达数组的右端时排序就完成了。
public class Insertion{
    public static void sort(Comparable[] a){
        int N = a.length;
        for (int i = 1; i < N ; i++) {
            //比较第i个元素和它前一个元素(此时i元素之前的元素是有序的)
            //如果小于前一个元素(即元素i-1)，交换位置
            //继续与此位置(即i-1)上的前一个元素(即i-2)比较
            //小于则继续向前交换，直到比前一个元素大(此时第i个
            //元素处于正确位置)
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
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