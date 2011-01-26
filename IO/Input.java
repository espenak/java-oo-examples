import java.util.Scanner;

public class Input1 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            String name = s.nextLine();
            System.out.println("Name=" + name);
        }
    }
}