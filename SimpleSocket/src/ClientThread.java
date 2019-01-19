import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// Handle clients' request in a new thread.
public class ClientThread extends Thread {
    private Socket _socket;

    public ClientThread(Socket socket) {
        this._socket = socket;
    }

    public void run() {
        try {
            PrintWriter out = new PrintWriter(_socket.getOutputStream(), true);
            // After connection, the server sent a message "Hello client" to client with PrintWriter.
            out.println("Hello client!");
            // Get message from client, and keep it into BufferedReader.
            BufferedReader input = new BufferedReader(new InputStreamReader(this._socket.getInputStream()));
            String clientInput = input.readLine();
            System.out.println(clientInput);
            input.close();
            out.close();
            this._socket.close();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
