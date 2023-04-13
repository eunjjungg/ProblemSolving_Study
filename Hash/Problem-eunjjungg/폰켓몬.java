// Strategy
// N개의 배열 중 폰켓몬 종류를 a라고 할 때
// a <= N/2 라면 a 리턴
// a > N/2 라면 N/2 리턴

import java.util.HashMap;

class Solution {
    public int solution(int[] nums) {
        HashMap <Integer, Integer> hMap = new HashMap<Integer, Integer>();
        for(int i=0; i< nums.length; i++) {
            hMap.put(nums[i], 0);
        }
        int pickSize = nums.length / 2;

        if (hMap.size() <= pickSize) {
            return hMap.size();
        } else {
            return pickSize;
        }
    }
}

// Review
// 그대로 풀었음
// 대신 종류 세는 걸 해시맵을 사용해서 풀었음. 종류가 필요할 때 Hash or Set 사용하면 될듯.
