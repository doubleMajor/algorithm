/**
 * 원형 큐
 */
public class ArrayCircularQueue {
    private static final Object[] queue = new Object[5];
    private int front = 0;
    private int rear = 0;

    // Queue에 데이터를 추가
    public void enqueue(Object data) {
        queue[rear] = data;
        rear++;
    }

    // Queue에서 데이터를 꺼냄
    public Object dequeue() {
        Object o = queue[front];
        queue[front] = null;
        front--;

        return o;
    }

    public static void main(String[] args) {
        ArrayCircularQueue queue = new ArrayCircularQueue();
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
