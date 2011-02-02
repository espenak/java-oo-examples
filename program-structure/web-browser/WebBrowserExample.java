import java.io.IOException;
import java.util.Scanner;
import java.net.URLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


class HttpDownloader {
    /** Downloads a website at the given url.
     *
     * This simply uses the well knows Scanner class, but
     * we use a URLConnection as input to Scanner instead of a 
     * file or System.in.
     * */
    String download(String url) throws IOException {
        URLConnection connection = new URL(url).openConnection();
        String entirePage = "";
        Scanner urlReader = new Scanner(connection.getInputStream());
        while(urlReader.hasNextLine()) {
            String line = urlReader.nextLine();
            entirePage += line + "\n";
        }
        return entirePage;
    }

    /** Get a LinkedList of strings containing all urls on 
     * the website at the given url.
     *
     * You will probably have some problems understanding all this code
     * unless you are familiar with regular expressions. Do not
     * worry about understanding it, and just see how it is used.
     * */
    LinkedList<String> getUrls(String url) throws IOException {
        String entirePage = download(url);
        LinkedList<String> urlList = new LinkedList<String>();
        Pattern p = Pattern.compile("http://([a-zA-Z0-9\\./-_]+)");
        Matcher m = p.matcher(entirePage);
        while (m.find() == true) {
            urlList.add(m.group());
        }
        return urlList;
    }
}


class UserInput {
    String command;
    String argument;
    UserInput(String command, String argument) {
        this.command = command;
        this.argument = argument;
    }
}


class CommandFeed {
    Scanner terminal = new Scanner(System.in);
    boolean hasMoreCommands() {
        return terminal.hasNextLine();
    }
    UserInput nextCommand() {
        String[] line = terminal.nextLine().split(" ");
        String command = line[0];
        String argument = line[1];
        return new UserInput(command, argument);
    }
}



class ReallySimpleTextBasedWebBrowser {
    HttpDownloader browser = new HttpDownloader();
    CommandFeed cli = new CommandFeed();

    ReallySimpleTextBasedWebBrowser() throws IOException {
        printInputPrefix();
        while(cli.hasMoreCommands()) {
            handleUserInput();
            printInputPrefix();
        }
    }

    void printInputPrefix() {
        System.out.println();
        System.out.print("~$ ");
    }

    void downloadAndDisplay(String url) throws IOException {
        String website = browser.download(url);
        System.out.println(website);
    }

    void downloadAndListUrls(String url) throws IOException {
        LinkedList<String> urlList = browser.getUrls(url);
        for(String foundUrl : urlList) {
            System.out.println("- " + foundUrl);
        }
    }

    void handleUserInput() throws IOException {
        UserInput userInput = cli.nextCommand();
        if(userInput.command.equals("download")) {
            downloadAndDisplay(userInput.argument);
        }
        else if(userInput.command.equals("urls")) {
            downloadAndListUrls(userInput.argument);
        }
        else {
            System.err.println("Invalid argument. Usage: <download|urls> <url>");
        }
    }
}


public class WebBrowserExample {
    public static void main(String[] args) throws IOException {
        new ReallySimpleTextBasedWebBrowser();
    }

}
