import java.util.ArrayList;

/**
 * ArrayList를 사용하여 Queue 구현
 */
public class ArrayListQueue {

    // ArrayList를 사용하여 Queue 구현
    private ArrayList<Object> queue = new ArrayList<Object>();

    // Queue에 데이터를 추가
    public void enqueue(Object data) {
        queue.add(0,data);
    }

    // Queue에서 데이터를 꺼냄
    public Object dequeue() {
        Object data = queue.get(queue.size() - 1);
        queue.remove(queue.size() - 1);

        return data;
    }

    public static void main(String[] args) {
        ArrayListQueue queue = new ArrayListQueue();
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
