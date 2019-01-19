// Pay attention that extending Thread class is not a good OO practice.
// It will be better to implement Runnable interface.
public class ExtendThread extends Thread{
    public ExtendThread(String threadName) {
        this.setName(threadName);
    }

    @Override
    // Set the thread name and print until 99
    public void run() {
        int clientNumber = 1;
        while (clientNumber != 100) {
            System.out.println(this.getName() + " sent data to clients." + clientNumber);
            clientNumber++;
        }
    }

    public static void main(String[] args) {
        ExtendThread extendThread = new ExtendThread("Server1");
        extendThread.start();
    }
}
