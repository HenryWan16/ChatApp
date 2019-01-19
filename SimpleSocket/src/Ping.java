import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;

public class Ping {

    public static void main(String [] args) throws Exception
    {
        try
        {
            String hostAddress = "172.21.122.173";
            InetAddress host = InetAddress.getByName(hostAddress);
            System.out.println(host.isReachable(1000));

            // The process class allows you to execute operating system commands in a subprocess.
            Process p = Runtime.getRuntime().exec("ping " + hostAddress);
            // Read the output of your command using the I mean reader.
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String commandOutput = "";
            boolean isReachable = true;
            // reading output stream of the command
            while ((commandOutput = inputStream.readLine()) != null) {
                //System.out.println(commandOutput);

                if(commandOutput.contains("Destination host unreachable"))
                {
                    isReachable = false;
                    break;
                }

            }

            if(isReachable)
            {
                System.out.println("Host is reachable!");
            }
            else
            {
                System.out.println("Host is not reachable!");
            }



        } catch (Exception e) {
            System.out.println(e.toString());
        }


    }
}
