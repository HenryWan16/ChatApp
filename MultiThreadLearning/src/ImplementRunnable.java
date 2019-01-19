public class ImplementRunnable implements Runnable{
    public static void main(String[] args) {
        ImplementRunnable implementRunnable = new ImplementRunnable();
        Thread thread = new Thread(implementRunnable);
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("Hello from a thread.");
    }
}
