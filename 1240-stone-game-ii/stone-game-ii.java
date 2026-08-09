import java.util.Arrays;

class Solution {
    private int[][] dp;
    private int[] suffixSum;
    private int n;

    public int stoneGameII(int[] piles) {
        n = piles.length;
        dp = new int[n + 1][n + 1];
        suffixSum = new int[n];

        // DP table ko -1 se initialize karte hain
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        // Suffix Sum calculate karna (piche se aage tak jodna)
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }

        // Alice index 0 aur M = 1 se start karegi
        return solve(0, 1, piles);
    }

    private int solve(int i, int M, int[] piles) {
        // Base Case: Agar bache hue saare piles ek hi baar me utha sakte hain (1 <= X <= 2M)
        if (i + 2 * M >= n) {
            return suffixSum[i];
        }

        // Memoization check: agar answer pehle se calculated hai
        if (dp[i][M] != -1) {
            return dp[i][M];
        }

        int maxStones = 0;

        // X ki value 1 se lekar 2*M tak check karenge
        for (int X = 1; X <= 2 * M; X++) {
            // Opponent ko index (i + X) se milne wale stones
            int opponentStones = solve(i + X, Math.max(M, X), piles);

            // Alice ke stones = (Bache hue kul stones) - (Opponent ke stones)
            maxStones = Math.max(maxStones, suffixSum[i] - opponentStones);
        }

        // Answer ko DP me store karke return karte hain
        return dp[i][M] = maxStones;
    }
}