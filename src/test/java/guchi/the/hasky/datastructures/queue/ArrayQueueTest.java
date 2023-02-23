package guchi.the.hasky.datastructures.queue;


public class ArrayQueueTest extends AbstractQueueTest {
    @Override
    Queue<String> getQueue() {
        return new ArrayQueue<>();
    }


}
