package guchi.the.hasky.datastructures.map;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class MapTests {

    @Test
    public void givenEmptyMapWhenGetByNullKeyThenNullShouldBeReturned() {
        HashMap<String, String> myMap = new HashMap<>();
        assertNull(myMap.get(null));
    }

    @Test
    public void givenEmptyMapWhenGetByNotNullKeyThenNullShouldBeReturned() {
        HashMap<String, String> myMap = new HashMap<>();
        assertNull(myMap.get("null"));
    }

    @Test
    public void givenNullKeyWhenPutOnceThenSizeShouldBeEqualToOneAndValueShouldBeEqualToInserted() {
        HashMap<String, String> myMap = new HashMap<>();
        String key = null;
        String value = "test";
        myMap.put(key, value);
        assertEquals(1, myMap.size());
        assertEquals(myMap.get(null), value);
    }

    @Test
    public void givenNullKeyWhenPutMultipleTimesThenSizeShouldBeEqualToOneAndValueShouldBeOverwrittenWithLast() {
        HashMap<String, String> myMap = new HashMap<>();

        String key = null;
        String firstValue = "test1";
        String secondValue = "test2";
        String thirdValue = "test3";

        myMap.put(key, firstValue);
        myMap.put(key, secondValue);
        myMap.put(key, thirdValue);

        assertEquals(1, myMap.size());
        assertEquals(myMap.get(key), thirdValue);
    }

    @Test
    public void givenNotNullKeyWhenPutThenSizeShouldBeEqualToOneAndValueShouldBeEqualToInserted() {
        HashMap<String, String> myMap = new HashMap<>();
        String key = null;
        String value = "test";

        myMap.put(key, value);

        assertEquals(myMap.size(), 1);
        assertEquals(myMap.get(key), value);
    }

    @Test
    public void givenMultipleNotNullKeysWhenPutThenSizeShouldBeEqualToSizeOfKeysAndGetByKeyReturnsCorrespondingValue() {
        HashMap<String, String> myMap = new HashMap<>();
        String firstKey = "key1";
        String secondKey = "key2";
        String firstValue = "value1";
        String secondValue = "value2";

        myMap.put(firstKey, firstValue);
        myMap.put(secondKey, secondValue);

        assertEquals(2, myMap.size());
        assertEquals(myMap.get(firstKey), firstValue);
        assertEquals(myMap.get(secondKey), secondValue);
    }

    @Test
    public void givenMultipleNodesInSameBucketWhenGetByExistingKeyThenGetByKeyReturnsCorrespondingValue() {
        HashMap<String, String> myMap = new HashMap<>();

        String firstKey = "key2"; //same bucket
        String secondKey = "key-1"; //same bucket
        String thirdKey = "key-10"; //same bucket

        String firstValue = "value2";
        String secondValue = "value3";
        String thirdValue = "value4";

        myMap.put(firstKey, firstValue);
        myMap.put(secondKey, secondValue);
        myMap.put(thirdKey, thirdValue);

        assertEquals(3, myMap.size());
        assertEquals(firstValue, myMap.get(firstKey));
        assertEquals(secondValue, myMap.get(secondKey));
        assertEquals(thirdValue, myMap.get(thirdKey));
    }

    @Test
    public void givenNotExistingKeyWhenGetByKeyThenNullShouldBeReturned() {
        HashMap<String, String> myMap = new HashMap<>();
        myMap.put("existing key", "value");
        assertNull(myMap.get("not existing key"));
    }

    @Test
    public void givenNotNullKeyWhenPutMultipleTimesWithSameKeyThenSizeShouldBeEqualToOneAndValueShouldBeOverwrittenWithLast() {
        HashMap<String, String> myMap = new HashMap<>();
        String key = "key";
        String firstValue = "test1";
        String secondValue = "test2";
        String thirdValue = "test3";

        myMap.put(key, firstValue);
        myMap.put(key, secondValue);
        myMap.put(key, thirdValue);

        assertEquals(1, myMap.size());
        assertEquals(thirdValue, myMap.get(key));
    }

    @Test
    public void givenEmptyMapWhenRemoveByNullKeyThenSizeShouldBeEqualToZero() {
        HashMap<String, String> myMap = new HashMap<>();
        myMap.remove(null);
        assertEquals(0, myMap.size());
    }

    @Test
    public void givenEmptyMapWhenRemoveByNotNullKeyThenSizeShouldBeEqualToZero() {
        HashMap<String, String> myMap = new HashMap<>();
        myMap.remove("key");
        assertEquals(0, myMap.size());
    }

    @Test
    public void givenNotEmptyMapWhenRemoveByNullKeyThenSizeShouldBeEqualToZero() {
        HashMap<String, String> myMap = new HashMap<>();
        myMap.put(null, "value");
        myMap.remove(null);
        assertEquals(0, myMap.size());
    }

    @Test
    public void givenNotEmptyMapWithNotNullKeyWhenPutWithNullKeyAndRemoveByNullKeyThenSizeShouldDecreaseByOne() {
        HashMap<String, String> myMap = new HashMap<>();
        myMap.put(null, "value");
        myMap.put("not null key", "value");

        assertEquals(2, myMap.size());
        myMap.remove(null);
        assertEquals(1, myMap.size());
    }

    @Test
    public void givenEmptyMapWhenRemoveThenSizeShouldBeEqualToZero() {
        HashMap<String, String> myMap = new HashMap<>();
        myMap.remove("key");
        assertEquals(0, myMap.size());
    }

    @Test
    public void givenNotEmptyMapWhenRemoveThenSizeShouldBeEqualToZero() {
        HashMap<String, String> myMap = new HashMap<>();
        myMap.put("key", "value");
        assertEquals(1, myMap.size());
        myMap.remove("key");
        assertEquals(0, myMap.size());
    }

    @Test
    public void givenNotEmptyMapWhenRemoveOneByOneThenSizeShouldDecreaseAfterEachRemovalByOne() {
        HashMap<String, String> myMap = new HashMap<>();

        String firstKey = "key1";
        String secondKey = "key2"; //same bucket
        String thirdKey = "key-1"; //same bucket

        String firstValue = "value1";
        String secondValue = "value2";
        String thirdValue = "value3";

        myMap.put(firstKey, firstValue);
        myMap.put(secondKey, secondValue); //case: remove first node
        myMap.put(thirdKey, thirdValue); //case: remove last node

        assertEquals(3, myMap.size());
        myMap.remove(firstKey);
        assertEquals(2, myMap.size());
        myMap.remove(secondKey);
        assertEquals(1, myMap.size());
        myMap.remove(thirdKey);
        assertEquals(0, myMap.size());
    }

    @Test
    public void givenNotEmptyMapAndObjectsInSameBucketWhenRemoveFirstNodeThenSizeShouldDecreaseByOne() {
        HashMap<String, String> myMap = new HashMap<>();
        String firstKey = "key1";
        String secondKey = "key2"; //same bucket
        String thirdKey = "key-1"; //same bucket
        String fourthKey = "key-10"; //same bucket

        String firstValue = "value1";
        String secondValue = "value2";
        String thirdValue = "value3";
        String fourthValue = "value4";

        myMap.put(firstKey, firstValue);
        myMap.put(secondKey, secondValue);
        myMap.put(thirdKey, thirdValue);
        myMap.put(fourthKey, fourthValue);

        assertEquals(4, myMap.size());

        myMap.remove(secondKey);
        assertEquals(3, myMap.size());
    }

    @Test
    public void givenNotEmptyMapAndObjectsInSameBucketWhenRemoveLastNodeThenSizeShouldDecreaseByOne() {
        HashMap<String, String> myMap = new HashMap<>();

        String firstKey = "key1";
        String secondKey = "key2"; //same bucket
        String thirdKey = "key-1"; //same bucket
        String fourthKey = "key-10"; //same bucket

        String firstValue = "value1";
        String secondValue = "value2";
        String thirdValue = "value3";
        String fourthValue = "value4";

        myMap.put(firstKey, firstValue);
        myMap.put(secondKey, secondValue);
        myMap.put(thirdKey, thirdValue);
        myMap.put(fourthKey, fourthValue);

        assertEquals(4, myMap.size());
        myMap.remove(firstKey);
        assertEquals(3, myMap.size());
    }

    @Test
    public void givenNotEmptyMapAndObjectsInSameBucketWhenRemoveNodeInTheMiddleThenSizeShouldDecreaseByOne() {
        HashMap<String, String> myMap = new HashMap<>();
        String firstKey = "key1";
        String secondKey = "key2"; //same bucket
        String thirdKey = "key-1"; //same bucket
        String fourthKey = "key-10"; //same bucket

        String firstValue = "value1";
        String secondValue = "value2";
        String thirdValue = "value3";
        String fourthValue = "value4";

        myMap.put(firstKey, firstValue);
        myMap.put(secondKey, secondValue);
        myMap.put(thirdKey, thirdValue);
        myMap.put(fourthKey, fourthValue);

        assertEquals(4, myMap.size());

        myMap.remove(thirdKey);
        assertEquals(3, myMap.size());
    }

    @Test
    public void givenEmptyMapNotAndNotExistingKeyWhenRemoveThenSizeShouldNotChange() {
        HashMap<String, String> myMap = new HashMap<>();
        myMap.remove("not existing key");
        assertEquals(0, myMap.size());
    }

    @Test
    public void givenNotEmptyMapNotAndNotExistingKeyWhenRemoveThenSizeShouldNotChange() {
        HashMap<String, String> myMap = new HashMap<>();
        myMap.put("key", "value");
        assertEquals(1, myMap.size());
        myMap.remove("not existing key");
        assertEquals(1, myMap.size());
    }

    @Test
    public void givenEmptyMapWhenContainsNullKeyThenFalseShouldBeReturned() {
        HashMap<String, String> myMap = new HashMap<>();
        assertFalse(myMap.containsKey(null));
    }

    @Test
    public void givenEmptyMapWhenContainsNotNullKeyThenFalseShouldBeReturned() {
        HashMap<String, String> myMap = new HashMap<>();
        assertFalse(myMap.containsKey("key"));
    }

    @Test
    public void givenMapWithExistingNullKeyWhenContainsNullKeyThenTrueShouldBeReturned() {
        HashMap<String, String> myMap = new HashMap<>();
        myMap.put(null, "value");
        assertTrue(myMap.containsKey(null));
    }

    @Test
    public void givenMapWithNotExistingNullKeyWhenContainsNullKeyThenFalseShouldBeReturned() {
        HashMap<String, String> myMap = new HashMap<>();
        myMap.put("key", "value");
        assertFalse(myMap.containsKey(null));
    }

    @Test
    public void givenNotExistingKeyWhenContainsKeyThenFalseShouldBeReturned() {
        HashMap<String, String> myMap = new HashMap<>();
        myMap.put("key", "value");
        assertTrue(myMap.containsKey("key"));
    }

    @Test
    public void givenExistingKeyWhenContainsKeyThenTrueShouldBeReturned() {
        HashMap<String, String> myMap = new HashMap<>();
        myMap.put("key", "value");
        assertFalse(myMap.containsKey("not existing key"));
    }

    @Test
    public void givenMultipleNodesInSameBucketAndExistingKeyWhenContainsByKeyThenTrueShouldBeReturned() {
        HashMap<String, String> myMap = new HashMap<>();
        String firstKey = "key1";
        String secondKey = "key2"; //same bucket
        String thirdKey = "key-1"; //same bucket
        String fourthKey = "key-10"; //same bucket

        String firstValue = "value1";
        String secondValue = "value2";
        String thirdValue = "value3";
        String fourthValue = "value4";

        myMap.put(firstKey, firstValue);
        myMap.put(secondKey, secondValue);
        myMap.put(thirdKey, thirdValue);
        myMap.put(fourthKey, fourthValue);

        assertTrue(myMap.containsKey(firstKey));
        assertTrue(myMap.containsKey(secondKey));
        assertTrue(myMap.containsKey(thirdKey));
        assertTrue(myMap.containsKey(fourthKey));
    }

    @Test
    public void givenMultipleNodesInSameBucketAndNotExistingKeyWithHashLeadingToSameBucketWhenContainsByKeyThenFalseShouldBeReturned() {
        HashMap<String, String> myMap = new HashMap<>();
        String firstKey = "key1";
        String secondKey = "key2"; //same bucket
        String thirdKey = "key-1"; //same bucket
        String fourthKey = "key-10"; //same bucket
        String notExistingKeyWithHashLeadingToSameBucket = "key+12";

        String firstValue = "value1";
        String secondValue = "value2";
        String thirdValue = "value3";
        String fourthValue = "value4";

        myMap.put(firstKey, firstValue);
        myMap.put(secondKey, secondValue);
        myMap.put(thirdKey, thirdValue);
        myMap.put(fourthKey, fourthValue);

        assertTrue(myMap.containsKey(firstKey));
        assertTrue(myMap.containsKey(secondKey));
        assertTrue(myMap.containsKey(thirdKey));
        assertTrue(myMap.containsKey(fourthKey));
        assertFalse(myMap.containsKey(notExistingKeyWithHashLeadingToSameBucket));
    }

    @Test
    public void givenEmptyMapWhenIteratorNextThenNoSuchElementExceptionShouldBeRaised() {
        Throwable thrown = assertThrows(NoSuchElementException.class, () -> {
            HashMap<String, String> testMap = new HashMap<>();
            Iterator<HashMap.Entry<String, String>> iterator = testMap.iterator();
            iterator.next();
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    public void givenIteratorWhenNextAfterLastElementThenNoSuchElementExceptionShouldBeRaised() {
        HashMap<String, String> map = new HashMap<>();
        String key = "key";
        String value = "value";
        map.put(key, value);

        Iterator<HashMap.Entry<String, String>> iterator = map.iterator();

        HashMap.Entry<String, String> entry = iterator.next();
        assertEquals(key, entry.getKey());
        assertEquals(value, entry.getValue());

        Throwable thrown = assertThrows(NoSuchElementException.class, iterator::next);
        assertNotNull(thrown.getMessage());
    }

    @Test
    public void givenIteratorWhenNextThenShouldReturnNextValue() {
        HashMap<String, String> myMap = new HashMap<>();
        String keyQ = "0";
        String keyW = "key1";
        String keyE = "key2";
        String keyR = "key-1";
        String keyT = "key-10";
        String keyY = "key-100";
        String keyZ = "1000";

        String keyQValue = "keyQValue";
        String keyWValue = "keyWValue";
        String keyEValue = "keyEValue";
        String keyRValue = "keyRValue";
        String keyTValue = "keyTValue";
        String keyYValue = "keyYValue";
        String keyZValue = "keyZValue";

        myMap.put(keyQ, keyQValue);
        myMap.put(keyW, keyWValue);
        myMap.put(keyE, keyEValue);
        myMap.put(keyR, keyRValue);
        myMap.put(keyT, keyTValue);
        myMap.put(keyY, keyYValue);
        myMap.put(keyZ, keyZValue);

        Iterator<HashMap.Entry<String, String>> iterator = myMap.iterator();

//        HashMap.Entry<String, String> resultQ = iterator.next();
//        HashMap.Entry<String, String> resultW = iterator.next();
//        HashMap.Entry<String, String> resultE = iterator.next();
//        HashMap.Entry<String, String> resultR = iterator.next();
//        HashMap.Entry<String, String> resultT = iterator.next();
//        HashMap.Entry<String, String> resultY = iterator.next();
//        HashMap.Entry<String, String> resultZ = iterator.next();
        HashMap.Entry<String, String> resultR = iterator.next();
        HashMap.Entry<String, String> resultZ = iterator.next();
        HashMap.Entry<String, String> resultT = iterator.next();
        HashMap.Entry<String, String> resultQ = iterator.next();
        HashMap.Entry<String, String> resultW = iterator.next();
        HashMap.Entry<String, String> resultE = iterator.next();
        HashMap.Entry<String, String> resultY = iterator.next();


        assertEquals(keyZ, resultZ.getKey());
        assertEquals(keyZValue, resultZ.getValue());

        assertEquals(keyQ, resultQ.getKey());
        assertEquals(keyQValue, resultQ.getValue());

        assertEquals(keyW, resultW.getKey());
        assertEquals(keyWValue, resultW.getValue());

        assertEquals(keyE, resultE.getKey());
        assertEquals(keyEValue, resultE.getValue());

        assertEquals(keyR, resultR.getKey());
        assertEquals(keyRValue, resultR.getValue());

        assertEquals(keyT, resultT.getKey());
        assertEquals(keyTValue, resultT.getValue());

        assertEquals(keyY, resultY.getKey());
        assertEquals(keyYValue, resultY.getValue());
    }

    @Test
    public void givenEmptyMapWhenIteratorHasNextThenShouldReturnFalse() {
        HashMap<String, String> map = new HashMap<>();
        assertFalse(map.iterator().hasNext());
    }

    @Test
    public void givenNotEmptyMapWhenIteratorHasNextThenShouldReturnTrue() {
        HashMap<String, String> map = new HashMap<>();
        map.put("key", "value");
        Iterator<HashMap.Entry<String, String>> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());

        map.remove("key");
        assertFalse(iterator.hasNext());

        map.put("key", "value");
        assertTrue(iterator.hasNext());
    }

    @Test
    public void givenMapWithTwoElementsWhenIteratorNextThenIteratorHasNextShouldReturnFalse() {
        HashMap<String, String> map = new HashMap<>();
        map.put("key", "value");
        map.put("key2", "value");
        Iterator<HashMap.Entry<String, String>> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
    }

    @Test
    public void givenMapWithOneElementWhenIteratorNextThenIteratorHasNextShouldReturnFalse() {
        HashMap<String, String> map = new HashMap<>();
        map.put("key", "value");
        Iterator<HashMap.Entry<String, String>> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void givenEmptyMapWhenIteratorRemoveThenNoSuchElementExceptionShouldBeRaised() {
        HashMap<String, String> map = new HashMap<>();
        Iterator<HashMap.Entry<String, String>> iterator = map.iterator();
        Throwable thrown = assertThrows(NoSuchElementException.class, iterator::next);
        assertNotNull(thrown.getMessage());
    }

    @Test
    public void givenIteratorWhenRemoveCalledWithoutNextThenIllegalStateExceptionShouldBeRaised() {
        HashMap<String, String> map = new HashMap<>();
        map.put("key", "value");
        assertEquals(1, map.size());
        Iterator<HashMap.Entry<String, String>> iterator = map.iterator();
        Throwable thrown = assertThrows(NoSuchElementException.class, iterator::remove);
        assertNotNull(thrown.getMessage());
    }

    @Test
    public void givenIteratorWhenRemoveCalledAfterNextThenSizeShouldBeDecreasedByOneAndMapShouldNotContainKey() {
        HashMap<String, String> map = new HashMap<>();
        String key = "key";
        map.put(key, "value");
        assertEquals(1, map.size());
        Iterator<HashMap.Entry<String, String>> iterator = map.iterator();
        iterator.next();
        iterator.remove();

        assertEquals(0, map.size());
        assertFalse(map.containsKey(key));
    }
}
