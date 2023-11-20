package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B10971 {
    public static ArrayList<Integer>[] costMap;
    public static Long minCost = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int size = Integer.parseInt(st.nextToken());
        costMap = new ArrayList[size];

        for (int row = 0; row < size; row++) {
            costMap[row] = new ArrayList<Integer>();
            st = new StringTokenizer(br.readLine(), " ");
            for (int idx = 0; idx < size; idx++) {
                costMap[row].add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int row = 0; row < size; row++) {
            int[] list = new int[size];
            Arrays.fill(list, 1);
            list[row] = 2;
            StringBuilder sb = new StringBuilder();
            sb.append(row);
            dfs(list, row, 0L, row, sb);
        }

        System.out.println(minCost);
    }

    public static int getCost(int start, int end) {
        return costMap[start].get(end);
    }

    public static void dfs(
            int[] visited,
            int current,
            Long cost,
            int init,
            StringBuilder sb
    ) {
        if (getSum(visited) == 2) {
            if (getCost(current, init) == 0) { return; }
            cost += getCost(current, init);
            if (minCost > cost) { minCost = cost; }
            sb.append(init);
            return;
        }

        for (int i = 0; i < visited.length; i++) {
            if (visited[i] == 1 && getCost(current, i) != 0) {
                int[] newArr = visited.clone();
                StringBuilder newSb = new StringBuilder(sb);
                newArr[i] = 0;
                newSb.append(i);
                dfs(newArr, i, cost + getCost(current, i), init, newSb);
            }

        }
    }

    public static int getSum(int[] visited) {
        int sum = 0;
        for(int i = 0; i < visited.length; i++) {
            sum += visited[i];
        }
        return sum;
    }
}
