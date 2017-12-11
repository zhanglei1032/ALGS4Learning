/*
动态调整数组大小的栈实现
*/
import java.util.Iterator;

public class ResizeingArrayStack<Item> implements Iterable<Item>
{
  //栈
  private Item[] a = (Item[]) new Object[1];
  //栈中元素数量
  private int N = 0;

  public boolean isEmpty(){
    return N == 0;
  }

  public int size(){
      return N;
  }

  private void resize(int max){
  //将栈a移动到一个大小为max的新数组
    Item[] temp = (Item[]) new Object[max];
    for (int i=0; i < N; i++ ) {
      temp[i] = a[i];
    }
    a = temp;
  }

  public void push(Item item){
    //栈满时，resize到2倍长度
    if (N == a.length) {
      resize(2*a.length);
    }
    //入栈，并把N加上1
    a[N++] = item;
  }

  public Item pop(){
    Item item = a[--N];
    a[N] = null;
    //栈中元素个数为长度的1/4时，栈resize为1/2
    if (N > 0 && N == a.length/4) {
      resize(a.length/2);
    }
    return item;
  }

  public Iterator<Item> iterator(){
    return new ReverseArrayIterator();
  }

  private class ReverseArrayIterator implements Iterator<Item>
  {
    private int i = N;
    public boolean hasNext(){
      return i > 0;
    }
    public Item next(){
      return a[--i];
    }
    public void remove(){
    }
  }
}
