class Solution {
    public int minInsertions(String s) {
        int res = 0;
        int needs = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                needs += 2;
                if (needs % 2 != 0) {
                    res++;
                    needs--;
                }
            } else {
                needs--;
                if (needs < 0) {
                    res++;
                    needs += 2;
                }
            }
        }
        return res + needs;
    }
}