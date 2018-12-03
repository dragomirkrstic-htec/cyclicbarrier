package rs.htec.cyclicbarrier;

public class AggregatorThread implements Runnable {

    private final SharedData sharedData;

    public AggregatorThread(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Override
    public void run() {
        int sum = 0;
        for (Integer number : sharedData.getResult()) {
            System.out.println(Thread.currentThread().getName() + " | Adding " + number);
            sum += number;
        }
        System.out.println(Thread.currentThread().getName() + " | Result: " + sum);
    }
}
