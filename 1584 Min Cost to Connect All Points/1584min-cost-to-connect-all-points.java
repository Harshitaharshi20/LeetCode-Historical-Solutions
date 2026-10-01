class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int[] minDist = new int[n];
        boolean[] visited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            minDist[i] = Integer.MAX_VALUE;
        }
        
        minDist[0] = 0;
        int totalCost = 0;
        
        for (int i = 0; i < n; i++) {
            int currMin = Integer.MAX_VALUE;
            int currNode = -1;
            
            for (int j = 0; j < n; j++) {
                if (!visited[j] && minDist[j] < currMin) {
                    currMin = minDist[j];
                    currNode = j;
                }
            }
            
            visited[currNode] = true;
            totalCost += currMin;
            
            for (int j = 0; j < n; j++) {
                if (!visited[j]) {
                    int dist = Math.abs(points[currNode][0] - points[j][0]) + 
                               Math.abs(points[currNode][1] - points[j][1]);
                    if (dist < minDist[j]) {
                        minDist[j] = dist;
                    }
                }
            }
        }
        
        return totalCost;
    }
}