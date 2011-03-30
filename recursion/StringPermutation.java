package recursion;

/**
 * Created by IntelliJ IDEA.
 * User: Jon Petter
 * Date: 24.mar.2010
 * Time: 12:05:23
 */
public class StringPermutation {

    public static void generateAll(String a, char[] original) {
        if (a.length() == original.length) {
            System.out.print(a + " ");
            return;
        }
        for (char c : original) {
            generateAll(a + c, original);
        }
    }

    public static void simplePerm(String a, char[] original) {
        if (a.length() == original.length) {
            System.out.print(a + " ");
            return;
        }
        for (char c : original) {
            if (!a.contains("" + c)) {
                simplePerm(a + c, original);
            }
        }
    }

    public static void main(String[] args) {

        String s = "abc";

        StringPermutation.generateAll("", s.toCharArray());
        System.out.println("\n*");
        StringPermutation.simplePerm("", s.toCharArray());
    }
}
