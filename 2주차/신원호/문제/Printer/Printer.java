import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 문제 설명
 * 일반적인 프린터는 인쇄 요청이 들어온 순서대로 인쇄합니다. 그렇기 때문에 중요한 문서가 나중에 인쇄될 수 있습니다.
 * 이런 문제를 보완하기 위해 중요도가 높은 문서를 먼저 인쇄하는 프린터를 개발했습니다. 이 새롭게 개발한 프린터는 아래와 같은 방식으로 인쇄 작업을 수행합니다.
 *
 * 1. 인쇄 대기목록의 가장 앞에 있는 문서(J)를 대기목록에서 꺼냅니다.
 * 2. 나머지 인쇄 대기목록에서 J보다 중요도가 높은 문서가 한 개라도 존재하면 J를 대기목록의 가장 마지막에 넣습니다.
 * 3. 그렇지 않으면 J를 인쇄합니다.
 * 예를 들어, 4개의 문서(A, B, C, D)가 순서대로 인쇄 대기목록에 있고 중요도가 2 1 3 2 라면 C D A B 순으로 인쇄하게 됩니다.
 * bcda
 * cdab
 * cdab
 *
 * 내가 인쇄를 요청한 문서가 몇 번째로 인쇄되는지 알고 싶습니다. 위의 예에서 C는 1번째로, A는 3번째로 인쇄됩니다.
 *
 * 현재 대기목록에 있는 문서의 중요도가 순서대로 담긴 배열 priorities와 내가 인쇄를 요청한 문서가 현재 대기목록의 어떤 위치에 있는지를 알려주는
 * location이 매개변수로 주어질 때, 내가 인쇄를 요청한 문서가 몇 번째로 인쇄되는지 return 하도록 solution 함수를 작성해주세요.
 *
 * 제한사항
 * 현재 대기목록에는 1개 이상 100개 이하의 문서가 있습니다.
 * 인쇄 작업의 중요도는 1~9로 표현하며 숫자가 클수록 중요하다는 뜻입니다.
 * location은 0 이상 (현재 대기목록에 있는 작업 수 - 1) 이하의 값을 가지며 대기목록의 가장 앞에 있으면 0, 두 번째에 있으면 1로 표현합니다.
 * 입출력 예
 * priorities	        location	return
 * [2, 1, 3, 2]	            2          1
 * [1, 1, 9, 1, 1, 1]	    0	       5            cdefab 순서,
 * 입출력 예 설명
 * 예제 #1
 *
 * 문제에 나온 예와 같습니다.
 *
 * 예제 #2
 *
 * 6개의 문서(A, B, C, D, E, F)가 인쇄 대기목록에 있고 중요도가 1 1 9 1 1 1 이므로 C D E F A B 순으로 인쇄합니다.
 *
 * 풀이...
 * deque를 사용해서 풀어보자.
 * deque에 배열을 넣고, location을 기준으로 인덱스를 넣는다.
 * deque에서 하나씩 빼면서, 빼낸 값보다 큰 값이 있는지 확인한다.
 * 있으면, 빼낸 값을 맨 뒤에 넣고, 없으면 인쇄한다.
 * 인덱스를 확인해서, location과 같으면, 인쇄된 순서를 반환한다.
 *
 */

public class Printer {

    public static void main(String[] args) {
        Printer p = new Printer();

        int printer = p.printer(new int[]{1, 1, 9, 1, 1, 1}, 0);
        System.out.println(printer);
    }

    /**
     * 배열과, 위치를 매개변수로 받으면, 배열 내에 위치에 있는 값이 몇 번째로 인쇄되는지 반환하는 메소드
     * @return
     */
    public int printer(int[] priorities, int location) {
        int newIndex = 1;

        // 알파벳과 정답의 위치를 매핑할 맵
        Map answerMap = new HashMap();

        /**
         * location = 문서의 기존 순서
         * return 할거 : 프린터가 우선순위에 따라 문서를 정리 한 후, 문서가 몇 번째로 출력되는지
         * 필요한 것 : 기존 배열에 원래 순서
         */

        // 초기 순서
        int[] alphabet = new int[priorities.length];

        for (int i = 0; i < priorities.length; i++) {
            alphabet[i] = i;
        }

        /**
         * 빼서 큰거 있으면 뒤로 박아야 하니까 앞 뒤로 빼서 큰거 있으면 뒤로 박고, 없으면 인쇄
         * 데큐를 사용
         */

        // deque에 배열을 넣고, location을 기준으로 인덱스를 넣는다.
        Deque<int[]> alphabets = new ArrayDeque<>();

        // 인덱스와 우선순위를 매핑.. 근데 맵으로 할라고 해도 결국에 빼서 큰걸 찾아야 하기 때문에 비교해서 그냥 배열로 했음
        for (int i = 0; i < priorities.length; i++) {
            alphabets.add(new int[]{alphabet[i], priorities[i]});
        }

        while (!alphabets.isEmpty()) {
            int[] picked = alphabets.poll();
            boolean isMax = true;

            // 빼낸 값보다 큰 값이 있는지 확인한다.
            for (int[] compareAlphabet : alphabets) {

                // 우선순위를 비교 더 큰게 있으면 빼서 맨 뒤로 넣는다.
                if (picked[1] < compareAlphabet[1]) {
                    alphabets.addLast(picked);
                    isMax = false;
                    break;
                }
            }

            // 제일 큰 값이면 정답 맵에 저장
            if (isMax) {
                answerMap.put(picked[0], newIndex++);
                answerMap.forEach((k, v) -> System.out.println(k + " : " + v));
            }
        }

        // 알파벳을 입력하면, 그 순서를 출력
        return (int) answerMap.get(location);
    }
}
