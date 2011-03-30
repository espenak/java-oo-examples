public class PermutateSingleString {
    private static void singlePerm(String result, char[] input, boolean[] f) {
        if (result.length() == input.length) {
            System.out.print(result + " "); // Print result
            return;
        }

        for (int i = 0; i < input.length; i++) {
            if (!f[i]) { // if value is not in use.
                f[i] = true;
                singlePerm(result + input[i], input, f);
                f[i] = false;
            }
        }
    }

    public static void singlePerm(String input) {
        singlePerm("", input.toCharArray(), new boolean[input.length()]);
    }


    public static void main(String[] args) {
        singlePerm("abc");
        System.out.println();
    }
}
