```java
class Solution {
    public String removeOuterParentheses(String s) {
        char[] charArr = s.toCharArray();
        StringBuffer answer = new StringBuffer();
        int checkCount = 0;

        for (int i = 0; i < charArr.length; i++) {
            if(charArr[i] == '(') {
                if(checkCount > 0) {
                    answer.append(charArr[i]);
                }
                checkCount++;
            } else {
                if(checkCount > 1) {
                    answer.append(charArr[i]);
                }
                checkCount--;
            }
        }
        return answer.toString();
    }
}
```
![img.png](img.png)

if else문을
if else if 문으로 수정

```java
class Solution {
    public String removeOuterParentheses(String s) {
        char[] charArr = s.toCharArray();
        StringBuffer answer = new StringBuffer();
        int checkCount = 0;

        for (int i = 0; i < charArr.length; i++) {
            if(charArr[i] == '(') {
                if(checkCount > 0) {
                    answer.append(charArr[i]);
                }
                checkCount++;
            } else if(charArr[i] == ')'){
                if(checkCount > 1) {
                    answer.append(charArr[i]);
                }
                checkCount--;
            }
        }
        return answer.toString();
    }
}
```
![img_1.png](img_1.png)