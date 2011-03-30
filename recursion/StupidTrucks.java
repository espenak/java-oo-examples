package recursion;

public class StupidTrucks {
    /**
     * Calculates the number of trips a stupid truck needs to move some crates.
     *
     * @param crates   The number of crates to be moved.
     * @param capacity The number of crates that can be moved in one go.
     * @return The number of trips needed to move all the crates.
     */
    public static int stupidTrucks(int crates, int capacity) {
        // If there are zero or less crates, we are done. No trips required.
        if (crates <= 0)
            return 0;

        // If there are 'capacity' or less (and more than 0) crates, we need one
        // trip.
        if (crates <= capacity)
            return 1;

        // If not we divide the stack in two, see how many trips we need for
        // each of the parts, and add that together.
        int half = crates / 2;

        return stupidTrucks(half, capacity) + stupidTrucks(crates - half, capacity);
    }

    public static void main(String[] args) {
        System.out.println(stupidTrucks(400, 4));
        System.out.println(stupidTrucks(6, 2));
	}
}