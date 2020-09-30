package com.scratch.covidfx.util;

public class RollingAverage {
    
    private final int windowSize;

    private int[] priorValues;
    private int index = 0;
    private int total = 0;
    
    public RollingAverage(int windowSize) {
        if (windowSize <= 0) {
            throw new IllegalArgumentException("window size must be positive");
        }
        this.windowSize = windowSize;
        priorValues = new int[windowSize];
    }
    
    public int average() {
        return total / windowSize;
    }
    
    public void register(int newValue) {
        total = total + newValue - priorValues[index];
        priorValues[index] = newValue;
        incrementIndex();
    }
    
    public void reset(int newTotal) {
        total = newTotal;
        priorValues = new int[windowSize];
    }

    private void incrementIndex() {
        index = index == windowSize - 1 ? 0 : index + 1;
    }

}
