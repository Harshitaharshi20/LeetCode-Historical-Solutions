import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() < 4 || s.length() > 12) {
            return result;
        }
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(String s, int startIndex, List<String> path, List<String> result) {
        if (path.size() == 4) {
            if (startIndex == s.length()) {
                result.add(String.join(".", path));
            }
            return;
        }

        for (int len = 1; len <= 3; len++) {
            if (startIndex + len > s.length()) {
                break; 
            }
            
            String segment = s.substring(startIndex, startIndex + len);
            
            if ((segment.startsWith("0") && segment.length() > 1) || Integer.parseInt(segment) > 255) {
                continue;
            }

            path.add(segment);
            backtrack(s, startIndex + len, path, result);
            path.remove(path.size() - 1); // Backtrack
        }
    }
}