import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ChatServer {
    static ArrayList<String> userNames = new ArrayList<String>();
    static ArrayList<PrintWriter> printWriters = new ArrayList<PrintWriter>();

    public static void main(String[] args) throws Exception {
        System.out.println("Waiting for clients...");
        ServerSocket ss = new ServerSocket(9806);
        while (true) {
            Socket soc = ss.accept();
            System.out.println("Connection established");
            ConversationHandler handler = new ConversationHandler(soc);
            handler.start();
        }
    }
}
