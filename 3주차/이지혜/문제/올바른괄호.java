import java.util.Stack;

public class 올바른괄호 {
    public static void main(String[] args) {
        올바른괄호 parenthesis = new 올바른괄호();
        //System.out.println("프로그래머스 올바른괄호 : " + parenthesis.checkParenthesis(")()("));
        //System.out.println("프로그래머스 올바른괄호 : " + parenthesis.checkParenthesis3("())(()"));
        //System.out.println("프로그래머스 올바른괄호 : " + parenthesis.checkParenthesis("(())()"));
        System.out.println("프로그래머스 올바른괄호 : " + parenthesis.checkParenthesis3("()()"));//true

    }

    public boolean checkParenthesis(String s){
        Boolean answer = false;
        int leftCnt = 0;
        int rightCnt = 0;
        for (int i=0; i<s.length();i++) {
            if(i == 0){
                if(s.charAt(i) ==')') break;
            } else if(i == s.length()-1){
                if(s.charAt(i) =='(') {
                    answer = false;
                    break;
                }
            } else{
                if(s.charAt(i) =='(') {
                    leftCnt++;
                }else{
                    rightCnt++;
                }
                if(leftCnt == rightCnt) answer = true;
            }
        }


        return answer;
    }

    public boolean checkParenthesis2(String s){
        boolean answer = true;
        int leftCnt = 0;
        int rightCnt = 0;
        for(int i =0; i<s.length();i++) {
            if(s.charAt(i) ==')'){
                rightCnt++;
                if(i == 0){
                    answer = false;
                    break;
                }
            } else if (s.charAt(i) =='('){
                leftCnt++;
                if(i == s.length()-1){
                    answer = false;
                    break;
                }
            }
        }
        if(leftCnt != rightCnt) answer = false;
        return answer;
    }

    public boolean checkParenthesis3(String s){
        boolean answer = true;
        Stack parenthesis = new Stack();

        for(int i = 1;i<s.length();i++){
            if(parenthesis.size() == 0)  parenthesis.push( s.charAt(i) );
            if((char) parenthesis.peek() == s.charAt(i)) {
                parenthesis.push(s.charAt(i));
            } else {
                if(((char) parenthesis.peek() == '(' )|| (s.charAt(i) == ')')){
                    parenthesis.pop();
                } else if(((char) parenthesis.peek() == ')')|| (s.charAt(i) == '(')){
                    parenthesis.push(s.charAt(i));
                }
            }
        }

        if(!parenthesis.isEmpty()) answer = false;
        return answer;
    }

    public boolean checkParenthesis4(String s){
        boolean answer = true;
        Stack parenthesis = new Stack();

        for(int i = 0;i<s.length();i++){

        }



        if(!parenthesis.isEmpty()) answer = false;
        return answer;
    }
}
