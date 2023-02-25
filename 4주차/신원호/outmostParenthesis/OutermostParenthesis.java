public class OutermostParenthesis {

    public static void main(String[] args) {
        System.out.println(removeOuterParentheses("(())"));
    }

    public static String removeOuterParentheses(String s) {
        StringBuilder sb = new StringBuilder();

        int length = s.length();
        int sum = 0;
        int start = 0;

        for (int i = 0; i < length; i++) {
            char charAt = s.charAt(i);

            if (charAt == '(') {
                sum++;
            } else {
                sum--;
            }

            if (sum == 0) {
                sb.append(s, start + 1, i);
                start = i + 1;
            }
        }

        return sb.toString();
    }
}

