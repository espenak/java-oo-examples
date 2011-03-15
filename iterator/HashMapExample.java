import java.util.HashMap;
import java.util.Iterator;

public class HashMapExample {

    public static void main(String[] args) {
	
	HashMap<String, String> test = new HashMap<String, String>();

	test.put("King of pop", "Michael Jackson");
	test.put("Prince of pop", "Justin Timberlake");
	test.put("King of rock", "Elvis Presley");

	Iterator it = test.keySet().iterator();

	while(it.hasNext()) {
	    System.out.println(it.next());
	}


	Iterator it2 = test.values().iterator();

	while(it2.hasNext()) {
	    System.out.println(it2.next());
	}
    }
}