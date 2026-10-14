class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // If the required sum is greater than the maximum possible sum with 'num' digits
        if (sum > 9 * num) {
            return "";
        }
        
        StringBuilder ans = new StringBuilder();
        
        int nines = sum / 9;
        int remainder = sum % 9;
        
        // Append as many 9s as possible
        for (int i = 0; i < nines; i++) {
            ans.append('9');
        }
        
        // Append the remainder if it exists
        if (remainder > 0) {
            ans.append(remainder);
        }
        
        // Fill the rest with zeros to match the exact length 'num'
        while (ans.length() < num) {
            ans.append('0');
        }
        
        return ans.toString();
    }
}