package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// pt 시 2개 운동기구만 선택 가능
// n개의 운동기구 모두 한 번씩 사용하고 싶음.
// not using 을 선택하고 되도록 2개 하도록
public class B20300 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());;
        List<Long> list = new ArrayList<Long>();
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        for (int i = 0; i < size; i++) {
            list.add(Long.parseLong(st.nextToken()));
        }

        Collections.sort(list);
        long max = -1L;
        if (size % 2 == 1) {
            max = list.remove(size - 1);
            size--;
        }

        int begin = 0;
        int end = size - 1;

        while (begin <= end) {
            long tmp = list.get(begin) + list.get(end);
            if (tmp > max) { max = tmp; }
            begin++;
            end--;
        }

        System.out.println(max);

    }
}
