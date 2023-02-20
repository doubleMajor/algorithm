import java.util.HashMap;
import java.util.Map;

public class HappyNumber {

    public static void main(String[] args) {
        HappyNumber happynum = new HappyNumber();
        System.out.println("해피넘버이다 : "+happynum.checkHappyNum(7));

    }

    public boolean checkHappyNum(int n){
        boolean flag = false;
        int happynum = n;
        Map<Integer,Boolean> nums = new HashMap<>();
        nums.put(happynum,true);
        if(happynum == 1) flag = true;
        while(happynum != 1){
            if(happynum<=9){
                happynum = happynum * happynum;
            }else{
                int temp = 0;
                for(int i=0; i<String.valueOf(happynum).length();i++){
                    temp += Integer.parseInt(String.valueOf(String.valueOf(happynum).charAt(i)))*Integer.parseInt(String.valueOf(String.valueOf(happynum).charAt(i)));
                }
                happynum = temp;
            }

            if (happynum == 1) {
                flag = true;
                break;
            } else if(nums.size()>0){
                if(nums.containsKey(happynum)) break;
            }
            nums.put(happynum,true);
        }
        return flag;
    }
}
