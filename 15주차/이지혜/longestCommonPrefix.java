import java.util.Arrays;

public class longestCommonPrefix {
    public static void main(String[] args) {
        longestCommonPrefix test = new longestCommonPrefix();
        String [] strs = {"flower","flow","flight"};
        String [] strs2 = {"dog","racecar","car"};
        System.out.println(test.longestCommonPrefix(strs));
        System.out.println(test.longestCommonPrefix(strs2));
    }

    public String longestCommonPrefix(String[] strs) {
        if(strs.length==0) return "";
        Arrays.sort(strs);
        int count=0;
        int k = strs[0].length();
        int j = strs[strs.length-1].length();
        for(int i=0;i<strs[0].length() && i<strs[strs.length-1].length() ;i++)
        {
            if(strs[0].charAt(i)==strs[strs.length-1].charAt(i)){
                count++;
            } else {
                break;
            }
        }
        return count==0?"":new String(strs[0].substring(0,count));
    }
}
