class PersonListe {
    Person forstePerson;

    void settInnPersonEtterRang(Person inn) {
        if (forstePerson == null) {
            forstePerson = inn;
            return;
        } else if(inn.rang < forstePerson.rang) {
            inn.nestePerson = forstePerson;
            forstePerson = inn;
            return;
        }

        for (Person p = forstePerson; ; p = p.nestePerson) {
            if (p.nestePerson == null) {
                p.nestePerson = inn;
                break;
            } else if(inn.rang < p.nestePerson.rang) {
                inn.nestePerson = p.nestePerson;
                p.nestePerson = inn;
                break;
            }
        }
    }

    void print() {
        for (Person p = forstePerson; p != null;) {
            System.out.printf("%-10s (%d)%n", p.navn, p.rang);
            p = p.nestePerson;
        }
    }
}


public class LinkedListWithoutHeadOrTail {
    public static void main(String[] args) {
        PersonListe l = new PersonListe();
        l.settInnPersonEtterRang(new Person("Superman", 10));
        l.settInnPersonEtterRang(new Person("Spiderman", 11));
        l.settInnPersonEtterRang(new Person("Batman", 30));
        l.print();
    }
}
