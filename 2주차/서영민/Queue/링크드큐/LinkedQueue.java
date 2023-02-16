package 링크드큐;

import service.YmQueue;

public class LinkedQueue<T> implements YmQueue<T> {
    private Node<T> first;
    private Node<T> last;
    private int length = 0;

    public boolean isInit() {
        return first == null;
    }

    @Override
    public void enQueue(T data) {

        if (this.isInit()) {
            first = node(data);
            last = first;
        } else {
            last = last.next = node(data);
        }

        length++;
    }

    @Override
    public T deQueue() {
        if (this.isEmpty()) throw new IllegalStateException("내보낼 데이터가 없습니다.");

        T data = first.data;

        first = first.next;

        length--;

        return data;
    }

    @Override
    public T peek() {
        return first == null ? null : first.data;
    }

    @Override
    public void clear() {

        while (first != null) {
            first = first.next;
        }

        length = 0;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int size() {
        return this.length;
    }

    @Override
    public int dataSize() {
        return this.length;
    }

    private Node<T> node(T data) {
        return new Node<T>(data);
    }
}
