import java.util.Arrays;

class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char task : tasks) {
            freq[task - 'A']++;
        }
        
        Arrays.sort(freq);
        int maxFreq = freq[25];
        int countMax = 0;
        for (int i = 25; i >= 0; i--) {
            if (freq[i] == maxFreq) {
                countMax++;
            } else {
                break;
            }
        }
        
        int ans = (maxFreq - 1) * (n + 1) + countMax;
        return Math.max(ans, tasks.length);
    }
}