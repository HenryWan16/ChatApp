public class MultiThreadSleep implements Runnable{
    private int threadIndex;

    public MultiThreadSleep(int threadIndex) {
        this.threadIndex = threadIndex;
    }

    @Override
    public void run() {
        int clientNumber = 1;
        while(clientNumber != 100)
        {
            System.out.println("Server " + this.threadIndex + " sent data to client: " + clientNumber);
            clientNumber++;
        }
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

    public static void main(String[] args) {
        // TODO code application logic here
        // We can set thread priority like this:
        // thread2.setPriority(Thread.MAX_PRIORITY);
        MultiThreadSleep runnable1 = new MultiThreadSleep(1);
        Thread thread1 = new Thread(runnable1);
        thread1.start();
        MultiThreadSleep runnable2 = new MultiThreadSleep(2);
        Thread thread2 = new Thread(runnable2, "Server2");
        thread2.start();
        MultiThreadSleep runnable3 = new MultiThreadSleep(3);
        runnable3.start();
    }
}
