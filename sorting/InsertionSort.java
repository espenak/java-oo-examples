public class InsertionSort {

    public static void main(String[] args) {
        int[] in = {5, 2, 1, 3, 6};
        for (int i : sort(in)) {
            System.out.println(i);
        }

        String[] in2 = {"b", "a", "c"};
        InsertionSortGen<String> s = new InsertionSortGen<String>();
        String[] result = s.sort(in2, new String[in2.length]);
        for (String o : result) {
            System.out.println(o);
        }
    }

    /**
     * Sort <tt>in</tt> using insertion sort.
     *
     * @param in An array which is not changed by the method.
     * @return A newly allocated sorted copy of <tt>in</tt> array.
     */
    static public int[] sort(int[] in) {
        int[] out = new int[in.length];
        for (int inc = 0; inc < in.length; inc++) {
            int current_value = in[inc];
            int correct_index = 0;

            // Move all items one step forward to make room for current_value.
            for (int dec = inc; dec > 0; dec--) {
                if (out[dec - 1] <= current_value) {
                    correct_index = dec;
                    break;
                }
                out[dec] = out[dec - 1];
            }

            // Insert current value at the correct index
            out[correct_index] = current_value;
        }
        return out;
    }
}

class InsertionSortGen<E extends Comparable<E>> {
    public E[] sort(E[] in, E[] out) {
        for (int inc = 0; inc < in.length; inc++) {
            E current_value = in[inc];
            int correct_index = 0;

            // Move all items one step forward to make room for current_value.
            for (int dec = inc; dec > 0; dec--) {
                if (out[dec - 1].compareTo(current_value) <= 0) {
                    correct_index = dec;
                    break;
                }
                out[dec] = out[dec - 1];
            }

            // Insert current value at the correct index
            out[correct_index] = current_value;
        }
        return out;
	}
}