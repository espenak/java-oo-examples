public class PermutateString {

    private static void perm(String result, char[] input) {
        if (result.length() == input.length) {
            System.out.print(result + " "); // Print result
            return;
        }
        for (char c : input) {
            if(result.indexOf(c) == -1) {
                perm(result + c, input);
            }
        }
    }

    public static void perm(String x) {
        perm("", x.toCharArray());
    }


    public static void main(String[] args) {
        perm("abc");
        System.out.println();
    }
}
