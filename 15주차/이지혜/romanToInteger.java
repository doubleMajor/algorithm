import java.util.HashMap;

public class romanToInteger {

    public static void main(String[] args) {
        romanToInteger test = new romanToInteger();
        String s = "III";
        String s2 = "LVIII";
        String s3 = "MCMXCIV";

        test.romanToInt(s);
        test.romanToInt(s2);
        test.romanToInt(s3);

    }

    public int romanToInt(String s) {
        if (s == null || s.length() == 0)
            return -1;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int len = s.length(), result = map.get(s.charAt(len - 1));
        // 작은 수가 큰 수 보다 오른쪽에 있으면 더해주고 왼쪽에 있으면 빼주면 됨.
        for (int i = len - 2; i >= 0; i--) {
            if (map.get(s.charAt(i)) >= map.get(s.charAt(i + 1)))
                result += map.get(s.charAt(i));
            else
                result -= map.get(s.charAt(i));
        }
        return result;
    }
}
