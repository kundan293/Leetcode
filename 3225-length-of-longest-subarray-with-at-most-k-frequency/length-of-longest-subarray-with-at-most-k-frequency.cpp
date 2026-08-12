#include <vector>
#include <unordered_map>
#include <algorithm>
using namespace std;

class Solution {
public:
    int maxSubarrayLength(vector<int>& nums, int k) {
        unordered_map<int, int> freq;
        int left = 0;
        int max_len = 0;
        
        for (int right = 0; right < nums.size(); right++) {
            freq[nums[right]]++;
            
            // Window ko shrink karo jab frequency > k ho
            while (freq[nums[right]] > k) {
                freq[nums[left]]--;
                left++;
            }
            
            max_len = max(max_len, right - left + 1);
        }
        
        return max_len;
    }
};