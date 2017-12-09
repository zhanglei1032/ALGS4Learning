/*
动态调整数组大小的栈实现
*/
import java.util.Iterator;

public class ResizeingArrayStack<Item> implements Iterable<Item>
{
  private Item[] a = (Item[]) new Object[1];
  private int N = 0;

  public boolean isEmpty()
  {
    return N == 0;
  }

  public int size()
  {
      return N;
  }

  private void resize(int max)
  {

  }

  public void push(Item item)
  {

  }

  public Item pop()
  {

  }

  public Iterator<Item> iterator()
  {}

  private class ReverseArrayIterator implements Iterator<Item>
  {

  }
}
