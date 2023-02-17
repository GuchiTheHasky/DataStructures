package guchi.the.hasky.queue;


public class ArrayQueueTest extends AbstractQueueTest {
    @Override
    Queue<String> getQueue() {
        return new ArrayQueue<>();
    }


}
