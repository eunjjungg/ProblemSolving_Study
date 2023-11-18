package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 5 * 5
// line 3 -> bingo
// 사회자 몇번째 불렀을 때 빙고인지?
public class B2578 {
    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer>[] bingo = new ArrayList[5];
        int count = 0;
        StringTokenizer st;

        for (int row = 0; row < 5; row++) {
            st = new StringTokenizer(br.readLine(), " ");
            bingo[row] = new ArrayList<>(5);
            for (int idx = 0; idx < 5; idx++) {
                bingo[row].add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int row = 0; row < 5; row++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int idx = 0; idx < 5; idx++) {
                int call = Integer.parseInt(st.nextToken());
                Node node = findNode(bingo, call);
                bingo[node.x].set(node.y, -1);
                count += isThereBingo(bingo, node);

                if (count >= 3) {
                    System.out.println(row * 5 + idx + 1);
                    return;
                }
            }
        }

        System.out.println(25);
    }

    // / : (0, 4) (1, 3) (2, 2) (3, 1) (4, 0)
    // \ : (0, 0) (1, 1) (2, 2) (3, 3) (4, 4)
    public static int isThereBingo(List<Integer>[] bingo, Node node) {
        int answer = 0;
        int x = node.x;
        int y = node.y;

        // /
        if (node.x + node.y == 4) {
            boolean flag = true;
            for (int i = 0; i < 5; i++) {
                if(bingo[i].get(4 - i) != -1) { flag = false; break;}
            }
            if (flag) { answer++; }
        }

        // \
        if (node.x == node.y) {
            boolean flag = true;
            for (int i = 0; i < 5; i++) {
                if(bingo[i].get(i) != -1) { flag = false; break;}
            }
            if (flag) { answer++; }
        }

        // 가로
        List<Integer> row = bingo[node.x];
        if (row.get(0) == -1 && row.get(1) == -1 && row.get(2) == -1 &&
                row.get(3) == -1 && row.get(4) == -1) {
            answer++;
        }

        // 세로
        if (bingo[0].get(y) == -1 && bingo[1].get(y) == -1 && bingo[2].get(y) == -1 &&
                bingo[3].get(y) == -1 && bingo[4].get(y) == -1) {
            answer++;
        }

        return answer;
    }

    public static Node findNode(List<Integer>[] bingo, int number) {
        int outer = bingo.length;
        int inner = bingo[0].size();

        for (int row = 0; row < outer; row++) {
            for (int idx = 0; idx < inner; idx++) {
                if (number == bingo[row].get(idx)) {
                    return new Node(row, idx);
                }
            }
        }

        return new Node(-1, -1);
    }
}
