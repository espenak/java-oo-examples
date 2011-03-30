package recursion;

public class SmartTrucks {
    /**
     * Calculates the number of trips a smart truck needs to move some crates.
     *
     * @param crates   The number of crates to be moved.
     * @param capacity The number of crates that can be moved in one go.
     * @return The number of trips needed to move all the crates.
     */
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