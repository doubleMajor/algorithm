package com.moon.java.collectionFramework.queue.deque.linkedListDeque;

import com.moon.java.collectionFramework.queue.QueueInterface;

import java.util.NoSuchElementException;

public class LinkedListDeque<E> implements QueueInterface<E> {

    private Node<E> head; // 가장 앞에 있는 노드를 가리키는 변수
    private Node<E> tail; // 가장 뒤에 있는 노드를 가리키는 변수
    private int size; // 요소 (노드)의 개수

    /*
     * head : 가장 앞에 있는 노드를 가리키는 변수, 큐에서의 front와 같다.
     * tail : 가장 뒤에 있는 노드를 가리키는 변수다. 큐에서의 rear와 같은 의미다.
     * */
    public LinkedListDeque() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /*
     * offer 계열 메서드
     * Deque의 삽입은 크게 두가지로 나뉜다.
     * 기본 메서드 offer() 및 offerLast()는 후방(맨 뒤)에 데이터를 추가한다.
     * 리스트로 치면 add(E value) , addLast (E value)와 같은 역할.
     * 두 번째는 전방( 맨 앞) 에 데이터를 추가하는 경우다.  이는 offerFirst()가 담당하며 리스트로 치면 addFirst()와 같은 역할.
     * 데이터가 비어있을 때를 주의해야 한다.
     * 마지막 부분 후방 추가 : offer(E item) offerLast(E item)
     * 가장 앞 부분에 추가 : offerFirst(E item)
     * */

    /*
     * offerFirst(E item) 전방 삽입
     * 원래는 기본 메소드인 offer() 즉, 가장 뒷 부분에 추가하는 것부터 구현할 수도 있지만, 이후에 구현해보면 알겠지만, 양방향 연결리스트의 경우는 전방 삽입부터 구현하는 것이 편리하다.
     * 이유는 데이터가 한개도 없을 때 결국 첫 번째 원소는 head는 이자 tail이 되는데, 이 때는 전방 삽입 알고리즘으로 구현 되는 것이 좀 더 직관성이 있기 때문이다.
     * Step 1. make new node
     * Step 2. linking
     * Step 3. update head
     * */
    public boolean offerFirst(E value) {
        Node<E> newNode = new Node<E>(value); // 새노드 생성
        newNode.next = head; // 새 노드의 다음 노드로 head노드를 연결
        /*
        * head가 null이 아닐 경우에만 기존 head노드의 prev 변수가
        * 새 노드를 가리키도록 한다.
        * 이유는 기존 head 노드가 없는 경우 (null)은 데이터가
        * 아무 것도 없던 상태였으므로 head.prev를 하면 잘못된 참조가 된다.
        * */
        if(head!=null){
            head.prev = newNode;
        }
        head = newNode; // head가 가리키는 노드가 새 노드를 가리킨다.
        size++;
        /*
        * 다음에 가리킬 노드가 없는 경우 (=데이터가 새 노드밖에 없는 경우 = 이전의 head가 null인 경우)
        * 데이터가 한 개 (새 노드)밖에 없으므로 새 노드는 처음부터 시작 노드이자
        * 마지막 노드다. 즉 tail = head이다.
        * */
        if (head.next == null){
            tail = head;
        }
        return true;
        /*
        * 데이터가 없는 경우, 즉 덱에 아무런 요소가 없는 경우는 head=null;인 상태다.
        * 만약 head=null인 상태일 경우 newNode.next= head 는 결국 newNode.next = null 이 된다.
        *
        * 그리고 원래는 앞부분에 추가하려면 기존 head의노드의 prev가 가리키는 노드를 새 노드로 연결해주어야 한다.
        * 근데 head =null 상태라면 null.prev즉 null 상태에서 어떠한 변수를 참조할수 없는데 , 참조하려고 하면 NullPointerException 예외가 던져진다.
        * 이러한 것을 방지하기 위해 head가 null이 아닐때만 head.prev를 새로운 노드를 가리키도록 해주어야 한다.
        *
        * 그 다음 마지막 그림처럼 head가 가리키는 노드를 새노드를 가리키도록 변경해주고 size를 1증가시킨다.
        *
        * 마지막으로 head.next 즉 , 구(이전) head가 null이라면 새로 들어온 노드가 결국 첫 노드였다는 말로 head와 tail이 가리키는 노드 모두 새노드를 가리키게 된다.
        * */

    }

    /*
     * 후방삽입
     *
     * 덱의 마지막 부분에 삽입하는 메서드다. 조금만 더 생각해본다면 offerFirst와 크게 다를 것은 없으니 한 번 그림을 보면 쉽게 구현 할 수 있을 것이다.
     * */

    @Override
    public boolean offer(E value) {
        return offerLast(value);
    }
    public boolean offerLast (E value){
        //데이터가 없을 경우 offerFirst()로 인자를 넘겨줌
        if (size ==0){
            return offerFirst(value);
        }

        Node<E> newNode = new Node<E>(value);
        
        tail.next = newNode; // tail이 가리키는 노드의 다음 노드를 새 노드를 가리키도록 연결
        newNode.prev = tail; // 새 노드가 가리키는 이전 노드 또한 tail이 가리키는 노드로 연결
        tail = newNode; // tail이 가리키는 노드를 새 노드로 가리키도록 변경
        size ++ ;

        return true;
    } // offer()와 offerLast()는 똑같은 메서드이기 때문에 offerLast를 구현해주고, offer()는 offerLast()를 호출해서 사용한다.

    /*
    * poll 메서드 또한 앞부분에서 offer와 마찬가지로 크게 두 가지 경우로 나뉜다.
    * poll 및 pollFirst  메서드는 맨 앞의 요소를 삭제하는 메서드로 리스트에서의 removeFirst()와 같은 역할이며 front +1 위치에 있는 요소를 삭제하면 된다.
    * 반대로 pollLast메서드는 맨 뒤의 요소를 삭제하는 메서드로 리스트에서의 removeLast()와 같은 역할이며 rear위치에 있는 요소를 삭제하면된다.
    * 다만 두가지 모두 중요한 점은 '삭제할 요소가 없는 경우'다.
    *
    * 자바의 Deque에 관한 API문서를 보면 알 수 있지만, 삭제하려는 요소가 없을 경우 Poll 계열의 경우 예외가 발생하는 것이 아닌
    * null을 반환한다. remove계열은 null이 아닌 NosuchElementException 예외를 던진다.
    * */
    /*
    * 전방삭제 : poll(), pollFirst(), remove(), removeFirst()
    * 일단 poll() 및 pollFirst() 메서드를 구현하고 이를 remove() 메서드에 응용하여 적용할 것이다.
    * */
    @Override
    public E poll() {
        return pollFirst();
    }
    public E pollFirst(){
        if(size==0){
            return null;
        }
        E element = head.data;// 반환하기 위한 데이터
        Node<E> nextNode = head.next; // head의 다음 노드

        //head가 가리키는 모든 데이터들 삭제
        head.data = null;
        head.next = null;

        //삭제하기전 데이터가 두 개 이상 있을 경우 (head와 tail이 같지 않을 경우)
        if (nextNode != null){
            nextNode.prev = null;
        }
        head = null;
        head = nextNode; //head가 가리키는 노드를 삭제한 노드의 다음 노드로 갱신.
        size--;
        /*
        * 삭제된 요소가 덱의 유일한 요소 였을 경우
        * 그 요소는 head 이자 tail이었으므로
        * 삭제되면서 tail도 가리킬 요소가 없기 때문에
        * size가 0일 경우 tail도 null로 변환
        * */
        if (size==0){
            tail = null;
        }
        return element;
    }

    public E remove(){
        return removeFirst();
    }

    public E removeFirst(){
        E element = poll();
        if (element == null){
            throw new NoSuchElementException();
        }
        return element;
    }

    /*
    * 후방삭제 : pollLast()
    * */
    public E pollLast(){
        if (size==0){
            return null;
        }
        E element = tail.data;// 반환하기 위한 데이터

        Node<E> prevNode = tail.prev;

        // tail이 가리키는 노드의 데이터와 링크 삭제
        tail.data = null;
        tail.prev = null;

        //삭제하기전 데이터가 두 개 이상 있을 경우(head와 tail이 같지 않을 경우)
        if (prevNode != null){
            prevNode.next = null;
        }

        tail = null;
        tail = prevNode;
        size--;
        /*
        * 삭제된 요소가 덱의 유일한 요소였을 경우
        * 그 요소는 head이자 tail이었으므로
        * 삭제되면서 head도 가리킬 요소가 없기 때문에
        * size가 0일 경우 head도 null로 변환
        * */
        if (size ==0){
            head = null;
        }
        return  element;
    }

    public E removeLast(){
        E element = pollLast();

        if(element == null){
            throw new NoSuchElementException();
        }
        return element;
    }

    /*
    * peek 계열 메소드 구현
    *
    * 보통 가장 앞 또는 뒤에 있는 데이터를 삭제하지 않고 확인만 하고 싶을때가 있다.
    * 그럴 때 쓰는 것이 peek() 계열의 메서드다.
    * 한마디로 poll() 메서드에서 삭제 과정만 없는 것이 peek() 이다.
    *
    * peek() 은 가장 앞에 있는 데이터를 반환하는 것이고, 이는 peekFirst() 메서드와 같다.
    * 또한 가장 뒤에 있는 데이터를 반환하는 것이 peekLasT()다.
    * 그래서 pollFirst(), pollLast()에서 삭제 과정만 빼고 그대로 반환하면 되기 때문에 설명할 것이 없다.
    *
    * 참고로 덱에 데이터가 없는 경우 null을 던진다.
    * 반대로 이와 유사한 element()메서드는 큐에 요소가 없을 경우 remove() 메서드와 마찬가지로 NoSuchElementException 예외를 던진다.
    * elementLast()와 elementFirst()로 나뉠 것으로 예상했겠지만, 자바에서는 getLast()와 getFirst()로 나뉜다.
    *
    *
    * */
    @Override
    public E peek() {
        return peekFirst();
    }
    public E peekFirst(){
        //요소가 없을 경우 null 반환
        if(size == 0 ){
            return null;
        }
        return head.data;
    }
    public E peekLast(){
        // 요소가 없을 경우 null
        if (size ==0 ){
            return null;
        }
        return tail.data;
    }

    public E element(){
        return getFirst();
    }
    public E getFirst(){
        E item = peek();
        //앞의 원소 null 이라면(size = 0) 예외를 던진다.
        if (item ==null){
            throw new NoSuchElementException();
        }
        return item;
        //element() 메서드와 getFirst()는 같은 메서드다. 또한 우리가 구현한 peek 메서드를 호출하여 맨 앞의 원소를 얻거,null이라면 예외를 던져준다.
    }
    public E getLast(){
        E item = peekLast();
        //앞의 원소 null 이라면 (size=0 ) 예외를 던진다.
        if(item == null){
            throw new NoSuchElementException();
        }
        return item;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public boolean contains (Object value){
        /*
        * head 데이터부터 x가 null 이 될 때까지 value랑 x의 데이터(x.data)랑
        * 같은지 비교하고 같을 경우 true를 반환한다.
        * */
        for (Node<E> x = head; x != null; x = x.next){
            if (value.equals(x.data)){
                return true;
            }
        }
        return false;
    }

    public void clear(){
        for (Node<E> x= head; x != null; ){
            Node<E> next = x.next;

            x.data = null;
            x.next = null;
            x.prev = null;
            x= next;
        }
        size =0;
        head = tail = null;
    }
}
