import java.util.*;

class Solution {
    public int countPaths(int n, int[][] roads) {
        int MOD = 1_000_000_007;
        
        List<long[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            long time = road[2];
            graph[u].add(new long[]{v, time});
            graph[v].add(new long[]{u, time});
        }

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        
        int[] ways = new int[n];
        
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        
        dist[0] = 0;
        ways[0] = 1;
        pq.offer(new long[]{0, 0});

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            int u = (int) curr[0];
            long currentDist = curr[1];

            if (currentDist > dist[u]) {
                continue;
            }

            for (long[] neighbor : graph[u]) {
                int v = (int) neighbor[0];
                long time = neighbor[1];
                long newDist = currentDist + time;

                if (newDist < dist[v]) {
                    
                    dist[v] = newDist;
                    ways[v] = ways[u];
                    pq.offer(new long[]{v, newDist});
                } else if (newDist == dist[v]) {
                    
                    ways[v] = (ways[v] + ways[u]) % MOD;
                }
            }
        }

        return ways[n - 1];
    }
}