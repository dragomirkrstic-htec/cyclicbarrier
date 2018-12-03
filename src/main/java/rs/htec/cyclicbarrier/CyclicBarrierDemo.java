package rs.htec.cyclicbarrier;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CyclicBarrierDemo {

    private static final Integer NUMBER_OF_WORKERS = 10;

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        SharedData sd = new SharedData();
        CyclicBarrier finishBarrier = new CyclicBarrier(2);
        AggregatorThread aggregatorThread = new AggregatorThread(sd, finishBarrier);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER_OF_WORKERS, aggregatorThread);
        List<Thread> workers = Stream.generate(() -> new Thread(new WorkerThread(cyclicBarrier, sd)))
                .limit(NUMBER_OF_WORKERS)
                .collect(Collectors.toList());
        workers.forEach(Thread::start);

        finishBarrier.await();
        System.out.println("All threads are done");
    }
}
