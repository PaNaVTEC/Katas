package me.panavtec.katas.recents;

import java.util.Stack;

public class Recents {

  public static final int BOUNDED_CAPCITY = -1;
  private final Stack<String> stack = new Stack<>();
  private final int capacity;

  public Recents() {
    this(BOUNDED_CAPCITY);
  }

  public Recents(int capacity) {
    this.capacity = capacity;
  }

  public int length() {
    return stack.size();
  }

  public void put(String url) {
    if (url == null) throw new IllegalArgumentException("No nulls allowed");
    removeIfAlreadyExistsElement(url);
    stack.push(url);
    checkBoundedCapacity();
  }

  public String current() {
    return stack.pop();
  }

  public String elementAtIndex(int i) {
    return stack.elementAt(length() - i - 1);
  }

  private void removeIfAlreadyExistsElement(String url) {
    if (stack.contains(url)) {
      stack.remove(url);
    }
  }

  private void checkBoundedCapacity() {
    if (capacity != BOUNDED_CAPCITY && length() > capacity) {
      stack.removeElementAt(0);
    }
  }
}
