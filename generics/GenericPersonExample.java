
class GenericPerson<IdType> {
    private IdType id;
    private String name;

    public GenericPerson(IdType id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return id + ": " + name;
    }
}


public class GenericPersonExample {
    public static void main(String[] args) {
        GenericPerson<Integer> superman = new GenericPerson<Integer>(
                10, "Superman");
        System.out.println(superman);
    }
}
