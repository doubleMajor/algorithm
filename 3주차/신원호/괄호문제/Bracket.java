import jdk.jfr.Unsigned;

/**
 * 괄호문제
 * https://programmers.co.kr/learn/courses/30/lessons/12909
 *
 * @author 신원호
 * @version 1.0
 * @since 2021.11.25
 * @see
 *
 *         1. 문제 설명
 *         올바른 괄호
 *         - 괄호가 바르게 짝지어졌다는 것은 '('문자로 열렸으면 반드시 짝지어서 ')' 문자로 닫혀야 한다는 뜻입니다.
 *         - 예를 들어
 *         - ()() 또는 (())() 는 올바른 괄호입니다.
 *         - )()( 또는 (()( 는 올바르지 않은 괄호입니다.
 *         - '(' 또는 ')' 로만 이루어진 문자열 s가 주어졌을 때,
 *         - 문자열 s가 올바른 괄호이면 true를 return 하고, 올바르지 않은 괄호이면 false를 return 하는 solution 함수를 완성해 주세요.
 *
 *         2. 제한사항
 *         - 문자열 s의 길이 : 100,000 이하의 자연수
 *         - 문자열 s는 '(' 또는 ')' 로만 이루어져 있습니다.
 *
 *         3. 입출력 예
 *         s	answer
 *         ()()	true
 *         (())()	true
 *         )()(	false
 *         (()(	false
 */
public class Bracket {

    boolean solution(String s) {
        boolean answer = true;
        char open = '(';
        char close = ')';
        int number = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == open) {
                number++;
            } else if (s.charAt(i) == close) {
                number--;
            }

            if (number < 0) {
                return false;
            }
        }

        if (number != 0) {
            return false;
        } else {
            return true;
        }
    }
}
