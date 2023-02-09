package com.moon.java.collectionFramework.queue.deque.linkedListDeque;

public class Node<E> {

    E data;// 데이터를 담을 변수
    Node<E> next; // 다음 노드를 가리키는 변수
    Node<E> prev; // 이전 노드를 가리키는 변수
    
    Node(E data){
        this.data = data;
        this.prev = null;
        this.next = null;
    }


}
