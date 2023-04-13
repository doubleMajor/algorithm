import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 전화번호부에 적힌 전화번호 중, 한 번호가 다른 번호의 접두어인 경우가 있는지 확인하려 합니다.
 * 전화번호가 다음과 같을 경우, 구조대 전화번호는 영석이의 전화번호의 접두사입니다.
 *
 * 구조대 : 119
 * 박준영 : 97 674 223
 * 지영석 : 11 9552 4421
 * 전화번호부에 적힌 전화번호를 담은 배열 phone_book 이 solution 함수의 매개변수로 주어질 때, 어떤 번호가 다른 번호의 접두어인 경우가 있으면 false를 그렇지 않으면 true를 return 하도록 solution 함수를 작성해주세요.
 *
 * 제한 사항
 * phone_book의 길이는 1 이상 1,000,000 이하입니다.
 * 각 전화번호의 길이는 1 이상 20 이하입니다.
 * 같은 전화번호가 중복해서 들어있지 않습니다.
 *
 * 접근 1. 각 번호들을 순회하며, 다른 번호들이 시작부분에 false 아니면 true를 반환 -> 효율성 테스트 실패
 * 접근 2. phoneBook을 문자열로 만든 후, 처음나오는 목표번호를 지운 후, 목표번호를 포함하는 번호가 있는지 찾는다. -> 효울성 테스트 실패
 * 접근 3. 전화번호부를 정렬하고 다음 번호가 이전 번호로 시작하는지 체크한다.
 */
public class PhoneBookList {
    public static void main(String[] args) {
        new PhoneBookList().solution(new String[]{"123", "1234", "12", "1235", "567", "88"});
    }
                // 123,456,789
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        String bookArr = Arrays.stream(phone_book).collect(Collectors.joining(","));

        for (String s : phone_book) {
            String replica = bookArr;
            if (replica.replaceFirst(s,"").contains(","+s)) {
                return false;
            }
        }

        return answer;
    }

    public boolean solution2(String[] phone_book) {
        Arrays.sort(phone_book);

        for (int i = 0; i < phone_book.length - 1; i++) {

            if (phone_book[i+1].startsWith(phone_book[i])) {
                return false;
            }
        }

        return true;
    }
}


