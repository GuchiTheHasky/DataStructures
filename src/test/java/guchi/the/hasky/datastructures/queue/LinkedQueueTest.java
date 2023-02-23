package guchi.the.hasky.datastructures.queue;


public class LinkedQueueTest extends AbstractQueueTest {

    @Override
    Queue<String> getQueue() {
        return new LinkedQueue<>();
    }
}
