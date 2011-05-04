package sorting;

public class MergeSort {

    public static void main(String[] args) {
        mergeSort(testArray);
        for (int i : testArray)
            System.out.print(i + " ");
    }

    static int[] testArray = {5, 2, 6, 7, 2, 1, 0, 9, 5, 3, 4, 5, 6, 1};
    static int[] testArray2 = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};

    public static void mergeSort(int[] a) {
        System.arraycopy(mergeSortRek(a), 0, a, 0, a.length);
    }

    // mye array copy...

    private static int[] mergeSortRek(int[] a) {

        // lengde = 0,1 = ferdig sortert
        if (a.length < 2) return a;

        // Finner indeksen til det midterste elementet
        int m = (a.length / 2) - 1;

        int[] b = new int[m + 1];
        int[] c = new int[a.length - (m + 1)];

        // Partisjonerer ved m
        System.arraycopy(a, 0, b, 0, m + 1);
        System.arraycopy(a, m + 1, c, 0, a.length - (m + 1));

        // Kaller mergeSort på de nye delene
        b = mergeSortRek(b);
        c = mergeSortRek(c);

        // Fletter b og c for så å returnere resultatet
        return merge(b, c);
    }

    /**
     * Generell flettemetode
     *
     * @param a og @param b. To sorterte arrays.
     *
     * @return Et sortert array med elementene fra @param a og @param b.
     */
    private static int[] merge(int[] a, int[] b) {

        // Lager nytt array med lengde lik summen av lengden til a og b
        int[] c = new int[a.length + b.length];

        int tellerA = 0, tellerB = 0;
        for (int tellerC = 0; tellerC < c.length; tellerC++) {

            // Hvis ingen elementer igjen fra b,
            // ELLER (flere elementer fra a OG elementet fra a er større enn elementet fra b)
            if ((tellerB >= b.length) || (tellerA < a.length && a[tellerA] < b[tellerB])) {
                // Ta fra a og sett inn i c
                c[tellerC] = a[tellerA++];
            } else {
                // Hvis ikke
                // Ta fra b og sett inn i c
                c[tellerC] = b[tellerB++];
            }
        }
        return c;
    }

    // merge in place

    /**
     * Input: array a[] indexed from 0 to n-1.
     * <p/>
     * m = 1
     * while m <= n do
     * i = 0
     * while i < n-m do
     * merge subarrays a[i..i+m-1] and a[i+m .. min(i+2*m-1,n-1)] in-place.
     * i = i + 2 * m
     * m = m * 2
     */
    public static void mergeInPlace(int[] a) {
        int n = a.length;
        int m = 1;
        while (m <= n) {
            int i = 0;
            while (i < n - m) {
                merge(a, i, m, n);
                i = i + 2 * m;
            }
            m = m * 2;
        }

    }

    private static void merge(int[] a, int i, int m, int n) {
        int u = Math.min(i+2*m-1, n-1);
		//...
	}

}
