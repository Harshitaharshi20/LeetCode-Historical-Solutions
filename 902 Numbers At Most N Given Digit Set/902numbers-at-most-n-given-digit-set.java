class Solution {
    public int atMostNGivenDigitSet(String[] digits, int n) {
        String s = String.valueOf(n);
        int k = s.length();
        int count = 0;
        int dLen = digits.length;
        for (int i = 1; i < k; i++) {
            count += Math.pow(dLen, i);
        }
        for (int i = 0; i < k; i++) {
            boolean prefixMatch = false;
            char c = s.charAt(i);

            for (String digit : digits) {
                char d = digit.charAt(0);
                if (d < c) {
                    count += Math.pow(dLen, k - i - 1);
                } else if (d == c) {
                    prefixMatch = true;
                    break;
                }
            }
            if (!prefixMatch) {
                return count;
            }
        }
        return count + 1;
    }
}