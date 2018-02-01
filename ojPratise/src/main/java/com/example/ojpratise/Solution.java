package com.example.ojpratise;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {
    /*
     * @param n: An integer
     * @return: the nth prime number as description.
     */

    Set<Integer> sets = new HashSet<>();
    Set<Integer> notCaters = new HashSet<>();

    public int nthUglyNumber(int n) {
        // write your code here
        int count = 0;
        int tmp = 1;
        while (true) {
            if (sets.contains(tmp)) {
                count++;
            } else if (judgeNum(tmp)) {
                count++;
                for (int a1 = 1; a1 <= 200; a1++) {
                    sets.add((int) Math.pow(2, a1));
                    sets.add((int) Math.pow(3, a1));
                    sets.add((int) Math.pow(5, a1));

                }
            }
            if (count >= n) {
                return tmp;
            }
            tmp++;
        }
    }

    private boolean judgeNum(int tmp) {
        int[] array = {2, 3, 5};
        boolean res = true;
        while (tmp > 1) {
            int ori = tmp;
            for (int t : array) {
                if (tmp >= t && tmp % t == 0) {
                    tmp = tmp / t;
                }
            }
            if (ori == tmp) {
                res = false;
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int n = scanner.nextInt();
            Solution solution = new Solution();
            System.out.println(solution.nthUglyNumber(n));
        }
    }


}
