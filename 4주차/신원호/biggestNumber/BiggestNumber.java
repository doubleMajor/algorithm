import java.util.Arrays;

/**
 * 0 또는 양의 정수가 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수를 알아내 주세요.
 * 예를 들어, 주어진 정수가 [6, 10, 2]라면 [6102, 6210, 1062, 1026, 2610, 2106]를 만들 수 있고, 이중 가장 큰 수는 6210입니다.
 * 0 또는 양의 정수가 담긴 배열 numbers가 매개변수로 주어질 때, 순서를 재배치하여 만들 수 있는 가장 큰 수를 문자열로 바꾸어 return 하도록 solution 함수를 작성해주세요.
 */
public class BiggestNumber {

    public static void main(String[] args) {
        BiggestNumber biggestNumber = new BiggestNumber();
        int[] numbers = {10,11,5};
    }

    public String solution(int[] numbers) {
        StringBuilder sb = new StringBuilder();
        String[] numbersChar = new String[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            numbersChar[i] = numbers[i] + "";
        }

        Arrays.sort(numbersChar, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));

        for (String s : numbersChar) {
            sb.append(s);
        }

        String answer = sb.toString();

        if (answer.charAt(0) == '0') {
            return "0";
        }

        return sb.toString();
    }
}
