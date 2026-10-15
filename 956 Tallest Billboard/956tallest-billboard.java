
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int tallestBillboard(int[] rods) {
        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(0, 0);

        for (int rod : rods) {
            Map<Integer, Integer> nextDp = new HashMap<>(dp);

            for (Map.Entry<Integer, Integer> entry : dp.entrySet()) {
                int diff = entry.getKey();
                int shorter = entry.getValue();
                int newDiff = diff + rod;
                nextDp.put(newDiff,
                    Math.max(nextDp.getOrDefault(newDiff, 0), shorter));

                newDiff = Math.abs(diff - rod);
                int newShorter = shorter + Math.min(diff, rod);

                nextDp.put(newDiff,
                    Math.max(nextDp.getOrDefault(newDiff, 0), newShorter));
            }

            dp = nextDp;
        }

        return dp.getOrDefault(0, 0);
    }
}
