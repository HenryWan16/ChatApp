import java.io.*;
import java.net.Socket;

public class ConversationHandler extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String name;
    private PrintWriter pw;
    static FileWriter fw;
    static BufferedWriter bw;

    public ConversationHandler(Socket socket) throws IOException {
        this.socket = socket;
        fw = new FileWriter("C:\\Users\\Abhay\\Desktop\\ChatServer-Logs.txt",true);
        bw = new BufferedWriter(fw);
        pw = new PrintWriter(bw,true);
    }

    public void run() {
        try {
            // Get infor from socket input stream.
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Sending socket output stream.
            out = new PrintWriter(socket.getOutputStream(), true);
            int count = 0;
            while (true) {
                if(count > 0)
                {
                    out.println("NAMEALREADYEXISTS");
                }
                else
                {
                    out.println("NAMEREQUIRED");
                }
                name = in.readLine();
                if (name == null)
                {
                    return;
                }
                if (!ChatServer.userNames.contains(name))
                {
                    ChatServer.userNames.add(name);
                    break;
                }
                count++;
            }
            out.println("NAMEACCEPTED"+name);
            ChatServer.printWriters.add(out);
            while (true) {
                String message = in.readLine();
                if (message == null)
                {
                    return;
                }
                // Send information "Name:message" to all the clients.
                pw.println(name + ": " + message);
                for (PrintWriter writer : ChatServer.printWriters) {
                    writer.println(name + ": " + message);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
