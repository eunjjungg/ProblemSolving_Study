import java.util.*;

class P140108 {
    public int solution(String s) {
        int sameCh = 0;
        int diffCh = 0;
        char ch = 'a';
        int chunck = 0;
        for(int i = 0; i < s.length(); i++) {
            if (sameCh == 0) {
                ch = s.charAt(i);
                chunck++;
            }

            if (ch == s.charAt(i)) { sameCh++; }
            else { diffCh++; }

            if(sameCh == diffCh) {
                sameCh = 0;
                diffCh = 0;
            }
        }
        return chunck;
    }
}