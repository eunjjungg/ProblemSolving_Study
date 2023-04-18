// Strategy
// Hash에 Participant의 이름으로 키를 생성하고 해당 이름의 참가자가 발견될 때마다 +1을 해줌
// Completion의 배열로 for 돌려서 위에서 만든 hash에서 키로 찾고 그 밸류를 -1 해줌
// 결론적으로 hash 변수에서 value가 1인 값을 찾아서 그 키 값을 리턴함

import java.util.HashMap;

public class P42576 {
    public String solution(String[] participant, String[] completion) {
        String answer = "";

        HashMap<String, Integer> hMap = new HashMap<String, Integer>();
        for(int i=0; i< completion.length; i++) {
            if(hMap.containsKey(completion[i])) {
                hMap.replace(completion[i], hMap.get(completion[i]) + 1);
            } else {
                hMap.put(completion[i], 1);
            }
        }

        for(int i=0; i< participant.length; i++) {
            if(!hMap.containsKey(participant[i]) || hMap.get(participant[i]) == 0) {
                answer = participant[i];
                break;
            }
            hMap.replace(participant[i], hMap.get(participant[i]) - 1);
        }

        return answer;
    }
}

// Review
// ?... 난잡함 코드가...
