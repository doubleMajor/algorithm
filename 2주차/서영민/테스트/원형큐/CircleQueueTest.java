package 원형큐;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CircleQueueTest {

    private CircleQueue<String> queue = null;
    private int size = 10;
    
    @BeforeEach
    public void init() {
        queue = new CircleQueue<String>(size);
    }

    @DisplayName("enqueue 테스트")
    @Test
    void enQueue() {

        // given

        // when
        queue.enQueue("1");
        queue.enQueue("2");
        queue.enQueue("3");

        // then
        assertAll(
                () -> assertEquals(queue.size(), size),
                () -> assertEquals(queue.dataSize(), 3),
                () -> assertEquals(queue.peek(), "1"),
                () -> assertFalse(queue.isEmpty()),
                () -> assertFalse(queue.isFull())
        );
    }

    @DisplayName("enqueue stack over flow")
    @Test
    void stackOverFlowError() {

        // given
        queue.enQueue("1");
        queue.enQueue("2");
        queue.enQueue("3");
        queue.enQueue("4");
        queue.enQueue("5");
        queue.enQueue("6");
        queue.enQueue("7");
        queue.enQueue("8");
        queue.enQueue("9");
        queue.enQueue("10");

        // when
        StackOverflowError thrown = Assertions.assertThrows(StackOverflowError.class, () -> {
            queue.enQueue("11");
        });

        // then
        assertThat(thrown)
                .isInstanceOf(StackOverflowError.class)
                .message().isEqualTo("스택이 가득 가득~");
    }

    @DisplayName("dequeue 테스트")
    @Test
    void deQueue() {

        // given
        queue.enQueue("1");
        queue.enQueue("2");
        queue.enQueue("3");

        // when
        queue.deQueue();
        queue.deQueue();

        // then
        assertAll(
                () -> assertEquals(queue.size(), size),
                () -> assertEquals(queue.dataSize(), 1),
                () -> assertEquals(queue.peek(), "3"),
                () -> assertFalse(queue.isEmpty()),
                () -> assertFalse(queue.isFull())
        );
    }

    @DisplayName("dequeue empty 테스트")
    @Test
    void deQueueEmptyError() {

        // given
        queue.enQueue("1");
        queue.enQueue("2");
        queue.enQueue("3");

        queue.deQueue();
        queue.deQueue();
        queue.deQueue();

        // when
        IllegalStateException thrown = Assertions.assertThrows(IllegalStateException.class, () -> {
            queue.deQueue();
        });

        // then
        assertThat(thrown)
                .isInstanceOf(IllegalStateException.class)
                .message().isEqualTo("내보낼 데이터가 없습니다.");
    }

    @DisplayName("dequeue/enqueue 테스트")
    @Test
    void randomQueue() {

        // given
        queue.enQueue("1");
        queue.enQueue("2");
        queue.enQueue("3");
        queue.enQueue("4");
        queue.enQueue("5");
        queue.enQueue("6");
        queue.enQueue("7");
        queue.enQueue("8");
        queue.enQueue("9");
        queue.enQueue("10");

        queue.deQueue();
        queue.deQueue();
        queue.deQueue();
        queue.deQueue();

        queue.enQueue("11");
        queue.enQueue("12");

        // when

        // then
        assertAll(
                () -> assertEquals(queue.size(), size),
                () -> assertEquals(queue.dataSize(), 8),
                () -> assertEquals(queue.peek(), "5"),
                () -> assertEquals(queue.peekRear(), "12"),
                () -> assertEquals(queue.getFront(), 4),
                () -> assertEquals(queue.getRear(), 1),
                () -> assertFalse(queue.isEmpty()),
                () -> assertFalse(queue.isFull())
        );
    }

    @DisplayName("PEEK")
    @Test
    void peek() {

        // given
        queue.enQueue("1");
        queue.enQueue("2");
        queue.enQueue("3");
        queue.enQueue("4");
        queue.enQueue("5");
        queue.enQueue("6");
        queue.enQueue("7");
        queue.enQueue("8");
        queue.enQueue("9");
        queue.enQueue("10");

        queue.deQueue();
        queue.deQueue();
        queue.deQueue();
        queue.deQueue();

        queue.enQueue("11");
        queue.enQueue("12");

        // when

        // then
        assertAll(
                () -> assertEquals(queue.peek(), "5")
        );
    }

    @DisplayName("EMPTY PEEK")
    @Test
    void emptyPeek() {

        // given
        queue.enQueue("1");
        queue.enQueue("2");
        queue.enQueue("3");
        queue.enQueue("4");
        queue.enQueue("5");
        queue.enQueue("6");
        queue.enQueue("7");
        queue.enQueue("8");
        queue.enQueue("9");
        queue.enQueue("10");

        queue.deQueue();
        queue.deQueue();
        queue.deQueue();
        queue.deQueue();

        queue.enQueue("11");
        queue.enQueue("12");

        queue.deQueue();
        queue.deQueue();
        queue.deQueue();
        queue.deQueue();
        queue.deQueue();
        queue.deQueue();
        queue.deQueue();
        queue.deQueue();

        // when

        // then
        assertAll(
                () -> assertTrue(queue.isEmpty())
        );
    }

    @DisplayName("Clear")
    @Test
    void clear() {

        // given
        queue.enQueue("1");
        queue.enQueue("2");
        queue.enQueue("3");
        queue.enQueue("4");
        queue.enQueue("5");
        queue.enQueue("6");
        queue.enQueue("7");
        queue.enQueue("8");
        queue.enQueue("9");
        queue.enQueue("10");

        queue.deQueue();
        queue.deQueue();
        queue.deQueue();
        queue.deQueue();

        queue.enQueue("11");
        queue.enQueue("12");

        // when
        queue.clear();

        // then
        assertAll(
                () -> assertEquals(queue.size(), size),
                () -> assertEquals(queue.dataSize(), 0),
                () -> assertEquals(queue.peek(), null),
                () -> assertTrue(queue.isEmpty()),
                () -> assertFalse(queue.isFull())
        );
    }

    @DisplayName("IS EMPTY")
    @Test
    void isEmpty() {

        // given
        queue.enQueue("1");
        queue.enQueue("2");
        queue.enQueue("3");

        // when
        queue.clear();

        // then
        assertAll(
                () -> assertTrue(queue.isEmpty())
        );
    }

    @DisplayName("IS FULL")
    @Test
    void isFull() {

        // given
        queue.enQueue("1");
        queue.enQueue("2");
        queue.enQueue("3");
        queue.enQueue("4");
        queue.enQueue("5");
        queue.enQueue("6");
        queue.enQueue("7");
        queue.enQueue("8");
        queue.enQueue("9");
        queue.enQueue("10");

        queue.deQueue();
        queue.deQueue();
        queue.deQueue();
        queue.deQueue();

        queue.enQueue("11");
        queue.enQueue("12");
        queue.enQueue("13");
        queue.enQueue("14");

        // when

        // then
        assertAll(
                () -> assertTrue(queue.isFull())
        );
    }

    @DisplayName("QUEUE SIZE")
    @Test
    void size() {

        // given
        queue.enQueue("1");
        queue.enQueue("2");
        queue.enQueue("3");

        // when

        // then
        assertAll(
                () -> assertEquals(queue.size(), size)
        );
    }

    @DisplayName("QUEUE DATA SIZE")
    @Test
    void dataSize() {

        // given
        queue.enQueue("1");
        queue.enQueue("2");
        queue.enQueue("3");
        queue.enQueue("4");
        queue.enQueue("5");
        queue.enQueue("6");
        queue.enQueue("7");
        queue.enQueue("8");
        queue.enQueue("9");
        queue.enQueue("10");

        queue.deQueue();
        queue.deQueue();
        queue.deQueue();
        queue.deQueue();

        queue.enQueue("11");
        queue.enQueue("12");

        // when

        // then
        assertAll(
                () -> assertEquals(queue.dataSize(), 8)
        );
    }
}