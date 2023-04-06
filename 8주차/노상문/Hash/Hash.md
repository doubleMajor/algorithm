# 자료구조

## 해시

### 해시란?
- 해시(Hash)는 데이터를 빠르게 저장하고 검색할 수 있는 자료구조.
- 해시는 해시 함수(hash function)를 사용하여 키(key)를 고유한 인덱스로 변환하고,  
이 인덱스를 사용하여 값을 저장소(해시 테이블)에 저장.
- 해시 함수는 일정한 길이의 해시 값을 생성하며,  
이 값은 배열이나 리스트와 같은 저장소에서 값의 위치를 결정하는 데 사용됩니다.

### 해시의 주요 특징

1. 빠른 검색 속도: 해시는 상수 시간(O(1))에 데이터를 검색할 수 있는 빠른 검색 속도를 제공.<br>
2. 이는 해시 함수를 사용하여 값을 찾는 인덱스를 직접 계산하기 때문입니다.<br>
3. 키-값 쌍: 해시는 키-값 쌍(key-value pair)을 저장하며, 키를 사용하여 값을 빠르게 검색할 수 있습니다.<br>
4. 해시 충돌: 서로 다른 키가 동일한 해시 값을 생성할 때 해시 충돌(hash collision)이 발생. 해시 충돌을 해결하는 방법에는 개별 체이닝(separate chaining)과 오픈 어드레싱(open addressing)이 있습니다.<br>
5. 개별 체이닝: 해시 테이블의 각 인덱스에 연결 리스트를 사용하여 충돌이 발생한 키-값 쌍을 저장.<br>
6. 오픈 어드레싱: 충돌이 발생한 경우, 해시 테이블 내의 다른 인덱스에 값을 저장하는 방법입니다. 선형 조사(linear probing), 제곱 조사(quadratic probing), 더블 해싱(double hashing) 등의 방법이 있습니다.<br>
7. 해시 함수의 중요성: 해시 테이블의 성능은 해시 함수의 품질에 크게 의존. 좋은 해시 함수는 키를 해시 테이블 전체에 고르게 분산시켜 충돌을 최소화하고 검색, 삽입, 삭제 작업의 성능을 향상시킵니다.<br>
8. 해시는 빠른 검색 속도를 제공하는 데 유용한 자료구조입니다. 그러나 해시 충돌을 해결하기 위한 추가적인 작업이 필요하며, 저장소의 공간 효율이 낮을 수 있습니다. 해시는 딕셔너리, 캐시, 해시 맵, 해시 셋 등 여러 가지 형태로 구현되며, 다양한 프로그래밍 언어에서 기본 제공되는 자료구조로 사용되고 있습니다.<br>

```java
import java.util.HashMap;
import java.util.Map;

public class HashMapExample {
    public static void main(String[] args) {
        // 해시 맵 생성
        Map<String, Integer> hashMap = new HashMap<>();

        // 키-값 쌍 추가
        hashMap.put("Alice", 25);
        hashMap.put("Bob", 30);
        hashMap.put("Charlie", 35);

        // 키를 사용하여 값 검색
        Integer ageOfAlice = hashMap.get("Alice");
        System.out.println("Alice's age: " + ageOfAlice);

        // 키의 존재 확인
        boolean hasAlice = hashMap.containsKey("Alice");
        System.out.println("Is Alice in the map? " + hasAlice);

        // 해시 맵에서 키-값 쌍 삭제
        hashMap.remove("Alice");

        // 삭제 후 다시 확인
        hasAlice = hashMap.containsKey("Alice");
        System.out.println("Is Alice in the map? " + hasAlice);
    }
}

```

```java
public class SimpleHashTable<K, V> {
    private int capacity;
    private Entry<K, V>[] table;

    // 생성자: 해시 테이블의 용량을 초기화하고, 테이블을 생성.
    public SimpleHashTable(int capacity) {
        this.capacity = capacity;
        table = new Entry[capacity];
    }

    // 해시 함수: 키의 해시코드를 사용하여 인덱스를 생성.
    private int hash(K key) {
        return key.hashCode() % capacity;
    }

    // put 메서드: 키와 값을 받아 해시 테이블에 저장.
    public void put(K key, V value) {
        int index = hash(key);

        // 충돌이 발생할 경우 선형 탐색을 사용하여 다음 빈 칸을 찾음.
        while (table[index] != null && !table[index].key.equals(key)) {
            index = (index + 1) % capacity;
        }

        // 키와 값을 저장.
        table[index] = new Entry<>(key, value);
    }

    // get 메서드: 키를 사용하여 해시 테이블에서 값을 검색.
    public V get(K key) {
        int index = hash(key);

        // 선형 탐색을 사용하여 값을 찾음.
        while (table[index] != null) {
            if (table[index].key.equals(key)) {
                return table[index].value;
            }
            index = (index + 1) % capacity;
        }

        // 값을 찾지 못한 경우 null을 반환.
        return null;
    }

    // remove 메서드: 키를 사용하여 해시 테이블에서 키-값 쌍을 삭제.
    public void remove(K key) {
        int index = hash(key);

        // 선형 탐색을 사용하여 키-값 쌍을 찾음.
        while (table[index] != null) {
            if (table[index].key.equals(key)) {
                table[index] = null;
                break;
            }
            index = (index + 1) % capacity;
        }
    }

    // Entry 클래스: 키와 값을 저장하는 데 사용.
    public static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
```

```java
public class SimpleHashTableTest {
    
    @Test
    public void hash_Test() {
        SimpleHashTable<String, Integer> simpleHashTable = new SimpleHashTable<>(10);

        // 키-값 쌍 추가
        simpleHashTable.put("Alice", 25);
        simpleHashTable.put("Bob", 30);
        simpleHashTable.put("Charlie", 35);

        // 키를 사용하여 값 검색
        Integer ageOfAlice = simpleHashTable.get("Alice");
        System.out.println("Alice's age: " + ageOfAlice); // Alice's age: 25

        // 해시 테이블에서 키-값 쌍 삭제
        simpleHashTable.remove("Alice");

        // 삭제 후 다시 확인
        ageOfAlice = simpleHashTable.get("Alice");
        System.out.println("Alice's age after removal: " + ageOfAlice); // Alice's age after removal: null

        // 존재하지 않는 키에 대해 검색
        Integer ageOfDavid = simpleHashTable.get("David");
        System.out.println("David's age: " + ageOfDavid); // David's age: null

        // 이미 존재하는 키에 대한 값을 업데이트
        simpleHashTable.put("Bob", 32);
        Integer ageOfBob = simpleHashTable.get("Bob");
        System.out.println("Bob's updated age: " + ageOfBob); // Bob's updated age: 32
    }
}

```