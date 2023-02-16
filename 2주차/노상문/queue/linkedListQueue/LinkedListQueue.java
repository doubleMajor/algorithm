package com.moon.java.collectionFramework.queue.linkedListQueue;

import com.moon.java.collectionFramework.queue.QueueInterface;

import java.util.NoSuchElementException;

public class LinkedListQueue<E> implements QueueInterface<E> {

    /*
     * head : 큐에서 가장 앞에 있는 노드 객체를 가리키는 변수
     * tail : 큐에서 가장 뒤에 있는 노드 객체를 가리키는 변수
     * size : 큐에 담긴 요소의 개수
     * */

    private Node<E> head;
    private Node<E> tail;
    private int size;

    /*
     * 처음 큐를 생성할 때는 아무런 데이터가 없으므로
     * head와 tail은 가리킬 노드가 없는 상태이므로
     * null로 초기화해주고, size 또한 0으로 초기화 해준다.
     *
     * 메인클래스에서 객체를 생성하면 다음과 같이 생성한다.
     * LinkedListQueue<Integer> q = new LinkedListQueue<>();
     * */

    public LinkedListQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /*
     * offer 메서드 구현
     *
     * Queue에 데이터를 추가하는 메서드다.
     * Queue는 기본적으로 offer는 후방 (맨 뒤)에 데이터를 추가해야한다.
     * 리스트로 치면 add(E value)와 같은 역할이다.
     *
     * -가장 마지막 부분에 추가 - offer(E item).
     * Step 1. make new Node   ex) Node<E> newNode = new Node<>(5).
     * Step 2. linking.
     * Step 3. update tail.
     * */
    @Override
    public boolean offer(E value) {
        Node<E> newNode = new Node<E>(value);

        /*
         * 비어 있을 경우
         * 큐에 아무 요소들이 없을 때, 즉 size가 0일 때는 새요소가 head이자 tail이 된다.
         * 그렇기 때문에 큐가 비어있을 경우에는 head르 새 노드로 가리키도록 해야하고 그 외에는 이미 요소가 있다는 의미이므로,
         * tail이 가리키는 요소의 다음 노드 (tail.next)를 새노드를 가리키도록 한뒤 tail이 가리키는 노드를 새 노드를 가리키도록 한다.
         * */
        if (size == 0) {
            head = newNode;
        }
        // 그 외의 경우 마지막 노드 (tail)의 다음 노드(next)가 새 노드를 가리키도록 한다.
        // 마지막노드일때 찾을 필요없이 next로 newNode에 연결 .
        else {
            tail.next = newNode;
        }
        /*
         * tail이 가리키는 노드를 새 노드로 바꿔준다.
         * */
        tail = newNode;
        size++;

        return true;
    }

    /*
     * poll 메소드
     * 또한 그리 어렵지 않게 만들 수 있다.
     * 리스트에서의 remove()와 같은 역할로 가장 앞에 있는 위치에 있는 요소인 head 요소를 삭제하면 된다.
     * 다만 중요한점은 '삭제할 요소가 없는 경우' 이다.
     *
     * 자바의 Queue에 관한 API 문서를 보면 알겠지만, add()- offer(),remove()-poll(),element()-peek()이 가각 대응된다.
     * add()의 경우 만약 용적이 제한 되어 있는데 용적보다 더 많은 요소를 추가할 경우 예외를 던지는데, 지금 구현하는 것에서는 최대 제한을 걸지 않아 넘어갔다.
     *
     * 삭제의 경우 remove() 같은 경우 삭제 할 요소가 없으면 NoSuchElementException() 예외를 던진다.
     * 반대로 poll() 의 경우 삭제할 필요가 없다면 null을 반환한다는 차이가 있다.
     * */
    @Override
    public E poll() {

        // 삭제할 요소가 없을 경우 null 반환
        if (size == 0) {
            return null;
        }
        // 삭제될 요소의 데이터를 반환하기 위한 임시 변수
        E element = head.data;

        // head 노드의 다음 노드
        Node<E> nextNode = head.next;

        // ead의 모든 데이터 삭제
        head.data = null;
        head.next = null;

        // head가 가리키는 노드를 삭제된 head노드의 다음 노드를 가리키도록 변경
        head = nextNode;
        size--;

        return element;
        /*
         * poll
         * */
    }

    /*
     * remove 메서드
     * */
    public E remove() {
        E element = poll();
        if (element == null) {
            throw new NoSuchElementException();
        }
        return element;
    }

    /*
     * 가장 앞에 있는 데이터(head.data)를 삭제하지 않고 확인만 하고 싶을 때가 있다.
     * 그럴 때 쓰는 것이 peek() 메서드다.
     * poll() 메서드에서 삭제과정만 없는 것이 peek()이다.
     * 그래서 삭제과정만 빼고 그대로 반환하면 된다.
     * 참고로 큐에 데이터가 없는 경우는 null을 던진다.
     * 반대로 이와 유사한 element() 메서드는 큐에 요소가 없을 경우 remove()메서드와
     * 마찬가지로 NoSuchElementException 예외를 던진다.
     * */
    @Override
    public E peek() {
        if (size==0){
            return null;
        }

        return head.data;
    }

    public E element(){
        E element = peek();

        if (element == null){
            throw new NoSuchElementException();
        }
        return  element;
    }

    /*
    * size() 메서드
    * size는 매우 간단하게 현재 큐에 있는 요소의 개수를 알려준다.
    * */
    public int size(){
        return size;
    }

    /*
    * isEmpty()메서드
    * isEmpty() 메서드는 현재 큐가 비어있는지를 확인할 때 쓰인다. 요소의 개수가 0개라면 비어있다는 뜻으로,
    * 비어있다면 true를 , 비어있지 않다면 false를 반환한다.
    * */
    public boolean isEmpty(){
        return size == 0;
    }

    public boolean contains(Object value){
        /*
        * head 데이터부터 x가 null이 될때 까지 value랑 x의 데이터(x.data)랑
        * 같은지를 비교하고 같을 경우 true를 반환한다.
        * */
        for (Node<E> x= head; x != null; x= x.next){
            if(value.equals(x.data)){
                return true;
            }
        }
        return false;
    }

    public void clear(){
        for (Node<E> x = head;x != null;) {
            Node<E> next = x.next;
            x.data = null;
            x.next = null;
            x= next;
        }
        size = 0;
        head = tail = null;
    }
}