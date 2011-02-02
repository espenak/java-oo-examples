class LinkedListWithHeadAndTail {
    Person head, tail;

    LinkedListWithHeadAndTail() {
        head = new Person();
        tail = new Person();
        head.nestePerson = tail;
    }

    void settInnEtterRang(Person inn) {
        for (Person p = head; p != tail; p = p.nestePerson) {
            if (p.nestePerson == tail || inn.rang < p.nestePerson.rang) {
                inn.nestePerson = p.nestePerson;
                p.nestePerson = inn;
                return;
            }
        }
    }

    void print() {
        for (Person p = head.nestePerson; p != tail; p = p.nestePerson) {
            System.out.printf("%s (%d)%n", p.navn, p.rang);
        }
    }
}


public class LinkedListWithHeadAndTailExample {
    public static void main(String[] args) {
        LinkedListWithHeadAndTail l = new LinkedListWithHeadAndTail();
        l.settInnEtterRang(new Person("Superman", 8));
        l.settInnEtterRang(new Person("Batman", 12));
        l.settInnEtterRang(new Person("Catwoman", 12 + 2)); // +2 i kjønnskvotering
        l.settInnEtterRang(new Person("Julenissen", 20));
        l.settInnEtterRang(new Person("Michael Jackson", 200000000));
        l.settInnEtterRang(new Person("Britney Spears", -50)); // Hvorfor ikke kjønnskvotering her?
        l.print();
    }
}
