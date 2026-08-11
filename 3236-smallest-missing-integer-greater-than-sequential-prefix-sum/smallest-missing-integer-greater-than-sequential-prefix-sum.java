class Solution {
    public int missingInteger(int[] nums) {
        int n  =  nums.length;
        int ans = nums[0];
    //    Arrays.sort(nums);
       for(int i=1; i< n; i++){
       if (nums[i] == nums[i - 1] + 1) {
                ans += nums[i];
            } else {
                break;
            }
        }

        // Find smallest integer >= sum that is not in nums
        while (contains(nums, ans)) {
            ans++;
        }

        return ans;
    }
 private boolean contains(int[] nums, int ans) {
        for(int n : nums ){
            if(n == ans){
                return  true;
            }
        }
        return  false;
    }
}