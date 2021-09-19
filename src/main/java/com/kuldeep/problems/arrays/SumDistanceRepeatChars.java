package com.kuldeep.problems.arrays;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
class SumDistanceRepeatChars {

    public static void main(String[] args) {
        Solution o = new Solution();

        System.out.println(o.sumDistance("abababcda"));
    }


    static class Solution {
        public int sumDistance(String s) {
            int[] visited = new int[256];
            int[] distance = new int[256];

            // Initially make all the distances
            // and number of characters visited as 0
            for(int i = 0; i < 256; i++)
            {
                visited[i] = 0;
                distance[i] = 0;
            }

            int sum = 0;

            for(int i = 0; i < s.length(); i++)
            {

                // Assuming that all the similar
                // characters are located at index 0

                // Add visited[s[i]]*i to sum
                // and subtract the distances of
                // characters from index 0
                sum += visited[s.charAt(i)] * i -
                        distance[s.charAt(i)];

                // Increment the number of
                // visited characters
                visited[s.charAt(i)]++;

                // Add the distance of the
                // character from position 0
                // i.e., (i - 0) = i
                distance[s.charAt(i)] += i;
            }

            // Return the answer
            return sum;
        }
    }
}
