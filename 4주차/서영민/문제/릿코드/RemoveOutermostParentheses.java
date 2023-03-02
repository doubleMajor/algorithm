import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedList;
import java.util.Stack;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveOutermostParentheses {

    private static Stream<Arguments> providedTestCase() {
        return Stream.of(
                Arguments.of("(()())(())", "()()()"),
                Arguments.of("(()())(())(()(()))", "()()()()(())"),
                Arguments.of("()()", "")

        );
    }

    @ParameterizedTest
    @MethodSource("providedTestCase")
    void removeOuterParentheses(String s, String solution) {
        assertAll(
                () -> assertEquals(solution, this.solution(s))
                , () -> assertEquals(solution, this.solution2(s))
                , () -> assertEquals(solution, this.solution3(s))
                , () -> assertEquals(solution, this.solution4(s))
        );
    }

    public String solution(String s) {
        final var length = s.length();
        StringBuilder result = new StringBuilder();
        int sum = 0;
        int point = 0;

        for (int i = 0; i < length; i++) {
            sum += s.charAt(i) == '(' ? 1 : -1;

            if (sum > 0 && i > point)
                result.append(s.charAt(i));
            else if (sum == 0)
                point = i + 1;
        }

        return result.toString();
    }

    public String solution2(String s) {
        final var length = s.length();
        StringBuilder result = new StringBuilder();
        int sum = 0;

        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == '(') {
                sum++;

                if (sum > 1) result.append('(');
            } else {
                sum--;

                if (sum > 0) result.append(")");
            }
        }

        return result.toString();
    }

    public String solution3(String s) {
        StringBuilder result = new StringBuilder();
        int sum = 0;

        for (var c : s.toCharArray()) {
            if (c == '(') {
                sum++;

                if (sum > 1) result.append(c);
            } else {
                sum--;

                if (sum > 0) result.append(c);
            }
        }

        return result.toString();
    }

    public String solution4(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();

        for (var c : s.toCharArray()) {
            if (c == '(') {

                if (stack.size() > 0) {
                    result.append(c);
                }

                stack.push(c);
            } else {
                stack.pop();

                if (stack.size() > 0) {
                    result.append(c);
                }
            }
        }

        return result.toString();
    }

    public String solution5(String s) {
        LinkedList ll = new LinkedList();
        StringBuilder result = new StringBuilder();

        for (var c : s.toCharArray()) {
            if (c == '(') {

                if (ll.size() > 0) {
                    result.append(c);
                }

                ll.add(c);
            } else {
                ll.pop();

                if (ll.size() > 0) {
                    result.append(c);
                }
            }
        }

        return result.toString();
    }
}
