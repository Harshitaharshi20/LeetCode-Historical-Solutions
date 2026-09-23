class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalSurplus = 0;
        int currentSurplus = 0;
        int startStation = 0;
        
        for (int i = 0; i < gas.length; i++) {
            int netGas = gas[i] - cost[i];
            
            totalSurplus += netGas;
            currentSurplus += netGas;
            
            if (currentSurplus < 0) {
                currentSurplus = 0;
                startStation = i + 1;
            }
        }
        
        return totalSurplus >= 0 ? startStation : -1;
    }
}