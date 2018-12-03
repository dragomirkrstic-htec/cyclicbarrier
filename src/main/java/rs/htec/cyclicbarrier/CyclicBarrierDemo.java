package rs.htec.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    private static final Integer NUMBER_OF_WORKERS = 10;

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        SharedData sd = new SharedData();
        CyclicBarrier finishBarrier = new CyclicBarrier(2);
        AggregatorThread aggregatorThread = new AggregatorThread(sd, finishBarrier);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER_OF_WORKERS, aggregatorThread);
        for (int i = 0; i < NUMBER_OF_WORKERS; i++) {
            Thread t = new Thread(new WorkerThread(cyclicBarrier, finishBarrier, sd));
            t.start();
        }
        finishBarrier.await();
        System.out.println("All threads are done");
    }
}
