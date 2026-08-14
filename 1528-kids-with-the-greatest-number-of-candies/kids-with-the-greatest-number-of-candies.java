class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List <Boolean > result  =  new  ArrayList<Boolean>();

        int maxCandies = 0;
        for (int candi : candies) {
            if (candi > maxCandies) {   // yaha  hum  maxCandies nikalrahe hain 
                maxCandies = candi;
            }
        }

          for(int i = 0 ; i < candies.length; i++){
            if( maxCandies<=candies[i] + extraCandies  ){
                result.add(true);

            }else{
                 result.add(false);
            }
         
          }

          return result;
        
    }
}