import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LargestNumber {

    private static Stream<Arguments> providedTestCase() {
        return Stream.of(
                Arguments.of(new int[]{6, 10, 2}, "6210"),
                Arguments.of(new int[]{3, 30, 34, 5, 9}, "9534330"),
                Arguments.of(new int[]{10, 101}, "10110"),
                Arguments.of(new int[]{51, 15}, "5115"),
                Arguments.of(new int[]{121, 12}, "12121"),
                Arguments.of(new int[]{0, 0, 0, 1000}, "1000000"),
                Arguments.of(new int[]{2, 22, 223}, "223222"),
                Arguments.of(new int[]{40, 405}, "40540"),
                Arguments.of(new int[]{40, 404}, "40440"),
                Arguments.of(new int[]{0, 0}, "0")

        );
    }

    @ParameterizedTest
    @MethodSource("providedTestCase")
    void largestNumber(int[] numbers, String solution) {
        assertAll(
                () -> assertEquals(solution, this.solution(numbers)),
                () -> assertEquals(solution, this.solution2(numbers))
        );
    }

    public String solution(int[] numbers) {
        List<String> values = new ArrayList<>();
        StringBuilder zeroBuilder = new StringBuilder();
        StringBuilder answerBuilder = new StringBuilder();
        StringBuilder thousandBuilder = new StringBuilder();

        for (int i = 0; i < numbers.length; i++) {
            final var n = numbers[i];

            if (n == 0) {
                zeroBuilder.append(0);
                continue;
            } else if (n == 9) {
                answerBuilder.append(9);
                continue;
            } else if (n == 1000) {
                thousandBuilder.append(1000);
                continue;
            }

            values.add(String.valueOf(n));
        }

        values.sort((o2, o1) -> o1.concat(o2).compareTo(o2.concat(o1)));
        values.forEach(o -> answerBuilder.append(o));

        answerBuilder.append(thousandBuilder);

        if (zeroBuilder.length() == numbers.length) {
            answerBuilder.append("0");
        } else {
            answerBuilder.append(zeroBuilder);
        }

        return answerBuilder.toString();
    }

    public String solution2(int[] numbers) {
        List<String> values = new ArrayList<>();
        StringBuilder answerBuilder = new StringBuilder();

        for (int i = 0; i < numbers.length; i++) {
            final var n = numbers[i];

            values.add(String.valueOf(n));
        }

        values.sort((o2, o1) -> o1.concat(o2).compareTo(o2.concat(o1)));
        values.forEach(o -> answerBuilder.append(o));

        if (values.get(0).equals("0")) {
            return "0";
        } else {
            return answerBuilder.toString();
        }
    }
}
