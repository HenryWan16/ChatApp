/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.*;
import java.io.*;

/**
 *
 * @author catalin
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) //throws Exception
    {
        try
        {
            // Open the port.
            ServerSocket serverSocket = new ServerSocket(9090);
            System.out.println("wainting for clients...");
            boolean stop = false;
            while(!stop) {
                // accept() method blocks the caller until a connection is present.
                // So, it will stay here until a
                // client is connected. Once a connection
                Socket socket = serverSocket.accept();
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println("Hello  client!");
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String clientInput = input.readLine();
                System.out.println(clientInput);
                input.close();
                out.close();
                // Conversation is over.
                socket.close();
            }
            serverSocket.close();
        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }

}
