package DSAPractise;

public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        int pointer1 = 0;
        int pointer2 = s.length()-1;

        while(pointer1<pointer2) {
            char x = returnAscii(s.charAt(pointer1));
            char y = returnAscii(s.charAt(pointer2));

            if (x != ' ' && y != ' ') {
                if (x == y) {
                    pointer1++;
                    pointer2--;
                } else {
                    return false;
                }

            }

            if(x ==' ') {
                pointer1++;
            }

            if(y == ' ') {
                pointer2--;
            }
        }

        return true;

    }

    private char returnAscii(char c) {
        if (c >= 'A' && c <= 'Z') {
            return (char) (97 + (c - 'A'));
        } else if (c >= 'a' && c <= 'z') {
            return c;
        } else if (c >= '0' && c <= '9') {
            return c;
        } else {
            return ' ';
        }
    }

}
