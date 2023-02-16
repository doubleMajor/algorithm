package 선형큐;

import service.YmQueue;

public class LinerQueue<T> implements YmQueue<T> {
    private T[] queue = null;
    private int rear = -1;

    public LinerQueue(int size) {
        this.checkSize(size);

        this.queue =  (T[]) new Object[size];
    }

    @Override
    public void enQueue(T data) {
        if (this.isFull()) throw new StackOverflowError("스택이 가득 가득~");

        queue[++rear] = data;
    }

    @Override
    public T deQueue() {
        if (this.isEmpty()) throw new IllegalStateException("내보낼 데이터가 없습니다.");

        T data = queue[0];

        for (int i = 0; i < rear + 1; i++) {
            queue[i] = (i == rear) ? null : queue[i + 1];
        }

        rear--;

        return data;
    }

    @Override
    public T peek() {
        return rear == -1 ? null : queue[0];
    }

    @Override
    public void clear() {

        while (rear > -1) {
            queue[rear--] = null;
        }
    }

    @Override
    public boolean isEmpty() {
        return this.rear == -1;
    }

    @Override
    public boolean isFull() {
        return this.queue.length == (rear + 1);
    }

    @Override
    public int size() {
        return this.queue.length;
    }

    @Override
    public int dataSize() {
        return rear + 1;
    }
}
