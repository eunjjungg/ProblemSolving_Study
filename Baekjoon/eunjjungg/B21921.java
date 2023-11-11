package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B21921 {

    public static ArrayList<Integer> arr;
    public static int X;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        arr = new ArrayList<Integer>();
        Long max = Long.MIN_VALUE;
        int duplicated = 0;

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }

        max = getXSum(X - 1);
        duplicated = 1;

        Long memo = max;

        for (int i = X; i < N; i++) {
            memo = getFastXSum(memo, i - X, i);
            if (memo > max) {
                max = memo;
                duplicated = 1;
            } else if (memo.equals(max)) {
                duplicated++;
            }
        }

        if (max == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(max);
            System.out.println(duplicated);
        }
    }

    public static Long getXSum(int idx) {
        Long answer = 0L;
        for (int i = idx - X + 1; i <= idx; i++) {
            answer += arr.get(i);
        }
        return answer;
    }

    public static Long getFastXSum(Long sum, int minus, int plus) {
        Long answer = sum;
        answer -= arr.get(minus);
        answer += arr.get(plus);
        return answer;
    }
}
