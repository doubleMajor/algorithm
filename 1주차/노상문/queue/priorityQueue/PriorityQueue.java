package com.moon.java.collectionFramework.queue.priorityQueue;

import com.moon.java.collectionFramework.queue.QueueInterface;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class PriorityQueue<E> implements QueueInterface<E> {
    private final Comparator<? super E> comparator;

    private static final int defaultCapacity = 10; //  최소 (기본 ) 용적 크기

    private int size; // 요소 개수
    private Object[] array; //요소를 담을 배열

    /*
     * comparator : 객체를 정렬하고자 할 때, 혹은 임의의 순서로 정렬하고 싶을 때 Comparator를 파라미터로 받아 설정할 수 있도록 한 변수다.
     * 즉 우선순위를 결정해주는 변수라고 보면 된다.
     * defaultCapacity : 배열의 기본 및 최소 용적이다. 한마디로 요소를 담을 배열의 크기를 의미한다.  배열을 동적으로 관리 할 때 최소 크기가 10미만으로 내려가지 않기 위한 변수.
     * 그리고 요소의 개수랑은 다른 의미이므로 혼동x.
     * size : 배열에 담긴 요소의 개수 변수
     * array : 요소를 담을 배열이다.
     * */

    /*
     * 생성자는 크게 4개로 구분했다.
     * 먼저 데이터(요소)의 개수를 예상할 수 있어 배열의 크기(용적)을 최적으로 하고 싶을 때
     * 초기에 생성할 배열의 크기를 설정 해줄 수 있도록 만든 방법과 사용자가 정렬 방법을 따로 넘겨주고자할 때 쓸 수 있도록 Comparator를 받는 방법을 조합하여 4가지로 나뉜다.
     * */

    //생성자 Type 1 (초기 공간 할당 X)
    public PriorityQueue() {
        this(null);
    }

    public PriorityQueue(Comparator<? super E> comparator) {
        this.array = new Object[defaultCapacity];
        this.size = 0;
        this.comparator = comparator;
    }

    //생성자 Type 2(초기 공간 할당 O)
    public PriorityQueue(int capacity) {
        this(capacity, null);
    }

    public PriorityQueue(int capacity, Comparator<? super E> comparator) {
        this.array = new Object[capacity];
        this.size = 0;
        this.comparator = comparator;
    }

    /*
     * 힙 설명에서 힙을 배열로 구현할 때의 성질이다.
     * [성질 ]
     * 1. 왼쪽 자식 노드 인덱스 = 부모 노드 인덱스 x 2
     * 2. 오릉쪽 자식 노드 인덱스 = 부모 노드 인덱스 x 2 +1
     * 3. 부모 노드 인덱스 = 자식 노드 인덱스 /2
     * 이 세가지 성질을 이용하기 위해 각가 부모 또는 자식의 인덱스를 반환해주는 메서드를 생성해주었다.
     * */
    //받은 인덱스의 부모 노드 인덱스를 변환
    private int getParent(int index) {
        return index / 2;
    }

    //받은 인덱스의 왼쪽 자식 노드 인덱스를 반환
    private int getLeftChild(int index) {
        return index * 2;
    }

    //받은 인덱스의 오른쪽 자식 노드 인덱스를 반환
    private int getRightChild(int index) {
        return index * 2 + 1;
    }

    /*
     * resize 메서드 구현
     * 모든 자료구조는 기본적으로 동적이어야 한다.
     * 만약 배열에 요소들이 모두 차면 배열의 크기를 늘려야하고 , 만약 요소가 배열의 용적에 비해 현저히 적으면 낭비되는 메모리가 크므로 적절히 줄여주어야한다.
     * 그럴 때 배열의 크기를 재조정 하기 위해 쓰는 메서드다.
     * */
    private void resize(int newCapacity) {
        //새로 만들 배열
        Object[] newArray = new Object[newCapacity];

        // 새 배열에 기존에 있떤 배열의 요소들을 모두 복사해준다.
        for (int i = 1; i <= size; i++) {
            newArray[i] = array[i];
        }
        /*
         * 현재 배열은 GC 처리를 위해 null로 명확하게 처리한 뒤
         * 새 배열을 array에 연결해준다.
         * */
        this.array = null;
        this.array = newArray;
        // 새 배열을 생성하고 기존에 있던 배열의 요쇼들을 복사해준 뒤, 새 배열을 가리키도록 새로 연결해주면 된다.
    }
    /*
     * offer 메서드 구현
     * 이제 본격적으로 priorityQueue에 데이터를 추가할 수 있도록 해보자.
     * 힙을 다룰 때는 add 라고 했지만, 우선 순위 큐는 Queue인터페이스를 구현해야하 하며, 그 안에 offer라는 메서드가 삽입 연산에 해당되니, offer로 명칭을 대체하겠다.
     *
     * 우선 순위 큐의 삽입은 두 가지로 나뉜다.
     *
     * 1. 사용자가 Comparator를 사용하여 정렬 방법을 PriorityQueue 생성단계에서 넘겨받은 경우 (comparator가 아닌 null이 아닌 경우)
     * 2. 클래스 내에 정렬 방식을 Comparable로 구현했거나 기본 정렬 방식을 따르는 경우 (comparator가 null인 경우)
     *
     * 이 두가지로 나누어 봐야한다.
     *
     * 기본적으로 Heap에서 원소가 추가되는 과정은 다음과 같다.
     * 배열의 마지막 부분에 원소를 넣고 부모노드를 찾아가면서 부모 노드가 삽입 노드보다 작을 때까지 요소를 교환해가면서 올라간다.
     * 위와 같은 과정을 흔히 올라가면서 선별한다고 하여 sift-up(상향선별)이라고도 불린다.
     * 즉, 값을 추가할 때는 size + 1 위치에 새로운 값을 추가하고 상향 선별 과정을 거쳐 '재배치' 해준다고 생각하면 된다.
     * 이 때, 재배치 되는 노드를 위 분홍색 노드, 즉 타겟노드라고 한다.
     * */

    @Override
    public boolean offer(E value) {
        //배열 용적이 꽉 차있을 경우 용적을 두배로 늘려준다.
        if (size + 1 == array.length) {
            resize(array.length * 2);
        }
        siftUp(size + 1, value); // 가장 마지막에 추가되는 위치와 넣을 값(타겟)을 넘겨줌
        size++;// 정상적으로 재배치가 끝나면 사이즈 (요소 개수) 증가
        return true;
    }

    /*
     * 상향 선별
     * @Param index 추가할 노드의 인덱스
     * @Param target // 재배치 할 노드
     * */
    private void siftUp(int index, E target) {
        /*
         * comparator가 존재 한다면 comparator를 넘겨주고,
         * 아닐 경우 comparable로 비교하도록 분리.
         * */
        if (comparator != null) {
            siftUpComparator(index, target, comparator);
        } else {
            siftUpComparable(index, target);
        }
    }

    // Comparator를 이용한 sift-up
    @SuppressWarnings("unchecked")
    private void siftUpComparator(int index, E target, Comparator<? super E> comparator) {
        // root 노드 보다 클 때 까지만 탐색한다.
        while (index > 1) {
            int parent = getParent(index); //삽입 노드의 부모노드 인덱스 구하기
            Object parentVal = array[parent]; // 부모노드의 값

            //타겟 노드 우선순위 (값)이 부모노드보다 크면 반복문 종료
            if (comparator.compare(target, (E) parentVal) >= 0) {
                break;
            }
            /*
             * 부모노드가 타겟노드보다 우선순위가 크므로
             * 현재 삽입 될 위치에 부모노드 값으로 교체해주고
             * 타겟 노드의 위치를 부모노드의 위치로 변경해준다.
             * */
            array[index] = parentVal;
            index = parent;
        }
        // 최종적으로 삽입 될 위치에 타겟 노드 요소를 지정해준다.
        array[index] = target;
    }

    //삽입 할 객체의 Comparable 을 이용한 sift-up
    @SuppressWarnings("unchecked")
    private void siftUpComparable(int index, E target) {
        //타겟노드가 비교 될 수 있도록 한 변수를 만든다.
        Comparable<? super E> comparable = (Comparable<? super E>) target;

        //노드 재배치 과정은 siftUpComparator와 같다.
        while (index > 1) {
            int parent = getParent(index);
            Object parentVal = array[parent];

            if (comparable.compareTo((E) parentVal) >= 0) {
                break;
            }
            array[index] = parentVal;
            index = parent;
        }
        array[index] = comparable;
    }
    /*
     * 요소를 추가하기 전에 추가 할 공간이 있는지를 검사해야 한다. 만약 배열의 길이(용적)가 10이고,
     * 요소의 개수인 size가 9일 경우 배열의 마지막 인덱스까지 꽉 차있다는 의미다. (힙은 index 1 부터 채우므로 )
     *
     * 그렇기에 용적의 크기를 2배로 늘려준 뒤, siftUp 메서드를 호출한다.
     *
     * 그 다음 Comparator로 넘겨받은 것이 있는지, 없는지에 따라 Comparator가 잇을 경우 compare()를,
     * 없을 경우엔 compareTo()를 사용하여 요소를 비교해야 하므로 검사를 한 뒤 각각의 siftUp 메서드로 넘어가 재배치를 작업해준다.
     *
     * 위와 같이 추가해보다보면 알겠지만 결국 '마지막 삽입 되는 인덱스'로부터 부모노드를 비교하면서 올라기기 때문에 항상 완전 이진 트리를 만족하면서,
     * 부모노드는 자식노드보다 우선 순위가 높다는 것 또한 침해 받지 않는다.
     *
     * 그리고 만약 최대 힙을 구현하고 싶은 경우 compare 혹은 compareTo에서의 >= 0 비교 연산자를 <=로 바꿔주면 된다.
     * */

    /*
     * Poll 구현
     *
     * 그럼 삭제 및 반환은 어떻게 구현해야할까? 간단하게 offer와 정반대로 하면 된다.
     *
     * offer의 경우 맨 마지막 노드에 추가하고 부모노드와 비교하면서 자리를 찾아갔다.
     * 이를 거꾸로하면 삭제연산의 경우 root에 있는 노드를 삭제하고,
     * 마지막에 위치해있던 노드를 rootNode로 가져와 offer와는 반대로 자식 노드가 재배치하려는 노드보다 크거나 자식 노드가 없을 때 까지 자신의 위치를 찾아가면 된다.
     *
     * 마지막 노드를 root노드로 가져온 뒤, 자식 노드와 비교함녀서 자리를 찾아가면 된다.
     * 비교대상이 되는 노드는 타겟이 되는 것이다. 이 타겟을 다른 노드와 비교하면서 타겟 노드가 배치 될 자리를 찾아가야 한다.
     * 중요한 점은 왼쪽 노드와 오른쪽 노드 중 ' 작은 값을 가진 노드'랑 재배치를 할 노드와 비교해야한다.
     *
     * 그래야 최소 힙을 만족 시킬 수 있다.
     * 만약 반대로 된다면 첫 비교 교환 단계에서 35가 root노드에 배치 되어버리는데,
     * 이는 왼쪽 자식 노드인 10보다 큰 값을 갖게 되면서 최소힙을 만족하지 못한다.
     * 이렇게 아래로 내려가면서 재배치 하는 과정을 sift-down (하향선별) 이라고도 한다.
     *
     * 이렇게 삭제된 요소는 우선순위가 높은 순서대로 반환이 되는 것이다.
     *
     * 그리고 삽입과정과 마찬가지로 Comparator를 쓰느냐, Comparable을 쓰느냐를 나누면서 만들겠다.
     *
     * 참고로 여기서 추가 되는 것은 Queue API를 보면 알 수 있겠지만, remove()의 경우에는 뽑으려는 root 노드가 null일 경우에 예외 (NoSuchElementException)을 던지지만
     * poll()의 경우는 null 반환한다.
     * 그러니 기존에 Heap 구현에서 썻던 remove() 메서드를 그대로 갖고오면서 poll(I)이 remove()를 호출하도록 한다.
     * */

    @Override
    public E poll() {
        //poll은 뽑으려는 요소(root)가 null일 경우 null을 반환한다.
        if (array[1] == null) {
            return null;
        }
        // 그 외의 경우 remove()에서 반환되는 요소를 반환한다.
        return remove();
    }

    @SuppressWarnings("unchecked")
    public E remove() {
        if (array[1] == null) {// 뽑으려는 요소(root)가 null일 경우 예외를 던지도록 한다.
            throw new NoSuchElementException();
        }

        E result = (E) array[1]; // 삭제된 요소를 반환하기 위한 임시 변수
        E target = (E) array[size]; // 타겟이 될 요소
        array[size] = null; // 타겟 노드 (index)를 비운다.
        size--; //사이즈를 1 감소
        siftDown(1, target);

        return result;
    }

    /*
     * 하향 선별
     * @param index 삭제할 노드의 인덱스
     * @param target 재배치 할 노드
     * */
    private void siftDown(int index, E target) {
        if (comparator != null) {
            siftDownComparator(index, target, comparator);
        } else {
            siftDownComparable(index, target);
        }
    }

    //Comparator 를 이용한 sift-down
    @SuppressWarnings("unchecked")
    private void siftDownComparator(int index, E target, Comparator<? super E> comparator) {
        array[index] = null; // 삭제할 인덱스의 노드를 삭제
        int parent = index; // 삭제 노드부터 시작 할 부모 노드 인덱스를 가리키는 변수
        int child; // 교환 될 자식 인덱스를 가리키는 변수

        //왼족 자식 노드의 인덱스가 요소의 개수보다 작을 때까지 반복
        while ((child = getLeftChild(parent)) <= size) {
            int right = getRightChild(parent);//오른쪽 자식 인덱스
            Object childVal = array[child];
            /*
             * 오른쪽 자식 인덱스가 size를 넘지 않으면서
             * 왼쪽 자식이 오른쪽 자식보다 큰 경우
             * 재배치할 노드는 작은 자식과 비교해야 하므로 child와 childVal을 오른쪽 자식으로 바꾸어 한다.
             * */
            if (right <= size && comparator.compare((E) childVal, (E) array[right]) > 0) {
                child = right;
                childVal = array[child];
            }
            //재배치 할 노드가 자식 노드보다 작을 경우 반복문 종료
            if (comparator.compare(target, (E) childVal) <= 0) {
                break;
            }
            /*
             * 현재 부모 인덱스에 자식 노드 값을 대체해주고
             * 부모  인덱스를 자식 인덱스로 교체
             * */
            array[parent] = childVal;
            parent = child;
        }
        array[parent] = target;

        //최종적으로 재배치 되는 위치에 타겟이 된 값을 전체  용적의 1/4 미만일 경우
        // 용적으 반으로 줄임 (단, 최소 용적 보단 카야함)
        if (array.length > defaultCapacity && size < array.length / 4) {
            resize(Math.max(defaultCapacity, array.length / 2));
        }
    }

    @SuppressWarnings("unchecked")
    private void siftDownComparable(int index, E target) {

        Comparable<? super E> comparable = (Comparable<? super E>) target;

        array[index] = null;

        int parent = index;
        int child;

        while ((child = (parent << 1)) <= size) {

            int right = child + 1;

            Object c = array[child];

            if (right <= size && ((Comparable<? super E>) c).compareTo((E) array[right]) > 0) {
                child = right;
                c = array[child];
            }

            if (comparable.compareTo((E) c) <= 0) {
                break;
            }
            array[parent] = c;
            parent = child;

        }
        array[parent] = comparable;
        if (array.length > defaultCapacity && size < array.length / 4) {
            resize(Math.max(defaultCapacity, array.length / 2));
        }
    }

    /*
     * size,peek, isEmpty, contains, clear 메서드
     * 현재 우선순위 큐에 저장 된 요소의 개수를 알고 싶을때 size 값을 리턴하기 위한 메서드로 size()를 하나 만들고,
     * 또한 가장 우선순위가 높은 원소인 루트 노드의 값만 확인하고 싶을 때 쓰는 메서드를 위해 peek() 메서드를 만들 것이다.
     * 데이터를 삭제하지 않고 확인만 하고 싶을 때 쓰는 것이 peek() 메서드다. 한마디로 poll () 메서드에서 삭제 과정만 없는 것이 peek()이다.
     *
     * 그리고 의외로 자주 쓰이는 현재 우선순위 큐 (Priority Queue )에 요소가 아무 것도 없는 경우를 판별하기 위한 메서드도 하나 만들어주자.
     * 이 때 힙에 아무 요소가 없다는 것은 size가 0이라는 소리이므로 size == 0 인지를 반환해주면 된다.
     *
     * 또한 contains 로 찾고자 하는 요소가 PriorityQueue의 벼열에 존재하는지 볼 수 있또록 아주 간단하게 구현해볼 것이다.
     *
     * 마지막으로 가끔 우선순위 큐에 있는 모든 요소들을 비우고 싶을 때가 있다. 그럴 때 쓸 수 있는 clear 메서드도 같이 보도록 하자.
     * */
    public int size() {
        return this.size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E peek() {
        if (array[1] == null) { // root 요소가 null일 경우 예외 던짐
            throw new NoSuchElementException();
        }
        return (E) array[1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object value) {
        for (int i = 1; i <= size; i++) {
            if (array[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
        size = 0;
    }

    /*
     * toArray ,Clone  메서드 구현
     *
     * toArray()는 크게 두가지가 있다.
     * 하나는 아무런 인자 없이 현재 있는 큐의 요소들을 객체배열(Object[])로 반환해주는 Object[] toArray() 메서드가 있고,
     * 다른 하나는 큐를 이미 생성된 다른 배열에 복사해주고자 할 때 쓰는 T[] toArray(T[] a)메서드가 있다.
     *
     * 즉 차이는 이런 것이다.
     * PriorityQueue < Integer> priorityQueue = new PriorityQueue<>();
     * // get priorityQueue to array (using toArray())
     * Object[] q1 = priorityQueue.toArray();
     *
     * //get priorityQueue to array (using toArray(T[] a))
     * Integer[] q2 = new Integer[10];
     * q2 = priorityQueue.toArray(q2);
     *
     * 1번의 장점이라면 우선순위 큐에 있는 요소의 수만큼 정확하게 배열의 크기가 할당되어 반환된다는 점이다.
     * 2번의 장점이라면 객체 클래스로 상속관계에 있는 타입이거나 Wrapper(Integer -> int) 같이 데이터 타입을 유연하게 캐스팅할 여지가 있다는 것이다.
     * 또한 큐의 원소5개가 있고, q2 배열에 10개의 원소가 있다면 q2에 0~4 index에 원소가 복사되고, 그외의 원소는 기존 q2 배열에 초기화 되어있던 원소가 그대로 남는다.
     *
     * */
    /*
     * 첫번째 toArray()
     * 두번째 toArray(Tp[]a)로 보내도록 하고 있다. (동작 구현이 겹치기 때문이다.)
     * */
    public Object[] toArray() {
        return toArray(new Object[size]);
    }

    /*
     * 두번째 T[] toArray(T[] a)메서드를 만들어보자
     * 이부분은 제네릭 메서드로, 만들었던 PriorityQueue의 E타입하고는 다른 제네릭이다.
     * 예로  E  type이 Integer라고 가정하고, T타입은 Object라고 해보자.
     * Object 는 Integer보다 상위 타입으로, Object안에 Integer 타입의 데이터를 담을 수도 있다.
     * 이 외에도 사용자가 만든 부모, 자식, 클래스 같이 상속관계에 있는 클래스들에서 하위 타입이 상우 ㅣ타입으로 데이터를 받고 싶을 때 쓸 수 있도록 위함이다.
     * 즉, 상위타입으로 들어오는 객체에 대해서도 데이터를 담을 수 있도록 별도의 제네릭메서드를 구성하는 것이다.
     * */
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length <= size) {
            return (T[]) Arrays.copyOfRange(array, 1, size, a.getClass());
        }
        System.arraycopy(array, 1, a, 0, size);
        return a;
    }
}
