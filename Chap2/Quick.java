//
public class Quick{
    public static void sort(Comparable[] a){
        StdRandom.shuffle(a);
        sort(a, 0, a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi){
        if (hi <= lo) {
            return;
        }
        //切分数组，a[lo]~a[j-1]的元素都比a[j]小，a[j+1]到a[hi]的元素都比a[j]大
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    public static int partition(Comparable[] a, int lo, int hi){
        //左右扫描指针
        int i = lo, j = hi + 1;
        //选定a[lo]为初始切分元素
        Comparable v = a[lo];
        while(true){
            //扫描左右，检查扫描是否结束并交换元素
            //第一个while循环，不断比较a[i]和v的大小，当a[i]大于v时退出
            //此时a[i]为第一个比v大的元素
            while (less(a[++i], v)) {
                //当v为数组最大素时的退出条件，也可设置最大元素为哨兵元素，
                //放在a[length-1]中，以去除此项检查
                //此时所有的a[i]都小于v，i持续增长至hi，防止a[++i]访问越界
                if (i == hi) {
                    break;
                }
            }
            //第二个while循环，不断比较a[j]和v的大小，当a[j]小于v时退出
            //此时a[j]为第一个比v小的元素
            while (less(v, a[--j])) {
                //当v为数组最小素时的退出条件，j==lo的条件是冗余的，因为a[lo]=v
                //此时所有的a[i]都大于v，j持续减少至lo，防止a[--j]访问越界
                if (j == lo) {
                    break;
                }
            }
            //i和j相遇时，退出循环
            if (i >=j) {
                break;
            }
            //交换逆序对a[i],a[j]
            exch(a, i, j);
        }
        //将v=a[j]放入正确的位置
        exch(a, lo, j);
        return j;
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