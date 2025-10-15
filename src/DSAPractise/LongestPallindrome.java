package DSAPractise;

public class LongestPallindrome {

    public String returnMaxLengthPallindrome(String input) {
        int ans = 0;
        String ansString = String.valueOf(input.charAt(0));
        for(int i = 0;i<input.length();i++){
            //odd length palindrome
            String temp = checkAnagram(input,i,i);
            if(temp.length()>ans){
                ansString = temp;
                ans = temp.length();
            }


        }

        for(int i = 0;i<input.length();i++){
            //odd length palindrome
            String temp = checkAnagram(input,i,i+1);
            if(temp.length()>ans){
                ansString = temp;
                ans = temp.length();
            }


        }
        return ansString;
    }

    public static String checkAnagram(String s, int start, int end) {
        while(start>=0 && end<=s.length()-1){
            if(s.charAt(start)==s.charAt(end)){
                start--;
                end++;
            } else{
                break;
            }
        }
        return s.substring(start+1,end);
    }
}
