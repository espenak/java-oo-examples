public class Fakultet {
    public static int fakultet(int n) {
        if (n == 0) return 1;
        return n * fakultet(n - 1);
    }

    public static void main(String[] args) {
        for (int n = 0; n < 10; n++)
            System.out.println(n + "! = " + fakultet(n));
    }
}
