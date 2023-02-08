package com.moon.java.collectionFramework.queue.linkedListQueue;

public class Node<E> {
    /*
    * LinkedList를 기반으로 Queue를 구현하기에 앞서 가장 기본적인 데이터를 담을 Node 클래스를 먼저 구현하고자한다.
    * 이 때 Node 클래스는 앞으로 구현 할 LinkedListQueue와 같은 패키지에 생성한다.
    *
    * */
    E data;
    Node<E> next; // 다음 노드를 가리키는 역할을 하는 변수

    Node(E data){
        this.data = data;
        this.next = null;
    }

}
