class Solution {
    public List<String> summaryRanges(int[] nums) {
        List <String> list = new ArrayList<String>();
        int n = nums.length;
        for(int i = 0 ; i< n ; i++){

            int start = nums[i];
            while(i+1 < n && nums[i+1] == nums[i]+1){
                i++;
            }
            if( start == nums[i]){
             list.add(String.valueOf(start));
            }else{
                list.add(start + "->" + nums[i]);
            }
        }
        return list;
        
    }
}