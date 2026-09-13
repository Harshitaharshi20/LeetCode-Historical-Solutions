import java.util.ArrayList;
import java.util.List;

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (img1[r][c] == 1) list1.add(r * 100 + c);
                if (img2[r][c] == 1) list2.add(r * 100 + c);
            }
        }
        
        int[] shiftCount = new int[10000]; 
        int maxOverlap = 0;
        
        for (int p1 : list1) {
            for (int p2 : list2) {
                int r1 = p1 / 100, c1 = p1 % 100;
                int r2 = p2 / 100, c2 = p2 % 100;
                
                int shift = (r2 - r1 + 30) * 100 + (c2 - c1 + 30);
                
                shiftCount[shift]++;
                maxOverlap = Math.max(maxOverlap, shiftCount[shift]);
            }
        }
        
        return maxOverlap;
    }
}