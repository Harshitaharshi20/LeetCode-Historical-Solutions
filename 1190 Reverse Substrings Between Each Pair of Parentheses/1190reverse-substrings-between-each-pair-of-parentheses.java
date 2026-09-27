class Solution {
    public String reverseParentheses(String s) {
        Stack<Integer> openBracketIndices = new Stack<>();
        StringBuilder res = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                openBracketIndices.push(res.length());
            } else if (c == ')') {
                
                int start = openBracketIndices.pop();
                
                String reversed = new StringBuilder(res.substring(start)).reverse().toString();
                res.replace(start, res.length(), reversed);
            } else {
            
                res.append(c);
            }
        }

        return res.toString();
    }
}