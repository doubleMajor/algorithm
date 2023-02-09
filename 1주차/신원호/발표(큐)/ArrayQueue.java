/**
 * 배열을 사용한 큐 구현
 *
 * 생성 시, 한정 된 크기를 가질 수 밖에 없고 그 크기만큼의 버퍼를 가진다는 한계가 있다.
 * 또한 dequeue 시, 배열의 첫번째 요소를 제거하고 나머지 요소들을 한칸씩 앞으로 당겨야 하므로
 * dequeue 시, O(n)의 시간이 소요된다.
 *
 */
public class ArrayQueue {
    private int size = 0;
    private Object[] queue = null;
    private int total = 0;

    public ArrayQueue(int total) {
        this.total = total;
        this.queue = new Object[total];
    }

    public void enqueue(Object data) {
        if (this.size == this.total) {
            throw new RuntimeException("Queue is full");
        }

        this.queue[this.size] = data;
        this.size++;
    }

    public Object dequeue() {
        if (this.size == 0) {
            throw new RuntimeException("Queue is empty");
        }


        Object data = this.queue[0];
        this.size--;
        for (int i = 0; i < this.size; i++) {
            this.queue[i] = this.queue[i + 1];
        }
        return data;
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
