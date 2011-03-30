public class Springer {
    int antallLoesninger;

    boolean[][] brukt;
    int antallRuter;
    int size;
    int[] xVei;
    int[] yVei;

    public int[] getXPath() {
        return xVei;
    }

    public int[] getYPath() {
        return yVei;
    }

    public Springer(int size) {
        antallLoesninger = 0;
        brukt = new boolean[size][size];
        antallRuter = size * size;
        this.size = size;
        xVei = new int[antallRuter];
        yVei = new int[antallRuter];

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                brukt = new boolean[size][size];
                boolean ruteFunnet = flytt(x, y, 0);

                if (ruteFunnet) {
                    for (int i = 0; i < antallRuter; i++) {
                        System.out.print("(" + xVei[i] + "," + yVei[i] + ")->");
                    }
                    System.out.println();
                    antallLoesninger++;
                    return;
                } else {
                    System.out.println("Det finnes ingen løsning for (x,y)=(" + x + "," + y + ")");
                }
            }
        }
    }

    private boolean flytt(int x, int y, int antFlytt) {
        // Basistilfellet
        if (antFlytt == antallRuter) return true;

        //Avskjæring slik at hesten holder seg innenfor brettets rammer.
        if (x < 0 || x >= size || y < 0 || y >= size) return false;

        //Avskjæring slik at en rute besøkes bare en gang.
        if (brukt[x][y]) return false;

        brukt[x][y] = true;
        xVei[antFlytt] = x;
        yVei[antFlytt] = y;

        if (flytt(x + 1, y + 2, antFlytt + 1)) return true;
        if (flytt(x + 1, y - 2, antFlytt + 1)) return true;
        if (flytt(x - 1, y + 2, antFlytt + 1)) return true;
        if (flytt(x - 1, y - 2, antFlytt + 1)) return true;
        if (flytt(x + 2, y + 1, antFlytt + 1)) return true;
        if (flytt(x + 2, y - 1, antFlytt + 1)) return true;
        if (flytt(x - 2, y + 1, antFlytt + 1)) return true;
        if (flytt(x - 2, y - 1, antFlytt + 1)) return true;

        brukt[x][y] = false;
        return false;
    }

    public static void main(String[] args) {
        Springer s = new Springer(6);
        System.out.println("Antall loesninger: " + s.antallLoesninger);
    }
}
