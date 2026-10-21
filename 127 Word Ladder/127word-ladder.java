import java.util.*;

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        
        int level = 1;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                String currentWord = queue.poll();
                
                if (currentWord.equals(endWord)) {
                    return level;
                }
                
                // Try changing each character from 'a' to 'z'
                char[] chars = currentWord.toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    char originalChar = chars[j];
                    
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar) continue;
                        
                        chars[j] = c;
                        String transformed = new String(chars);
                        
                        if (wordSet.contains(transformed)) {
                            queue.offer(transformed);
                            wordSet.remove(transformed); // Mark as visited
                        }
                    }
                    chars[j] = originalChar; // Restore original character
                }
            }
            level++;
        }
        
        return 0;
    }
}