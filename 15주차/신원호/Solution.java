import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> romanToIntMap = new HashMap<>();

        romanToIntMap.put('I', 1);
        romanToIntMap.put('V', 5);
        romanToIntMap.put('X', 10);
        romanToIntMap.put('L', 50);
        romanToIntMap.put('C', 100);
        romanToIntMap.put('D', 500);
        romanToIntMap.put('M', 1000);

        int length = s.length();
        int sum = 0;

        for (int i = 0; i < length; i++) {
            int number = romanToIntMap.get(s.charAt(i));

            if (i < length -1 && number < romanToIntMap.get(s.charAt(i+1))) {
                sum -= number;
            } else {
                sum += number;
            }
        }

        return sum;
    }
}

class Solution {
    public String longestCommonPrefix(String[] strs) {
        String candidate = strs[0];

        if (strs.length == 1) {
            return candidate;
        }

        while (candidate.length() != 0) {
            boolean longestCommonPrefixBoolean = true;

            for (int i = 1; i < strs.length; i++) {
                String word = strs[i];

                if (!word.startsWith(candidate)) {
                    candidate = candidate.substring(0, candidate.length() - 1);
                    longestCommonPrefixBoolean = false;

                    break;
                }
            }

            if (longestCommonPrefixBoolean) {
                return candidate;
            }
        }

        return "";
    }
}

import java.util.Stack;

class Solution {
    public static boolean isValid(String s) {
        Stack<Character> parenthesisStack = new Stack<>();
        boolean isValid = false;

        for (int i = 0; i < s.length(); i++) {
            char parenthesis = s.charAt(i);

            if (parenthesis == '(' || parenthesis == '[' || parenthesis == '{') {
                parenthesisStack.push(parenthesis);

            } else if (parenthesis == ')') {
                if (!parenthesisStack.isEmpty()) {
                    if (parenthesisStack.peek() != '(') {
                        return false;
                    } else {
                        isValid = true;
                        parenthesisStack.pop();
                    }
                } else {
                    return false;
                }
            } else if (parenthesis == ']') {

                if (!parenthesisStack.isEmpty()) {
                    if (parenthesisStack.peek() != '[') {
                        return false;
                    } else {
                        isValid = true;
                        parenthesisStack.pop();
                    }
                } else {
                    return false;
                }
            } else if (parenthesis == '}') {
                if (!parenthesisStack.isEmpty()) {
                    if (parenthesisStack.peek() != '{') {
                        return false;
                    } else {
                        isValid = true;
                        parenthesisStack.pop();
                    }
                } else {
                    return false;
                }
            }
        }

        if (!parenthesisStack.isEmpty()) {
            isValid = false;
        }

        return isValid;
    }
}