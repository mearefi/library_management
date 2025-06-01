package datastructures.lists;
import datastructures.interfaces.LinkedList;
import java.util.NoSuchElementException;
import java.util.Objects;


public class CustomLinkedList<T> implements LinkedList<T>{
    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(T data) {
            this.data = data;
        }

        Node(Node<T> prev, T data, Node<T> next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node<T> head, tail;
    private int size = 0;

    public CustomLinkedList() {

    }

    @Override
    public void addFirst(T t) {
        if (t == null) throw new NullPointerException("Cannot add null elements");
        final Node<T> h = head;
        final Node<T> newNode = new Node<>(null, t, h);
        head = newNode;
        if (h == null)
            tail = newNode;
        else
            h.prev = newNode;
        size++;
    }

    @Override
    public void addLast(T t) {
        if (t == null) throw new NullPointerException("Cannot add null elements");
        final Node<T> tl = tail;
        final Node<T> newNode = new Node<>(tl, t, null);
        tail = newNode;
        if (tl == null)
            head = newNode;
        else
            tl.next = newNode;
        size++;
    }

    private T unlinkFirst(Node<T> h) {
        final T element = h.data;
        final Node<T> next = h.next;
        h.data = null;
        h.next = null;
        head = next;
        if (next == null)
            tail = null;
        else
            next.prev = null;
        size--;
        return element;
    }

    @Override
    public T removeFirst() {
        final Node<T> h = head;
        if (h == null)
            throw new NoSuchElementException();
        return unlinkFirst(h);
    }

    private T unlinkLast(Node<T> tl) {
        final T element = tl.data;
        final Node<T> prev = tl.prev;
        tl.data = null;
        tl.prev = null;
        tail = prev;
        if (prev == null)
            head = null;
        else
            prev.next = null;
        size--;
        return element;
    }

    @Override
    public T removeLast() {
        final Node<T> tl = tail;
        if (tl == null)
            throw new NoSuchElementException();
        return unlinkLast(tl);
    }

    @Override
    public T getFirst() {
        final Node<T> h = head;
        if (h == null)
            throw new NoSuchElementException();
        return h.data;
    }

    @Override
    public T getLast() {
        final Node<T> tl = tail;
        if (tl == null)
            throw new NoSuchElementException();
        return tl.data;
    }

    @Override
    public void clear() {
        for (Node<T> x = head; x != null; ) {
            Node<T> next = x.next;
            x.data = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        head = tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(T t) {
        addLast(t);
        return true;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) throw new NullPointerException("Cannot check for contains(null) as nulls are not supported");
        for (Node<T> x = head; x != null; x = x.next) {
            if (o.equals(x.data)) {
                return true;
            }
        }
        return false;
    }

    private T unlink(Node<T> x) {
        final T element = x.data;
        final Node<T> next = x.next;
        final Node<T> prev = x.prev;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }
        x.data = null;
        size--;
        return element;
    }


    @Override
    public boolean remove(Object o) {
        if (o == null) throw new NullPointerException("Cannot remove null");
        for (Node<T> x = head; x != null; x = x.next) {
            if (o.equals(x.data)) {
                unlink(x);
                return true;
            }
        }
        return false;
    }

    private Node<T> node(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        if (index < (size >> 1)) {
            Node<T> x = head;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<T> x = tail;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }


    @Override
    public T get(int index) {
        return node(index).data;
    }

    @Override
    public T set(int index, T element) {
        if (element == null) throw new NullPointerException("Cannot set null element");
        Node<T> x = node(index);
        T oldVal = x.data;
        x.data = element;
        return element;
    }
}