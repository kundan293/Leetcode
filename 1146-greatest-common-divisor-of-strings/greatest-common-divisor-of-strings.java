class Solution {
    public String gcdOfStrings(String str1, String str2) {
        // 1. Check karein ki kya dono strings kisi common pattern se bani hain
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }

        // 2. Dono strings ki length ka GCD nikaalein
        int gcdLength = gcd(str1.length(), str2.length());

        // 3. GCD length tak ki substring return karein
        return str1.substring(0, gcdLength);
    }

    // Helper method: Euclidean Algorithm se GCD nikaalne ke liye
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}