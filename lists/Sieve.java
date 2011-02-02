import java.util.LinkedList;

class SieveElem {
    int value;
    boolean isPrime = true;
    
    SieveElem(int value) {
	this.value = value;
    }
}

class Sieve {
    public static void main(String[] args) {
        LinkedList<SieveElem> sieveList = new LinkedList<SieveElem>();

        int limit = 100;

        for(int i=2; i<=limit; i++) {
            sieveList.add(new SieveElem(i));
        }

        for(int value=2; value<=limit; value++) {

            for(SieveElem se : sieveList) {
                if (se.value <= value) {
                    continue;
                }

                if (se.value % value == 0) {
                    se.isPrime = false;
                }
            }   
        }
        
        for (SieveElem se : sieveList) {
            if (se.isPrime) {
                System.out.println(se.value);                    
            }
        }     
    }
}