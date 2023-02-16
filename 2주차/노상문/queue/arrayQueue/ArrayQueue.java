package com.moon.java.collectionFramework.queue.arrayQueue;

import com.moon.java.collectionFramework.queue.QueueInterface;

import java.util.NoSuchElementException;

public class ArrayQueue<E> implements QueueInterface<E> {

    private static final int defaultCapacity = 64;//최소 (기본) 용적 크기

    private Object[] array;// 요소를 담을 배열
    private int size; // 요소 갯수

    private int front; // 시작 인덱스를 가리키는 변수(빈 공간임을 유의)
    private int rear; // 마지막 요소의 인덱스를 가리키는 변수
    /*
     * defaultCapacity : 배열이 생성될 때 최초 할당 크기 (용적) 이자 최소 할당 용적 변수로 기본값은 64로 설정해두었다.(capacity가 용적이라는 의미)
     * array : 요소들을 담을 배열
     * size : 배열에 담긴 요소(원소)의 개수 변수 (용적 크기가 아니다, 절대 헷갈리면 안됨)
     * front : 시작 위치(index)를 가리키는 변수 (빈 공간이다.)
     * rear : 마지막 요소의 위치 (index)를 가리키는 변수
     *
     * 그리고 defaultCapacity 변수는 상수로 쓸 것이기 때문에 static final 키워드를 붙인다.
     *
     * (size는 요소(원소)의 개수를 의미한다. 공간을 할당하는 것과는 다르다.
     * */

    //생성자1 (초기 용적을 할당을 안할 경우) ArrayQueue<Integer> q = bew ArrayQueue<>();
    public ArrayQueue() {
        this.array = new Object[this.defaultCapacity];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    //생성자2 (초기 용적을 할당을 할 경우) arrayQueue<Integer> q = new ArrayQueue<>(100);
    public ArrayQueue(int capacity) {
        this.array = new Object[capacity];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    /*
     * 동적할당을 위한 resize 메서드 구현
     *
     * 들어오는 데이터의 개수에 따라 '최적화'된 용적을 갖을 필요가 있다.
     * 만약 데이터는 적은데 용적이 크면 낭비이고, 반대로 용적은 적은데 데이터가 많으면 넘치는 데이터들은 보관할 수 없게 되는 상황을 마주할 수 있다.
     *
     * 그렇기 때문에 size(요소의 개수)가 용적(capacity)에 얼마만큼 차있는지를 확인하고, 적절한 크기에 맞게 배열의 용적을 변경해야 한다.
     * 또한 용적은 외부에서 마음대로 접근하면 데이터의 손상을 야기할 수 있기 때문에 private로 접근을 제한해주도록 하자.
     *
     * 용적이 변경되는 경우는 크게 용적을 증가시켜야 하는 경우와 용적을 줄여야 하는 경우, 이 두가지로 나뉜다.
     * 그러나 따로 용적을 증가해야 하는 경우와 줄여야 하는 경우를 굳이 나눌 필요가 없다.
     * */
    private void resize(int newCapacity) {
        int arrayCapacity = array.length; // 현재 용적크기
            //10                          20
        Object[] newArray = new Object[newCapacity]; // 용적을 변경한 배열

        /*
         * i = new array index
         * j = original array
         * index 요소 개수 (size)만큼 새 배열에 값 복사
         * */
        // front 7    rear  10                  new   orig
        //                                       1     8
        //                                       2     9
        //                                       3     10
        for (int i = 1, j = front + 1; i <= size; i++, j++) {
            //      1  = array[8]          8  / 10
            //      2  = array[9]          9  / 10
            //      3  = array[0]          10 / 10
            newArray[i] = array[j % arrayCapacity];
        }

        this.array = null;
        this.array = newArray; //새 배열을 기준 array의 배열로 덮어 씌움
        front = 0;
        rear = size;
        /*
        * 용적이 가득 찰 경우, rear의 다음 인덱스(rear +1)이 front랑 같다는 말과 동일하다.
        * 다만 rear+1에 %arrayCapacity 즉, 기존 배열의 길이를 나누는 이유가 front가 rear보다 작을 경우를 고려해야 하기 때문이다.
        * 예로 들어 rear가 7이고
        * */
    }

    /*
    * Queue의 offer는 항상 후방(맨 뒤)에 데이터를 추가해야 하므로 한종류 밖에 없다.
    * 리스트로 치면 add(E value) 와 같은 역할이다.
    * 다만 고려해야할 부분이라면 배열의 마지막 인덱스에 도달했을 경우 또는 배열이 꽉 차있을 경우다.
    *
    * - 가장 마지막 부분에 추가 - offer( E item)
    * */
    @Override
    public boolean offer(E value) {
        //  7       8
        if((rear+1)% array.length == front){
            resize(array.length * 2); //용적을 두 배 늘려준다.
        }

        rear = (rear +1) % array.length; // rear를 rear의 다음 위치로 갱신

        array[rear] = value;
        size++; //사이즈 1 증가
        

        return true;
    } // 동적 할당을 위해 용적이 가득 찼다면 array 의 용적을 늘려주어야 한다. 그렇기 때문에 앞서 구현한 resize()메서드를 호출하여 용적을 두 배 늘려준 뒤 데이터를 추가하도록 하자.

    /*
    * poll 메서드 구현
    * 리스트에서의 remove()와 같은 역할로 front +1 위치에 있는 요소를 삭제하면 된다.
    * 다만 중요한 점은 '삭제할 요소가 없는 경우'다.
    *
    * 자바의 Queue에 관한 API 문서를 보면 알겠지만, add() -offer(), remove()-poll(), element()-peek()이 각각 대응 된다.
    * add() 의 경우 만약 용적이 제한되어 있는데 용적보다 더 많은 요소를 추가할 경우 예외를 던지는데, 지금 구현하는 것에서는 최대 제한을 걸지 않아 넘어갔다.)
    *
    * 삭제의 경우 remove() 같은 경우 삭제 할 요소가 없으면 NoSuchElementException() 예외를 던진다.
    * 반대로 poll()의 경우는 삭제할 요소가 없다면 null을 반환한다는 차이점이 있다.
    * 일단 poll() 메서드를 구현하고 이를 remove () 메소드에 응용하여 적용해보도록 한다.
    * */
    @Override
    public E poll() {

        if (size ==0){ // 삭제할 요소가 없을 경우 null 반환
            return null;
        }
        front = (front+1)% array.length;//  front 를 한칸 옮긴다.
        /*
        * @SuppressWarnings("unchecked")  ?
        * @SuppressWarnings("unchecked")을 붙이지 않으면 type safe(타입 안정성)에 대해 경고를 보낸다.
        * 반환되는 것을 보면 E 타입으로 캐스팅을 하고 있고 그 대상이 되는 것은 Object[] 배열의 Object 데이터다.
        * 즉, Object -> E 탕비으로 변환을 하는 것인데 이 과정에서 변환할 수 없는 타입을 가능성이 있다는 것을 경고로
        * 메소드 옆에 경고표시가 뜨는데, 우리가 push 하여 받아들이는 데이터 타입은 유일하게 E타입만 존재한다.
        * 그렇기 때문에 형 안정성이 보장된다.  한마디로 ClassCastException이 뜨지 않으니 이 경고들을 무시하겠다는 것이
        * @SuppressWarnings("unchecked") 이다. 물론 남발하는것은 문제가 된다.
        * */
        @SuppressWarnings("unchecked")
        E item = (E) array[front]; // 반환할 데이터 임시 저장

        array[front] = null; // 해당 front의 데이터 삭제
        size--; // 사이즈 감소

        //용적이 최소 크기(64) 보다 크고 요소 개수가 1/4 미만일 경우
        if (array.length > defaultCapacity && size<(array.length)/4){
            //아무리 작아도 최소용적 미만으로 줄이지는 않도록 한다.
            resize((Math.max(defaultCapacity, array.length)/2));
        }
        return item;
    }
    public  E remove() {
        E item = poll();
        if (item == null){
            throw new NoSuchElementException();
        }
        return item;
    }

    /*
    * 가장 앞에 있는 데이터 ((front +1 )% array.length) 를 삭제하지 않고 확인만 하고 싶을 때가 있다.
    * 그럴 때 쓰는 것이 peek() 메서드다. 한 마디로 poll() 메서드에서 삭제 과정만 없는 것이 peek()이다.
    *
    * 그래서 삭제과정만 빼고 그대로 반환하면 되기 때문에 설명할 것이 없다.
    *
    * 그리고 참고로 큐에 데이터가 없는 경우는 null을 던진다.
    * 반대로 이와 유사한 element()메서드는 큐에 요소가 없을 경우 remove()메소드와 마찬가지로 NoSuchElementException 예외를 던진다.
    * 위처럼 두가지 모두 구현한다.
    * */
    @Override
    public E peek() {
        if (size==0){
            return null;
        }

        @SuppressWarnings("unchecked")
        E item = (E) array[(front +1)% array.length];

        return item;
    }
    /*
    * 마찬가지로 E 타입 원소를 반환해야하는지라 Object 타입을 E 타입으로 캐스팅을 해주면서 경고창이 뜬다.
    * 하지만 poll()메서드에서 설명했듯이 마지막 원소 또한 E type 외에 들어오는 것이 없기 때문에 형 안정성이 확보 되므로 경고 표시를 무시하기 위해
    * @SuppressWarnings("unchecked")를 붙인다.
    * */

    public E element(){

        E item = peek();

        if(item == null){
            throw new NoSuchElementException();
        }
        return item;
    }
/*
    자바에서 Queue라고 한다면
    `ArrayQueue<Integer> q = new LinkedList<>();`
    물론 대게는 LinkedList로 구현한 큐가 쓰이지만, 상황에 따라 ArrayDeque나 PriorityQueue처럼 내부적으로 배열을 사용하여
    구현하고 사용하고 있는 큐의 자료구조도 있기 때문에 이번에는 배열을 사용해서도 구현하고자 한다.
    기본적으로 배열을 사용하여 구현되는 자료구조 클래스는 내부에서 최상위 타입 배열인 Object[]배열을 사용하여 데이터들을 관리하고 있다.
    큐의 기본 구조는 선입 선출이다.
    흔히 줄 을 서서 대기하는 모습을 상상하면 된다.
    문제는 배열을 이용하여 구현 할 경우 문제가 있다. 예로 다음과 같이 배열에 원소들을 추가해놓았다고 가정해보자.
    |a|b|c| | | | | |
    |0|1|2|3|4|5|6|7|

    그리고 큐는 선입선출이므로 큐 원소를 삭제(poll)할 경우 가장 앞에 있는원소, 즉 inde 0 의 원소가 삭제된다.

    poll()
    | |b|c| | | | | | | |....|
    |0|1|2|3|4|5|6|7|8|9|....|

    그리고 위에서 다시 원소를 추가(offer)하면 맨 뒤의 원소 바로 뒷자리, 즉 index 3의 자리에 원소가 추가 된다.
    offer(d)
    | |b|c|d| | | | | | |...|
    |0|1|2|3|4|5|6|7|8|9|...|

    이런식으로 큐의 삽입 삭제가 반복되면 결국에 원소들이 뒤로 치우치게 되는 경우가 발생한다.
    | | | | | | | | | | |...|m | o| p| q|
    |0|1|2|3|4|5|6|7|8|9|...|n1|n2|n3|n4|
     이렇게 선형적으로 접근하면 되면 쏠림현상이 발생하는데,
     그렇다고 매 번 삭제 연산 때마다 삭제된 원소 뒤의 모든 원소들을 한자리씩 앞으로 떙겨오는 것은 매우 비효율적이고,
     그렇다고 배열 크기를 늘리자니 배열이 꽉찬 상태인 것도 아니라 빈 공간을 낭비하게 된다.

     이를 해결하기 위한 아주 간단한 방법은 앞의 빈 자리에 다시 채워 넣는것이다.
     이해가 어렵다면 배열을 원형이라고 생각했을때, 마지막 원소의 위치를 가리키는 변수 'front' 와 'rear'가 필요하다.
     만약 원소를 추가한다면 ? 0번째 인덱스에 원소를 추가하는 것이다.

     그리고 반대로 원소를 삭제한다면? front +1번째 index 4에 위치한 원소가 삭제되는 것이다.

     쉽게 생각하면 rear를 따라 원소가 추가되고, front를 따라 원소가 삭제된다고 보면 된다.
     위와 같은 방식으로 원소들을 채워나가면 효율적으로 배열을 관리할 수 있다.
     만약에 "더 이상 빈 자리가 없을 경우"에만 배열의 크기를 늘려주면 된다.

     여기서 여러분들이 한 가지 의아할 점이 있을 것이다. 왜 front 변수 다음부터 원소가 추가 되는 것인가?
     이는 연속하여 삭제하게 될 경우, front와 rear가 엇갈리는 상황이 생긴다.

     이는 배열이 가득차있는지비 비어 있는지 알수가 없기 때문에 문제가 된다.

     반대로 front +1 했을때 , 배열이 비어있다고 하면 front와 rear가 같은 위치에 있어 front== rear가 된다.
     이 상태에서 다시 두변수 모두 0으로 초기화 해주면 더욱 깔끔해진다.

     이러한 구조를 보통 Circular Queue, 우리 나라 말로는 원형큐 라고 한다.

*/

    /*
    * size는 매우 간단하게 현재 큐에 있는 요소의 개수를 알려준다.
    * */
    public int size (){
        return size;
    }
    /*
    * isEmpty() 메소드는 현재 큐가 비어 있는지를 확인 할 때 쓰인다.
    * 요소의 개수가 0개라면 비어있다는 뜻이므로, 비어있다면 true를 비어있다면 false를 반환한다.
    * */
    public boolean isEmpty(){
        return size == 0;
    }
    
    /*
    * contains() 메서드
    *
    * contains() 는 현재 찾고자하는 요소가 큐에 들어가있는지를 알려주는 메서드다.
    * */
    public boolean contains (Object value){

        int start = (front + 1) % array.length;

        /*
        * i : 요소 개수만큼만 반복한다.
        * index : 원소 위치, 매 회 (index + 1) %array.length; 의 위치로 갱신
        *
        * 0번째 인덱스부터 용적크기가지 모두 검사할 수도 있겠지만,
        * 기본적으로 용적 크기에 비해 요소의 개수가 훨씬 적은 경우가 많다.
        * 굳이 모든 공간을 찾기보단, 요소의 개수만큼만 정확히 범위를 짚어서 반복해주는 것이 더 효율적이다.
        *
        * 만약 해당 범위 외의 공간에 요소가 있으면, 구현이 잘못된 것이라고 보자.
        * 잘못된 참조가 있다는 것이다.
        * */
        for (int i = 0, index = start; i < size; i++, index = (index+1)% array.length) {
            if (array[index].equals(value)){
                return true;
            }
        }
        return false;
    }

    /*
    * clear() 메서드는 단어 그대로 Queue의 모든 요소를 비워버린다.
    *
    * 혹시 모를 경우까지 대비해 모든 공간을 명시적으로 null 처리를 해준다.
    * 그리고 빈 공간은 초기 상태와 마찬가지로 front와 rear와 size를 모두 0으로 초기화 해준다.
    *
    * 이렇게 size, isEmpty, contains, clear 메서드들을 만들어보았다.
    *
    * */
    public void clear() {

        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }

        front = rear = size = 0;
    }

    public int length(){
        return array.length;
    }
    public E get(int index){

        return (E) array[index];
    }
}
