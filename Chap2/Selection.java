//选择排序；每个元素会经过一次交换和N-1-i次比较
//所以总共有N次交换和(N-1)+(N-2)+····+2+1 = N(N-1)/2次比较
//特点：1、数据移动是最少的
//      2、运行时间和输入的初始状态无关
//      为找出最小元素的一次扫描并不能为下次扫描提供信息
//      （有序的数组排序时间与无序相同）
public class Selection{
    public static void sort(Comparable[] a){
        int N = a.length;
        //外层循环，i为首个未排序元素
        for (int i = 0; i < N ; i++) {
            int min = i;
            //内层循环，从第二个未排序元素(即i+1)开始遍历后续元素
            //找出最小值然后与第一个未排序元素(即i)交换
            for (int j = i + 1; j < N; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i, min);
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