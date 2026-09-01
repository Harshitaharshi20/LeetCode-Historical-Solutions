import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        
        int startX = -1, startY = -1;
        int[][] litterPos = new int[10][2];
        int litterCount = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);
                if (c == 'S') {
                    startX = i;
                    startY = j;
                } else if (c == 'L') {
                    litterPos[litterCount][0] = i;
                    litterPos[litterCount][1] = j;
                    litterCount++;
                }
            }
        }
        
        int fullMask = (1 << litterCount) - 1;
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY, 0, energy, 0});
        
        int[][][] bestEnergy = new int[m][n][1 << litterCount];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(bestEnergy[i][j], -1);
            }
        }
        bestEnergy[startX][startY][0] = energy;
        
        int[] dirs = {-1, 0, 1, 0, -1};
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];
            int mask = curr[2];
            int curEnergy = curr[3];
            int moves = curr[4];
            
            if (mask == fullMask) {
                return moves;
            }
            
            for (int d = 0; d < 4; d++) {
                int nx = x + dirs[d];
                int ny = y + dirs[d + 1];
                
                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    char c = classroom[nx].charAt(ny);
                    
                    if (c == 'X') continue; 
                    int nextEnergy = curEnergy - 1;
                    if (nextEnergy < 0) continue; 
                    
                    if (c == 'R') {
                        nextEnergy = energy; 
                    }
                    
                    int nextMask = mask;
                    if (c == 'L') {
                        for (int i = 0; i < litterCount; i++) {
                            if (litterPos[i][0] == nx && litterPos[i][1] == ny) {
                                nextMask |= (1 << i);
                                break;
                            }
                        }
                    }
                    
                    if (nextEnergy > bestEnergy[nx][ny][nextMask]) {
                        bestEnergy[nx][ny][nextMask] = nextEnergy;
                        queue.offer(new int[]{nx, ny, nextMask, nextEnergy, moves + 1});
                    }
                }
            }
        }
        
        return -1; 
    }
}