public class IndexOutOfBound
{
    public static void main( String[] args )
    {
       try{
            int[] test = new int[5];
            test[6] = 666;
        }catch (IndexOutOfBoundsException e){
            System.out.println("IndexOutOfBoundsException: You try to access" +
                    " element of array of index greater than length of array.");
        }
    }

}
