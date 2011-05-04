package sorting;

public class Bubblesort {

    static BubbleGui gui;

    static int[] testArray = {5, 2, 6, 7, 2, 1, 0, 9, 5, 3, 4, 5, 6, 1};
    static int[] testArray2 = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};

    public static void bubblesort(int[] a) {

        boolean continueSorting = true;

        while (continueSorting) {
            continueSorting = false;
            for (int i = 0; i < a.length - 1; i++)
                if (a[i] > a[i + 1]) {
                    swap(a, i, i + 1);
                    continueSorting = true;
                }
        }
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;

        // Hvis vi har gui'et. Oppdater det.
        if (gui != null) gui.swap(i, j);
    }

    public static void main(String[] args) {
        int[] a = testArray;

        gui = new BubbleGui(a);

        bubblesort(a);
        for (int i : a)
            System.out.print(i + " ");
        System.out.println();
    }
}
