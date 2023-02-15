package 원형큐;

import service.YmQueue;

public class CircleQueue<T> implements YmQueue<T> {
    private T[] queue = null;
    private int front = -1;
    private int rear = -1;
    private int dataSize = 0;

    public CircleQueue(int size) {
        this.checkSize(size);

        this.queue =  (T[]) new Object[size];
    }

    public boolean isInit() {
        return front == -1;
    }

    @Override
    public void enQueue(T data) {
        if (this.isFull()) throw new StackOverflowError("스택이 가득 가득~");
        if (this.isInit()) front++;

        rear = (rear + 1) % queue.length;

        queue[rear] = data;
        dataSize++;
    }

    @Override
    public T deQueue() {
        if (this.isEmpty()) throw new IllegalStateException("내보낼 데이터가 없습니다.");

        front = (front + 1) % queue.length;

        if (--dataSize == 0) {
            front = -1;
            rear = -1;
        }
        return null;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean isEmpty() {
        return dataSize == 0;
    }

    @Override
    public boolean isFull() {
        return dataSize == queue.length;
    }

    @Override
    public int size() {
        return queue.length;
    }

    @Override
    public int dataSize() {
        return dataSize;
    }
}
