import java.math.BigInteger;

/**
 * 분수의 덧셈
 * https://programmers.co.kr/learn/courses/30/lessons/12947
 *
 *  - 문제 설명
 *  두 개의 분수를 입력받아 덧셈의 결과를 반환하는 함수, solution을 완성해 보세요.
 *  단, return하는 값은 배열 [분자, 분모] 형태입니다.
 *  예를 들어 2/5와 3/7의 덧셈 결과는 17/35입니다.
 *
 *  - 제한 사항
 *  분수는 1/100,000 이하의 자연수입니다.
 *  분모는 1/100,000 이하의 자연수입니다.
 *
 * - 입출력 예
 * numer1	denom1	numer2	denom2	result
 * 2	    5	    3	    7	    [17, 35]
 * 1	    2	    3	    4	    [5, 4]
 * 1	    3	    2	    5	    [11, 15]
 * 1	    1	    1	    1	    [2, 1]
 * 1	    1	    1	    2	    [3, 2]
 *
 * 풀이법
 * 1.
 * 분모를 공통분모로 만들고 분자를 더한다. 결과의 최대공약수로 분자 분모를 나눈 후에 배열에 담아 반환한다.
 *
 * 2.
 * 답변 제출 후 확인해보니까 모두 최대공약수를 구하는 메서드를 직접 생성했다...
 * 나는 남이 해놓은걸로 했는데... 다른 방법을 찾아보자
 */
public class AdditionFraction {

    public static void main(String[] args) {
        AdditionFraction af = new AdditionFraction();
        int[] result = af.solution2(1, 2, 3, 4);
    }

    /**
     * 분수의 덧셈 > 2번 풀이 - start
     */
    public int[] solution2(int numer1, int denom1, int numer2, int denom2) {
        int mother = denom1 * denom2;
        int son = (numer1 * denom2) + (numer2 * denom1);

        int gcd = getGCD(mother, son);

        if (gcd != 0) {
            mother /= gcd;
            son /= gcd;
        }

        return new int[]{son, mother};
    }

    /**
     * 두 수의 최대공약수를 구한다.
     * 나 할줄 모르는데...
     * 주어진 두 수 중 하나라도 공약수보다 작으면 더이상 나눌 수 없다. 그러므로 루프 종료
     * 루프
     * 1. 공약수로 두 수를 나눠서 나눠 떨어지면 최대공약수에 공약수를 곱하고,
     * 2. 나눠 떨어지지 않으면 공약수를 1 증가 시킨다.
     */
    public int getGCD(int n1, int n2) {
        if (n1 == 1 || n2 == 1) {
            return 0;
        }

        int gcd = 1;
        int divisor = 2;

        while (divisor <= n1 && divisor <= n2) {

            if (n1 % divisor == 0 && n2 % divisor == 0) {
                n1 = n1/divisor;
                n2 = n2/divisor;

                gcd *= divisor;
            } else {
                divisor++;
            }
        }

        return gcd;
    }

    /**
     * 분수의 덧셈 > 2번 풀이 - end
     */


    /**
     * 분수의 덧셈 > 1번 풀이 - start
     */
    public int[] solution1(int numer1, int denom1, int numer2, int denom2) {
        int mother = denom1 * denom2;
        int son = (numer1 * denom2) + (numer2 * denom1);

        BigInteger b1 = new BigInteger(String.valueOf(mother));
        BigInteger b2 = new BigInteger(String.valueOf(son));

        BigInteger gcd = b1.gcd(b2);

        if (gcd.intValue() != 0) {
            mother /= gcd.intValue();
            son /= gcd.intValue();
        }

        return new int[]{son, mother};
    }
    /**
     * 분수의 덧셈 > 1번 풀이 - end
     */
}