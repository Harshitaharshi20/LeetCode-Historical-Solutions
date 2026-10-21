
class Solution {
    public int minOperations(String s) {
        int n = s.length();
        boolean sorted = true;

        char minChar = s.charAt(0);
        char maxChar = s.charAt(0);

        for (int i = 1; i < n; i++) {
            char c = s.charAt(i);

            if (c < s.charAt(i - 1)) {
                sorted = false;
            }

            minChar = (char) Math.min(minChar, c);
            maxChar = (char) Math.max(maxChar, c);
        }

        // Already sorted
        if (sorted) {
            return 0;
        }

        // Cannot sort an unsorted string of length 2
        if (n == 2) {
            return -1;
        }

        // One operation is enough
        if (s.charAt(0) == minChar ||
            s.charAt(n - 1) == maxChar) {
            return 1;
        }

        // Check whether the minimum or maximum
        // character appears in the middle
        for (int i = 1; i < n - 1; i++) {
            if (s.charAt(i) == minChar ||
                s.charAt(i) == maxChar) {
                return 2;
            }
        }

        // Otherwise, three operations are needed
        return 3;
    }
}
