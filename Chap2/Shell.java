//
public class Shell{
    public static void sort(Comparable[] a){
        int N = a.length;
        int h = 1;
        //确定一个h值，常用的还有N/2
        //此处设为序列1,4,13,40,121,364,1093，...中的一个值
        while (h < N/3) {
            h = 3*h +1;
        }
        while (h >=1) {
            //内部为步长为h的插入排序
            for (int i = h; i < N ; i++ ) {
                for (int j = i; j >= h && less(a[j], a[j-h]); j-=h) {
                    exch(a, j, j-h);
                }
            }
            //缩小h
            h = h/3;
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