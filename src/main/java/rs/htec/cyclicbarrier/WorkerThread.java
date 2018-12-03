package rs.htec.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class WorkerThread implements Runnable {

    private final CyclicBarrier cyclicBarrier;
    private final SharedData sharedData;

    public WorkerThread(CyclicBarrier cyclicBarrier, SharedData sharedData) {
        this.cyclicBarrier = cyclicBarrier;
        this.sharedData = sharedData;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Integer number = random.nextInt(100);
            System.out.println(Thread.currentThread().getName() + " | generated " + number);
            sharedData.getResult().add(number);
        }
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
