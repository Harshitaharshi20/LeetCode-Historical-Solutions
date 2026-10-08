import java.util.Arrays;
import java.util.Collections;

class Solution {
    public boolean makesquare(int[] matchsticks) {
        if (matchsticks == null || matchsticks.length < 4) {
            return false;
        }

        int sum = 0;
        for (int match : matchsticks) {
            sum += match;
        }

        if (sum % 4 != 0) {
            return false;
        }

        int target = sum / 4;
        Integer[] sticks = new Integer[matchsticks.length];
        for (int i = 0; i < matchsticks.length; i++) {
            sticks[i] = matchsticks[i];
        }
        Arrays.sort(sticks, Collections.reverseOrder());
        if (sticks[0] > target) {
            return false;
        }

        int[] sides = new int[4];
        return dfs(sticks, 0, sides, target);
    }

    private boolean dfs(Integer[] sticks, int index, int[] sides, int target) {
        if (index == sticks.length) {
            return sides[0] == target && sides[1] == target && sides[2] == target && sides[3] == target;
        }

        int current = sticks[index];

        for (int i = 0; i < 4; i++) {
            if (sides[i] + current <= target) {
                sides[i] += current;
                if (dfs(sticks, index + 1, sides, target)) {
                    return true;
                }
                sides[i] -= current;
            }
            if (sides[i] == 0) {
                break;
            }
        }

        return false;
    }
}