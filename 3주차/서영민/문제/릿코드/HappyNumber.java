package 릿코드;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HappyNumber {

    @ParameterizedTest
    @MethodSource("providedTestCase")
    void happyNumber(int n, boolean expected) {
        int div = n;
        int sum = 0;
        boolean result;

        while(true) {
            int remain = div % 10;
            div /= 10;
            sum += remain * remain;

            if (sum < 11 && div == 0) {
                result = (sum == 7 || sum == 1 || sum == 10);

                break;
            } else {
                if (div == 0 && remain == 0) {
                    div = sum;
                    sum = 0;
                }
            }
        }

        assertAll(
                () -> assertEquals(expected, result)
        );
    }

    private static Stream<Arguments> providedTestCase() {
        return Stream.of(
                Arguments.of(19, true),
                Arguments.of(2, false),
                Arguments.of(1111111, true)
        );
    }
}
