class Solution {
    public int minimumRefill(int[] plants, int capacityA, int capacityB) {
        int n = plants.length;
        int i = 0;
        int j = n - 1;
        int waterA = capacityA;
        int waterB = capacityB;
        int refills = 0;
        while (i < j) {
            if (waterA < plants[i]) {
                refills++;
                waterA = capacityA; // Refill
            }
            waterA -= plants[i];
            i++;
            
            if (waterB < plants[j]) {
                refills++;
                waterB = capacityB; // Refill
            }
            waterB -= plants[j];
            j--;
        }
        
        if (i == j) {
            if (Math.max(waterA, waterB) < plants[i]) {
                refills++;
            }
        }
        
        return refills;
    }
}