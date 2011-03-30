// http://heim.ifi.uio.no/inf1010/blog/?p=1431
public class StupidTrucks {
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
