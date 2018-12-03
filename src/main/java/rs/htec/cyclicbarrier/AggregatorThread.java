package rs.htec.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class AggregatorThread implements Runnable {

    private final SharedData sharedData;
    private final CyclicBarrier finishBarrier;

    public AggregatorThread(SharedData sharedData, CyclicBarrier finishBarrier) {
        this.sharedData = sharedData;
        this.finishBarrier = finishBarrier;

    }

    @Override
    public void run() {
        int sum = 0;
        for (Integer number : sharedData.getResult()) {
            System.out.println(Thread.currentThread().getName() + " | Adding " + number);
            sum += number;
        }
        System.out.println(Thread.currentThread().getName() + " | Result: " + sum);
        try {
            finishBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
