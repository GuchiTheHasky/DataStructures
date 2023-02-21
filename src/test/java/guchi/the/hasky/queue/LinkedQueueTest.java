package guchi.the.hasky.queue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class LinkedQueueTest extends AbstractQueueTest {

    @Override
    Queue<String> getQueue() {
        return new LinkedQueue<>();
    }
}
