import java.io.IOException;

class OnlyOneKingOfPopException extends Exception
{
    OnlyOneKingOfPopException(String msg)
    {
        super(msg);
    }
}

class Person {
    String name;
    String id;

    public Person( String name, String id)
    {
        super();
        this.name = name;
        this.id = id;
        
        System.out.println("Sucessfully added Person: " + this.name);
        
    }
}

class Musician extends Person{
    String status;
    String url = null;

    public Musician( String name, String id, String status, String url ) throws OnlyOneKingOfPopException, IOException
    {
        super( name, id);
        this.status = status;
        this.url = url;
        
        validateStatus();

        if(url != null) {
            Process p = Runtime.getRuntime().exec( "firefox " + url);
        }
        
        
    }

    public void validateStatus() throws OnlyOneKingOfPopException
    {
        if(!super.name.equals( "Michael Jackson" )) {
            throw new OnlyOneKingOfPopException( "There is only one king of pop! Michael Jackson" );
        }
    }
}

class MovieDirector extends Person {
    String country;
    boolean famous;

    public MovieDirector( String name, String id, String country, boolean famous )
    {
        super( name, id );
        this.country = country;
        this.famous = famous;

        if (famous && country.equalsIgnoreCase( "Norway" )) {
            System.out.println(" *** Take care of yourself ***");
        }        
    }
}

class Lecturer extends Person {
    String course;

    public Lecturer( String name, String id, String course )
    {
        super( name, id );
        this.course = course;
    }
}

class SubclassWithException
{

    public static void main (String[] args) throws IOException {

        try
        {

            Lecturer lec = new Lecturer( "Stein Gjessing", "4", "INF1010" );
            Musician mjj = new Musician( "Michael Jackson", "1", "King of Pop", "http://www.youtube.com/watch?v=_Bf6AST5Itw&feature=player_detailpage#t=93s" );
            //Musician rw = new Musician("Robbie Williams", "2", "King of Pop");

            MovieDirector md = new MovieDirector( "Liv Ullmann", "3","Norway", true );

        }
        catch ( OnlyOneKingOfPopException e )
        {
            e.printStackTrace();
        }

    }
}
