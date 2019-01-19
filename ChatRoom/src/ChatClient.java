import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class ChatClient {
    static JFrame chatWindow = new JFrame("Chat Application");
    static JTextArea chatArea = new JTextArea(22, 40);
    static JTextField textField = new JTextField(40);
    static JLabel blankLabel = new JLabel("           ");
    static JButton sendButton = new JButton("Send");
    static BufferedReader in;
    static PrintWriter out;
    static JLabel nameLabel = new JLabel("         ");

    public ChatClient() {
        chatWindow.setLayout(new FlowLayout());
        chatWindow.add(nameLabel);
        chatWindow.add(new JScrollPane(chatArea));
        chatWindow.add(blankLabel);
        chatWindow.add(textField);
        chatWindow.add(sendButton);
        // Click the close button.
        chatWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chatWindow.setSize(475, 500);
        chatWindow.setVisible(true);
        textField.setEditable(false);
        chatArea.setEditable(false);
        sendButton.addActionListener(new Listener());
        textField.addActionListener(new Listener());
    }

    public void startChat() throws Exception {
        String ipAddress = JOptionPane.showInputDialog(
                chatWindow,
                "Enter IP Address:",
                "IP Address Required!!",
                JOptionPane.PLAIN_MESSAGE);
        Socket soc = new Socket(ipAddress, 9806);
        in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        out = new PrintWriter(soc.getOutputStream(), true);
        // Different kinds of message from server, different steps.
        while (true) {
            String str = in.readLine();
            // Show "Name Required!!" on board.
            if (str.equals("NAMEREQUIRED"))
            {
                String name = JOptionPane.showInputDialog(
                        chatWindow,
                        "Enter a unique name:",
                        "Name Required!!",
                        JOptionPane.PLAIN_MESSAGE
                );
                out.println(name);
            }
            // Show "Name Already Exits!!" on board.
            else if(str.equals("NAMEALREADYEXISTS"))
            {
                String name = JOptionPane.showInputDialog(
                        chatWindow,
                        "Enter another name:",
                        "Name Already Exits!!",
                        JOptionPane.WARNING_MESSAGE
                );
                out.println(name);
            }
            // Show "You are logged in as: " ...
            else if (str.startsWith("NAMEACCEPTED"))
            {
                textField.setEditable(true);
                nameLabel.setText("You are logged in as: "+str.substring(12));
            }
            // Show message on the chat board.
            else
            {
                chatArea.append(str + "\n");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        ChatClient client = new ChatClient();
        client.startChat();
    }
}
