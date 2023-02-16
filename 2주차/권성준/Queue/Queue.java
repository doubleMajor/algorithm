public class Queue {
        int size;
        int front = 0; // 시작
        int rear = 0; // 마지막
        Object[] queue;

        public Queue(int size) {
            this.size = size;
            this.queue = new Object[size];
        }

        public boolean isFull(){
            return front == rear && queue.length > 0;
        }

        public boolean isEmpty(){
            return front == rear && queue.length == 0;
        }

        public void enqueue(Object data){
            if(isFull()) {
                System.out.println("큐에 공간이 없습니다.");
                return;
            }
            queue[rear++] = data;
            rear = rear % queue.length;
        }

        public Object dequeue(){
            if(isEmpty()){
                System.out.println("큐가 비어있습니다");
                throw new ArrayIndexOutOfBoundsException();
            }
            Object dequeuedData = queue[front];
            queue[front++] = null;
            front = front % queue.length;

            return dequeuedData;
        }

        public Object peek(){
            return queue[front];
        }
}