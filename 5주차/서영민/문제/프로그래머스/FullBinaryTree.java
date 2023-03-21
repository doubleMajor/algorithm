package 프로그래머스;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FullBinaryTree {

    private static Stream<Arguments> providedTestCase() {
        return Stream.of(
//                Arguments.of(new long[]{7, 42, 5}, new int[]{1, 1, 0}),
//                Arguments.of(new long[]{63, 111, 95}, new int[]{1, 1, 0}),
                Arguments.of(new long[]{47, 109, 106}, new int[]{1, 1, 1})
        );
    }

    @ParameterizedTest
    @MethodSource("providedTestCase")
    void largestNumber(long[] numbers, int[] solution) {
        final var results = this.solution(numbers);
        assertAll(
//                () -> assertEquals(solution[0], results[0]),
                () -> assertEquals(solution[1], results[1])
//                () -> assertEquals(solution[2], results[2])
        );
    }

    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            String binary = Long.toBinaryString(numbers[i]);
            int binaryLength = binary.length();
            int square = 0;

            while((int) Math.pow(2, square) - 1 < binaryLength) {
                square++;
            }

            int nodeCount = (int) Math.pow(2, square) - 1;

            while(nodeCount != binary.length()) {
                binary = "0" + binary;
            }

            answer[i] = this.search(binary);
        }

        return answer;
    }

    private int search(String binary) {
        int binaryLength = binary.length();
        int rootIndex = this.findRootIndex(binary);
        String leftBinary = binary.substring(0, rootIndex);
        String rightBinary = binary.substring(rootIndex + 1, binaryLength);
        char root = binary.charAt(rootIndex);

        if (root == '0') {
            int leftRootIndex = this.findRootIndex(leftBinary);
            int rightRootIndex = this.findRootIndex(rightBinary);

            if (leftBinary.charAt(leftRootIndex) == '1'
                    || rightBinary.charAt(rightRootIndex) == '1') {
                return 0;
            }
        }

        if (leftBinary.length() >= 3) {
            return this.search(leftBinary) == 1 ? this.search(rightBinary) : 0;
        }

        return 1;
    }

    private int findRootIndex(String binary) {
        return (binary.length() - 1) / 2;
    }
}
