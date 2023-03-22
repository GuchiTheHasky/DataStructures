package guchi.the.hasky.datastructures.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayListTest extends AbstractListTest {

    List<String> getList() {
        return new ArrayList<>();
    }

    @Test
    public void testInnerArrayGrowInitCapacityZero() {
        ArrayList<Object> numbersList = new ArrayList<>(0);
        numbersList.add(1);
        numbersList.add(2);
        numbersList.add(3);
        numbersList.add(4);
        numbersList.add(5);
        numbersList.add(6);
        numbersList.add(7);
        numbersList.add(8);
        numbersList.add(9);
        numbersList.add(10);

        int actualLength = numbersList.getArray().length;
        assertEquals(15, actualLength);
    }

    @Test
    public void testInnerArrayGrowInitCapacityOne() {
        ArrayList<Object> numbersList = new ArrayList<>(1);
        numbersList.add(1);
        numbersList.add(2);
        numbersList.add(3);
        numbersList.add(4);
        numbersList.add(5);
        numbersList.add(6);
        numbersList.add(7);
        numbersList.add(8);
        numbersList.add(9);
        numbersList.add(10);

        int actualLength = numbersList.getArray().length;
        assertEquals(15, actualLength);
    }

    @Test
    public void testInnerArrayGrowInitCapacityMoreThanOne() {
        ArrayList<Object> numbersList = new ArrayList<>(5);
        numbersList.add(1);
        numbersList.add(2);
        numbersList.add(3);
        numbersList.add(4);
        numbersList.add(5);
        numbersList.add(6);
        numbersList.add(7);
        numbersList.add(8);
        numbersList.add(9);
        numbersList.add(10);

        int actualLength = numbersList.getArray().length;
        assertEquals(15, actualLength);
    }

    @Test
    public void testInnerArrayGrowInitCapacityLessThanZero() {
        Throwable thrown = assertThrows(IndexOutOfBoundsException.class, () -> {
            ArrayList<Integer> numbersList = new ArrayList<>(-1);
        });
        assertNotNull(thrown.getMessage());
    }
}
