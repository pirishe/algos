package myjavacode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Main
{
    // enable full log
    private static final boolean logOn = false;

    // array of pyramid numbers
    private static Integer[] pyramid1;

    // array of sums of all pairs of pyramid numbers
    private static Integer[] pyramid2Searcher;

    // map sum of a pair of pyramid numbers to actual numbers
    private static final HashMap<Integer, Integer> pyramid2Getter = new HashMap<>();

    // track time taken by every search
    private static final ArrayList<Long> time1 = new ArrayList<>();
    private static final ArrayList<Long> time2 = new ArrayList<>();
    private static final ArrayList<Long> time3 = new ArrayList<>();
    private static final ArrayList<Long> time4 = new ArrayList<>();
    private static final ArrayList<Long> time5 = new ArrayList<>();
    private static final ArrayList<Long> time6 = new ArrayList<>();
    private static final ArrayList<Long> time7 = new ArrayList<>();

    // for every n find index in pyramid1 with max value less than n
    private static int maxIndex1 = 0;
    // for every sum find index in pyramid2Searcher with max value less than this sum
    private static int maxIndex2 = 0;

    private static void log(String text)
    {
        if (Main.logOn) {
            System.out.println(text);
        }
    }

    /**
     * Initialize data that is used in every iteration from 0 to n
     * @param nMax maximum n
     */
    private static void init(int nMax)
    {
        // 1. Make fixed array of pyramid numbers
        ArrayList<Integer> pyramid1 = new ArrayList<>();
        Double p;
        for (int m = 2;;m++) {
            p = (Math.pow(m, 3) - m) / 6;
            if (p > nMax) {
                break;
            }
            pyramid1.add(p.intValue());
        }
        Main.pyramid1 = new Integer[pyramid1.size()];
        pyramid1.toArray(Main.pyramid1);
        pyramid1.clear();
        Arrays.sort(Main.pyramid1);
        log("Pyramid1 size is " + Main.pyramid1.length);

        // 2. Make fixed array of sums of every pair of pyramid numbers
        ArrayList<Integer> pyramid2Searcher = new ArrayList<>();
        for (int i : Main.pyramid1) {
            for (int j : Main.pyramid1) {
                if (i + j <= nMax) {
                    pyramid2Searcher.add(i + j);
                    pyramid2Getter.put(i + j, i);
                }
            }
        }
        HashSet<Integer> uniquePyramid2 = new HashSet<>(pyramid2Searcher);
        Main.pyramid2Searcher = new Integer[uniquePyramid2.size()];
        uniquePyramid2.toArray(Main.pyramid2Searcher);
        uniquePyramid2.clear();
        pyramid2Searcher.clear();
        Arrays.sort(Main.pyramid2Searcher);
        log("pyramid2Searcher size is " + Main.pyramid2Searcher.length);
        log("pyramid2Getter size is " + pyramid2Getter.size());
    }

    private static ArrayList<Integer> getPyramidNumbers(int n)
    {
        long startTime;
        int i, j;
        // don't need to use pyramid numbers more than n =>
        // => define index with max number less than n
        // NOTE: no need to implement self-written binary search here as the time
        // used by this code block is statistically insignificant
        startTime = System.currentTimeMillis();
        int maxIndex1;
        for (maxIndex1 = Main.maxIndex1; maxIndex1 < pyramid1.length; maxIndex1++) {
            if (pyramid1[maxIndex1] > n) {
                Main.maxIndex1 = maxIndex1;
                break;
            }
        }
        time1.add(System.currentTimeMillis() - startTime);

        // don't need to use sums of pyramid numbers more than n =>
        // => define index with max sum of pyramid numbers less than n
        // NOTE: no need to implement self-written binary search here as the time
        // used by this code block is statistically insignificant
        startTime = System.currentTimeMillis();
        int maxIndex2;
        for (maxIndex2 = Main.maxIndex2; maxIndex2 < pyramid2Searcher.length; maxIndex2++) {
            if (pyramid2Searcher[maxIndex2] > n) {
                Main.maxIndex2 = maxIndex2;
                break;
            }
        }
        time2.add(System.currentTimeMillis() - startTime);

        // put function result here
        ArrayList<Integer> ret = new ArrayList<>();
        log("Go to 1x");

        // 3. Check n is a pyramid number
        startTime = System.currentTimeMillis();
        int index = Arrays.binarySearch(pyramid1, 0, maxIndex1, n);
        if (index >= 0) {
            ret.add(pyramid1[index]);
            return ret;
        }
        time3.add(System.currentTimeMillis() - startTime);
        log("Go to 2x");

        // 4. Check n is a sum of 2 numbers
        startTime = System.currentTimeMillis();
        index = Arrays.binarySearch(pyramid2Searcher, 0, maxIndex2, n);
        if (index >= 0) {
            int val = pyramid2Getter.get(pyramid2Searcher[index]);
            ret.add(val);
            ret.add(pyramid2Searcher[index] - val);
            return ret;
        }
        time4.add(System.currentTimeMillis() - startTime);
        log("Go to 3x");

        // 5. Check n is a sum of 3 digits
        startTime = System.currentTimeMillis();
        // pyramid1: m = 1800
        // pyramid2: n = 1_600_000
        // n > m
        // 1: m*log2(n) = 1800 * 20.6 = 37_000
        // 2: n*log2(m) = 1_600_000 * 11 = 17_300_000
        // => iterate over short collection, and binary search long one
        for (int key = 0; key < maxIndex1; key++) {
            i = pyramid1[key];
            index = Arrays.binarySearch(pyramid2Searcher, 0, maxIndex2, n - i);
            if (index >= 0) {
                ret.add(i);
                int val = pyramid2Getter.get(pyramid2Searcher[index]);
                ret.add(val);
                ret.add(pyramid2Searcher[index] - val);
                return ret;
            }
        }
        time5.add(System.currentTimeMillis() - startTime);
        log("Go to 4x");

        // 6. Check n is a sum of 4 numbers
        startTime = System.currentTimeMillis();
        for (int key = 0; key < maxIndex2; key++) {
            i = pyramid2Searcher[key];
            index = Arrays.binarySearch(pyramid2Searcher, 0, maxIndex2, n - i);
            if (index >= 0) {
                int val = pyramid2Getter.get(i);
                ret.add(val);
                ret.add(i - val);
                val = pyramid2Getter.get(pyramid2Searcher[index]);
                ret.add(val);
                ret.add(pyramid2Searcher[index] - val);
                return ret;
            }
        }
        time6.add(System.currentTimeMillis() - startTime);
        log("Go to 5x");

        // 7. Check n is a sum of 5 numbers
        startTime = System.currentTimeMillis();
        for (int key1 = 0; key1 < maxIndex1; key1++) {
            for (int key2 = 0; key2 < maxIndex2; key2++) {
                i = pyramid1[key1];
                j = pyramid2Searcher[key2];
                index = Arrays.binarySearch(pyramid2Searcher, 0, maxIndex2, n - i - j);
                if (index >= 0) {
                    ret.add(i);
                    int val = pyramid2Getter.get(j);
                    ret.add(val);
                    ret.add(j - val);
                    val = pyramid2Getter.get(pyramid2Searcher[index]);
                    ret.add(val);
                    ret.add(pyramid2Searcher[index] - val);
                    return ret;
                }
            }
        }
        time7.add(System.currentTimeMillis() - startTime);

        return ret;
    }

    public static void main(String[] args)
    {
        /*
         * TASK:
         * A pyramidal number is a number of the form (pow(m, 3) − m)/6, for m ≥ 2. Thus the first several
         * pyramidal numbers are 1, 4, 10, 20, 35, 56, 84, 120, and 165. The conjecture is that every integer
         * can be represented by the sum of at most five such pyramidal numbers. Write algorithm to prove
         * this conjecture on all numbers from 1 to 1,000,000,000.
         */
        long startTime = System.currentTimeMillis();
        int nMax = 1_000_000_000;
        init(nMax);
        for (int n = 1; n <= nMax; n++) {
            ArrayList<Integer> ret = getPyramidNumbers(n);
            if (n % 1_000_000 == 0) {
                System.out.println("n = " + n + "; values = " + ret.toString());
                System.out.println("time1 average: " + (time1.stream().mapToDouble(val -> val).sum()));
                System.out.println("time2 average: " + (time2.stream().mapToDouble(val -> val).sum()));
                System.out.println("time3 average: " + (time3.stream().mapToDouble(val -> val).sum()));
                System.out.println("time4 average: " + (time4.stream().mapToDouble(val -> val).sum()));
                System.out.println("time5 average: " + (time5.stream().mapToDouble(val -> val).sum()));
                System.out.println("time6 average: " + (time6.stream().mapToDouble(val -> val).sum()));
                System.out.println("time7 average: " + (time7.stream().mapToDouble(val -> val).sum()));
                System.out.println("maxIndex1: " + maxIndex1);
                System.out.println("maxIndex2: " + maxIndex2);
                System.out.println("Time (ms) : " + (System.currentTimeMillis() - startTime));
            }
            if (ret.size() == 0) {
                break;
            }
        }
        System.out.println("Time (ms) : " + (System.currentTimeMillis() - startTime));
        System.out.println();
    }
}
