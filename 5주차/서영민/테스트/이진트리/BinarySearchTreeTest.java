package 이진트리;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {


    /**
     *                       10
     *                8              14
     *             6     9       11      15
     *          5     7              12
     */
    @Test
    void add() {

        // given
        Traversal<Integer> bst = new Traversal<>();

        // when
        bst.add(10);
        bst.add(8);
        bst.add(6);
        bst.add(9);
        bst.add(5);
        bst.add(7);
        bst.add(14);
        bst.add(11);
        bst.add(12);
        bst.add(15);

        // then
        final var root = bst.root();

        assertAll(
                () -> assertEquals(root.left.data, 8),
                () -> assertEquals(root.left.left.data, 6),
                () -> assertEquals(root.left.left.left.data, 5),
                () -> assertEquals(root.left.left.right.data, 7),
                () -> assertEquals(root.left.right.data, 9),
                () -> assertEquals(root.right.data, 14),
                () -> assertEquals(root.right.left.data, 11),
                () -> assertEquals(root.right.left.right.data, 12),
                () -> assertEquals(root.right.right.data, 15),
                () -> assertEquals(bst.size, 10)
        );
    }

    /**
     *                       10
     *                8              14
     *             6     9       11      15
     *          5     7              12
     */
    @Test
    void addRecursive() {

        // given
        Traversal<Integer> bst = new Traversal<>();

        // when
        bst.addRecursive(10);
        bst.addRecursive(8);
        bst.addRecursive(6);
        bst.addRecursive(9);
        bst.addRecursive(5);
        bst.addRecursive(7);
        bst.addRecursive(14);
        bst.addRecursive(11);
        bst.addRecursive(12);
        bst.addRecursive(15);

        // then
        final var root = bst.root();

        assertAll(
                () -> assertEquals(root.left.data, 8),
                () -> assertEquals(root.left.left.data, 6),
                () -> assertEquals(root.left.left.left.data, 5),
                () -> assertEquals(root.left.left.right.data, 7),
                () -> assertEquals(root.left.right.data, 9),
                () -> assertEquals(root.right.data, 14),
                () -> assertEquals(root.right.left.data, 11),
                () -> assertEquals(root.right.left.right.data, 12),
                () -> assertEquals(root.right.right.data, 15),
                () -> assertEquals(bst.size, 10)
        );
    }

    /**
     *                       10
     *                8              14
     *             6     9       11      15
     *          5     7              12
     */
    @Test
    void find() {

        // given
        Traversal<Integer> bst = new Traversal<>();
        bst.addRecursive(10);
        bst.addRecursive(8);
        bst.addRecursive(6);
        bst.addRecursive(9);
        bst.addRecursive(5);
        bst.addRecursive(7);
        bst.addRecursive(14);
        bst.addRecursive(11);
        bst.addRecursive(12);
        bst.addRecursive(15);

        // when

        // then
        final var root = bst.root();

        assertAll(
                () -> assertEquals(root.left.data, bst.find(8).data),
                () -> assertEquals(root.left.left.data, bst.find(6).data),
                () -> assertEquals(root.left.left.left.data, bst.find(5).data),
                () -> assertEquals(root.left.left.right.data, bst.find(7).data),
                () -> assertEquals(root.left.right.data, bst.find(9).data),
                () -> assertEquals(root.right.data, bst.find(14).data),
                () -> assertEquals(root.right.left.data, bst.find(11).data),
                () -> assertEquals(root.right.left.right.data, bst.find(12).data),
                () -> assertEquals(root.right.right.data, bst.find(15).data),
                () -> assertNull(bst.find(999)),
                () -> assertEquals(bst.size, 10)
        );
    }
    /**
     *                       10
     *                8              14
     *             6     9       11      15
     *          5     7              12     17
     *                                    16   18
     *
     */
    @Test
    void findRecursive() {

        // given
        Traversal<Integer> bst = new Traversal<>();
        bst.addRecursive(10);
        bst.addRecursive(8);
        bst.addRecursive(6);
        bst.addRecursive(9);
        bst.addRecursive(5);
        bst.addRecursive(7);
        bst.addRecursive(14);
        bst.addRecursive(11);
        bst.addRecursive(12);
        bst.addRecursive(15);

        // when

        // then
        final var root = bst.root();

        assertAll(
                () -> assertEquals(root.left.data, bst.findRecursive(8).data),
                () -> assertEquals(root.left.left.data, bst.findRecursive(6).data),
                () -> assertEquals(root.left.left.left.data, bst.findRecursive(5).data),
                () -> assertEquals(root.left.left.right.data, bst.findRecursive(7).data),
                () -> assertEquals(root.left.right.data, bst.findRecursive(9).data),
                () -> assertEquals(root.right.data, bst.findRecursive(14).data),
                () -> assertEquals(root.right.left.data, bst.findRecursive(11).data),
                () -> assertEquals(root.right.left.right.data, bst.findRecursive(12).data),
                () -> assertEquals(root.right.right.data, bst.findRecursive(15).data),
                () -> assertNull(bst.find(999)),
                () -> assertEquals(bst.size, 10)
        );
    }

    /**
     *                       10
     *                8              14
     *             6     9        11       15
     *          [5] 7                   12       17
     *        4                               16   18
     *                       V
     *                       V
     *
     *                       10
     *                8              14
     *             6     9        11       15
     *          4     7              12       17
     *                                      16   18
     *
     */
    @DisplayName("[삭제] 좌측 자식만 있는 경우")
    @Test
    void remove1() {

        // given
        Traversal<Integer> bst = new Traversal<>();
        bst.addRecursive(10);
        bst.addRecursive(8);
        bst.addRecursive(6);
        bst.addRecursive(9);
        bst.addRecursive(5);
        bst.addRecursive(4);
        bst.addRecursive(7);
        bst.addRecursive(14);
        bst.addRecursive(11);
        bst.addRecursive(12);
        bst.addRecursive(15);
        bst.addRecursive(17);
        bst.addRecursive(18);
        bst.addRecursive(16);

        // when

        // then
        final var root = bst.root();
        bst.remove(5);

        assertAll(
                () -> assertEquals(root.left.data, bst.findRecursive(8).data),
                () -> assertEquals(root.left.left.data, bst.findRecursive(6).data),
                () -> assertEquals(root.left.left.left.data, bst.findRecursive(4).data),
                () -> assertEquals(root.left.left.right.data, bst.findRecursive(7).data),
                () -> assertEquals(root.left.right.data, bst.findRecursive(9).data),
                () -> assertEquals(root.right.data, bst.findRecursive(14).data),
                () -> assertEquals(root.right.left.data, bst.findRecursive(11).data),
                () -> assertEquals(root.right.left.right.data, bst.findRecursive(12).data),
                () -> assertNull(bst.find(5)),
                () -> assertEquals(bst.size, 13)
        );
    }

    /**
     *                       10
     *                8              14
     *             6     9       11      [15]
     *          5     7              12     17
     *                                    16   18
     *                       V
     *                       V
     *
     *                       10
     *                8              14
     *             6     9       11      17
     *          5     7            12   16 18
     *
     */
    @DisplayName("[삭제] 우측 자식만 있는 경우")
    @Test
    void remove2() {

        // given
        Traversal<Integer> bst = new Traversal<>();
        bst.addRecursive(10);
        bst.addRecursive(8);
        bst.addRecursive(6);
        bst.addRecursive(9);
        bst.addRecursive(5);
        bst.addRecursive(7);
        bst.addRecursive(14);
        bst.addRecursive(11);
        bst.addRecursive(12);
        bst.addRecursive(15);
        bst.addRecursive(17);
        bst.addRecursive(18);
        bst.addRecursive(16);

        // when

        // then
        final var root = bst.root();
        bst.remove(15);

        assertAll(
                () -> assertEquals(root.left.data, bst.findRecursive(8).data),
                () -> assertEquals(root.left.left.data, bst.findRecursive(6).data),
                () -> assertEquals(root.left.left.left.data, bst.findRecursive(5).data),
                () -> assertEquals(root.left.left.right.data, bst.findRecursive(7).data),
                () -> assertEquals(root.left.right.data, bst.findRecursive(9).data),
                () -> assertEquals(root.right.data, bst.findRecursive(14).data),
                () -> assertEquals(root.right.left.data, bst.findRecursive(11).data),
                () -> assertEquals(root.right.left.right.data, bst.findRecursive(12).data),
                () -> assertEquals(root.right.right.data, bst.findRecursive(17).data),
                () -> assertEquals(root.right.right.left.data, bst.findRecursive(16).data),
                () -> assertEquals(root.right.right.right.data, bst.findRecursive(18).data),
                () -> assertNull(bst.find(15)),
                () -> assertEquals(bst.size, 12)
        );
    }

    /**
     *                       10
     *                8                [15]
     *             6     9       11            18
     *          5     7             12      17    20
     *                                    16
     *
     *                       V
     *                       V
     *
     *                       10
     *                8                 16
     *             6     9       11            18
     *          5     7             12      17    20
     *
     */
    @DisplayName("[삭제] 자식이 둘다 있는 경우 - 가장 작은 우측 자손노드의 우측 자식이 없을 경우")
    @Test
    void remove3() {

        // given
        Traversal<Integer> bst = new Traversal<>();
        bst.addRecursive(10);
        bst.addRecursive(8);
        bst.addRecursive(6);
        bst.addRecursive(9);
        bst.addRecursive(5);
        bst.addRecursive(7);
        bst.addRecursive(15);
        bst.addRecursive(11);
        bst.addRecursive(12);
        bst.addRecursive(18);
        bst.addRecursive(17);
        bst.addRecursive(16);
        bst.addRecursive(20);

        // when

        // then
        final var root = bst.root();
        bst.remove(15);

        assertAll(
                () -> assertEquals(root.left.data, bst.findRecursive(8).data),
                () -> assertEquals(root.left.left.data, bst.findRecursive(6).data),
                () -> assertEquals(root.left.left.left.data, bst.findRecursive(5).data),
                () -> assertEquals(root.left.left.right.data, bst.findRecursive(7).data),
                () -> assertEquals(root.left.right.data, bst.findRecursive(9).data),
                () -> assertEquals(root.right.data, bst.findRecursive(16).data),
                () -> assertEquals(root.right.left.data, bst.findRecursive(11).data),
                () -> assertEquals(root.right.left.right.data, bst.findRecursive(12).data),
                () -> assertEquals(root.right.right.data, bst.findRecursive(18).data),
                () -> assertEquals(root.right.right.left.data, bst.findRecursive(17).data),
                () -> assertEquals(root.right.right.right.data, bst.findRecursive(20).data),
                () -> assertNull(bst.find(15)),
                () -> assertEquals(bst.size, 12)
        );
    }


    /**
     *                       10
     *                8                [15]
     *             6     9       11            20
     *          5     7             12      18    21
     *                                    16  19
     *                                      17
     *                       V
     *                       V
     *                       10
     *                8                 16
     *             6     9       11            20
     *          5     7             12      18    21
     *                                    17  19
     *
     */
    @DisplayName("[삭제] 자식이 둘다 있는 경우 - 가장 작은 우측 자손노드의 우측 자식이 있을 경우")
    @Test
    void remove4() {

        // given
        Traversal<Integer> bst = new Traversal<>();
        bst.addRecursive(10);
        bst.addRecursive(8);
        bst.addRecursive(6);
        bst.addRecursive(9);
        bst.addRecursive(5);
        bst.addRecursive(7);
        bst.addRecursive(15);
        bst.addRecursive(11);
        bst.addRecursive(12);
        bst.addRecursive(20);
        bst.addRecursive(18);
        bst.addRecursive(16);
        bst.addRecursive(19);
        bst.addRecursive(17);
        bst.addRecursive(21);

        // when

        // then
        final var root = bst.root();
        bst.remove(15);

        assertAll(
                () -> assertEquals(root.left.data, bst.findRecursive(8).data),
                () -> assertEquals(root.left.left.data, bst.findRecursive(6).data),
                () -> assertEquals(root.left.left.left.data, bst.findRecursive(5).data),
                () -> assertEquals(root.left.left.right.data, bst.findRecursive(7).data),
                () -> assertEquals(root.left.right.data, bst.findRecursive(9).data),
                () -> assertEquals(root.right.data, bst.findRecursive(16).data),
                () -> assertEquals(root.right.left.data, bst.findRecursive(11).data),
                () -> assertEquals(root.right.left.right.data, bst.findRecursive(12).data),
                () -> assertEquals(root.right.right.data, bst.findRecursive(20).data),
                () -> assertEquals(root.right.right.left.data, bst.findRecursive(18).data),
                () -> assertEquals(root.right.right.left.left.data, bst.findRecursive(17).data),
                () -> assertEquals(root.right.right.left.right.data, bst.findRecursive(19).data),
                () -> assertEquals(root.right.right.right.data, bst.findRecursive(21).data),
                () -> assertNull(bst.find(15)),
                () -> assertEquals(bst.size, 14)
        );
    }
}