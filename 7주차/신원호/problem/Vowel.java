import java.util.HashMap;
import java.util.Map;

/**
 * aeiou 5개의 모음이 주어졌을 때, 이들로 이뤄진 단어가 사전에 몇 번쨰 있는지 반환하라
 * word의 길이는 1 이상 5 이하입니다.
 * word는 알파벳 대문자 'A', 'E', 'I', 'O', 'U'로만 이루어져 있습니다.
 *
 * 접근 1. 깊이 우선 탐색을 통해 모든 경우의 수를 구한다.
 * 각 경우의 수를 맵에 넣는다.
 */
public class Vowel {
    Map<String, Integer> book = new HashMap<>();

    char[] vowels = {'A', 'E', 'I', 'O', 'U'};
    int index = 0;


    public int solution(String word) {
        dfs("", 0);
        return book.get(word);
    }

    public void dfs(String word, int length) {
        book.put(word, index++);

        if (length == 5) {
            return;
        }

        for (int i = 0; i < vowels.length; i++) {
            dfs(word + vowels[i], length+1);
        }
    }
}
