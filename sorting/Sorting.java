package myjavacode;

class Sorting {
    static int[] insertion(int[] arr) {
        int n = arr.length;
        int i, j, buf;
        for (i = 1; i < n; i++) {
            j = i;
            while (j > 0 && arr[j] < arr[j - 1]) {
                buf = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = buf;
                j = j - 1;
            }
        }
        return arr;
    }
}