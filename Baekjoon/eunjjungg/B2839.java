package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2839 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int goal = Integer.parseInt(st.nextToken());

        if (goal < 5) {
            if (goal % 3 == 0) {
                System.out.println(goal / 3);
            } else {
                System.out.println(-1);
            }
            return;
        }

        int ongoing = goal / 5;
        int left = goal % 5;

        // goal >= 5
        while (left % 3 != 0) {
            if (ongoing == 0) { System.out.println(-1); return; }
            ongoing--;
            left += 5;
        }

        ongoing += left / 3;

        // output
        System.out.println(ongoing);
    }
}
