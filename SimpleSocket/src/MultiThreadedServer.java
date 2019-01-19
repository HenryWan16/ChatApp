import java.net.*;
import java.io.*;

public class MultiThreadedServer {
    public static void main(String [] args)
    {
        try
        {
            ServerSocket serverSocket = new ServerSocket(9090);
            boolean stop = false;
            while(!stop)
            {
                System.out.println("Waiting for clients...");
                // handle the transactions in the new request.
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client is connected.");
                // waiting and accept new request with a client thread.
                ClientThread clientThread = new ClientThread(clientSocket);
                clientThread.start();
            }
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
    }
}
