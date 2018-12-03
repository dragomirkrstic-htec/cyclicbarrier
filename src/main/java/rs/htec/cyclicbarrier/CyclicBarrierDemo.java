package rs.htec.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    private static final Integer NUMBER_OF_WORKERS = 10;

    public static void main(String[] args) throws InterruptedException {
        SharedData sd = new SharedData();
        AggregatorThread aggregatorThread = new AggregatorThread(sd);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER_OF_WORKERS, aggregatorThread);
        for (int i = 0; i < NUMBER_OF_WORKERS; i++) {
            Thread t = new Thread(new WorkerThread(cyclicBarrier, sd));
            t.start();
        }
    }
}
