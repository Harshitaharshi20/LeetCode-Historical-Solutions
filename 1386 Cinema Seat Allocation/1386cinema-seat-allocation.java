import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            
            int mask = map.getOrDefault(row, 0);
            
            if (col >= 2 && col <= 5) mask |= 1;
            if (col >= 4 && col <= 7) mask |= 2;
            if (col >= 6 && col <= 9) mask |= 4;
            
            map.put(row, mask);
        }
        
        int maxGroups = (n - map.size()) * 2;
        
        for (int mask : map.values()) {
            if ((mask & 5) == 0) {
                maxGroups += 2;
            } else if ((mask & 1) == 0 || (mask & 2) == 0 || (mask & 4) == 0) {
                maxGroups += 1;
            }
        }
        
        return maxGroups;
    }
}