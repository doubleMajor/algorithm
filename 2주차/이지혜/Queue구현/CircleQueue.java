package Main.과제;

import java.util.LinkedList;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;

interface CircleQueueInterface{
    public void newQueue(int maxSize);
    public boolean isFull();
    public boolean isEmpty();
    public boolean enqueue(int value);
    public boolean dequeue();
    public int peek();

}

public class CircularQueue implements CircleQueueInterface{
    //원형 큐 - 메모리가 낭비되는 다른 큐의 단점을 보완하기 위해 만들어진 큐
    private int front;
    private int rear;
    private int maxSize;
    private int[] QArray;

    public void CircleQue(int maxSize){
        this.front = 0;
        this.rear = -1;
    }

    @Override
    public void newQueue(int maxSize) {
        front = 0;
        rear = 0;
        this.maxSize = maxSize;
        QArray = new int[maxSize];

        System.out.println("QSize = "+maxSize);

    }

    @Override
    public boolean isFull() {
        return (rear+1) % maxSize == front;
    }

    @Override
    public boolean isEmpty() {
        return front == rear;
    }

    @Override
    public boolean enqueue(int value) {
        if(isFull()) {
            return false;
        }else{
            rear = (++rear) % maxSize;
            QArray[rear] = value;
            return true;
        }
    }

    @Override
    public boolean dequeue() {
        if(isFull()) {
            return false;
        }else{
            front = (++front) % maxSize;
            QArray[front] = 0;
            return true;
        }
    }

    @Override
    public int peek() {
        if(isEmpty()) return -1;
        else return QArray [(front+1)%maxSize];
    }


}
