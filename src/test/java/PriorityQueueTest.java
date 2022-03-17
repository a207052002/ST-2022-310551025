import java.util.stream.Stream;
import java.util.PriorityQueue;
import java.util.*;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class dummyClass { }

public class PriorityQueueTest {
    static Stream<Arguments> streamProvider() {
        return Stream.of(
                Arguments.of(new int[]{11, 2, 1, 99, 0}, new int[]{0, 1, 2, 11, 99}),
                Arguments.of(new int[]{5, 4, 2, 3}, new int[]{2, 3, 4, 5}),
                Arguments.of(new int[]{1, 2, 1, -3, 66}, new int[]{-3, 1, 1, 2, 66}),
                Arguments.of(new int[]{0, 1, 1, 99, -99}, new int[]{-99, 0, 1, 1, 99}),
                Arguments.of(new int[]{78, 626, -55, -123}, new int[]{-123, -55, 78, 626})
        );
    }

    @ParameterizedTest(name="#{index} - Test with Argument={0},{1}")
    @MethodSource("streamProvider")
    public void queueFuntionalityTest(int[] random_array, int[] correct_array) {
        PriorityQueue<Integer> test = new PriorityQueue<Integer>();
        int index = 0;
        Integer s;
        int[] result = new int[random_array.length];
        for(int number : random_array) {
            test.add(number);
        }

        for(int i=0 ; i < random_array.length ; i++) {
            result[i] = test.poll();
        }

        assertArrayEquals(correct_array, result);
    }

    @Test
    public void exceptionTest_capacityLessThanOne() {
        Exception exception = assertThrows(IllegalArgumentException.class, ()->{
            PriorityQueue<Integer> test = new PriorityQueue<Integer>(-1);
        });
    }

    @Test
    public void exceptionTest_addWithNull() {
        Exception exception = assertThrows(NullPointerException.class, ()->{
            PriorityQueue<Integer> test = new PriorityQueue<Integer>();
            test.add(null);
        });
    }

    @Test
    public void exceptionTest_cannotCompare() {
        Exception exception = assertThrows(ClassCastException.class, ()->{
            PriorityQueue<dummyClass> test = new PriorityQueue<dummyClass>();
            dummyClass c = new dummyClass();
            test.add(c);
        });
    }
}
