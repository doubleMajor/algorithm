import java.util.Stack;

public class validParentheses {

    public static void main(String[] args) {
        validParentheses test = new validParentheses();
        String str = "()";
        String str2 = "()[]{}";
        String str3 = "(]";
        test.isValid(str);
        test.isValid(str2);
        test.isValid(str3);
    }

    public boolean isValid(String x) {
        Stack<Character> s = new Stack<>();
        for(int i=0;i<x.length();i++){
            if(x.charAt(i)=='{' ||x.charAt(i)=='['|| x.charAt(i)=='('){
                s.push(x.charAt(i));
            }
            else{
                if(s.isEmpty()) return false;
                if((s.peek()=='(' && x.charAt(i)==')') || (s.peek()=='{' && x.charAt(i)=='}')|| (s.peek()=='[' && x.charAt(i)==']'))  s.pop();
                else return false;
            }
        }
        return (s.isEmpty());
    }
}
