// http://heim.ifi.uio.no/inf1010/blog/?p=1431
public class SmartTrucks {
    public static int smartTrucks(int crates, int capacity) {
        // If there are zero or less crates, we are done. No trips required.
        if (crates <= 0)
            return 0;

        // If not we take one trip and check how many more we need after
        // subtracting 'capacity' crates.
        return 1 + smartTrucks(crates - capacity, capacity);
    }

    public static void main(String[] args) {
        System.out.println(smartTrucks(400, 4));
        System.out.println(smartTrucks(6, 2));
	}
}
