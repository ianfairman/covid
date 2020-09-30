package com.scratch.covidfx.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class RollingAverageTest {
    
    @Test
    public void initialAverageIsZero() {
        RollingAverage average = new RollingAverage(10);
        assertEquals(0, average.average());
    }
    
    @Test
    public void averageOfOneNumberIsNumber() {
        RollingAverage average = new RollingAverage(1);
        average.register(5);
        assertEquals(5, average.average());
    }
    
    @Test
    public void averageOfTwoNumbersWithWindowSizeOfOneIsLastNumber() {
        RollingAverage average = new RollingAverage(1);
        average.register(6);
        average.register(47);
        assertEquals(47, average.average());
    }
    
    @Test
    public void averageOfTwoNumbersWithWindowSizeOfTwoIsCorrect() {
        RollingAverage average = new RollingAverage(2);
        average.register(10);
        average.register(30);
        assertEquals(20, average.average());
    }
    
    @Test
    public void resetValueIsAverageWithWindowSizeOfOne() {
        RollingAverage average = new RollingAverage(1);
        average.register(5);
        average.reset(11);
        assertEquals(11, average.average());
    }
    
    @Test
    public void averageOfOneNumberWithWindowSizeTwoIsHalfValue() {
        RollingAverage average = new RollingAverage(2);
        average.register(12);
        assertEquals(6, average.average());
    }
}
