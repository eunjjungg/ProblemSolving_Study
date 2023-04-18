// Strategy
// 일단 배열을 그냥 정렬을 함
// val arr = mutableListOf<String>("119", "97674223", "1195524421", "113", "1192", "1198", "1190")
// arr.sort() -> [113, 119, 1190, 1192, 1195524421, 1198, 97674223]
// 여기서 걍 startsWith() 돌리면 성능 면에서 안 좋으려나?
// 다른 방법으로는 hash 생성하고
// 1. 전화번호의 첫 번째 글자가 같은 것들에 대해서 아래 동작 반복
// 2. hash의 모든 키에 대해서 startswith가 true라면 바로 false 리턴
// 3. hash의 모든 키에 대해서 startsWith가 false라면 해당 값을 키로 추가
// 근데 이럴거면 hash를 써야되나? 그냥 배열로 처리해도 될 것 같은데

import java.util.Arrays;

class Solution {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);

        boolean answer = true;
        if (phone_book.length == 1) {
            return true;
        }

        for (int i = 0; i < phone_book.length - 1; i++) {
            if (phone_book[i + 1].startsWith(phone_book[i])) {
                return false;
            }
        }


        return answer;
    }
}

// Review
// 해시를 사용 안 하고 문자열 그냥 비교하는게 간단했음.
