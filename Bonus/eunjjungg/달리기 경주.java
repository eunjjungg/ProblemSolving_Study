import java.util.*;

public class P178871 {
    public String[] solution(String[] players, String[] callings) {

        HashMap map = new HashMap<String, Integer>(players.length);
        for (int i = 0; i < players.length; i++) {
            map.put(players[i], i);
        }

        for (int i = 0; i < callings.length; i++) {
            String callee = callings[i];
            int rank = (int) map.get(callee);
            players[rank] = players[rank - 1];
            players[rank - 1] = callee;
            map.put(callee, rank - 1);
            map.put(players[rank], rank);
        }

        return players;
    }
}

// m s p k mi
// m s k p mi
// m k s p mi
// m k s mi p
// m k mi s p