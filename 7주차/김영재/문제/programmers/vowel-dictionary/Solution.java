public class Solution {
    public int solution(String word) {
        /*
         * A, E, I, O, U 만을 사용하여 만들 수 있는 길이 5 이하의 모든 단어
         * 사전의 첫 단어는 'A' 그 다음은 'AA', 마지막 단어는 'UUUUU'
         * 사전은 대문자로만 이루어져 있음
         * AAAAA : 5
         * AAAAE : 6
         * AAAAI : 7
         * AAAAO : 8
         * AAAAU : 9
         * 4번 째 : 6씩 증가
         * AAAA  : 4
         * AAAE  : 10
         * AAAEA : 11
         * .....
         * AAAI  : 16
         * AAAIA : 17
         * ......
         * AAAO  : 22
         * AAAU  : 28
         * 3번 째 : 31씩 증가
         * AAA   : 3
         * AAE   : 34
         * AAEA  : 35
         * AAEAA : 36
         * .....
         * AAI   : 65
         * AAO   : 96
         * AAU   : 127
         *
         * 2번 째 : 156씩 증가
         * AA    : 2
         * AE    : 158
         * AEA   : 159
         * AI    : 314
         * AO    : 470
         * AU    : 626
         *
         * 1번 째 : 781씩 증가
         * A     : 1
         * E     : 782
         * I     : 1563
         * O     : 2345
         * U     : 3126
         *
         * [ 1 : 781, 2 : 156, 3 : 31, 4 : 6, 5 : 1 ]
         * 781, 312, 93
         */

        char[] c = word.toCharArray();
        int[] increment = {781, 156, 31, 6, 1};
        int length = c.length;
        int answer = 0;
        for(int i = 0; i < length; i++) {
            answer += switch (c[i]) {
                case 'A' -> 1;
                case 'E' -> increment[i] + 1;
                case 'I' -> increment[i] * 2 + 1;
                case 'O' -> increment[i] * 3 + 1;
                case 'U' -> increment[i] * 4 + 1;
                default -> 0;
            };
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution("AAAAE"));
        System.out.println(new Solution().solution("AAAE"));
        System.out.println(new Solution().solution("I"));
        System.out.println(new Solution().solution("EIO"));
    }
}
