public class 분수의덧셈 {
        public int gcd(int num1, int num2) {
            if(num1 % num2 == 0) {
                return num2;
            }
            return gcd(num2, (num1 % num2));
        }

        public int[] solution(int numer1, int denom1, int numer2, int denom2) {
            int[] answer = new int[2];
            int numer = (numer1*denom2) + (numer2*denom1);
            int denom = denom1 * denom2;

            //기약분수로 만들기  (방법 : 분모와 분자를 최대공약수로 나누기) -> 구해야하는것 최대공약수
            int numGcd;
            if(numer > denom){
                numGcd = gcd(numer, denom);
            } else {
                numGcd = gcd(denom, numer);
            }

            answer[0] = numer / numGcd;
            answer[1] = denom / numGcd;
            return answer;
        }
}
