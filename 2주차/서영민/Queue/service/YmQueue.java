package service;

public interface YmQueue<T> {
    void enQueue(T data);
    T deQueue();
    T peek();
    void clear();
    boolean isEmpty();
    boolean isFull();
    int size();
    int dataSize();

    default void checkSize(int size) {
        if (size < 0) throw new IllegalStateException("사이즈는 0보다 커야합니다");
    }
}
