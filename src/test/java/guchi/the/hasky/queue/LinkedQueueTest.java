package guchi.the.hasky.queue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.*;

public class LinkedQueueTest extends AbstractQueueTest {

    @Override
    Queue<String> getQueue() {
        return new LinkedQueue<>();
    }
}
