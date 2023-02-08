package com.moon.java.collectionFramework.queue.deque.arrayDeque;

import com.moon.java.collectionFramework.queue.QueueInterface;

import java.util.NoSuchElementException;

public class ArrayDeque<E> implements QueueInterface<E> {

    private static final int defaultCapacity = 64; // 최소(기본)용적 크기
    private Object[] array; // 요소를 담을 배열
    private int size; //요소 개수

    private int front; // 시작 인덱스를 가리키는 변수(빈 공간임을 유의)
    private int rear; // 마지막 요소의 인덱스를 가리키는 변수

    /*
     * defaultCapacity : 배열이 생성 될 때의 최초 할당 크기 (용적) 이자 최소 할당 용적 변수로 기본값은 64로 설정한다.
     * array : 요소들을 담을 배열
     * size : 배열에 담긴 요소(원소)의 개수 변수 (용적 크기가 아니다.)
     * front : 시작 위치(index)를 가리키는 변수 (빈공간)
     * rear : 마지막 요소의 위치(index)가리키는 변수
     *  front와 rear는 "Queue" 관점을 기준으로 쓴다.
     *  그리고 defaultCapacity 변수는 상수로 쓸 것이기 떄문에 static final 키워드를 붙인다.
     * */
    //생성자1 (초기 용적 할당을 안할경우)
    public ArrayDeque() {
        this.array = new Object[defaultCapacity];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    //생성자2 (초기 용적 할당을 할 경우)
    //array의 공간 할당을 입력 된 수만큼(예제에서는 array= new Object[100]) 배열을 만든다. 그리고 마찬가지로 size, front, rear들을 0으로 초기화 시킨다.
    public ArrayDeque(int capacity) {
        this.array = new Object[capacity];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    /*
     * resize 메서드
     * 동적 할당을 위한 메서드이다.
     * 들어오는 데이터에 개수에 따라 '최적화'된 용적을 갖을 필요가 있다.
     * 만약 데이터는 적은데 용적이 크면 메모리가 낭비되고, 반대로 용적은 적은데 데이터가 많으면 넘치는 데이터들은 보관할 수 없게 되는 상황을 마주칠 수 있다.
     * 그렇기 때문에 size(요소의 개수)가 용적(capacity)에 얼마만큼 차있는지를 확인하고, 적절한 크기에 맞게 배열의 용적을 변경해야 한다.
     * 또한 용적은 외부에서 마음대로 접근하면 데이터의 손상을 야기할 수 있기 때문에 private로 접근을 제한해주도록 하자.
     *
     * 용적이 변경되는 경우는 크게 용적을 증가시켜야 하는 경우와 용적을 줄여야 하는 경우, 이 두가지로 나뉜다.
     * 그러나 따로 용적을 증가해야 하는 경우와 줄여야 하는 경우를 굳이 나눌 필요가 없다.
     *

     * 용적이 가득 찰 경우다. 이 의미는 rear의 다음 인덱스(rear +1)이 front랑 같다. 는 말과 동일하다.
     * 다만 (rear+1) 에 % arrayCapacity 즉, 기존 배열의 길이를 나누는 이유는 front가 rear보다 작을 경우를 고려해야 하기 때문이다.
     * 예로 rear가 7이고, front가 0이라면 7+1 = 8 이므로 , 0과 같지 않다.
     * 이러한 이유로 길이(예시에서는 8) 로 나눠준 나머지 (7+1)%8 = 0 을 해야 정확한 조건 때 용적을 증가 시킬 수 있다.
     *
     * 용적을 줄여야하는 경우 : (size< (arrayCapacity /4))
     *
     * 데이터가 1/4미만으로 떨어징 경우에는 필요 없는 공간이 너무 많아지게 되어서 절반정도로만 줄인다.
     *
     * 코드를 보면 알겠지만 새로운 용적의 배열에 값을 복사하는 과정 자체는 똑같기 때문에 따로 두 경우의 조건문은 달지않고 파라미터 (newCapacity)로 받은 새 용적을 이용하여 용적의 사이즈를 변경한다.
     */
    private void reszie(int newCapacity) {
        int arrayCapacity = array.length;// 현재 용적 크기

        Object[] newArray = new Object[newCapacity]; // 용적을 변경한 배열
        /*
         * i = new array index
         * j = original array
         * index 요소 개수(size)만큼 새 배열에 값 복사
         * */
        for (int i = 1, j = front + 1; i <= size; i++, j++) {
            newArray[i] = array[j % arrayCapacity];
        }
        this.array = null;
        this.array = newArray; // 새 배열에 기존 array의 배열로 덮어씌움

        front = 0;
        rear = size;
    }

    /*
     * offer게열 메서드
     *
     * Deque의 삽입은 크게 두가지로 나뉜다.
     * 기본 메서드 offer() 및 offerLast()는 후방(맨 뒤)에 데이터를 추가한다.
     * 리스트로 치면 add(E value)및 addLst(E value)와 같은 역할이다.
     * 두 번재는 전방(맨 앞)에 데이터를 추가하는 경우다. 이는 offerFirst()가 담당하며 리스트로 치면 addFirst()와 같은 역할이다.
     *
     * 다만 두 메소드 모두 고려해야할 부분이라면 배열이 꽉 차있을 경우다.
     * -가장 마지막 부분에 추가 : offer(E item), offerLast(E item)
     * -가장 앞의 부분에 추가 : offerFirst(E item)
     * */
    /*
     * 기본 삽입 : offer(E item), offerLast(E item)
     * 가장 기본적인 삽입 방법이다. 만약 Array Queue를 구현해보았다면 원리는 다를게 없다.
     * */
    @Override
    public boolean offer(E item) {
        return offerLast(item);
    }

    public boolean offerLast(E item) {
        //용적이 가득 찼을 경우
        if ((rear + 1) % array.length == front) {
            reszie(array.length * 2);// 용적을 두 배 늘려준다.
        }
        rear = (rear + 1) % array.length; // rear를 rear의 다음 위치로 갱신

        array[rear] = item;
        size++;// 사이즈 1증가

        return true;
    }

    /*
     * 전방 삽입 : offerFirst(E tiem)
     * 덱의 앞 부분에 삽입하는 메서드다.
     * 주의해야할 점은 offerLast의 경우 rear 값을 새로 갱신하는 경우 1증가 시킨 뒤
     * 용적 크기로 나머지 값을 구했다. 즉,rear = (rear+1)%array.length이었다.
     *
     * 하지만 이번에는 front를 오히려 감소 시켜야 한다. 이게 왜 문제냐면 front =0일 때 앞부분에 원소를 추가하면 front값을 새로운 값으로 갱신시키게 된다.
     * 이 때 front 에 1을 빼버리면 -1이 된다.
     * 만약 front = (front-1) % array.length 수식에 front =0 이라면 어떻게 될까, 예로 용적 (array.lenth)이 8이라고해보자.
     * 수식에 대입한다면?
     * (0-1)% 8
     * = -1% 8
     * = ???
     * -1을 8로 나눈 나머지 값은 얼마일까 ? 자바에서는 다음과 같은 값을 내보내게 된다.
     * -1 % 8 = -1
     * 즉, 음수가 나와버리게 되어 배열 인덱스를 잘못 참조해버리는 일이 발생하게 된다. 그러면 어떻게 해결하나?
     *
     * 매우 간단하다. 나누려는 값만큼 먼저 더해주면 된다. front -1 에서 용적의 크기를 더한 뒤 그 수를 용적의 크기로 나눈 나머지를 구해주면 된다.
     * 쉽게 생각해서 다음과 같은 수식을 써야한다.
     * front = (front -1 +array.length)% array.length
     *
     * front 위치 자체가 빈공 간이기 때문에 먼저 채운 뒤 front 값을 갱신해주어야 한다.
     * 이 부분만 조심해주면 된다.
     * */
    public boolean offerFirst(E item) {
        //용적이 가득 찼을 경우
        if ((front - 1 + array.length) % array.length == rear) {
            reszie(array.length * 2);// 용적을 두 배 늘려준다.
        }
        array[front] = item; // front 위치는 빈 공간이기 때문에 추가 해준 뒤 front값을 갱신한다.
        front = (front - 1+ array.length) % array.length;// front를 다음 위치로 갱신
        size++;
        return true;
    }

    /*
     * poll 계열 메서드 구현
     * poll 메서드 또한 앞부분에서 offer와 마찬가지로 크게 두 가지 경우로 나뉜다.
     * poll 및 pollFirst 메서드는 만 앞의 요소를 삭제하는 메서드로 리스트에서의 removerFirst()와 같은 역할이며
     * front +1 위치에 있는 요소를 삭제하면 된다.
     * 반대로 pollLast 메서드는 맨 뒤의 요소를 삭제하는 메서드로 리스트에서의 removeLast()와 같은 역할이며 rear위치에 있는 요소를 삭제하면된다.
     *
     * 다만 두가지 모두 중요한 점은 '삭제할 요소가 없는 경우다.'
     * 자바의 Deque에 관한 API 문서를 보면 알겠지만 삭제하려는ㄴ 요소가 없을 경우 poll계열의 경우 예외가 발생하는 것이 아닌 null을 반환한다.
     * 하지만 remove계열은 null이 아닌 NosuchElementException 예외를 던진다..
     * (add()의 경우 앞서 말했듯이 만약 용적이 제한되어있는데 용적보다 더 많은 요소를 추가할 경우 예외를 던지는데, 지금 구현하는 것에서는 최대 제한을 걸지않넘어갔다.
     * */
    @Override
    public E poll() {
        return pollFirst();
    }

    public E pollFirst() {
        if (size == 0) {// 삭제할 요소가 없을 경우 null 반환.
            return null;
        }
        front = (front + 1) % array.length;// front를 한칸 옮긴다.
        /*
         * @SuppressWarnings("unchecked")
         * type Safe(타입 안정성)에 대해 경고를 보낸다. 반환되는 것을 보면 E 타입으로 캐스팅을 하고 있고,
         * 그 대상이 되는 것은 Object[] 배열의 Object데이터다. 즉, Object -> E 타입으로 변환을 하는 것인데
         * 이 과정에서 변환할 수 없는 타입을 가능성이 있다는 경고로 메서드 옆에 경고 표시가 뜨는데, 우리가 push하여 받아들이는 데이터 타입은
         * 유일하게 E타입만 존재한다.
         * 그렇기 때문에 형 안정성이 보장된다. 한마디로 ClassCastException이 뜨지 않으니 이 경고들을 무시하겠다는 것이
         * @SuppressWarnings("unchecked")이다.  물론 절대 남발하면 안된다.
         * remove()는 removeFirst()를 호출하고, removeFirst()는 pollFirst()을 호출하여 null일 경우에만 예외를 던지게 되기 때문에 별다를게 없다.
         * */
        @SuppressWarnings("unchecked")
        E item = (E) array[front]; // 반환할 데이터 임시저장
        array[front] = null; // 해당 front의 데이터 삭제
        size--;//사이즈 감소

        //용적이 최소 크기(64) 보다 크고 요소 개수가 1/4 미만일 경우
        if (array.length > defaultCapacity && size < (array.length / 4)) {
            //아무리 적어도 최소 용적 미만으로 줄어지지 않도록 한다.
            reszie(Math.max(defaultCapacity, array.length / 2));
        }
        return item;

    }

    public E remove() {
        return removeFirst(); // 예외는 removeFirst()에서 던져준다.
    }

    public E removeFirst() {
        E item = pollFirst(); // pollFirst()을 호출한다.

        if (item == null) {
            throw new NoSuchElementException();
        }
        return item;
    }

    /*
     * pollLast() 후방삭제
     * pollFirst와는 달리 마지막에 있는 요소를 삭제하는 메서드다. 리스트에서는 removeLast()와 같은 역할이다.
     * 조금 응용하여 생각해보면 offer()와 pollLast()를 조합하면 스택으로도 사용할 수 있는 것이다.
     * 여기서 중요한 점은 offerFirst 때 했던것 처럼rear또한 0일 경우를 대비하여 -1음수가 나오지 않도록 위치를 갱신할대 용적의 크기만큼 더해준뒤 나누어야 한다.
     * 즉, rear = (rear -1 + array.length) % array.length 를 해주어야 음수가 나오지 않는다.
     *
     * */
    public E pollLast() {
        if (size == 0) {//삭제할 요소가 없을 경우 null반환
            return null;
        }

        @SuppressWarnings("unchecked")
        E item = (E) array[rear]; // 반환할 데이터 임시 저장
        array[rear] = null; // 해당 rar의 데이터 삭제

        rear = (rear - 1 + array.length) % array.length;// rear를 한칸 옮긴다.
        size--;  //사이즈 감소

        //용적이 최소 크기 (64) 보다 크고 요소 개수가 1/4 미만일 경우
        if (array.length > defaultCapacity && size < (array.length / 4)) {
            //아무리 작아도 최소 용적 미만으로 줄이지는 않도록 한다.
            reszie(Math.max(defaultCapacity, array.length / 2));
        }
        return item;

    }

    public E removeLast() {
        E item = pollLast();

        if (item == null) {
            throw new NoSuchElementException();
        }
        return item;
    }

    /*
     * peek 계열 메서드 구현
     *
     * 보통 가장 앞 또는 뒤에 있는 데이터를 삭제하지 않고 확인만 하고 싶을때가 있다. 그럴 때 쓰는 것이 peek() 계열의 메서드다.
     * 한마디로 poll() 메서드에서 삭제 과정만 없는 것이 peek()이다.
     *
     * peek()은 가장 앞에 있는 데이터를 반환하는 것이고, 이는 peekFirst()메서드와 같다.
     * 또한 가장 뒤에 있는 데이터를 반환하는 것이 peekLast()이다.
     * 그래서 pollFirst(), pollLast()에서 삭제 과정만 빼고 그대로 반환하면 된다.
     * */
    @Override
    public E peek() {
        return peekFirst();
    }
    /*
     * 마찬가지로 E타입 원소를 반환해야하는 지라 Object 타입을 E타입으로 캐스팅을 해주면서 경고창이 뜬다.
     * 하지만 poll()메서드에서 설명했듯이 마지막 원소 또한 E Type외에 들어오는 것이 없기 때문에 형 안정성이 확보 되므로 경고 표시를 무시하기 위해
     * @SuppressWarnings("unchecked")를 붙여 준다.
     * */

    public E peekFirst() {
        //요소가 없을 경우 null 반환
        if (size == 0) {
            return null;
        }
        @SuppressWarnings("unchecked")
        E item = (E) array[(front + 1) % array.length];
        return item;
    }

    public E peekLast() {
        //요소가 없을 경우 null 반환
        if (size == 0) {
            return null;
        }
        @SuppressWarnings("unchecked")
        E item = (E) array[(rear)];
        return item;
    }

    public E element(){
        return getFirst();
    }
    public E getFirst(){
        E item = peek();

        //앞의 원소가 null 이라면 (size = 0) 예외를 던진다.
        if(item ==null){
            throw new NoSuchElementException();
        }
        return item;
    }
    public E getLast(){
        E item = peekLast();

        //앞의 원소가 null 이라면 (size = 0)예외를 던진다.
        if(item ==null){
            throw new NoSuchElementException();
        }
        return item;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size ==0;
    }
    /*
    * 이 때 0번째 인덱스로부터 용적크기 (array.length)까지 모두 검사할 수도 있겠지만 , 기본적으로 용적크기에 비해 요소의 개수가 훨씬 적은 경우가 많다.
    *
    * 굳이 모든 공간을 찾기보단, 요소의 개수만큼만 정확히 범위를 짚어서 반복해주는것이 좋다.
    * 만약 해당 범위 외에 공간에 요소가 있다면 구현이 불량.
    * */
    public boolean contains(Object value){
        int start = (front +1 )% array.length;

        /*
        * i : 요소 개수만큼만 반복한다.
        * index : 원소 위치로, 매 회 (index+1) % array.length;의 위치로 갱신
        * */
        for (int i = 0, index = start; i < size; i++,index =(index +1)%array.length) {
            if(array[index].equals(value)){
                return true;
            }
        }
        return false;
    }
    public void clear(){
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
        front = rear = size = 0;
    }

}
