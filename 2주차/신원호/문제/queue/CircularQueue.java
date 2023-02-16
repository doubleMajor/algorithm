public class CircularQueue<T> {

    private T[] elements;
    private int front;
    private int rear;
    private int maxSize;

    public CircularQueue(int size) {
        this.elements = (T[]) new Object[size + 1];

        this.front = 0;
        this.rear = 0;
        this.maxSize = size + 1;
    }

    public void enqueue(T data) {
        if (this.isFull()) {
            throw new RuntimeException("Q is empty");
        }

        this.rear = (this.rear + 1) % this.maxSize;
        this.elements[this.rear] = data;
    }

    public T dequeue() {

        if (this.isEmpty()) {
            throw new RuntimeException("Q is empty");
        }

        this.front = (this.front + 1) % this.maxSize;

        return this.elements[this.front];
    }

    public int size() {
        if (this.front <= this.rear) {
            return this.rear - this.front;
        }

        return this.maxSize - this.front + this.rear;
    }

    public boolean isEmpty() {
        return this.front == this.rear;
    }

    private boolean isFull() {
        return (this.rear + 1) % this.maxSize == this.front;
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(50);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);
        queue.enqueue(9);
        queue.enqueue(10);
        queue.enqueue(11);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }
}