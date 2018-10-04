package myjavacode;

public class Main {
    public static void main(String[] args) {
        int[] l = {6, 3, 10, 2, 5, 8, 0, 7, 1, 4, 9};
        int[] out = Sorting.insertion(l);
        for (int num : out) {
            System.out.println(num);
        }
    }
}
