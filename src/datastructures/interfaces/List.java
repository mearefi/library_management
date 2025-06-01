package datastructures.interfaces;

public interface List<T> extends Collection<T> {
    T get(int index);
    T set(int index, T element);
}