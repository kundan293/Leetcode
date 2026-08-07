class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int  left = 0;
        int  window = 0;
        int ans = n+1;

        for(int right = 0; right< n; right++){
            window += nums[right]; //  huym window ko  store kar rahe hian 

            while(window >= target){
                ans = Math.min(ans ,  right-left+1);
                window = window-nums[left++]; //  pichce se ak  element  ko remove kar raha hain 

            }
        }

        return ans == n+1 ? 0 : ans ;
    }
}