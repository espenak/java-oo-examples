public class ImplisittSuper {
    public static void main(String[] args) {
        new Blokk(10);
    }
}

class Bygning {
    Bygning() {
        System.out.println("Bygning");
    }
}
class Bolighus extends Bygning {
    Bolighus() {
        System.out.println("Bolighus");
    }
}
class Blokk extends Bolighus {
    Blokk(int i) {
        System.out.println("Blokk " + i);
    }
}
